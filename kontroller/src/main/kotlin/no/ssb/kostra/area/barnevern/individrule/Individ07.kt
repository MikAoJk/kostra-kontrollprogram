package no.ssb.kostra.area.barnevern.individrule

import no.ssb.kostra.area.barnevern.SharedValidationConstants.AGE_TWENTY_FOUR
import no.ssb.kostra.area.barnevern.extension.ageInYears
import no.ssb.kostra.barn.xsd.KostraIndividType
import no.ssb.kostra.program.Arguments
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.AbstractRule

class Individ07 : AbstractRule<KostraIndividType>(
    ruleName = IndividRuleId.INDIVID_07.title,
    severity = Severity.ERROR
) {
    override fun validate(context: KostraIndividType, arguments: Arguments) =
        context.fodselsnummer
            ?.ageInYears(arguments.aargang.toInt())
            ?.takeIf { it > AGE_TWENTY_FOUR }
            ?.let { ageInYears ->
                createSingleReportEntryList(
                    journalId = context.journalnummer,
                    contextId = context.id,
                    messageText = "Individet er $ageInYears år og skal avsluttes som klient"
                )
            }
}
