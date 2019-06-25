package no.ssb.kostra.control.regnskap.regn0N;

import java.util.Vector;
import no.ssb.kostra.control.Constants;

final class ControlKontoklasse extends no.ssb.kostra.control.Control
{
  private Vector<Integer> lineNumbers = new Vector<Integer>();

  public boolean doControl(String line, int lineNumber, String region, String statistiskEnhet)
  {
    String kontoklasse = RecordFields.getKontoklasse (line);

    boolean lineHasError = ! kontoklasse.equalsIgnoreCase("2");
    
    if (lineHasError)
    {
      lineNumbers.add (new Integer (lineNumber));
    }
    return lineHasError;
  }

  public String getErrorReport (int totalLineNumber)
  {
    String errorReport = "Kontroll 4, kontoklasse:" + lf + lf;
    if (foundError())
    {
      int numOfRecords = lineNumbers.size();
      errorReport += "\tFeil: ukjent kontoklasse i " + numOfRecords + " record" + (numOfRecords == 1 ? "" : "s") + "." + lf;
      if (numOfRecords <= 10)
      {
        errorReport += "\t\t(Gjelder record nr.";
        for (int i=0; i<numOfRecords; i++)
        {
          errorReport += " " + lineNumbers.elementAt(i) + (i>0 ? "" : ", ");
        }
        errorReport += ").";
      }
    }
    errorReport +=lf + "\tKorreksjon: Rett kontoklasse. Kontoklasse 2 er gyldig for balanseregnskapet." + lf + lf;
    return errorReport;
  }

  public boolean foundError()
  {
    return lineNumbers.size() > 0;
  }  

  public int getErrorType() {
    return Constants.CRITICAL_ERROR;
  }
}