package no.ssb.kostra.control.regnskap.regn0P;

/*
 * $Log: ControlNumericalFields.java,v $
 * Revision 1.5  2008/10/09 13:40:40  pll
 * Tekstendring.
 *
 * Revision 1.4  2008/10/04 10:30:01  pll
 * Versjon: 2008-rapportering.
 *
 * Revision 1.3  2007/11/07 10:49:32  pll
 * Endret implementasjon av getErrorType().
 *
 * Revision 1.2  2007/10/25 11:37:12  pll
 * Implementerer getErrorType.
 *
 * Revision 1.1.1.1  2007/09/18 12:24:07  pll
 * Versjon: 2006-rapporteringen
 *
 * Revision 1.2  2006/09/22 09:13:51  lwe
 * Oppdatert årgang
 *
 * Revision 1.1  2006/09/22 08:18:31  lwe
 * Flyttet 2005-filene over i 2006-katalog - men ikke oppdatert årstallene
 *
 * Revision 1.3  2006/01/05 08:31:54  lwe
 * replaced _final class_ with _public final class_
 *
 * Revision 1.2  2006/01/05 08:16:37  lwe
 * added logmessage
 * 
 */
 
import java.util.Vector;
import no.ssb.kostra.control.Constants;
import no.ssb.kostra.utils.CompatJdk13;

public class ControlNumericalFields extends no.ssb.kostra.control.Control
    implements no.ssb.kostra.control.SingleRecordErrorReport
{
  private final String ERROR_TEXT = "K20: Ulovlig tegn i tallfeltene";
  private Vector<Integer> lineNumbers = new Vector<Integer>();

  public boolean doControl(String line, int lineNumber, String region, String statistiskEnhet)
  {
	  boolean lineHasError = false;
	  
	  String concatenatedNumericalFields = 
	  RecordFields.getAllNumericalFieldsConcatenated(line);
	  String funksjon = RecordFields.getFunksjon(line);
	  String art = RecordFields.getArt(line);

	  if (! CompatJdk13.isNumericalWithSpace(concatenatedNumericalFields) ||
			  funksjon.equalsIgnoreCase("000") ||
			  art.equalsIgnoreCase("000"))
	  {
	  lineHasError = true;
	    lineNumbers.add(new Integer (lineNumber));
	  }
	  return lineHasError;
	}

  public String getErrorReport (int totalLineNumber)
  {
    String errorReport = ERROR_TEXT + ":" + lf + lf;
    errorReport += "\tDet forekommer andre verdier enn tallene 0 til 9. " + lf +
    "\tSjekk at posisjonene til funksjon/art ikke inneholder bare nuller (0) " + lf +
    "\tog/eller at filen ikke inneholder andre enn gyldige funksjoner og arter." + lf;
    int numOfErrors = lineNumbers.size();
    if (numOfErrors <= 10)
    {
      errorReport += "\t\t(Gjelder record nr: ";
      for (int i=0; i<numOfErrors; i++)
      {
        errorReport += " " + lineNumbers.elementAt(i);
      }
      errorReport += ")" + lf;
    }
    errorReport += lf;
    return errorReport;
  }
  public boolean foundError()
  {
    return lineNumbers.size()>0;
  }  

  public String getErrorText()
  {
    return  ERROR_TEXT;
  }

  public int getErrorType() {
    return Constants.CRITICAL_ERROR;
  }
}
