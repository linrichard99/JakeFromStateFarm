import java.util.*;
import java.text.*;


public class PDA {

    public static void main (String[] args) {
	PDA IEH = new PDA();
	IEH.printMain();

    }

    public String getDateSimple() {
	Date currentDate = new Date(); //Instantiating the date
	SimpleDateFormat displayDate = new SimpleDateFormat("E yyyy-MM-dd"); //Instantiating the SimpleDateFormat object
	return "Date: " + displayDate.format(currentDate); //this line displays the date the way we want it to
    }

    public void printMain() {
	Printer.println();
	Printer.println();
	Printer.println(getDateSimple());
	Printer.println();
	Printer.println("<Time>");
	Printer.println();
	Printer.println("<Calendar>");
	Printer.println("<Scheduler>");
	Printer.println("<Contacts>");
	Printer.println("<Memo>");
	Printer.println("<Calculator>");
	Printer.println();
    }

}
