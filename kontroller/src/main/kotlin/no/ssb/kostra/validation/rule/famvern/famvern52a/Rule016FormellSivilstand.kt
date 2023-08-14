package no.ssb.kostra.validation.rule.famvern.famvern52a

import no.ssb.kostra.area.famvern.famvern52a.Familievern52aColumnNames.FORMELL_SIVILS_A_COL_NAME
import no.ssb.kostra.area.famvern.famvern52a.Familievern52aColumnNames.JOURNAL_NR_A_COL_NAME
import no.ssb.kostra.area.famvern.famvern52a.Familievern52aColumnNames.KONTOR_NR_A_COL_NAME
import no.ssb.kostra.area.famvern.famvern52a.Familievern52aColumnNames.PRIMK_SIVILS_A_COL_NAME
import no.ssb.kostra.area.famvern.famvern52a.Familievern52aFieldDefinitions.fieldDefinitions
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.program.KotlinArguments
import no.ssb.kostra.program.extension.byColumnName
import no.ssb.kostra.program.extension.codeExists
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.AbstractRule

class Rule016FormellSivilstand : AbstractRule<List<KostraRecord>>(
    Familievern52aRuleId.FAMILIEVERN52A_RULE016.title,
    Severity.WARNING
) {
    override fun validate(context: List<KostraRecord>, arguments: KotlinArguments) = context.filter {
        it[PRIMK_SIVILS_A_COL_NAME] in listOf("3", "4")
    }.filterNot {
        fieldDefinitions.byColumnName(FORMELL_SIVILS_A_COL_NAME).codeExists(it[FORMELL_SIVILS_A_COL_NAME])
    }.map {
        val codeList =
            fieldDefinitions.byColumnName(FORMELL_SIVILS_A_COL_NAME).codeList
        createValidationReportEntry(
            messageText = "Det er oppgitt at primærklientens samlivsstatus er Samboer eller at primærklient ikke " +
                    "lever i samliv, men det er ikke fylt ut hva som er primærklientens korrekt sivilstand. " +
                    "Fant '${it[FORMELL_SIVILS_A_COL_NAME]}', forventet én av: $codeList.",
            lineNumbers = listOf(it.lineNumber)
        ).copy(
            caseworker = it[KONTOR_NR_A_COL_NAME],
            journalId = it[JOURNAL_NR_A_COL_NAME]
        )
    }.ifEmpty { null }
}