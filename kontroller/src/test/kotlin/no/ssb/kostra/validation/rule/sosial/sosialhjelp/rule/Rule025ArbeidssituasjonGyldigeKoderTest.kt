package no.ssb.kostra.validation.rule.sosial.sosialhjelp.rule

import io.kotest.core.spec.style.BehaviorSpec
import no.ssb.kostra.area.sosial.sosialhjelp.SosialhjelpColumnNames.ARBSIT_COL_NAME
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.ForAllRowItem
import no.ssb.kostra.validation.rule.KostraTestFactory
import no.ssb.kostra.validation.rule.sosial.sosialhjelp.SosialhjelpTestUtils

class Rule025ArbeidssituasjonGyldigeKoderTest : BehaviorSpec({
    include(
        KostraTestFactory.validationRuleTest(
            sut = Rule025ArbeidssituasjonGyldigeKoder(),
            forAllRows = listOf(
                ForAllRowItem(
                    "arbsitCode = 01",
                    kostraRecordInTest("01"),
                ),
                ForAllRowItem(
                    "arbsitCode = XX",
                    kostraRecordInTest("XX"),
                    expectedErrorMessage = "Mottakerens arbeidssituasjon ved siste kontakt med sosial-/NAV-kontoret " +
                            "er ikke fylt ut, eller feil kode er benyttet. Utfylt verdi er '(XX=Ukjent)'. " +
                            "Feltet er obligatorisk å fylle ut.",
                ),
            ),
            expectedSeverity = Severity.ERROR
        )
    )
}) {
    companion object {
        private fun kostraRecordInTest(
            arbsitCode: String
        ) = SosialhjelpTestUtils.sosialKostraRecordInTest(
            mapOf(
                ARBSIT_COL_NAME to arbsitCode
            )
        )
    }
}