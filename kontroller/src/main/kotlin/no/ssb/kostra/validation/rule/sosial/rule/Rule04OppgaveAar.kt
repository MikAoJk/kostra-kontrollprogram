package no.ssb.kostra.validation.rule.sosial.rule

import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringColumnNames.VERSION_COL_NAME
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.program.KotlinArguments
import no.ssb.kostra.program.extension.fieldAs
import no.ssb.kostra.program.extension.yearWithCentury
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.AbstractRule
import no.ssb.kostra.validation.rule.sosial.SosialRuleId

class Rule04OppgaveAar : AbstractRule<KostraRecord>(
    SosialRuleId.OPPGAVE_AAR_04.title,
    Severity.ERROR
) {
    override fun validate(context: KostraRecord, arguments: KotlinArguments) =
        context.fieldAs<Int>(VERSION_COL_NAME).yearWithCentury()
            .takeIf { it != arguments.aargang.toInt() }?.let {
                createSingleReportEntryList(
                    "Korrigér årgang. Fant ${context[VERSION_COL_NAME]}, forventet " +
                            "${arguments.aargang.toInt() - 2_000}."
                )
            }
}