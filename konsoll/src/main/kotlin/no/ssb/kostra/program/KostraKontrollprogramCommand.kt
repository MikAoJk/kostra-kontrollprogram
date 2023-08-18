package no.ssb.kostra.program

import io.micronaut.configuration.picocli.PicocliRunner
import io.micronaut.validation.validator.Validator
import jakarta.inject.Inject
import no.ssb.kostra.extensions.toKostraArguments
import no.ssb.kostra.viewmodel.CompanyIdVm
import no.ssb.kostra.viewmodel.KostraFormVm
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import java.time.Year

@Command(
    name = "kostra-kontrollprogram-konsoll", description = ["..."],
    mixinStandardHelpOptions = true
)
class KostraKontrollprogramCommand : Runnable {

    @Inject
    lateinit var validator: Validator

    @Option(names = ["-v", "--verbose"], description = ["..."])
    private var verbose: Boolean = false

    override fun run() {
        // business logic here
        if (verbose) {
            println("Hi!")
        }

        /** TODO Jon Ole: Putt inn verdier fra argumentene her  */
        val kostraForm = KostraFormVm(
            aar = Year.now().value,
            skjema = "0G",
            region = "667600",
            orgnrForetak = "987654321",
            orgnrVirksomhet = listOf(CompanyIdVm("987654321")),
            filnavn = "0G.dat"
        )

        val validationErrors = validator.validate(kostraForm).iterator().asSequence().plus(
            /** temp-fix for issues with MN:4.x and validating collections */
            kostraForm.orgnrVirksomhet.flatMap { companyId ->
                validator.validate(companyId).iterator().asSequence()
            }
        )

        if (validationErrors.any()) {
            /** TODO Jon Ole: Presenter validationErrors her  */
        } else {

            /** TODO #1 Jon Ole: Putt inn verdier fra argumentene her  */
            /** TODO #2 Jon Ole: Se på om toKostraArguments har nok kode */
            /** TODO #3 Jon Ole: Sjekk om fil er input stream */
            val errorReport = ControlDispatcher.validate(kostraForm.toKostraArguments(PLAIN_TEXT_0G.byteInputStream()))

            /** TODO Jon Ole: Presenter ErrorReport her  */
        }
    }

    companion object {

        /** DUMMY DATA */
        private const val PLAIN_TEXT_0G =
            "0G2020 300500976989732         510  123      263\n0G2020 300500976989732         510           263"


        @JvmStatic
        fun main(args: Array<String>) {
            PicocliRunner.run(KostraKontrollprogramCommand::class.java, *args)
        }
    }
}
