package no.ssb.kostra.validation.rule.regnskap.kostra

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import no.ssb.kostra.area.regnskap.RegnskapConstants.FIELD_BELOP
import no.ssb.kostra.area.regnskap.RegnskapConstants.FIELD_FUNKSJON
import no.ssb.kostra.area.regnskap.RegnskapConstants.FIELD_KONTOKLASSE
import no.ssb.kostra.area.regnskap.RegnskapConstants.FIELD_SEKTOR
import no.ssb.kostra.area.regnskap.RegnskapConstants.FIELD_SKJEMA
import no.ssb.kostra.area.regnskap.RegnskapFieldDefinitions.fieldDefinitions
import no.ssb.kostra.program.extension.asList
import no.ssb.kostra.program.extension.toKostraRecord
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.rule.TestUtils.verifyValidationResult

class Rule120SummeringBalansePassivaTest : BehaviorSpec({
    Given("context") {
        val sut = Rule120SummeringBalansePassiva()

        forAll(
            row("0B", "2", "31  ", "010", "1", false),
            row("0B", "2", "31  ", "590", "1", false),
            row("0B", "2", "31  ", "590", "0", true),
            row("0D", "2", "31  ", "010", "1", false),
            row("0D", "2", "31  ", "590", "1", false),
            row("0D", "2", "31  ", "590", "0", true),
            row("0J", "5", "31  ", "010", "1", false),
            row("0J", "5", "31  ", "590", "1", false),
            row("0J", "5", "31  ", "590", "0", true),
            row("0L", "5", "31  ", "010", "1", false),
            row("0L", "5", "31  ", "590", "1", false),
            row("0L", "5", "31  ", "590", "0", true),
            row("0N", "5", "31  ", "010", "1", false),
            row("0N", "5", "31  ", "590", "1", false),
            row("0N", "5", "31  ", "590", "0", true),
            row("0Q", "5", "31  ", "010", "1", false),
            row("0Q", "5", "31  ", "590", "1", false),
            row("0Q", "5", "31  ", "590", "0", true)
        ) { skjema, kontoklasse, kapittel, sektor, belop, expectError ->
            val kostraRecordList = mapOf(
                FIELD_SKJEMA to skjema,
                FIELD_KONTOKLASSE to kontoklasse,
                FIELD_FUNKSJON to kapittel,
                FIELD_SEKTOR to sektor,
                FIELD_BELOP to belop
            ).toKostraRecord(1, fieldDefinitions).asList()

            When("Activa is zero for $skjema, $kontoklasse, $kapittel, $sektor, $belop") {
                verifyValidationResult(
                    validationReportEntries = sut.validate(kostraRecordList),
                    expectError = expectError,
                    expectedSeverity = Severity.ERROR,
                    "Korrigér slik at fila inneholder registrering av passiva/gjeld og egenkapital " +
                            "($belop), sum sektor 000-990 for kapittel 31-5990 i balanse."
                )
            }
        }
    }
})