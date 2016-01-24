import java.util.*;
import java.text.*;
import java.io.*;

public class Memos extends App implements ListApp{

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

	    else if (input.equals("Delete")) {
		delete();
		printMain();
	    }

	    else if (input.equals("Details")) {
		printMain(); //This goes before so that the details actually show up on the screen
		details();
	    }

	    else if (input.equals("Exit")) {
		goToMain = false;
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
	println( "Notes" );
	println();
	printLine();

	//read CSV file and display date and summary of event

	try (BufferedReader reader = new BufferedReader(new FileReader("memos.csv"))) {
		
		String line = "";
		String[] print;
		int counter = 1;
		while ((line = reader.readLine()) != null) { //read CSV file line by line
		    print = line.split("#@!!");
		    
		    String test = "";
		    for (int i = 0; i < print.length; i++) {
			test += print[i] + "  ";
		    }

		    //System.out.println(test);
		    
		    
		    String name = truncate(print[0], 10);
		    String details = truncate(print[1], 15);
		    println();
		    println( counter + " : " + name + " : " + details );
		    println();
		    printLine();
		    counter++;
		}
		
	    }
	
	catch (IOException e) {}
	
    }

    public void create() {

	String memoName = "";
	String memoNotes = "";

	System.out.println("What do you want to call your memo?");

	try {
	    memoName = readVal.readLine();
	}
	catch ( IOException e) {}

	System.out.println("You may write down your notes here:");
	
	try {
	    memoNotes = readVal.readLine();
	}
	catch ( IOException e) {}
	
	//Write to file                                                                                                                                                                                                       
	try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("memos.csv", true)))) {
		out.println(memoName + "#@!!" + memoNotes);
	    }
	catch (IOException e) {}
	
	System.out.println("Memo created!");
	
    }

    public void delete() {

        int input = 0;

        System.out.println( "Specify the number of a memo:" );

        try {
            input = Integer.parseInt( readVal.readLine() );
        }
        catch ( IOException e) {}

        ArrayList<String> temp = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader("memos.csv"))) {

                String line = "";
                int counter = 1;
                while ((line = reader.readLine()) != null) {
                    if ( counter != input ) {
                        temp.add(line);
                    }
                    counter++;
                }
            }
        catch ( IOException e) {}

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("memos.csv", false)))) {

                for (int index = 0;index < temp.size();index++) {
                    out.println(temp.get(index));
                }
            }
        catch ( IOException e) {}
    }
    
    public void details() {
	int input = 0;
	
	System.out.println( "Specify the number of a memo:" );
	
	try {
	    input = Integer.parseInt( readVal.readLine() );
	}
	catch ( IOException e) {}

	try (BufferedReader reader = new BufferedReader(new FileReader("memos.csv"))) {
		
		String line = "";
                String[] print;
                int counter = 1;
                while ((line = reader.readLine()) != null) { //while loop is better because this boolean prevents String line to be null
                    if (counter == input) {
			print = line.split("#@!!");
			System.out.println("Here's what you wrote: \n" + print[1] );
		    }
		    counter++;
                }
	    }
	catch (IOException e) {}
    }
    
}
