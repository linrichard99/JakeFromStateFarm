import java.util.*;
import java.text.*;


public class PDA {
    

    //    private String[] 

    public static void main (String[] args) {

	Date currentDate = new Date();
	SimpleDateFormat displayDate = new SimpleDateFormat("E yyyy-MM-dd");
	System.out.println("Date: " + displayDate.format(currentDate));

    }
}
