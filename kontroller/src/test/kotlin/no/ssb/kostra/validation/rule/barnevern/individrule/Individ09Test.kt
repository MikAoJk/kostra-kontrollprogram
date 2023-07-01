package no.ssb.kostra.validation.rule.barnevern.individrule

import io.kotest.core.spec.style.BehaviorSpec
import no.ssb.kostra.SharedConstants.OSLO_MUNICIPALITY_ID
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.RuleTestData.argumentsInTest
import no.ssb.kostra.validation.rule.barnevern.BarnevernTestFactory.barnevernValidationRuleTest
import no.ssb.kostra.validation.rule.barnevern.ForAllRowItem
import no.ssb.kostra.validation.rule.barnevern.individrule.IndividRuleTestData.individInTest

class Individ09Test : BehaviorSpec({
    include(
        barnevernValidationRuleTest(
            sut = Individ09(),
            forAllRows = listOf(
                ForAllRowItem(
                    description = "individ without bydelsnummer",
                    context = individInTest.copy(bydelsnummer = null),
                    expectError = false,
                    arguments = argumentsInTest.copy(region = "123400")
                ),
                ForAllRowItem(
                    description = "individ with bydelsnummer, Oslo",
                    context = individInTest,
                    expectError = false,
                    arguments = argumentsInTest.copy(region = "${OSLO_MUNICIPALITY_ID}14")
                ),
                ForAllRowItem(
                    description = "individ without bydelsnummer, Oslo",
                    context = individInTest.copy(bydelsnummer = null),
                    expectError = true,
                    arguments = argumentsInTest.copy(region = "${OSLO_MUNICIPALITY_ID}14")
                ),
            ),
            expectedSeverity = Severity.ERROR,
            expectedErrorMessage = "Filen mangler bydelsnummer.",
            expectedContextId = individInTest.id
        )
    )
})
