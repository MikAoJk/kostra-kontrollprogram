package no.ssb.kostra.utils;

public final class RegionerKvartal {
    private static final String[][] regions = {
            {"110000", "ROGALAND"},
            {"150000", "MØRE OG ROMSDAL"},
            {"180000", "NORDLAND"},
            {"300000", "VIKEN"},
            {"340000", "INNLANDET"},
            {"380000", "VESTFOLD OG TELEMARK"},
            {"420000", "AGDER"},
            {"460000", "VESTLAND"},
            {"500000", "TRØNDELAG"},
            {"540000", "TROMS OG FINNMARK"},
            {"030100", "Oslo"},
            {"110100", "Eigersund"},
            {"110300", "Stavanger"},
            {"110600", "Haugesund"},
            {"110800", "Sandnes"},
            {"111100", "Sokndal"},
            {"111200", "Lund"},
            {"111400", "Bjerkreim"},
            {"111900", "Hå"},
            {"112000", "Klepp"},
            {"112100", "Time"},
            {"112200", "Gjesdal"},
            {"112400", "Sola"},
            {"112700", "Randaberg"},
            {"113000", "Strand"},
            {"113300", "Hjelmeland"},
            {"113400", "Suldal"},
            {"113500", "Sauda"},
            {"114400", "Kvitsøy"},
            {"114500", "Bokn"},
            {"114600", "Tysvær"},
            {"114900", "Karmøy"},
            {"115100", "Utsira"},
            {"116000", "Vindafjord"},
            {"150500", "Kristiansund"},
            {"150600", "Molde"},
            {"150700", "Ålesund"},
            {"151100", "Vanylven"},
            {"151400", "Sande"},
            {"151500", "Herøy"},
            {"151600", "Ulstein"},
            {"151700", "Hareid"},
            {"152000", "Ørsta"},
            {"152500", "Stranda"},
            {"152800", "Sykkylven"},
            {"153100", "Sula"},
            {"153200", "Giske"},
            {"153500", "Vestnes"},
            {"153900", "Rauma"},
            {"154700", "Aukra"},
            {"155400", "Averøy"},
            {"155700", "Gjemnes"},
            {"156000", "Tingvoll"},
            {"156300", "Sunndal"},
            {"156600", "Surnadal"},
            {"157300", "Smøla"},
            {"157600", "Aure"},
            {"157700", "Volda"},
            {"157800", "Fjord"},
            {"157900", "Hustadvika"},
            {"180400", "Bodø"},
            {"180600", "Narvik"},
            {"181100", "Bindal"},
            {"181200", "Sømna"},
            {"181300", "Brønnøy"},
            {"181500", "Vega"},
            {"181600", "Vevelstad"},
            {"181800", "Herøy"},
            {"182000", "Alstahaug"},
            {"182200", "Leirfjord"},
            {"182400", "Vefsn"},
            {"182500", "Grane"},
            {"182600", "Hattfjelldal"},
            {"182700", "Dønna"},
            {"182800", "Nesna"},
            {"183200", "Hemnes"},
            {"183300", "Rana"},
            {"183400", "Lurøy"},
            {"183500", "Træna"},
            {"183600", "Rødøy"},
            {"183700", "Meløy"},
            {"183800", "Gildeskål"},
            {"183900", "Beiarn"},
            {"184000", "Saltdal"},
            {"184100", "Fauske - Fuosko"},
            {"184500", "Sørfold"},
            {"184800", "Steigen"},
            {"185100", "Lødingen"},
            {"185300", "Evenes"},
            {"185600", "Røst"},
            {"185700", "Værøy"},
            {"185900", "Flakstad"},
            {"186000", "Vestvågøy"},
            {"186500", "Vågan"},
            {"186600", "Hadsel"},
            {"186700", "Bø"},
            {"186800", "Øksnes"},
            {"187000", "Sortland - Suortá"},
            {"187100", "Andøy"},
            {"187400", "Moskenes"},
            {"187500", "Hamarøy - Hábmer"},
            {"300100", "Halden"},
            {"300200", "Moss"},
            {"300300", "Sarpsborg"},
            {"300400", "Fredrikstad"},
            {"300500", "Drammen"},
            {"300600", "Kongsberg"},
            {"300700", "Ringerike"},
            {"301100", "Hvaler"},
            {"301200", "Aremark"},
            {"301300", "Marker"},
            {"301400", "Indre Østfold"},
            {"301500", "Skiptvet"},
            {"301600", "Rakkestad"},
            {"301700", "Råde"},
            {"301800", "Våler (Viken)"},
            {"301900", "Vestby"},
            {"302000", "Nordre Follo"},
            {"302100", "Ås"},
            {"302200", "Frogn"},
            {"302300", "Nesodden"},
            {"302400", "Bærum"},
            {"302500", "Asker"},
            {"302600", "Aurskog-Høland"},
            {"302700", "Rælingen"},
            {"302800", "Enebakk"},
            {"302900", "Lørenskog"},
            {"303000", "Lillestrøm"},
            {"303100", "Nittedal"},
            {"303200", "Gjerdrum"},
            {"303300", "Ullensaker"},
            {"303400", "Nes (Akershus)"},
            {"303500", "Eidsvoll"},
            {"303600", "Nannestad"},
            {"303700", "Hurdal"},
            {"303800", "Hole"},
            {"303900", "Flå"},
            {"304000", "Nes (Buskerud)"},
            {"304100", "Gol"},
            {"304200", "Hemsedal"},
            {"304300", "Ål"},
            {"304400", "Hol"},
            {"304500", "Sigdal"},
            {"304600", "Krødsherad"},
            {"304700", "Modum"},
            {"304800", "Øvre Eiker"},
            {"304900", "Lier"},
            {"305000", "Flesberg"},
            {"305100", "Rollag"},
            {"305200", "Nore og Uvdal"},
            {"305300", "Jevnaker"},
            {"305400", "Lunner"},
            {"340100", "Kongsvinger"},
            {"340300", "Hamar"},
            {"340500", "Lillehammer"},
            {"340700", "Gjøvik"},
            {"341100", "Ringsaker"},
            {"341200", "Løten"},
            {"341300", "Stange"},
            {"341400", "Nord-Odal"},
            {"341500", "Sør-Odal"},
            {"341600", "Eidskog"},
            {"341700", "Grue"},
            {"341800", "Åsnes"},
            {"341900", "Våler (Innlandet)"},
            {"342000", "Elverum"},
            {"342100", "Trysil"},
            {"342200", "Åmot"},
            {"342300", "Stor-Elvdal"},
            {"342400", "Rendalen"},
            {"342500", "Engerdal"},
            {"342600", "Tolga"},
            {"342700", "Tynset"},
            {"342800", "Alvdal"},
            {"342900", "Folldal"},
            {"343000", "Os (Innlandet)"},
            {"343100", "Dovre"},
            {"343200", "Lesja"},
            {"343300", "Skjåk"},
            {"343400", "Lom"},
            {"343500", "Vågå"},
            {"343600", "Nord-Fron"},
            {"343700", "Sel"},
            {"343800", "Sør-Fron"},
            {"343900", "Ringebu"},
            {"344000", "Øyer"},
            {"344100", "Gausdal"},
            {"344200", "Østre Toten"},
            {"344300", "Vestre Toten"},
            {"344600", "Gran"},
            {"344700", "Søndre Land"},
            {"344800", "Nordre Land"},
            {"344900", "Sør-Aurdal"},
            {"345000", "Etnedal"},
            {"345100", "Nord-Aurdal"},
            {"345200", "Vestre Slidre"},
            {"345300", "Øystre Slidre"},
            {"345400", "Vang"},
            {"380100", "Horten"},
            {"380200", "Holmestrand"},
            {"380300", "Tønsberg"},
            {"380400", "Sandefjord"},
            {"380500", "Larvik"},
            {"380600", "Porsgrunn"},
            {"380700", "Skien"},
            {"380800", "Notodden"},
            {"381100", "Færder"},
            {"381200", "Siljan"},
            {"381300", "Bamble"},
            {"381400", "Kragerø"},
            {"381500", "Drangedal"},
            {"381600", "Nome"},
            {"381700", "Midt-Telemark"},
            {"381800", "Tinn"},
            {"381900", "Hjartdal"},
            {"382000", "Seljord"},
            {"382100", "Kviteseid"},
            {"382200", "Nissedal"},
            {"382300", "Fyresdal"},
            {"382400", "Tokke"},
            {"382500", "Vinje"},
            {"420100", "Risør"},
            {"420200", "Grimstad"},
            {"420300", "Arendal"},
            {"420400", "Kristiansand"},
            {"420500", "Lindesnes"},
            {"420600", "Farsund"},
            {"420700", "Flekkefjord"},
            {"421100", "Gjerstad"},
            {"421200", "Vegårshei"},
            {"421300", "Tvedestrand"},
            {"421400", "Froland"},
            {"421500", "Lillesand"},
            {"421600", "Birkenes"},
            {"421700", "Åmli"},
            {"421800", "Iveland"},
            {"421900", "Evje og Hornnes"},
            {"422000", "Bygland"},
            {"422100", "Valle"},
            {"422200", "Bykle"},
            {"422300", "Vennesla"},
            {"422400", "Åseral"},
            {"422500", "Lyngdal"},
            {"422600", "Hægebostad"},
            {"422700", "Kvinesdal"},
            {"422800", "Sirdal"},
            {"460100", "Bergen"},
            {"460200", "Kinn"},
            {"461100", "Etne"},
            {"461200", "Sveio"},
            {"461300", "Bømlo"},
            {"461400", "Stord"},
            {"461500", "Fitjar"},
            {"461600", "Tysnes"},
            {"461700", "Kvinnherad"},
            {"461800", "Ullensvang"},
            {"461900", "Eidfjord"},
            {"462000", "Ulvik"},
            {"462100", "Voss"},
            {"462200", "Kvam"},
            {"462300", "Samnanger"},
            {"462400", "Bjørnafjorden"},
            {"462500", "Austevoll"},
            {"462600", "Øygarden"},
            {"462700", "Askøy"},
            {"462800", "Vaksdal"},
            {"462900", "Modalen"},
            {"463000", "Osterøy"},
            {"463100", "Alver"},
            {"463200", "Austrheim"},
            {"463300", "Fedje"},
            {"463400", "Masfjorden"},
            {"463500", "Gulen"},
            {"463600", "Solund"},
            {"463700", "Hyllestad"},
            {"463800", "Høyanger"},
            {"463900", "Vik"},
            {"464000", "Sogndal"},
            {"464100", "Aurland"},
            {"464200", "Lærdal"},
            {"464300", "Årdal"},
            {"464400", "Luster"},
            {"464500", "Askvoll"},
            {"464600", "Fjaler"},
            {"464700", "Sunnfjord"},
            {"464800", "Bremanger"},
            {"464900", "Stad"},
            {"465000", "Gloppen"},
            {"465100", "Stryn"},
            {"500100", "Trondheim"},
            {"500600", "Steinkjer"},
            {"500700", "Namsos"},
            {"501400", "Frøya"},
            {"502000", "Osen"},
            {"502100", "Oppdal"},
            {"502200", "Rennebu"},
            {"502500", "Røros"},
            {"502600", "Holtålen"},
            {"502700", "Midtre Gauldal"},
            {"502800", "Melhus"},
            {"502900", "Skaun"},
            {"503100", "Malvik"},
            {"503200", "Selbu"},
            {"503300", "Tydal"},
            {"503400", "Meråker"},
            {"503500", "Stjørdal"},
            {"503600", "Frosta"},
            {"503700", "Levanger"},
            {"503800", "Verdal"},
            {"504100", "Snåase - Snåsa"},
            {"504200", "Lierne"},
            {"504300", "Raarvihke - Røyrvik"},
            {"504400", "Namsskogan"},
            {"504500", "Grong"},
            {"504600", "Høylandet"},
            {"504700", "Overhalla"},
            {"504900", "Flatanger"},
            {"505200", "Leka"},
            {"505300", "Inderøy"},
            {"505400", "Indre Fosen"},
            {"505500", "Heim"},
            {"505600", "Hitra"},
            {"505700", "Ørland"},
            {"505800", "Åfjord"},
            {"505900", "Orkland"},
            {"506000", "Nærøysund"},
            {"506100", "Rindal"},
            {"540100", "Tromsø"},
            {"540200", "Harstad - Hárstták"},
            {"540300", "Alta"},
            {"540400", "Vardø"},
            {"540500", "Vadsø"},
            {"540600", "Hammerfest"},
            {"541100", "Kvæfjord"},
            {"541200", "Tjeldsund"},
            {"541300", "Ibestad"},
            {"541400", "Gratangen"},
            {"541500", "Loabák - Lavangen"},
            {"541600", "Bardu"},
            {"541700", "Salangen"},
            {"541800", "Målselv"},
            {"541900", "Sørreisa"},
            {"542000", "Dyrøy"},
            {"542100", "Senja"},
            {"542200", "Balsfjord"},
            {"542300", "Karlsøy"},
            {"542400", "Lyngen"},
            {"542500", "Storfjord - Omasvuotna - Omasvuono"},
            {"542600", "Gáivuotna - Kåfjord - Kaivuono"},
            {"542700", "Skjervøy"},
            {"542800", "Nordreisa - Ráisa - Raisi"},
            {"542900", "Kvænangen"},
            {"543000", "Guovdageaidnu - Kautokeino"},
            {"543200", "Loppa"},
            {"543300", "Hasvik"},
            {"543400", "Måsøy"},
            {"543500", "Nordkapp"},
            {"543600", "Porsanger - Porsángu - Porsanki"},
            {"543700", "Kárásjohka - Karasjok"},
            {"543800", "Lebesby"},
            {"543900", "Gamvik"},
            {"544000", "Berlevåg"},
            {"544100", "Deatnu - Tana"},
            {"544200", "Unjárga - Nesseby"},
            {"544300", "Båtsfjord"},
            {"544400", "Sør-Varanger"},
    };

    public static boolean fylkeskommuneNrIsValid(String fylkeskommuneNr) {
        for (int i = regions.length - 1; i >= 0; i--) {
            if (fylkeskommuneNr.equalsIgnoreCase(regions[i][0]) &&
                    fylkeskommuneNr.substring(2, 4).equalsIgnoreCase("00")) {
                return true;
            }
        }
        return false;
    }

    public static String getRegionName(String regionNr) {
        for (int i = regions.length - 1; i >= 0; i--) {
            if (regionNr.equalsIgnoreCase(regions[i][0])) {
                return regions[i][1];
            }
        }
        return "";
    }
}

