package bussinesslogic;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Days {
	
//count the noOfDays 
 public static long daysBetween(Date one, Date two) {
  long difference =  (one.getTime()-two.getTime())/86400000;
  return Math.abs(difference);
}
   
}

