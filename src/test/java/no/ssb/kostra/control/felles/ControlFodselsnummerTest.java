package no.ssb.kostra.control.felles;

import no.ssb.kostra.controlprogram.Arguments;
import no.ssb.kostra.felles.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControlFodselsnummerTest {
    private ErrorReport er;
    private ErrorReportEntry ere;
    private List<FieldDefinition> fieldDefinitions;
    private Record r;


    @Before
    public void beforeTest() {
        Arguments args = new Arguments(new String[]{"-s", "Test", "-y", "9999", "-r", "888888"});
        er = new ErrorReport(args);
        ere = new ErrorReportEntry(" ", " ", " ", " "
                , "TEST av FNR", "Feil i FNR", Constants.CRITICAL_ERROR);
        fieldDefinitions = List.of(
                new FieldDefinition(1, "ssn", "String", "", 1, 11, new ArrayList<>(), "", false)
        );
    }

    @Test
    public void testOK1() {
        r = new Record("26117540129", fieldDefinitions);
        assertFalse(ControlFodselsnummer.doControl(er, ere, r.getFieldAsString("ssn")));
    }

    @Test
    public void testOK2() {
        r = new Record("41010150572", fieldDefinitions);
        assertFalse(ControlFodselsnummer.doControl(er, ere, r.getFieldAsString("ssn")));
    }

    @Test
    public void testFail1() {
        r = new Record("01010150590", fieldDefinitions);
        assertTrue(ControlFodselsnummer.doControl(er, ere, r.getFieldAsString("ssn")));
        assertEquals(Constants.CRITICAL_ERROR, er.getErrorType());
    }

    @Test
    public void testFail2() {
        r = new Record("010101     ", fieldDefinitions);
        assertTrue(ControlFodselsnummer.doControl(er, ere, r.getFieldAsString("ssn")));
        assertEquals(Constants.CRITICAL_ERROR, er.getErrorType());
    }
}
