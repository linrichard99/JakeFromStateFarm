import java.util.*;
import java.text.*;
import java.io.*;


public class PDA implements Printer{

    private InputStreamReader inVal;
    private BufferedReader readVal;
    private boolean goToMain;

    public static void main (String[] args) {
	PDA IEH = new PDA();
	IEH.printMain();
	
	while (IEH.goToMain) {
	    
	    String input = "";

	    try {
		input = IEH.readVal.readLine();
	    }

	    catch ( IOException e) {}


	    if (input.equals("Time")) {
		System.out.println();
		System.out.println("Time:" + IEH.getTime());
		IEH.printMain();
	    }
	       
	}		
	
    }

    public PDA(){
	inVal = new InputStreamReader(System.in);
	readVal = new BufferedReader(inVal);
	goToMain = true;
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
    //Non-static because the interface does not allow for static values
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
	printLine();
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
	printLine();
    }

    public void printLine() {
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    
    public String getTime() {
	Date currentDate = new Date(); //Instantiating the date
	SimpleDateFormat displayTime = new SimpleDateFormat("hh:mm:ss");
	return displayTime.format(currentDate);
    }
	

}
