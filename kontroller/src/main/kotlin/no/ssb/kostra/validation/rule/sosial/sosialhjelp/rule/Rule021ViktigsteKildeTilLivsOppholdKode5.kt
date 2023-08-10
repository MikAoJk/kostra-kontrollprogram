package no.ssb.kostra.validation.rule.sosial.sosialhjelp.rule

import no.ssb.kostra.area.sosial.sosialhjelp.SosialhjelpColumnNames
import no.ssb.kostra.area.sosial.sosialhjelp.SosialhjelpColumnNames.ARBSIT_COL_NAME
import no.ssb.kostra.area.sosial.sosialhjelp.SosialhjelpColumnNames.VKLO_COL_NAME
import no.ssb.kostra.area.sosial.sosialhjelp.SosialhjelpFieldDefinitions.fieldDefinitions
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.program.KotlinArguments
import no.ssb.kostra.program.extension.byColumnName
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.AbstractRule
import no.ssb.kostra.validation.rule.sosial.sosialhjelp.SosialhjelpRuleId

class Rule021ViktigsteKildeTilLivsOppholdKode5 : AbstractRule<List<KostraRecord>>(
    SosialhjelpRuleId.SOSIALHJELP_K021_SOSIALHJELP.title,
    Severity.WARNING
) {
    override fun validate(context: List<KostraRecord>, arguments: KotlinArguments) = context
        .filter {
            it[VKLO_COL_NAME] == "5"
        }.filterNot {
            it[ARBSIT_COL_NAME] in validCodes
        }.map {
            createValidationReportEntry(
                "Mottakerens viktigste kilde til livsopphold ved siste kontakt med sosial-/NAV-kontoret " +
                        "er ${
                            fieldDefinitions.byColumnName(VKLO_COL_NAME).codeList
                                .first { item -> item.code == it[VKLO_COL_NAME] }.value
                        }. Arbeidssituasjonen er '(${it[ARBSIT_COL_NAME]})', " +
                        "forventet én av '(${
                            fieldDefinitions.byColumnName(ARBSIT_COL_NAME).codeList
                                .filter { item -> item.code in validCodes }
                        })'. Feltet er obligatorisk å fylle ut."
            ).copy(
                caseworker = it[SosialhjelpColumnNames.SAKSBEHANDLER_COL_NAME],
                journalId = it[SosialhjelpColumnNames.PERSON_JOURNALNR_COL_NAME],
                individId = it[SosialhjelpColumnNames.PERSON_FODSELSNR_COL_NAME],
            )
        }.ifEmpty { null }

    companion object {
        val validCodes = listOf("02", "04", "05", "06", "07", "08")
    }
}