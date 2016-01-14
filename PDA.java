import java.util.*;
import java.text.*;


public class PDA implements Printer{

    public static void main (String[] args) {
	PDA IEH = new PDA();
	IEH.printMain();

    }

    public String getDateSimple() {
	Date currentDate = new Date(); //Instantiating the date
	SimpleDateFormat displayDate = new SimpleDateFormat("E yyyy-MM-dd"); //Instantiating the SimpleDateFormat object
	return "Date: " + displayDate.format(currentDate); //this line displays the date the way we want it to
    }

    //Printer interface implementations

    //%-s makes it the following string left justified
    //The 15 means width of 15
    //The \t and \n are just tabs and new lines spectively
    public void print(String arg) {
	System.out.printf("|\t %-35s|", arg); 
    }

    public void println(String arg) {
	System.out.printf("|\t %-35s|\n", arg);
    }

    public void println() {
	System.out.printf("|\t %-35s|\n", "");
    }
    
    //Printing the screen

    public void printMain() {
	println();
	println();
	println(getDateSimple());
	println();
	println("<Time>");
	println();
	println("<Calendar>");
	println("<Scheduler>");
	println("<Contacts>");
	println("<Memo>");
	println("<Calculator>");
	println();
	println();
    }

}
