package no.ssb.kostra.validation.rule.sosial.sosialhjelp.rule

import no.ssb.kostra.area.sosial.sosialhjelp.SosialhjelpColumnNames
import no.ssb.kostra.area.sosial.sosialhjelp.SosialhjelpColumnNames.BIDRAG_COL_NAME
import no.ssb.kostra.area.sosial.sosialhjelp.SosialhjelpColumnNames.LAAN_COL_NAME
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.program.KotlinArguments
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.AbstractRule
import no.ssb.kostra.validation.rule.sosial.sosialhjelp.SosialhjelpRuleId

class Rule031StonadssumMinimum : AbstractRule<List<KostraRecord>>(
    SosialhjelpRuleId.SOSIALHJELP_K031_STONADSSUMMIN.title,
    Severity.ERROR
) {
    override fun validate(context: List<KostraRecord>, arguments: KotlinArguments) = context
        .filterNot {
            (it.fieldAsIntOrDefault(BIDRAG_COL_NAME) + it.fieldAsIntOrDefault(LAAN_COL_NAME)) > min
        }.map {
            val stonad = it.fieldAsIntOrDefault(BIDRAG_COL_NAME) + it.fieldAsIntOrDefault(LAAN_COL_NAME)
            createValidationReportEntry(
                "Det samlede stønadsbeløpet (summen ($stonad) " +
                        "av bidrag (${it[BIDRAG_COL_NAME]}) og lån (${it[LAAN_COL_NAME]})) som mottakeren " +
                        "har fått i løpet av rapporteringsåret er lik/lavere enn Statistisk sentralbyrås " +
                        "kontrollgrense på kr. (${min}),-."
            ).copy(
                caseworker = it[SosialhjelpColumnNames.SAKSBEHANDLER_COL_NAME],
                journalId = it[SosialhjelpColumnNames.PERSON_JOURNALNR_COL_NAME],
                individId = it[SosialhjelpColumnNames.PERSON_FODSELSNR_COL_NAME],
            )
        }.ifEmpty { null }

    companion object {
        internal const val min = 200
    }
}
