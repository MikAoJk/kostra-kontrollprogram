package no.ssb.kostra.validation.rule.barnevern.individrule

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.testutil.RandomUtils.generateRandomSSN
import no.ssb.kostra.validation.rule.RuleTestData.argumentsInTest
import no.ssb.kostra.validation.rule.barnevern.individrule.IndividRuleTestData.dateInTest
import no.ssb.kostra.validation.rule.barnevern.individrule.IndividRuleTestData.individInTest
import no.ssb.kostra.validation.rule.barnevern.individrule.IndividRuleTestData.kategoriTypeInTest
import no.ssb.kostra.validation.rule.barnevern.individrule.IndividRuleTestData.tiltakTypeInTest

class Tiltak05Test : BehaviorSpec({
    val sut = Tiltak05()

    Given("valid context") {
        forAll(
            row("individ without fodselsnummer", individInTest.copy(fodselsnummer = null)),
            row(
                "individ with invalid fodselsnummer",
                individInTest.copy(fodselsnummer = "12345612345")
            ),
            row(
                "individ with fodselsnummer, age below 7",
                individInTest.copy(fodselsnummer = generateRandomSSN(dateInTest, dateInTest.plusYears(1)))
            ),
            row(
                "individ with fodselsnummer, age above 7, no tiltak",
                individInTest.copy(
                    fodselsnummer = generateRandomSSN(
                        dateInTest.minusYears(9),
                        dateInTest.minusYears(8)
                    )
                )
            ),
            row(
                "individ with fodselsnummer, age above 7, tiltak with kategori#kode different from 4.1",
                individInTest.copy(
                    fodselsnummer = generateRandomSSN(
                        dateInTest.minusYears(9),
                        dateInTest.minusYears(8)
                    ),
                    tiltak = mutableListOf(tiltakTypeInTest)
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
                "individ with fodselsnummer, age above 7, tiltak with kategori#kode different from 4.1",
                individInTest.copy(
                    fodselsnummer = generateRandomSSN(
                        dateInTest.minusYears(9),
                        dateInTest.minusYears(8)
                    ),
                    tiltak = mutableListOf(tiltakTypeInTest.copy(
                        kategori = kategoriTypeInTest.copy(kode = "4.1")
                    ))
                )
            )
        ) { description, currentContext ->

            When(description) {
                val reportEntryList = sut.validate(currentContext, argumentsInTest)

                Then("expect null") {
                    reportEntryList.shouldNotBeNull()
                    reportEntryList.size shouldBe 1

                    assertSoftly(reportEntryList.first()) {
                        it.severity shouldBe Severity.WARNING

                        with(currentContext.tiltak.first()) {
                            it.contextId shouldBe id
                            it.messageText shouldStartWith  "Tiltak ($id). Barnet er over 7 år og i barnehage. " +
                                    "Barnets alder er"
                        }
                    }
                }
            }
        }
    }
})
