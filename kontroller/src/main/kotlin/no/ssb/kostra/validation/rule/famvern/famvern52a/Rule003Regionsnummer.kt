package no.ssb.kostra.validation.rule.famvern.famvern52a

import no.ssb.kostra.area.famvern.famvern52a.Familievern52aMain
import no.ssb.kostra.area.famvern.famvern52a.Familievern52aColumnNames as Columns
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.program.KotlinArguments
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.AbstractRule

class Rule003Regionsnummer(
    private val mappingList: List<Familievern52aMain.KontorFylkeRegionMapping>
) : AbstractRule<List<KostraRecord>>(
    Familievern52aRuleId.FAMILIEVERN52A_RULE003.title,
    Severity.WARNING
) {
    override fun validate(context: List<KostraRecord>, arguments: KotlinArguments) = context.filterNot {
        mappingList.any { mapping -> it[Columns.REGION_NR_A_COL_NAME] == mapping.region }
    }.map {
        val regionList = mappingList.map { item -> item.region }.distinct().sorted()
        createValidationReportEntry(
            messageText = "Regionsnummeret som er oppgitt i recorden fins ikke i listen med gyldige regionsnumre. "
                    + "Fant '${it[Columns.REGION_NR_A_COL_NAME]}', forventet én av : $regionList.",
            lineNumbers = listOf(it.lineNumber)
        ).copy(
            caseworker = it[Columns.KONTOR_NR_A_COL_NAME],
            journalId = it[Columns.JOURNAL_NR_A_COL_NAME]
        )
    }.ifEmpty { null }
}