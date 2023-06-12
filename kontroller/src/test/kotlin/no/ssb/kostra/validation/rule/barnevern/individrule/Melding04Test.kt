package no.ssb.kostra.validation.rule.barnevern.individrule

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.RuleTestData.argumentsInTest
import no.ssb.kostra.validation.rule.barnevern.individrule.IndividRuleTestData.dateInTest
import no.ssb.kostra.validation.rule.barnevern.individrule.IndividRuleTestData.kostraIndividInTest
import no.ssb.kostra.validation.rule.barnevern.individrule.IndividRuleTestData.kostraMelderTypeInTest
import no.ssb.kostra.validation.rule.barnevern.individrule.IndividRuleTestData.kostraMeldingTypeInTest

class Melding04Test : BehaviorSpec({
    val sut = Melding04()

    Given("valid context") {
        forAll(
            row("individ without melding", kostraIndividInTest),
            row(
                "melding without sluttDato",
                kostraIndividInTest.copy(
                    melding = mutableListOf(kostraMeldingTypeInTest)
                )
            ),
            row(
                "melding with sluttDato in reporting year",
                kostraIndividInTest.copy(
                    melding = mutableListOf(kostraMeldingTypeInTest.copy(sluttDato = dateInTest.minusYears(1)))
                )
            ),
            row(
                "melding with sluttDato after reporting year, no konklusjon",
                kostraIndividInTest.copy(
                    melding = mutableListOf(kostraMeldingTypeInTest.copy(sluttDato = dateInTest))
                )
            ),
            row(
                "melding with sluttDato after reporting year, with konklusjon '42'",
                kostraIndividInTest.copy(
                    melding = mutableListOf(
                        kostraMeldingTypeInTest.copy(
                            sluttDato = dateInTest,
                            konklusjon = "42"
                        )
                    )
                )
            ),
            row(
                "melding with sluttDato after reporting year, with konklusjon '1'",
                kostraIndividInTest.copy(
                    melding = mutableListOf(
                        kostraMeldingTypeInTest.copy(
                            sluttDato = dateInTest,
                            konklusjon = "1",
                            melder = mutableListOf(kostraMelderTypeInTest)
                        )
                    )
                )
            )
        ) { description, currentContext ->

            When(description) {
                val reportEntryList = sut.validate(currentContext, argumentsInTest)

                Then("expect null") {
                    reportEntryList.shouldBeNull()
                }
            }
        }
    }

    Given("invalid context") {
        forAll(
            row(
                "melding with sluttDato after reporting year, with konklusjon '2'",
                kostraIndividInTest.copy(
                    melding = mutableListOf(
                        kostraMeldingTypeInTest.copy(
                            sluttDato = dateInTest,
                            konklusjon = "2"
                        )
                    )
                )
            ),
        ) { description, currentContext ->

            When(description) {
                val reportEntryList = sut.validate(currentContext, argumentsInTest)

                Then("expect null") {
                    reportEntryList.shouldNotBeNull()
                    reportEntryList.size shouldBe 1

                    assertSoftly(reportEntryList.first()) {
                        it.severity shouldBe Severity.ERROR

                        with(currentContext.melding.first()) {
                            it.contextId shouldBe id
                            it.messageText shouldBe "Melding ($id). Konkludert melding mangler melder(e)."
                        }
                    }
                }
            }
        }
    }
})
