package no.ssb.kostra.validation.rule.barnevern.xmlhandling

import no.ssb.kostra.barn.xsd.KostraAvgiverType
import no.ssb.kostra.barn.xsd.KostraIndividType
import no.ssb.kostra.program.KotlinArguments
import no.ssb.kostra.validation.report.Severity
import no.ssb.kostra.validation.report.ValidationReportEntry
import no.ssb.kostra.validation.rule.barnevern.AvgiverRuleId
import no.ssb.kostra.validation.rule.barnevern.IndividRuleId
import java.io.InputStream
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamConstants
import javax.xml.stream.XMLStreamReader

class DefaultStreamHandler(
    private val avgiverXmlElementHandler: XmlElementHandler<KostraAvgiverType>,
    private val individXmlElementHandler: XmlElementHandler<KostraIndividType>,
) : BarnevernStreamHandler {

    override fun handleStream(
        fileStream: InputStream,
        arguments: KotlinArguments,
        avgiverCallbackFunc: (KostraAvgiverType) -> Unit,
        individCallbackFunc: (KostraIndividType) -> Unit
    ): List<ValidationReportEntry> = mutableListOf<ValidationReportEntry>().apply {
        XMLInputFactory.newInstance().createXMLStreamReader(fileStream).let { xmlStreamReader ->
            while (xmlStreamReader.hasNext()) {
                xmlStreamReader.next()

                if (xmlStreamReader.eventType != XMLStreamConstants.START_ELEMENT) continue

                when (xmlStreamReader.localName) {
                    AVGIVER_XML_TAG -> addAll(
                        processAvgiverElement(
                            xmlStreamReader = xmlStreamReader,
                            arguments = arguments,
                            avgiverCallbackFunc = avgiverCallbackFunc
                        )
                    )

                    INDIVID_XML_TAG -> addAll(
                        processIndividElement(
                            xmlStreamReader = xmlStreamReader,
                            arguments = arguments,
                            individCallbackFunc = individCallbackFunc
                        )
                    )
                }
            }
        }
    }

    private fun processAvgiverElement(
        xmlStreamReader: XMLStreamReader,
        arguments: KotlinArguments,
        avgiverCallbackFunc: (KostraAvgiverType) -> Unit
    ) = try {
        avgiverXmlElementHandler.handleXmlElement(
            xmlStreamReader = xmlStreamReader,
            arguments = arguments
        ).let { (validationErrors, avgiver) ->
            if (avgiver != null) avgiverCallbackFunc(avgiver)
            validationErrors
        }
    } catch (thrown: Throwable) {
        listOf(avgiverFileError)
    }

    private fun processIndividElement(
        xmlStreamReader: XMLStreamReader,
        arguments: KotlinArguments,
        individCallbackFunc: (KostraIndividType) -> Unit
    ) = try {
        individXmlElementHandler.handleXmlElement(
            xmlStreamReader = xmlStreamReader,
            arguments = arguments
        ).let { (validationErrors, individ)  ->
            if (individ != null) individCallbackFunc(individ)
            validationErrors
        }
    } catch (thrown: Throwable) {
        listOf(individFileError)
    }


    companion object {
        private const val AVGIVER_XML_TAG = "Avgiver"
        private const val INDIVID_XML_TAG = "Individ"

        val avgiverFileError = ValidationReportEntry(
            severity = Severity.ERROR,
            ruleName = AvgiverRuleId.AVGIVER_01.title,
            messageText = "Klarer ikke å validere Avgiver mot filspesifikasjon"
        )

        val individFileError = ValidationReportEntry(
            severity = Severity.ERROR,
            ruleName = IndividRuleId.INDIVID_01.title,
            messageText = "Definisjon av Individ er feil i forhold til filspesifikasjonen"
        )
    }
}