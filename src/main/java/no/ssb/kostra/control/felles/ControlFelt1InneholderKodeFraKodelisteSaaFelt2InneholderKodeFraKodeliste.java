package no.ssb.kostra.control.felles;

import no.ssb.kostra.felles.ErrorReport;
import no.ssb.kostra.felles.ErrorReportEntry;

import java.util.List;

public class ControlFelt1InneholderKodeFraKodelisteSaaFelt2InneholderKodeFraKodeliste {
    public static boolean doControl(ErrorReport er, ErrorReportEntry ere, String fieldvalue1, List<String> codeList1, String fieldvalue2, List<String> codeList2) {
        return (Comparator.isCodeInCodelist(fieldvalue1, codeList1)
                && !Comparator.isCodeInCodelist(fieldvalue2, codeList2)
                && er.addEntry(ere));
    }
}
