package no.ssb.kostra.validation.rule.sosial.rule

import no.ssb.kostra.SharedConstants.OSLO_MUNICIPALITY_ID
import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringColumnNames.BYDELSNR_COL_NAME
import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringColumnNames.KOMMUNE_NR_COL_NAME
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.program.KotlinArguments
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.AbstractRule
import no.ssb.kostra.validation.rule.sosial.SosialRuleId

class Rule03Bydelsnummer : AbstractRule<KostraRecord>(
    SosialRuleId.BYDELSNUMMER_03.title,
    Severity.ERROR
) {
    private val osloDistrictIds = (1..15).map { it.toString().padStart(2, '0') }

    override fun validate(context: KostraRecord, arguments: KotlinArguments) = context.let { kostraRecord ->
        kostraRecord[KOMMUNE_NR_COL_NAME] to kostraRecord[BYDELSNR_COL_NAME]
    }.takeUnless { (municipalityId, districtId) ->
        when (municipalityId) {
            OSLO_MUNICIPALITY_ID -> districtId in osloDistrictIds
            else -> districtId.isBlank()
        }
    }?.let { (municipalityId, districtId) ->
        createSingleReportEntryList(
            when (municipalityId) {
                OSLO_MUNICIPALITY_ID -> "Korrigér bydel. Fant ${districtId}, forventet en av ${osloDistrictIds}."
                else -> "Korrigér bydel. Fant ${districtId}, forventet blank / '  '."
            }
        )
    }
}
