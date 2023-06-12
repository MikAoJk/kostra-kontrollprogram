package no.ssb.kostra.validation.rule.sosial.rule

import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringColumnNames.AGE_COL_NAME
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.program.KotlinArguments
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.report.ValidationReportEntry
import no.ssb.kostra.validation.rule.AbstractRule
import no.ssb.kostra.validation.rule.sosial.kvalifisering.KvalifiseringRuleId

class AlderUnder18Aar06 : AbstractRule<KostraRecord>(
    KvalifiseringRuleId.ALDER_UNDER_18_AAR_06.title,
    Severity.WARNING
) {
    override fun validate(context: KostraRecord, arguments: KotlinArguments): List<ValidationReportEntry>? =
        context.getFieldAsInteger(AGE_COL_NAME)
            ?.takeIf { ageInYears -> ageInYears < 18 }
            ?.let {
                createSingleReportEntryList(
                    messageText = "Deltakeren ($it år) er under 18 år."
                )
            }
}