package no.ssb.kostra.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("SpellCheckingInspection")
public final class Fnr {

    static boolean isValidDate(final String dateStr, final String dateFormat) {
        if (dateStr == null || dateFormat == null) return false;
        final var simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setLenient(false);

        try {
            simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidNorwId(final String fnr) {
        final var s = new int[12];

        if (fnr == null) return false;
        if (!Pattern.compile("^\\d{11}$").matcher(fnr).matches()) return false;

        for (var i = 1; i <= 11; i++) {
            s[i] = Format.parseInt(fnr.substring(i - 1, i));
        }

        var k1 = (s[1] * 3) + (s[2] * 7) + (s[3] * 6) + s[4] + (s[5] * 8) +
                (s[6] * 9) + (s[7] * 4) + (s[8] * 5) + (s[9] * 2);

        var rest = k1 % 11;
        if (rest == 1) return false;
        if (rest == 0) k1 = 0;
        else k1 = 11 - rest;

        var k2 = (s[1] * 5) + (s[2] * 4) + (s[3] * 3) + (s[4] * 2) + (s[5] * 7) +
                (s[6] * 6) + (s[7] * 5) + (s[8] * 4) + (s[9] * 3) + (k1 * 2);

        rest = k2 % 11;
        if (rest == 1) return false;
        if (rest == 0) k2 = 0;
        else k2 = 11 - rest;

        return (k1 == s[10]) && (k2 == s[11]);
    }

    public static boolean isPartiallyValidNorwId(final String fnr) {
        if (fnr == null) return false;
        if (!Pattern.compile("^\\d{11}$").matcher(fnr).matches()) return false;
        if (isValidNorwId(fnr)) return true;

        return (isValidDate(fnr.substring(0, 6), "ddMMyy") && Stream.of("00100", "00200", "99999").anyMatch(it -> it.equalsIgnoreCase(fnr.substring(6, 11))));
    }

    public static int getAlderFromFnr(final String fnrDDMMYYSSGKK, final String rappAarYYYY) {
        if (fnrDDMMYYSSGKK == null || rappAarYYYY == null) return -1;

        if (isValidDate(fnrDDMMYYSSGKK.substring(0, 6), "ddMMyy")) {
            final var fodselsAar = Integer.parseInt(fnrDDMMYYSSGKK.substring(4, 6));
            final var aargang = Integer.parseInt(rappAarYYYY.substring(2, 4));
            final var alder = (aargang < fodselsAar) ? aargang + 100 - fodselsAar : aargang - fodselsAar;

            return (alder == 99) ? -1 : alder;
        }
        return -1;
    }

    public static boolean isValidDUFnr(final String dufnr) {
        if (dufnr == null) return false;

        if (!Pattern.compile("^\\d{12}$").matcher(dufnr).matches()) return false;

        final var weights = List.of(4, 6, 3, 2, 4, 6, 3, 2, 7, 5);
        final var numbers = dufnr.chars().mapToObj(e -> (char) e).toList();

        try {
            final var sum = IntStream.range(0, Math.min(numbers.size(), weights.size()))
                    .mapToObj(i -> Integer.parseInt(String.valueOf(numbers.get(i))) * weights.get(i))
                    .reduce(0, Integer::sum);
            final var remainder = sum % 11;
            final var kontrollTall = (remainder < 10 ? "0" : "") + remainder;
            return dufnr.substring(10).equalsIgnoreCase(kontrollTall);
        } catch (Exception e) {
            return false;
        }
    }
}
