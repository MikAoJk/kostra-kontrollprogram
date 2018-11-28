package no.ssb.helseforetak.control.regnskap.regn0Y;

import no.ssb.kostra.control.Constants;
import no.ssb.kostra.control.Control;
import no.ssb.kostra.control.ControlRecord;
import no.ssb.kostra.utils.Regioner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;

public final class Main {
    private final String lf = Constants.lineSeparator;
    private final String VERSION = "0Y." + Constants.kostraYear + ".01";
    private String regionNumber;
    private String[] regnskap;
    private String statistiskEnhet;
    private File sourceFile;
    private File reportFile;
    private Control[] controls;


    public Main(String regionNumber, File sourceFile,
                File reportFile, String[] regnskap, String statistiskEnhet) {

        this.regionNumber = regionNumber;
        this.sourceFile = sourceFile;
        this.reportFile = reportFile;
        this.regnskap = regnskap;
        this.statistiskEnhet = statistiskEnhet;
        initControls();
    }


    public int start() {
        int lineNumber = 0;
        int error_type = Constants.NO_ERROR;

        for (int i = 0; i < regnskap.length; i++) {

            if (!regnskap[i].equalsIgnoreCase("")) {

                lineNumber += 1;

                //Sjekker recordlengde forst, fordi feil reccordlengde
                //vil kunne odelegge mange andre kontroller
                //(StringIndexOutOfBoundsException etc.)
                if (!controls[0].doControl(regnskap[i], lineNumber, regionNumber, statistiskEnhet)) {

                    for (int j = 1; j < controls.length; j++)
                        controls[j].doControl(regnskap[i], lineNumber, regionNumber, statistiskEnhet);

                }
            }
        }

        try {
            BufferedWriter fileWriter = null;
            if (reportFile != null)
                fileWriter = new BufferedWriter(new FileWriter(reportFile));

            StringBuilder report = new StringBuilder("-------------------------------------------------" + lf);
            report.append(lf);
            report.append(" Feilmeldingsrapport for " + regionNumber + " " + Regioner.getRegionName(regionNumber.substring(0, 2)) + lf);
            report.append(lf);
            report.append("-------------------------------------------------" + lf + lf);

            report.append("Kontrollprogramversjon: " + VERSION + lf);
            report.append("Rapport generert: " + Calendar.getInstance().getTime() + lf);
            report.append("Kontrollert fil: " + (sourceFile != null ? sourceFile.getAbsolutePath() : "") + lf);
            report.append("Type regnskap: 0Y - balanse helseforetak/regionale helseforetak" + lf + lf);

            int numOfErrors = 0;
            int control_error_type;
            StringBuilder buffer = new StringBuilder();

            for (int i = 0; i < controls.length; i++) {
                if (controls[i].foundError()) {
                    control_error_type = controls[i].getErrorType();
                    if (control_error_type > error_type)
                        error_type = control_error_type;
                    buffer.append(controls[i].getErrorReport(lineNumber));
                    numOfErrors += 1;
                }
            }

            if (numOfErrors > 0) {
                report.append("Følgende " + (numOfErrors == 1 ? "kontroll" : (numOfErrors + " kontroller")) + " har funnet feil eller advarsler:" + lf + lf);
                report.append(buffer);
            } else {
                if (lineNumber == 0) {
                    report.append("Finner ingen data!");
                    error_type = Constants.CRITICAL_ERROR;
                } else {
                    report.append("Ingen feil funnet!");
                }
            }
            if (fileWriter != null) {
                fileWriter.write(report.toString());
                fileWriter.close();
            } else {
                System.out.println(report.toString());
            }
        } catch (Exception e) {
            System.out.println("Can't write report file: ");
            System.out.println(e.toString());
            ;
            System.exit(Constants.IO_ERROR);
        }

        return error_type;
    }

    private void initControls() {
        controls = new Control[10];
        controls[0] = new ControlRecordlengde();
        controls[1] = new ControlRegnskapstype();
        controls[2] = new ControlAargang();
        controls[3] = new ControlRegion();
        controls[4] = new ControlOrgNummer();
        controls[5] = new ControlKontokoderBalanse();
        controls[6] = new Control11EiendelerLikEgenkapitalOgGjeld();
        controls[7] = new Control_19();
        controls[8] = new ControlRecord();
        controls[9] = new ControlStatistiskEnhet();
    }
}