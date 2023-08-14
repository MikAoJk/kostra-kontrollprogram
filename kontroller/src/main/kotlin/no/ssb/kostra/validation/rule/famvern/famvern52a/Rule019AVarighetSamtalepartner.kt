package no.ssb.kostra.validation.rule.famvern.famvern52a

import no.ssb.kostra.area.famvern.famvern52a.Familievern52aColumnNames.JOURNAL_NR_A_COL_NAME
import no.ssb.kostra.area.famvern.famvern52a.Familievern52aColumnNames.KONTOR_NR_A_COL_NAME
import no.ssb.kostra.area.famvern.famvern52a.Familievern52aColumnNames.PART_LENGDE_A_COL_NAME
import no.ssb.kostra.area.famvern.famvern52a.Familievern52aColumnNames.PRIMK_VSRELASJ_A_COL_NAME
import no.ssb.kostra.area.famvern.famvern52a.Familievern52aFieldDefinitions.fieldDefinitions
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.program.KotlinArguments
import no.ssb.kostra.program.extension.byColumnName
import no.ssb.kostra.program.extension.codeExists
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.AbstractRule

class Rule019AVarighetSamtalepartner : AbstractRule<List<KostraRecord>>(
    Familievern52aRuleId.FAMILIEVERN52A_RULE019A.title,
    Severity.WARNING
) {
    override fun validate(context: List<KostraRecord>, arguments: KotlinArguments) = context.filter {
        it[PRIMK_VSRELASJ_A_COL_NAME] == "1"
    }.filterNot {
        fieldDefinitions.byColumnName(PART_LENGDE_A_COL_NAME).codeExists(it[PART_LENGDE_A_COL_NAME])
    }.map {
        val codeList =
            fieldDefinitions.byColumnName(PART_LENGDE_A_COL_NAME).codeList
        createValidationReportEntry(
            messageText = "Det er oppgitt at primærklientens relasjon til viktigste deltager er partner, men det er " +
                    "ikke oppgitt hvor lenge partene har vært gift, samboere eller registrerte partnere. " +
                    "Fant '${it[PART_LENGDE_A_COL_NAME]}', forventet én av: $codeList.",
            lineNumbers = listOf(it.lineNumber)
        ).copy(
            caseworker = it[KONTOR_NR_A_COL_NAME],
            journalId = it[JOURNAL_NR_A_COL_NAME]
        )
    }.ifEmpty { null }
}