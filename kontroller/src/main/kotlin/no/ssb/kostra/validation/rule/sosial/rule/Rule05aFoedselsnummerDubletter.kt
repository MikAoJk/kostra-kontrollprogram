package no.ssb.kostra.validation.rule.sosial.rule

import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringColumnNames.PERSON_FODSELSNR_COL_NAME
import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringColumnNames.PERSON_JOURNALNR_COL_NAME
import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringColumnNames.SAKSBEHANDLER_COL_NAME
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.program.KotlinArguments
import no.ssb.kostra.program.util.SsnValidationUtils.isValidSocialSecurityIdOrDnr
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.AbstractRule
import no.ssb.kostra.validation.rule.sosial.SosialRuleId

class Rule05aFoedselsnummerDubletter : AbstractRule<List<KostraRecord>>(
    SosialRuleId.FODSELSNUMMER_DUBLETTER_05A.title,
    Severity.ERROR
) {
    override fun validate(context: List<KostraRecord>, arguments: KotlinArguments) =
        context.takeIf { it.size > 1 }?.let {
            it.filter { kostraRecord ->
                isValidSocialSecurityIdOrDnr(kostraRecord.getFieldAsTrimmedString(PERSON_FODSELSNR_COL_NAME))
            }.groupBy { kostraRecord -> kostraRecord.getFieldAsString(PERSON_FODSELSNR_COL_NAME) }
                .filter { (_, group) -> group.size > 1 }
                .flatMap { (foedselsnummer, group) ->
                    group.map { kostraRecord ->
                        val journalId = kostraRecord.getFieldAsString(PERSON_JOURNALNR_COL_NAME)

                        val otherJournalIds = group
                            .map { innerRecord -> innerRecord.getFieldAsString(PERSON_JOURNALNR_COL_NAME) }
                            .filter { innerJournalId -> innerJournalId != journalId }
                            .joinToString(", ")

                        createValidationReportEntry(
                            "Fødselsnummeret i journalnummer $journalId fins også i journalene $otherJournalIds.",
                            lineNumbers = listOf(kostraRecord.lineNumber)
                        ).copy(
                            caseworker = kostraRecord.getFieldAsString(SAKSBEHANDLER_COL_NAME),
                            journalId = journalId,
                            individId = foedselsnummer
                        )
                    }
                }.ifEmpty { null }
        }
}