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

    //interface implementations

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

	    else if (input.equals("Exit")) {
		goToMain = false;
	    }
	    
	    else if (input.equals("Time")) {
		printMainTime();
	    }
	    
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
	    /*
	    else if (input.equals("Memo")) {
		Memos C = new Memos();
		M.run();
		printMain();
	    }
	    */
	    else {
		System.out.println( "Invalid input, try again." );
	    }

	}
    }
    
}
