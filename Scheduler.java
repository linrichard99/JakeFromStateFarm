import java.util.*;
import java.text.*;
import java.io.*;

public class Scheduler extends App implements ListApp{

    public void run() {
	
	printMain();
	
        while (goToMain) {

	    System.out.println("Available commands: Create, Delete, Details, Exit");
	    
	    String input = "";
	    	    
            try {
                input = readVal.readLine();
            }
	    
            catch ( IOException e) {}

	    if (input.equals("Create")) { //to create an event, a bunch of prompts will be asked
		create();
		printMain(); //This goes after so you can see the new event
	    }

	    else if (input.equals("Details")) {
		printMain(); //This goes before so that the details actually show up on the screen
		details();
	    }

	    else if (input.equals("Exit")) {
		goToMain = false;
	    }

	    else if (input.equals("Delete")) {
		delete();
		printMain();
	    }

	    else {
		System.out.println("Invalid");
	    }
	}
    }
    
    //interface implementations

    public void print(String arg) {
        System.out.printf("|\t %-35s|", arg);
    }
    
    public void println(String arg) {
        System.out.printf("|\t %-35s|\n", arg);
    }
    
    public void println() {
        System.out.printf("|\t %-35s|\n", "");
    }

    //display screen

    public void printMain() {

	clear();

	printLine();
	println();
	println( "Upcoming Events" );
	println();
	printLine();

	//read CSV file and display date and summary of event

	try (BufferedReader reader = new BufferedReader(new FileReader("events.csv"))) {
		
		String line = "";
		String[] print; //Not an array list because split() requires an actual array.
		int counter = 1;
		while ((line = reader.readLine()) != null) { //read CSV file line by line
		    print = line.split(",");
		    
		    String date = print[0];
		    String summary = truncate(print[1], 16);
		    
		    println();
		    println( counter + " : " + summary + " : " + date );
		    println();
		    printLine();
		    counter++;
		}
		
	    }
	
	catch (IOException e) {}
	
    }

    public void create() {

	String input1 = "";
	String input2 = "";
	String input3 = "";

	System.out.println("What date is the event? (YYYY-MM-DD)");

	try {
	    input1 = readVal.readLine();
	}
	catch ( IOException e) {}

	System.out.println("Summary of the event: (15 chars max)");
	
	try {
	    input2 = readVal.readLine();
	}
	catch ( IOException e) {}
	
	System.out.println("Details of the event: (Write as much as you want)");
	
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
    
    public void delete() {
	
        int input = 0;

        System.out.println( "Specify the number of an event:" );

        try {
            input = Integer.parseInt( readVal.readLine() );
        }
        catch ( IOException e) {}

	ArrayList<String> temp = new ArrayList<String>(); //this temp arraylist will hold everything in the old file (file to be replaced)

	try (BufferedReader reader = new BufferedReader(new FileReader("events.csv"))) {
		
		String line = "";
		int counter = 1;
		while ((line = reader.readLine()) != null) {
		    if ( counter != input ) { //Takes out part you're deleting
			temp.add(line);
		    }
		    counter++;
		}
	    }
	catch ( IOException e) {}
	
	try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("events.csv", false)))) {
		
		for (int index = 0;index < temp.size();index++) {
		    out.println(temp.get(index)); //transfer elements from temp to new file line by line
		}
	    }
	catch ( IOException e) {}
    }


    public void details() {
	
	int input = 0;
	
	System.out.println( "Specify the number of an event:" );
	
	try {
	    input = Integer.parseInt( readVal.readLine() );
	}
	catch ( IOException e) {}

	try (BufferedReader reader = new BufferedReader(new FileReader("events.csv"))) {
		
		String line = "";
                String[] print;
                int counter = 1;
                while ((line = reader.readLine()) != null) { //while loop is better because this boolean prevents String line to be null
                    if (counter == input) {
			print = line.split(",");
			System.out.println("Details of event " + input + ": " + print[2] );
		    }
		    counter++;
                }
	    }
	catch (IOException e) {}
    }
		
}
