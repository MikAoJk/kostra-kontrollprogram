package no.ssb.kostra.control.sosial.s11_sosialhjelp;

import no.ssb.kostra.felles.Code;
import no.ssb.kostra.felles.FieldDefinition;

import java.util.List;

import static no.ssb.kostra.felles.Constants.*;

public class FieldDefinitions {
    private FieldDefinitions(){}

    public static List<FieldDefinition> getFieldDefinitions() {
        return List.of(
                new FieldDefinition(1, "KOMMUNE_NR",
                        STRING_TYPE,
                        TEXTBOX_VIEWTYPE,
                        1, 4,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(2, "VERSION",
                        STRING_TYPE,
                        TEXTBOX_VIEWTYPE,
                        5, 6,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(3, "BYDELSNR",
                        STRING_TYPE,
                        TEXTBOX_VIEWTYPE,
                        7, 8,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(4, "DISTRIKTSNR",
                        STRING_TYPE,
                        TEXTBOX_VIEWTYPE,
                        9, 10,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(5, "PERSON_JOURNALNR",
                        STRING_TYPE,
                        TEXTBOX_VIEWTYPE,
                        11, 18,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(6, "PERSON_FODSELSNR",
                        STRING_TYPE,
                        TEXTBOX_VIEWTYPE,
                        19, 29,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(7, "PERSON_DUF",
                        STRING_TYPE,
                        TEXTBOX_VIEWTYPE,
                        30, 41,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(8, "KJONN",
                        STRING_TYPE,
                        DROPDOWNLIST_VIEWTYPE,
                        42, 42,
                        List.of(
                                new Code("1", "Mann"),
                                new Code("2", "Kvinne")),
                        "",
                        false),
                new FieldDefinition(9, "EKTSTAT",
                        STRING_TYPE,
                        DROPDOWNLIST_VIEWTYPE,
                        43, 43,
                        List.of(
                                new Code("1", "Ugift"),
                                new Code("2", "Gift"),
                                new Code("3", "Samboer"),
                                new Code("4", "Skilt/separert"),
                                new Code("5", "Enke/enkemann")),
                        "",
                        false),
                new FieldDefinition(101, "BU18",
                        STRING_TYPE,
                        DROPDOWNLIST_VIEWTYPE,
                        44, 44,
                        List.of(
                                new Code("1", "Ja"),
                                new Code("2", "Nei")),
                        "",
                        false),
                new FieldDefinition(102, "ANTBU18",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        45, 46,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(11, "VKLO",
                        STRING_TYPE,
                        DROPDOWNLIST_VIEWTYPE,
                        47, 47,
                        List.of(new Code("1", "Arbeidsinntekt"),
                                new Code("2", "Kursstønad/lønn i arbeidsmarkedstiltak"),
                                new Code("3", "Trygd/pensjon"),
                                new Code("4", "Stipend/lån"),
                                new Code("5", "Sosialhjelp"),
                                new Code("6", "Introduksjonsstøtte"),
                                new Code("7", "Ektefelle/samboers arbeidsinntekt"),
                                new Code("8", "Kvalifiseringsstønad"),
                                new Code("9", "Annen inntekt")),
                        "",
                        false),
                new FieldDefinition(12, "TRYGDESIT",
                        STRING_TYPE,
                        DROPDOWNLIST_VIEWTYPE,
                        48, 49,
                        List.of(new Code("01", "Sykepenger"),
                                new Code("02", "Dagpenger"),
                                new Code("04", "Uføretrygd"),
                                new Code("05", "Overgangsstønad"),
                                new Code("06", "Etterlattepensjon"),
                                new Code("07", "Alderspensjon"),
                                new Code("09", "Supplerende stønad (kort botid)"),
                                new Code("10", "Annen trygd"),
                                new Code("11", "Arbeidsavklaringspenger"),
                                new Code("12", "Har ingen trygd/pensjon")),
                        "",
                        false),
                new FieldDefinition(13, "ARBSIT",
                        STRING_TYPE,
                        DROPDOWNLIST_VIEWTYPE,
                        50, 51,
                        List.of(new Code("01", "Arbeid, heltid"),
                                new Code("02", "Arbeid, deltid"),
                                new Code("03", "Under utdanning"),
                                new Code("04", "Ikke arbeidssøker"),
                                new Code("05", "Arbeidsmarkedstiltak (statlig)"),
                                new Code("06", "Kommunalt tiltak"),
                                new Code("07", "Registrert arbeidsledig"),
                                new Code("08", "Arbeidsledig, men ikke registrert hos NAV"),
                                new Code("09", "Introduksjonsordning"),
                                new Code("10", "Kvalifiseringsprogram")),
                        "",
                        false),
                new FieldDefinition(141, "STMND_1",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        52, 53,
                        List.of(
                                new Code("01", "Januar")),
                        "",
                        false),
                new FieldDefinition(142, "STMND_2",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        54, 55,
                        List.of(
                                new Code("02", "Februar")),
                        "",
                        false),
                new FieldDefinition(143, "STMND_3",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        56, 57,
                        List.of(
                                new Code("03", "Mars")),
                        "",
                        false),
                new FieldDefinition(144, "STMND_4",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        58, 59,
                        List.of(
                                new Code("04", "April")),
                        "",
                        false),
                new FieldDefinition(145, "STMND_5",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        60, 61,
                        List.of(
                                new Code("05", "Mai")),
                        "",
                        false),
                new FieldDefinition(146, "STMND_6",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        62, 63,
                        List.of(
                                new Code("06", "Juni")),
                        "",
                        false),
                new FieldDefinition(147, "STMND_7",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        64, 65,
                        List.of(
                                new Code("07", "Juli")),
                        "",
                        false),
                new FieldDefinition(148, "STMND_8",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        66, 67,
                        List.of(
                                new Code("08", "August")),
                        "",
                        false),
                new FieldDefinition(149, "STMND_9",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        68, 69,
                        List.of(
                                new Code("09", "September")),
                        "",
                        false),
                new FieldDefinition(1410, "STMND_10",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        70, 71,
                        List.of(
                                new Code("10", "Oktober")),
                        "",
                        false),
                new FieldDefinition(1411, "STMND_11",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        72, 73,
                        List.of(
                                new Code("11", "November")),
                        "",
                        false),
                new FieldDefinition(1412, "STMND_12",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        74, 75,
                        List.of(
                                new Code("12", "Desember")),
                        "",
                        false),
                new FieldDefinition(151, "BIDRAG",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        76, 82,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(152, "LAAN",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        83, 89,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(161, "BIDRAG_JAN",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        90, 96,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(162, "LAAN_JAN",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        97, 103,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(163, "BIDRAG_FEB",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        104, 110,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(164, "LAAN_FEB",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        111, 117,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(165, "BIDRAG_MARS",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        118, 124,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(166, "LAAN_MARS",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        125, 131,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(167, "BIDRAG_APRIL",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        132, 138,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(168, "LAAN_APRIL",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        139, 145,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(169, "BIDRAG_MAI",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        146, 152,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(1610, "LAAN_MAI",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        153, 159,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(1611, "BIDRAG_JUNI",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        160, 166,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(1612, "LAAN_JUNI",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        167, 173,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(1613, "BIDRAG_JULI",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        174, 180,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(1614, "LAAN_JULI",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        181, 187,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(1615, "BIDRAG_AUG",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        188, 194,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(1616, "LAAN_AUG",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        195, 201,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(1617, "BIDRAG_SEPT",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        202, 208,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(1618, "LAAN_SEPT",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        209, 215,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(1619, "BIDRAG_OKT",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        216, 222,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(1620, "LAAN_OKT",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        223, 229,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(1621, "BIDRAG_NOV",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        230, 236,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(1622, "LAAN_NOV",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        237, 243,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(1623, "BIDRAG_DES",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        244, 250,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(1624, "LAAN_DES",
                        INTEGER_TYPE,
                        TEXTBOX_VIEWTYPE,
                        251, 257,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(17, "GITT_OKONOMIRAD",
                        STRING_TYPE,
                        DROPDOWNLIST_VIEWTYPE,
                        258, 258,
                        List.of(
                                new Code("1", "Ja"),
                                new Code("2", "Nei")),
                        "",
                        false),
                new FieldDefinition(18, "FAAT_INDIVIDUELL_PLAN",
                        STRING_TYPE,
                        DROPDOWNLIST_VIEWTYPE,
                        259, 259,
                        List.of(
                                new Code("1", "Ja"),
                                new Code("2", "Nei")),
                        "",
                        false),
                new FieldDefinition(19, "SAKSBEHANDLER",
                        STRING_TYPE,
                        TEXTBOX_VIEWTYPE,
                        260, 269,
                        List.of(),
                        "",
                        false),
                new FieldDefinition(20, "BOSIT",
                        INTEGER_TYPE,
                        DROPDOWNLIST_VIEWTYPE,
                        270, 270,
                        List.of(new Code("1", "Bor i leid privat bolig"),
                                new Code("2", "Bor i leid kommunal bolig"),
                                new Code("3", "Bor i eid bolig"),
                                new Code("4", "Er uten bolig"),
                                new Code("5", "Annet"),
                                new Code("6", "Bor i institusjon")),
                        "",
                        false),
                new FieldDefinition(21, "VILKARSOSLOV",
                        STRING_TYPE,
                        DROPDOWNLIST_VIEWTYPE,
                        271, 271,
                        List.of(
                                new Code("1", "Ja"),
                                new Code("2", "Nei")),
                        "",
                        false),
                new FieldDefinition(22, "VILKARSAMEKT",
                        STRING_TYPE,
                        DROPDOWNLIST_VIEWTYPE,
                        272, 272,
                        List.of(
                                new Code("1", "Ja"),
                                new Code("2", "Nei")),
                        "",
                        false),
                new FieldDefinition(23, "UTBETDATO",
                        DATE_TYPE,
                        TEXTBOX_VIEWTYPE,
                        273, 278,
                        List.of(),
                        DATE6_PATTERN,
                        false),
                new FieldDefinition(24, "UTBETTOMDATO",
                        DATE_TYPE,
                        TEXTBOX_VIEWTYPE,
                        279, 284,
                        List.of(),
                        DATE6_PATTERN,
                        false),
                new FieldDefinition(251, "VILKARARBEID",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        285, 286,
                        List.of(
                                new Code("16", "Delta på arbeidstrening/arbeidspraksis")),
                        "",
                        false),
                new FieldDefinition(252, "VILKARKURS",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        287, 288,
                        List.of(
                                new Code("17", "Delta på arbeidsrettede kurs, opplæring eller jobbsøkingskurs")),
                        "",
                        false),
                new FieldDefinition(254, "VILKARUTD",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        289, 290,
                        List.of(
                                new Code("04", "Benytte rett til skole")),
                        "",
                        false),
                new FieldDefinition(256, "VILKARJOBBLOG",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        291, 292,
                        List.of(
                                new Code("06", "Registrere seg som arbeidssøker/føre jobblogg")),
                        "",
                        false),
                new FieldDefinition(257, "VILKARJOBBTILB",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        293, 294,
                        List.of(
                                new Code("07", "Ta imot et konkret jobbtilbud")),
                        "",
                        false),
                new FieldDefinition(258, "VILKARSAMT",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        295, 296,
                        List.of(
                                new Code("08", "Møte til veiledningssamtaler")),
                        "",
                        false),
                new FieldDefinition(2510, "VILKAROKRETT",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        297, 298,
                        List.of(
                                new Code("10", "Gjøre gjeldende andre økonomiske rettigheter")),
                        "",
                        false),
                new FieldDefinition(2511, "VILKARLIVSH",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        299, 300,
                        List.of(
                                new Code("18", "Realisere formuesgoder/ redusere boutgifter")),
                        "",
                        false),
                new FieldDefinition(2514, "VILKARHELSE",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        301, 302,
                        List.of(
                                new Code("14", "Oppsøke helsetjenester/ lege")),
                        "",
                        false),
                new FieldDefinition(2515, "VILKARANNET",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        303, 304,
                        List.of(
                                new Code("15", "Annet")),
                        "",
                        false),
                new FieldDefinition(2516, "VILKARDIGPLAN",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        305, 306,
                        List.of(
                                new Code("19", "Bruke og følge opp digital aktivitetsplan")),
                        "",
                        false),
                new FieldDefinition(26, "VEDTAKDATO",
                        DATE_TYPE,
                        TEXTBOX_VIEWTYPE,
                        307, 312,
                        List.of(),
                        DATE6_PATTERN,
                        false),
                new FieldDefinition(271, "VEDTAKARB",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        313, 314,
                        List.of(
                                new Code("01", "Mottaker er i arbeid")),
                        "",
                        false),
                new FieldDefinition(272, "VEDTAKAKT",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        315, 316,
                        List.of(
                                new Code("02", "Mottaker er allerede i aktivitet knyttet til mottak av statlig eller annen kommunal ytelse")),
                        "",
                        false),
                new FieldDefinition(273, "VEDTAKHELSE",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        317, 318,
                        List.of(
                                new Code("03", "Mottakers helsemessige eller sosiale situasjon hindrer deltakelse i aktivitet")),
                        "",
                        false),
                new FieldDefinition(274, "VEDTAKGRUNN",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        319, 320,
                        List.of(
                                new Code("04", "Andre tungtveiende grunner taler mot at det stilles vilkår om aktivitet")),
                        "",
                        false),
                new FieldDefinition(281, "SANKSJONRED",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        321, 322,
                        List.of(
                                new Code("01", "Redusert stønad")),
                        "",
                        false),
                new FieldDefinition(282, "SANKSJONANDRE",
                        STRING_TYPE,
                        CHECKBOX_VIEWTYPE,
                        323, 324,
                        List.of(
                                new Code("02", "Andre konsekvenser")),
                        "",
                        false)
        );
    }

    public static Integer getFieldLength() {
        return getFieldDefinitions().get(getFieldDefinitions().size() - 1).getTo();
    }
}

