package no.ssb.kostra.validation.rule.sosial.rule

import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringColumnNames.BYDELSNR_COL_NAME
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.program.KotlinArguments
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.report.ValidationReportEntry
import no.ssb.kostra.validation.rule.AbstractRule
import no.ssb.kostra.validation.rule.sosial.SosialRuleId
import no.ssb.kostra.area.sosial.extension.districtIdFromRegion

class Rule03Bydelsnummer : AbstractRule<KostraRecord>(
    SosialRuleId.BYDELSNUMMER_03.title,
    Severity.ERROR
) {
    override fun validate(context: KostraRecord, arguments: KotlinArguments): List<ValidationReportEntry>? {

        val districtId = context.getFieldAsTrimmedString(BYDELSNR_COL_NAME)
        val districtIdFromRegion = arguments.region.districtIdFromRegion()

        return if (districtId != districtIdFromRegion) {
            createSingleReportEntryList(
                "Korrigér bydel. Fant $districtId, forventet $districtIdFromRegion"
            )
        } else null
    }
}