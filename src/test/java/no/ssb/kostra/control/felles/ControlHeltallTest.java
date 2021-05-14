package no.ssb.kostra.control.felles;

import no.ssb.kostra.controlprogram.Arguments;
import no.ssb.kostra.felles.Constants;
import no.ssb.kostra.felles.ErrorReport;
import no.ssb.kostra.felles.ErrorReportEntry;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControlHeltallTest {
    private ErrorReport er;
    private ErrorReportEntry ere;

    @Before
    public void beforeTest() {
        Arguments args = new Arguments(new String[]{"-s", "Test", "-y", "9999", "-r", "888888"});
        er = new ErrorReport(args);
        ere = new ErrorReportEntry(" ", " ", " ", " "
                , "TEST av Heltall", "Feil i Heltall", Constants.CRITICAL_ERROR);
    }

    @Test
    public void testOK1() {
        assertFalse(ControlHeltall.doControl(er, ere, 12345));
    }

    @Test
    public void testHeltallFail1() {
        assertTrue(ControlHeltall.doControl(er, ere, null));
        assertEquals(Constants.CRITICAL_ERROR, er.getErrorType());
    }
}
