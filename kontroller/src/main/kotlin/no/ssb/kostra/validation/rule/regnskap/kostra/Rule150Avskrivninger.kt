package no.ssb.kostra.validation.rule.regnskap.kostra

import no.ssb.kostra.area.regnskap.RegnskapConstants
import no.ssb.kostra.area.regnskap.RegnskapConstants.ACCOUNTING_TYPE_REGIONALE
import no.ssb.kostra.area.regnskap.RegnskapConstants.getRegnskapTypeBySkjema
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.AbstractRule
import no.ssb.kostra.validation.rule.regnskap.kostra.extensions.isBevilgningDriftRegnskap
import no.ssb.kostra.validation.rule.regnskap.kostra.extensions.isOsloBydel

class Rule150Avskrivninger : AbstractRule<List<KostraRecord>>(
    "Kontroll 150 : Avskrivninger, avskrivninger",
    Severity.ERROR
) {
    override fun validate(context: List<KostraRecord>) = context
        .filter { !it.isOsloBydel() && it.isBevilgningDriftRegnskap() }
        .filter {
            it.fieldAsIntOrDefault(RegnskapConstants.FIELD_FUNKSJON) in 100..799
                    && it[RegnskapConstants.FIELD_ART] == "590"
        }.takeIf { it.any() }
        ?.let { avskrivningPosteringer ->
            Pair(
                avskrivningPosteringer.first()[RegnskapConstants.FIELD_SKJEMA],
                avskrivningPosteringer.sumOf { it.fieldAsIntOrDefault(RegnskapConstants.FIELD_BELOP) }
            ).takeIf { (_, avskrivninger) -> avskrivninger == 0 }?.let { (skjema, avskrivninger) ->
                createSingleReportEntryList(
                    messageText = "Korrigér i fila slik at den inneholder avskrivninger " +
                            "($avskrivninger), føres på tjenestefunksjon og art 590.",
                    severity = if (ACCOUNTING_TYPE_REGIONALE in getRegnskapTypeBySkjema(skjema)) Severity.ERROR
                    else Severity.INFO
                )
            }
        }
}