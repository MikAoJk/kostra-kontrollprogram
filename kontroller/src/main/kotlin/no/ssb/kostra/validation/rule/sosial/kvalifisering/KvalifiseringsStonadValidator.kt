package no.ssb.kostra.validation.rule.sosial.kvalifisering

import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringColumnNames.CASE_WORKER_COL_NAME
import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringColumnNames.INDIVID_ID_COL_NAME
import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringColumnNames.JOURNAL_ID_COL_NAME
import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringFieldDefinitions
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.program.KotlinArguments
import no.ssb.kostra.program.toKostraRecord
import no.ssb.kostra.validation.report.ValidationReportEntry
import no.ssb.kostra.validation.rule.AbstractRule

object KvalifiseringsStonadValidator {

    @JvmStatic
    fun validateKvalifisering(arguments: KotlinArguments): List<ValidationReportEntry>? =
        arguments
            .getInputContentAsStringList()
            .asSequence()
            .withIndex()
            .map { (index, recordString) ->
                recordString.toKostraRecord(
                    index = index + 1,
                    fieldDefinitions = KvalifiseringFieldDefinitions.fieldDefinitions
                )
            }.map { record ->
                validationRules
                    .mapNotNull { it.validate(record, arguments) }
                    .flatten()
                    .map { reportEntry ->
                        reportEntry.copy(
                            caseworker = record.getFieldAsString(CASE_WORKER_COL_NAME),
                            journalId = record.getFieldAsString(JOURNAL_ID_COL_NAME),
                            individId = record.getFieldAsString(INDIVID_ID_COL_NAME)
                        )
                    }
            }.filter { it.any() }.flatten().toList().ifEmpty { null }
}

private val validationRules = listOf<AbstractRule<KostraRecord>>(

)

/*
private val fatalRules = listOf(
    Rule001RecordLength(KvalifiseringFieldDefinitions.getFieldLength())
)
*/
