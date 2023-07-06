package no.ssb.kostra.validation.rule.regnskap.kostra

import io.kotest.core.spec.style.BehaviorSpec
import no.ssb.kostra.area.regnskap.RegnskapConstants.FIELD_ART
import no.ssb.kostra.area.regnskap.RegnskapConstants.FIELD_BELOP
import no.ssb.kostra.area.regnskap.RegnskapConstants.FIELD_FUNKSJON
import no.ssb.kostra.area.regnskap.RegnskapConstants.FIELD_SKJEMA
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.ForAllRowItem
import no.ssb.kostra.validation.rule.KostraTestFactory.validationRuleTest
import no.ssb.kostra.validation.rule.regnskap.RegnskapTestUtils.asList
import no.ssb.kostra.validation.rule.regnskap.RegnskapTestUtils.toKostraRecord

class Rule065KombinasjonBevilgningFunksjonArtTest : BehaviorSpec({
    include(
        validationRuleTest(
            sut = Rule065KombinasjonBevilgningFunksjonArt(),
            forAllRows = listOf(
                ForAllRowItem(
                    "isBevilgningRegnskap = true",
                    kostraRecordsInTest("0A", "899 ", "010", 1),
                    expectedErrorMessage = "Artene 589, 980 og 989 er kun tillat brukt i kombinasjon med funksjon " +
                            "899. Og motsatt, funksjon 899 er kun tillat brukt i kombinasjon med artene 589, 980 og 989."
                ),
                ForAllRowItem(
                    "isBevilgningRegnskap = false",
                    kostraRecordsInTest("0X", "899 ", "010", 1)
                ),
                ForAllRowItem(
                    "belop mismath",
                    kostraRecordsInTest("0A", "899 ", "010", 0)
                ),
                ForAllRowItem(
                    "funksjon/art mismath #1",
                    kostraRecordsInTest("0A", "899 ", "589", 1)
                ),
                ForAllRowItem(
                    "funksjon/art mismath #2",
                    kostraRecordsInTest("0A", "898 ", "588", 1)
                ),
            ),
            expectedSeverity = Severity.ERROR,
            useArguments = false
        )
    )
}) {
    companion object {
        private fun kostraRecordsInTest(
            skjema: String,
            funksjon: String,
            art: String,
            belop: Int
        ) = mapOf(
            FIELD_SKJEMA to skjema,
            FIELD_FUNKSJON to funksjon,
            FIELD_ART to art,
            FIELD_BELOP to "$belop"
        ).toKostraRecord().asList()
    }
}