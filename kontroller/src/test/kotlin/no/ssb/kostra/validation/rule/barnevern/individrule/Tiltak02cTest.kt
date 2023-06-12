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
import no.ssb.kostra.validation.rule.barnevern.individrule.IndividRuleTestData.kostraTiltakTypeInTest

class Tiltak02cTest : BehaviorSpec({
    val sut = Tiltak02c()

    Given("valid context") {
        forAll(
            row("individ without sluttDato", kostraIndividInTest),
            row(
                "individ with sluttDato, without tiltak",
                kostraIndividInTest.copy(
                    sluttDato = dateInTest.plusDays(1)
                )
            ),
            row(
                "tiltak without sluttDato",
                kostraIndividInTest.copy(
                    sluttDato = dateInTest.plusDays(1),
                    tiltak = mutableListOf(kostraTiltakTypeInTest)
                )
            ),
            row(
                "tiltak with sluttDato equal to individ",
                kostraIndividInTest.copy(
                    sluttDato = dateInTest.plusDays(1),
                    tiltak = mutableListOf(
                        kostraTiltakTypeInTest.copy(
                            sluttDato = dateInTest.plusDays(1)
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
                "tiltak with sluttDato after individ sluttDato",
                kostraIndividInTest.copy(
                    sluttDato = dateInTest.plusDays(1),
                    tiltak = mutableListOf(
                        kostraTiltakTypeInTest.copy(
                            sluttDato = dateInTest.plusDays(2)
                        )
                    )
                )
            )
        ) { description, currentContext ->

            When(description) {
                val reportEntryList = sut.validate(currentContext, argumentsInTest)

                Then("expect null") {
                    reportEntryList.shouldNotBeNull()
                    reportEntryList.size shouldBe 1

                    assertSoftly(reportEntryList.first()) {
                        it.severity shouldBe Severity.ERROR

                        with(currentContext.tiltak.first()) {
                            it.contextId shouldBe id
                            it.messageText shouldBe "Tiltak ($id). Sluttdato ($startDato) er " +
                                    "etter individets sluttdato (${currentContext.sluttDato})"
                        }
                    }
                }
            }
        }
    }
})
