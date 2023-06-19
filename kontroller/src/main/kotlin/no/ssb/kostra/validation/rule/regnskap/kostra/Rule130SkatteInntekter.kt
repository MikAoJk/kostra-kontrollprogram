package no.ssb.kostra.validation.rule.regnskap.kostra

import no.ssb.kostra.area.regnskap.RegnskapConstants
import no.ssb.kostra.area.regnskap.RegnskapConstants.FIELD_ART
import no.ssb.kostra.area.regnskap.RegnskapConstants.FIELD_FUNKSJON
import no.ssb.kostra.program.KostraRecord
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.report.ValidationReportEntry
import no.ssb.kostra.validation.rule.AbstractRecordRule
import no.ssb.kostra.validation.rule.regnskap.kostra.extensions.*

class Rule130SkatteInntekter : AbstractRecordRule(
    "Kontroll 130 : Skatteinntekter",
    Severity.ERROR
) {
    override fun validate(context: List<KostraRecord>): List<ValidationReportEntry>? = context
        .filter { !it.isOsloBydel() && !it.isLongyearbyen() && it.isRegional() && it.isBevilgningDriftRegnskap() }
        .takeIf { it.any() }
        ?.filter { it.getFieldAsString(FIELD_FUNKSJON) == "800 " && it.getFieldAsString(FIELD_ART) == "870" }
        ?.sumOf { it.getFieldAsIntegerDefaultEquals0(RegnskapConstants.FIELD_BELOP) }
        ?.takeUnless { skatteInntekter -> skatteInntekter < 0 }
        ?.let { skatteInntekter ->
            createSingleReportEntryList(
                messageText = "Korrigér slik at fila inneholder skatteinntekter ($skatteInntekter)."
            )
        }
}