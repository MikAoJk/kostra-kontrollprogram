package no.ssb.kostra.validation.rule.regnskap.kostra

import no.ssb.kostra.area.regnskap.RegnskapConstants
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.AbstractRule
import no.ssb.kostra.validation.rule.regnskap.kostra.extensions.isBevilgningDriftRegnskap
import no.ssb.kostra.validation.rule.regnskap.kostra.extensions.isLongyearbyen
import no.ssb.kostra.validation.rule.regnskap.kostra.extensions.isOsloBydel
import no.ssb.kostra.validation.rule.regnskap.kostra.extensions.isRegional

class Rule135Rammetilskudd : AbstractRule<List<KostraRecord>>(
    "Kontroll 135 : Rammetilskudd",
    Severity.ERROR
) {
    override fun validate(context: List<KostraRecord>) = context
        .filterNot { it.isOsloBydel() || it.isLongyearbyen() }
        .filter { it.isRegional() && it.isBevilgningDriftRegnskap() }
        .takeIf { it.any() }
        ?.filter {
            it.fieldAsTrimmedString(RegnskapConstants.FIELD_FUNKSJON) == "840"
                    && it[RegnskapConstants.FIELD_ART] == "800"
        }
        ?.sumOf { it.fieldAsIntOrDefault(RegnskapConstants.FIELD_BELOP) }
        ?.takeUnless { rammeTilskudd -> rammeTilskudd < 0 }
        ?.let { rammeTilskudd ->
            createSingleReportEntryList(
                messageText = "Korrigér slik at fila inneholder rammetilskudd ($rammeTilskudd)."
            )
        }
}