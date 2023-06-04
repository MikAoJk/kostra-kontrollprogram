package no.ssb.kostra.validation.rule.barnevern.individrule

import no.ssb.kostra.barn.xsd.KostraIndividType
import no.ssb.kostra.program.KotlinArguments
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.AbstractRule

class Melding02a : AbstractRule<KostraIndividType>(
    ruleName = IndividRuleId.MELDING_02A.title,
    severity = Severity.ERROR
) {
    override fun validate(context: KostraIndividType, arguments: KotlinArguments) = context.melding
        .filter {
            it.sluttDato != null
                    && it.startDato.isAfter(it.sluttDato)
        }.map { melding ->
            createValidationReportEntry(
                journalId = context.journalnummer,
                contextId = melding.id,
                messageText = "Melding (${melding.id}). Meldingens startdato (${melding.startDato}) er etter " +
                        "meldingens sluttdato (${melding.sluttDato})"
            )
        }.ifEmpty { null }
}
