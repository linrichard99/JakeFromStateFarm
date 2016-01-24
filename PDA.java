import java.util.*;
import java.text.*;
import java.io.*;


public class PDA extends App{

    public static void main (String[] args) {

	PDA IEH = new PDA();
	
	IEH.run();
	
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

    //Interface implementations

    //%-s makes the following string left justified                            
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


    //two different screens can be displayed

    public void printMain() {

	clear();
	
	printLine();
	eventPrinter();
	println();
	println(getDateSimple());
	println();
	println("<Time>");
	println();
	println("<Calendar>");
	println("<Scheduler>");
	println("<Contacts>");
	println("<Memo>");
	println("<Exit>");
	println();
	println();
	printLine();
    }
	
    public void printMainTime() {
	
	clear();
	
        printLine();
	eventPrinter();
        println();
        println(getDateSimple());
        println(getTime());
        println("<Time>");
        println();
        println("<Calendar>");
        println("<Scheduler>");
        println("<Contacts>");
        println("<Memo>");
	println("<Exit>");
        println();
        println();
        printLine();
    }

    //This pulls from events.csv, and checks if there is an event. If there is, it diplays it.
    public void eventPrinter() {
	if ( checkDate(getDateSimple().substring(10,20)) ) { //this can be hard-coded because length of getDateSimple() is definite and we're using a standardized date format
            println("YOU HAVE AN EVENT TODAY!");
        }
	else {
            println();
	}
    }

    //The run method
    public void run() {
	printMain();
	
	while (goToMain) { //As long as goToMain is active, the whole thing runs

	    System.out.print("> "); //Input indicator
	    
	    String input = ""; //We only need one input slot since there will only be one thing going on at all times. 

	    //A try catch block is necessary to read the lines
	    try {
		input = readVal.readLine();
	    }
	    
	    catch ( IOException e) {}

	    
	    if (input.equals("")) {
		printMain();
	    }

	    else if (input.equals("Exit")) {
		goToMain = false; //This ends the loop, thus ending the program
	    }
	    
	    else if (input.equals("Time")) {
		printMainTime();
	    }

	    //Each one of these apps have their separate run methods, and it is invoked with the inputs. Once the run method ends, the main screen is reprinted.
	    else if (input.equals("Scheduler")) {
		Scheduler S = new Scheduler();
		S.run();
		printMain();
	    }

	    else if (input.equals("Calendar")) {
		CalendarPDA C = new CalendarPDA();
		C.run();
		printMain();
	    }

	    else if (input.equals("Contacts")) {
		Contacts O = new Contacts();
		O.run();
		printMain();
	    }
	   
	    else if (input.equals("Memo")) {
		Memos M = new Memos();
		M.run();
		printMain();
	    }

	    
	    else {
		System.out.println( "Invalid input, try again." );
	    }

	}
    }
    
}
