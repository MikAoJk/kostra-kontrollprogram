package no.ssb.kostra.control.regnskap.regn0L;

import no.ssb.kostra.control.Constants;

import java.util.Vector;

/**
 * Created by ojj on 02.05.2018.
 */
final class ControlOrgNummer4a extends no.ssb.kostra.control.Control {
    private Vector<Integer> recordNumbers = new Vector<>();
    private String orgNummer = null;

    @Override
    public boolean doControl(String line, int lineNumber, String region, String statistiskEnhet) {
        String orgNum = RecordFields.getOrgNummer(line).trim();

        if (this.orgNummer == null) {
            this.orgNummer = orgNum;
        }

        boolean orgNummerIsNotValid = (this.orgNummer != null
                && this.orgNummer.length() > 0
                && orgNum.matches("^0")
        ) ? true : false;

        if (orgNummerIsNotValid) {
            recordNumbers.add(new Integer(lineNumber));
        }
        return orgNummerIsNotValid;
    }

    @Override
    public String getErrorReport(int totalLineNumber) {
        String errorReport = "Kontroll 5a, Samme organisasjonsnummer i hele filen og organisasjonsnummer forskjellig fra 0 (null):" + lf + lf;
        int numOfRecords = recordNumbers.size();
        if (numOfRecords > 0) {
            errorReport += "\tFeil: Det er oppgitt ulike organisasjonsnumre i filen, eller det er oppgitt organisasjonsnummer som bare består av 0 (null)." + lf +
                    "\t\t" + numOfRecords + " av " + totalLineNumber + " records har feil organisasjonsnummer.";
            if (numOfRecords <= 10) {
                errorReport += lf + "\t\t(Gjelder følgende records:";
                for (int i = 0; i < numOfRecords; i++) {
                    errorReport += " " + recordNumbers.elementAt(i);
                }
                errorReport += ")";
            }
            errorReport += lf + "\tKorreksjon: Legg inn kun ett organisasjonsnummer og dette må samsvare med organisasjonsnummeret som ligger på skjemaforsiden." + lf + lf;
        }
        return errorReport;
    }

    @Override
    public boolean foundError() {
        return recordNumbers.size() > 0;
    }

    @Override
    public int getErrorType() {
        return Constants.CRITICAL_ERROR;
    }
}
