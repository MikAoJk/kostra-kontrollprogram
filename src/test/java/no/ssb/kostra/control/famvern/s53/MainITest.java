package no.ssb.kostra.control.famvern.s53;

import no.ssb.kostra.controlprogram.Arguments;
import no.ssb.kostra.felles.Constants;
import no.ssb.kostra.felles.ErrorReport;
import no.ssb.kostra.felles.FieldDefinition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MainITest {
    InputStream sysInBackup;
    private List<FieldDefinition> fieldDefinitions;


    @Before
    public void beforeTest() {
        sysInBackup = System.in; // backup System.in to restore it later
        fieldDefinitions = FieldDefinitions.getFieldDefinitions();
    }

    @After
    public void afterTest() {
        System.setIn(sysInBackup);
    }

    @Test
    public void testDoControlHasWarnings() {
        String inputFileContent =
                //@formatter:off
                //0000000011111111112222222222333333333344444444445555555555666666666677777777778888888
                //2345678901234567890123456789012345678901234567890123456789012345678901234567890123456
                """
                        3002300500070002000300080009000300040004000500030004000100020
                        3002400500070002000300080009000300040004000500030004000100020
                        0101700500070002000300080009000300040004000500030004000100020
                        30   00500070002000300080009000300040004000500030004000100020
                        3002100500070002000300080009000300040004000500030004000100020
                        3003800500070002000300080009000300040004000500030004000100020
                        30 2800500070002000300080009000300040004000500030004000100020
                        03030    0070002000300080009000300040004000500030004000100020
                        030370050    002000300080009000300040004000500030004000100020
                        0303800500070    00300080009000300040004000500030004000100020
                        03039005000700020    0080009000300040004000500030004000100020
                        340450050007000200030    009000300040004000500030004000100020
                        3405200500070002000300080    00300040004000500030004000100020
                        30061005000700020003000800090    0040004000500030004000100020
                        300650050007000200030008000900030    004000500030004000100020
                        3807100500070002000300080009000300040    00500030004000100020
                        38073005000700020003000800090003000400040    0030004000100020
                        380810050007000200030008000900030004000400050    004000100020
                        3808200500070002000300080009000300040004000500030    00100020
                        42091005000700020003000800090003000400040005000300040    0020
                        421010050007000200030008000900030004000400050003000400010   \s
                        1111100500070002000300080009000300040004000500030004000100020
                        1111200500070002000300080009000300040004000500030004000100020
                        4612500500070002000300080009000300040004000500030004000100020
                        4612700500070020000300080009000300040004000500030004000100020
                        4614100500070002000300080009000300040004000500030004000100020
                        4614200500070002000300080009000300040004000500030004000100020
                        1515100500070002000300080009000300040004000600030004000100020
                        1515200500070002000300080009000300040004000500030004000100020
                        1515300500070002000300080009000300040004000500030004000100020
                        5016200500070002000300080009000300040004000500030004000100020
                        5017100500070002000300080009000300040004000500030004000100020
                        5017200500070002000300080009000300040004000500030004000100020
                        1818100500070002000300080009000300040004000500030004000100020
                        1818300500070002000300080009000300040004000500030004000100020
                        1818400500070002000300080009000300040004000500030000400100020
                        1818500500070002000300080009000300040004000500030004000100020
                        5419200500070002000600080006000300040004000500030004000100020
                        5419300500070002000300080009000300040004000500030004000100020
                        5419400500070002000300080009000300040004000500030004000100020
                        5420200500070002000300080009000300040004000500030004000100020
                        5420300500070002000300080009000300040004000500030004000100020
                        5420500500070002000300080009000300040004000500030004000100020""";

        //@formatter:on


        ByteArrayInputStream in = new ByteArrayInputStream(inputFileContent.getBytes(StandardCharsets.ISO_8859_1));
        System.setIn(in);

        Arguments args = new Arguments(new String[]{"-s", "53F", "-y", "2020", "-r", "667600"});
        ErrorReport er = Main.doControls(args);

        if (Constants.DEBUG) {
            System.out.print(er.generateReport());
        }

        assertNotNull("Has content ErrorReport", er);
        assertEquals(Constants.NORMAL_ERROR, er.getErrorType());
    }

    @Test
    public void testDoControlNormal() {
        String inputFileContent =
                //@formatter:off
                //0000000011111111112222222222333333333344444444445555555555666666666677777777778888888
                //2345678901234567890123456789012345678901234567890123456789012345678901234567890123456
                """
                        3001700500070002000300080009000300040004000500030004000100020
                        3002300500070002000300080009000300040004000500030004000100020
                        3002400500070002000300080009000300040004000500030004000100020
                        3002500500070002000300080009000300040004000500030004000100020
                        3002700500070002000300080009000300040004000500030004000100020
                        0303000500070002000300080009000300040004000500030004000100020
                        0303700500070002000300080009000300040004000500030004000100020
                        0303800500070002000300080009000300040004000500030004000100020
                        0303900500070002000300080009000300040004000500030004000100020
                        3404500500070002000300080009000300040004000500030004000100020
                        3405200500070002000300080009000300040004000500030004000100020
                        3006100500070002000300080009000300040004000500030004000100020
                        3006500500070002000300080009000300040004000500030004000100020
                        3807100500070002000300080009000300040004000500030004000100020
                        3807300500070002000300080009000300040004000500030004000100020
                        3808100500070002000300080009000300040004000500030004000100020
                        3808200500070002000300080009000300040004000500030004000100020
                        4209100500070002000300080009000300040004000500030004000100020
                        4210100500070002000300080009000300040004000500030004000100020
                        1111100500070002000300080009000300040004000500030004000100020
                        1111200500070002000300080009000300040004000500030004000100020
                        4612500500070002000300080009000300040004000500030004000100020
                        4612700500070002000300080009000300040004000500030004000100020
                        4614100500070002000300080009000300040004000500030004000100020
                        4614200500070002000300080009000300040004000500030004000100020
                        1515100500070002000300080009000300040004000500030004000100020
                        1515200500070002000300080009000300040004000500030004000100020
                        1515300500070002000300080009000300040004000500030004000100020
                        5016200500070002000300080009000300040004000500030004000100020
                        5017100500070002000300080009000300040004000500030004000100020
                        5017200500070002000300080009000300040004000500030004000100020
                        1818100500070002000300080009000300040004000500030004000100020
                        1818300500070002000300080009000300040004000500030004000100020
                        1818400500070002000300080009000300040004000500030004000100020
                        1818500500070002000300080009000300040004000500030004000100020
                        5419200500070002000300080009000300040004000500030004000100020
                        5419300500070002000300080009000300040004000500030004000100020
                        5419400500070002000300080009000300040004000500030004000100020
                        5420200500070002000300080009000300040004000500030004000100020
                        5420300500070002000300080009000300040004000500030004000100020
                        5420500500070002000300080009000300040004000500030004000100020""";
        //@formatter:on


        ByteArrayInputStream in = new ByteArrayInputStream(inputFileContent.getBytes(StandardCharsets.ISO_8859_1));
        System.setIn(in);

        Arguments args = new Arguments(new String[]{"-s", "53F", "-y", "2020", "-r", "667600"});
        ErrorReport er = Main.doControls(args);

        if (Constants.DEBUG) {
            System.out.print(er.generateReport());
        }

        assertNotNull("Has content ErrorReport", er);
        assertEquals(Constants.NO_ERROR, er.getErrorType());
    }

}
