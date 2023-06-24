package no.ssb.kostra.validation.rule.sosial.rule

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import no.ssb.kostra.program.extension.municipalityIdFromRegion
import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringColumnNames.KOMMUNE_NR_COL_NAME
import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringColumnNames.PERSON_FODSELSNR_COL_NAME
import no.ssb.kostra.area.sosial.kvalifisering.KvalifiseringFieldDefinitions.fieldDefinitions
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.RandomUtils.generateRandomSsn
import no.ssb.kostra.validation.rule.RuleTestData.argumentsInTest
import no.ssb.kostra.validation.util.SsnValidationUtils.getAgeFromSocialSecurityId

class Rule07AlderEr68AarEllerOverTest : BehaviorSpec({
    val sut = Rule07AlderEr68AarEllerOver()

    Given("valid context") {
        forAll(
            row(
                "record with valid age",
                kostraRecordInTest(generateRandomSsn(67))
            ),
            row(
                "record with blank fødselsnummer",
                kostraRecordInTest(" ".repeat(11))
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
                "record with invalid age",
                generateRandomSsn(69)
            )
        ) { description, foedselsnummer ->

            When(description) {
                val reportEntryList = sut.validate(kostraRecordInTest(foedselsnummer), argumentsInTest)

                Then("expect non-null result") {
                    reportEntryList.shouldNotBeNull()
                    reportEntryList.size shouldBe 1

                    val actualAge = getAgeFromSocialSecurityId(foedselsnummer, argumentsInTest.aargang)

                    assertSoftly(reportEntryList.first()) {
                        it.severity shouldBe Severity.WARNING
                        it.messageText shouldBe "Deltakeren ($actualAge år) er 68 år eller eldre."
                    }
                }
            }
        }
    }
}) {
    companion object {
        private fun kostraRecordInTest(foedselsnummer: String) = KostraRecord(
            1,
            mapOf(
                KOMMUNE_NR_COL_NAME to argumentsInTest.region.municipalityIdFromRegion(),
                PERSON_FODSELSNR_COL_NAME to foedselsnummer
            ),
            fieldDefinitions.associate { with(it) { name to it } }
        )
    }
}
