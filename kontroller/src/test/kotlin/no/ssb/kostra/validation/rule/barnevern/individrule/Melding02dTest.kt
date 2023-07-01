package no.ssb.kostra.validation.rule.barnevern.individrule

import io.kotest.core.spec.style.BehaviorSpec
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.barnevern.BarnevernTestFactory.barnevernValidationRuleTest
import no.ssb.kostra.validation.rule.barnevern.ForAllRowItem
import no.ssb.kostra.validation.rule.barnevern.SharedValidationConstants.KOSTRA_IS_CLOSED_TRUE
import no.ssb.kostra.validation.rule.barnevern.individrule.IndividRuleTestData.dateInTest
import no.ssb.kostra.validation.rule.barnevern.individrule.IndividRuleTestData.individInTest
import no.ssb.kostra.validation.rule.barnevern.individrule.IndividRuleTestData.meldingTypeInTest

class Melding02dTest : BehaviorSpec({
    include(
        barnevernValidationRuleTest(
            sut = Melding02d(),
            forAllRows = listOf(
                ForAllRowItem(
                    "individ with avslutta3112 = '2'",
                    individInTest,
                    false
                ),
                ForAllRowItem(
                    "avslutta3112 = '1', no melding",
                    individInTest.copy(
                        avslutta3112 = KOSTRA_IS_CLOSED_TRUE
                    ),
                    false
                ),
                ForAllRowItem(
                    "avslutta3112 = '1', melding with sluttDato",
                    individInTest.copy(
                        avslutta3112 = KOSTRA_IS_CLOSED_TRUE,
                        sluttDato = dateInTest.minusYears(1).plusDays(1),
                        melding = mutableListOf(
                            meldingTypeInTest.copy(
                                sluttDato = dateInTest.minusYears(1).plusDays(1)
                            )
                        )
                    ),
                    false
                ),

                ForAllRowItem(
                    "avslutta3112 = '1', melding without sluttDato",
                    individInTest.copy(
                        avslutta3112 = KOSTRA_IS_CLOSED_TRUE,
                        sluttDato = dateInTest.minusYears(1).plusDays(1),
                        melding = mutableListOf(
                            meldingTypeInTest.copy(
                                sluttDato = null
                            )
                        )
                    ),
                    true,
                    expectedErrorMessage = "Melding (${meldingTypeInTest.id}). Individet er avsluttet hos " +
                            "barnevernet og dets meldinger skal dermed være avsluttet. " +
                            "Sluttdato er uoppgitt"
                ),
                ForAllRowItem(
                    "avslutta3112 = '1', melding with valid sluttDato, context without sluttDato",
                    individInTest.copy(
                        avslutta3112 = KOSTRA_IS_CLOSED_TRUE,
                        sluttDato = null,
                        melding = mutableListOf(
                            meldingTypeInTest.copy(
                                sluttDato = dateInTest.minusYears(1).plusDays(1)
                            )
                        )
                    ),
                    true,
                    expectedErrorMessage = "Melding (${meldingTypeInTest.id}). Individet er avsluttet hos " +
                            "barnevernet og dets meldinger skal dermed være avsluttet. " +
                            "Sluttdato er ${dateInTest.minusYears(1).plusDays(1)}"
                ),
                ForAllRowItem(
                    "avslutta3112 = '1', melding with sluttDato after reporting year",
                    individInTest.copy(
                        avslutta3112 = KOSTRA_IS_CLOSED_TRUE,
                        sluttDato = dateInTest.minusYears(1).plusDays(1),
                        melding = mutableListOf(
                            meldingTypeInTest.copy(
                                sluttDato = dateInTest
                            )
                        )
                    ),
                    true,
                    expectedErrorMessage = "Melding (${meldingTypeInTest.id}). Individet er avsluttet hos " +
                            "barnevernet og dets meldinger skal dermed være avsluttet. " +
                            "Sluttdato er $dateInTest"
                )
            ),
            expectedSeverity = Severity.ERROR,
            expectedErrorMessage = "N/A",
            expectedContextId = meldingTypeInTest.id
        )
    )
})
