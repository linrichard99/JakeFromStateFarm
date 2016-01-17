import java.util.*;
import java.text.*;
import java.io.*;


public class PDA extends Printer{

    private InputStreamReader inVal;
    private BufferedReader readVal;
    private boolean goToMain; //flag that allows the PDA to constantly run

    public static void main (String[] args) {

	PDA IEH = new PDA();
	
	IEH.run();
	
    }

    public PDA(){
	inVal = new InputStreamReader(System.in);
	readVal = new BufferedReader(inVal);
	goToMain = true;
    }

    //get date and time

    public String getDateSimple() {
	Date currentDate = new Date(); //Instantiating the date
	SimpleDateFormat displayDate = new SimpleDateFormat("E yyyy-MM-dd"); //Instantiating the SimpleDateFormat object
	return "Date: " + displayDate.format(currentDate); //this line displays the date the way we want it to
    }

    public String getTime() {
        Date currentDate = new Date(); //Instantiating the date                                                                                                                                                                                                               
        SimpleDateFormat displayTime = new SimpleDateFormat("hh:mm:ss");
        return displayTime.format(currentDate);
    }

    //two different screens can be displayed

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

    public void printMainTime() {
        printLine();
        println();
        println();
        println(getDateSimple());
        println(getTime());
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

    public void run() {
	printMain();
	
	while (goToMain) {
	    
	    String input = "";
	    
	    try {
		input = readVal.readLine();
	    }
	    
	    catch ( IOException e) {}
	    
	    if (input.equals("")) {
		printMain();
	    }

	    if (input.equals("Exit")) {
		goToMain = false;
	    }
	    
	    if (input.equals("Time")) {
		printMainTime();
	    }
	    
	}
    }
    
}
