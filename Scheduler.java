import java.util.*;
import java.text.*;
import java.io.*;

public class Scheduler {
    
    private InputStreamReader inVal;
    private BufferedReader readVal;
    private boolean goToMain;

    public Scheduler(){
        inVal = new InputStreamReader(System.in);
        readVal = new BufferedReader(inVal);
        goToMain = true;
    }


    public void run() {
	
        while (goToMain) {

	    printMain();
	    
	    String input = "";
	    String input1 = "";
	    String input2 = "";
	    String input3 = "";
	    
            try {
                input = readVal.readLine();
            }
	    
            catch ( IOException e) {}

	    if (input.equals("Create")) {
		
		System.out.println("What date is the event? (YYYY-MM-DD)");
		
		try {
		    input1 = readVal.readLine();
		}
		
		catch ( IOException e) {}
		
		System.out.println("Summary of the event:");

                try {
                    input2 = readVal.readLine();
                }
		
                catch ( IOException e) {}

		System.out.println("Details of the event:");

                try {
                    input3 = readVal.readLine();
                }
		
                catch ( IOException e) {}

		//Write to file
		
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("events.csv", true)))) {
			out.println(input1 + "," + input2 + "," + input3);
		    }
		
		catch (IOException e) {}
		
		System.out.println("Event created!");
	    }
	    
	    else if (input.equals("Exit")) {
		goToMain = false;
	    }
	    
	    else {
		System.out.println("Invalid");
	    }
	}
    }
    
    public void print(String arg) {
        System.out.printf("|\t %-35s|", arg);
    }
    
    public void println(String arg) {
        System.out.printf("|\t %-35s|\n", arg);
    }
    
    public void println() {
        System.out.printf("|\t %-35s|\n", "");
    }
    
    public void printLine() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void printMain() {

	try (BufferedReader reader = new BufferedReader(new FileReader("events.csv"))) {
		
		ArrayList<String[]> events = new ArrayList<String[]>();
		String line = "";
		int counter = 0;
		while (((line = reader.readLine()) != null) && (counter < 3)) {
		    events.add(counter,line.split(","));
		    counter++;
		}
		
		//////////
		
		printLine();
		println();
		println("Upcoming Events");
		println();
		printLine();
		for (int capacity = 0;capacity < 3;capacity++) {
		    println();
		    println( events.get(capacity)[0] + " : " + events.get(capacity)[1] );
		    println();
		    printLine();
		}
	    }
	
	catch (IOException e) {}
	
    }
    
    
}