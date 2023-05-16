package no.ssb.kostra.validation.rule.regnskap.kostra

import no.ssb.kostra.area.regnskap.RegnskapConstants.FIELD_ART
import no.ssb.kostra.area.regnskap.RegnskapConstants.FIELD_FUNKSJON
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.report.ValidationReportEntry
import no.ssb.kostra.validation.rule.AbstractRecordRule
import no.ssb.kostra.validation.rule.regnskap.isBevilgningRegnskap

class Rule065KombinasjonBevilgningFunksjonArt : AbstractRecordRule(
    "Kontroll 065 : Ugyldig kombinasjon i bevilgningsregnskapet, funksjon og art",
    Severity.ERROR
) {
    override fun validate(context: List<KostraRecord>): List<ValidationReportEntry>? = context
        .filter { kostraRecord ->
            kostraRecord.isBevilgningRegnskap()
                    && (
                    (
                            kostraRecord.getFieldAsString(FIELD_FUNKSJON) == "899 "
                                    && kostraRecord.getFieldAsString(FIELD_ART) !in listOf("589", "980", "989")
                            ) || (
                            kostraRecord.getFieldAsString(FIELD_ART) in listOf("589", "980", "989")
                                    && kostraRecord.getFieldAsString(FIELD_FUNKSJON) != "899 "
                            )
                    )
        }
        .map { kostraRecord ->
            createValidationReportEntry(
                messageText = "Artene 589, 980 og 989 er kun tillat brukt i kombinasjon med funksjon 899. Og motsatt, funksjon 899 er kun tillat brukt i kombinasjon med artene 589, 980 og 989.",
                lineNumbers = listOf(kostraRecord.index)
            )
        }
        .ifEmpty { null }
}