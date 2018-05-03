package no.ssb.kostra.control.sensitiv.famvern;

import java.util.Vector;
import no.ssb.kostra.control.Constants;

public final class Control8 extends no.ssb.kostra.control.Control
    implements no.ssb.kostra.control.SingleRecordErrorReport
{
  private final String ERROR_TEXT = "K8: Hvordan blitt kjent med familievernet";
  private Vector<Integer> lineNumbers = new Vector<Integer>();

  public boolean doControl(String line, int lineNumber, String region, String statistiskEnhet)
  {
    String henvInstans = RecordFields.getFieldValue(line, 5);

    boolean lineHasError; 
  
    try
    {
      int kode = Integer.parseInt(henvInstans);
      lineHasError = (kode<1 || kode>15);
    }
    catch (Exception e)
    {
      lineHasError = true;
    }
  
    if (lineHasError)
    {
      lineNumbers.add (new Integer (lineNumber));
    }
    return lineHasError;
  }

  public String getErrorReport (int totalLineNumber)
  {
    String errorReport = ERROR_TEXT + ":" + lf + lf;
    if (foundError())
    {
      int numOfRecords = lineNumbers.size();
      errorReport += "\tFeil: i " + numOfRecords + 
      " record" + (numOfRecords == 1 ? "" : "s") + 
      " er klientens henvisningsinstans ikke fylt ut," + lf +
      "\teller feil kode er benyttet. Feltet er obligatorisk."; 
      if (numOfRecords <= 10)
      {
        errorReport += lf + "\t\t(Gjelder record nr.";
        for (int i=0; i<numOfRecords; i++)
        {
          errorReport += " " + lineNumbers.elementAt(i);
        }
        errorReport += ").";
      }
    }
    errorReport += lf + lf;
    return errorReport;
  }

  public boolean foundError()
  {
    return lineNumbers.size() > 0;
  }  

  public String getErrorText()
  {
    return  ERROR_TEXT;
  }

  public int getErrorType() {
    return Constants.NORMAL_ERROR;
  }
}
