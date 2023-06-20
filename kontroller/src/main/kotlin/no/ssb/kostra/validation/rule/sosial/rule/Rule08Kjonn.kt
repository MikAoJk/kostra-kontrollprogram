package no.ssb.kostra.validation.rule.sosial.rule

import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringColumnNames.KJONN_COL_NAME
import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringFieldDefinitions.fieldDefinitions
import no.ssb.kostra.area.sosial.kvalifisering.codeIsMissing
import no.ssb.kostra.area.sosial.kvalifisering.codeListToString
import no.ssb.kostra.area.sosial.kvalifisering.findByColumnName
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.program.KotlinArguments
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.AbstractRule
import no.ssb.kostra.validation.rule.sosial.SosialRuleId

class Rule08Kjonn : AbstractRule<KostraRecord>(
    SosialRuleId.KJONN_08.title,
    Severity.ERROR
) {
    override fun validate(context: KostraRecord, arguments: KotlinArguments) =
        (fieldDefinitions.findByColumnName(KJONN_COL_NAME) to
                context.getFieldAsTrimmedString(KJONN_COL_NAME))
            .takeIf { (fieldDefinition, value) -> fieldDefinition.codeIsMissing(value) }
            ?.let { (fieldDefinition, value) ->
                createSingleReportEntryList(
                    "Korrigér kjønn. Fant '$value', forventet én av ${fieldDefinition.codeListToString()}." +
                            " Mottakerens kjønn er ikke fylt ut, eller feil kode er benyttet. " +
                            "Feltet er obligatorisk å fylle ut."
                )
            }
}