package no.ssb.kostra.validation.rule.regnskap.helseforetak

import io.kotest.core.spec.style.BehaviorSpec
import no.ssb.kostra.area.regnskap.RegnskapConstants
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.ForAllRowItem
import no.ssb.kostra.validation.rule.KostraTestFactory
import no.ssb.kostra.validation.rule.regnskap.RegnskapTestUtils

class Rule540EiendelerErLikEgenkaptialPlussGjeldTest : BehaviorSpec({
    include(
        KostraTestFactory.validationRuleTest(
            sut = Rule540EiendelerErLikEgenkaptialPlussGjeld(),
            forAllRows = listOf(
                ForAllRowItem(
                    "feil skjema",
                    listOf(
                        kostraRecordInTest("0X", "100", 0),
                    ),
                ),
                ForAllRowItem(
                    "riktig skjema, men feil sektor",
                    listOf(
                        kostraRecordInTest("0Y", "999", 0),
                    ),
                ),
                ForAllRowItem(
                    "riktig skjema og sektor, men feil beløp",
                    listOf(
                        kostraRecordInTest("0Y", "100", 1000),
                        kostraRecordInTest("0Y", "200", 0),
                        kostraRecordInTest("0Y", "210", 0),
                    ),
                    expectedErrorMessage = "Balansen (1000) skal balansere ved at sum eiendeler (1000)  = sum " +
                            "egenkapital (0) + sum gjeld (0) . Differanser +/- 50' kroner godtas"
                ),
                ForAllRowItem(
                    "riktig skjema, sektor og beløp, differanse = 0",
                    listOf(
                        kostraRecordInTest("0Y", "100", 1000),
                        kostraRecordInTest("0Y", "200", -500),
                        kostraRecordInTest("0Y", "210", -500),
                    ),
                ),
            ),
            expectedSeverity = Severity.WARNING
        )
    )

}) {
    companion object {
        private fun kostraRecordInTest(
            skjema: String,
            sektor: String,
            belop: Int
        ) = RegnskapTestUtils.regnskapRecordInTest(
            mapOf(
                RegnskapConstants.FIELD_SKJEMA to skjema,
                RegnskapConstants.FIELD_SEKTOR to sektor,
                RegnskapConstants.FIELD_BELOP to belop.toString()
            )
        )
    }
}
