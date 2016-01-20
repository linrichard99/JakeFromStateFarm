import java.util.*;
import java.text.*;
import java.io.*;

public class Contacts extends App implements ListApp{

    public void run() {
	
	printMain();
	
        while (goToMain) {

	    System.out.println("Available commands: Create, Details, Exit");
	    
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

	printLine();
	println();
	println( "Contacts" );
	println();
	printLine();

	//read CSV file and display date and summary of event

	try (BufferedReader reader = new BufferedReader(new FileReader("contacts.csv"))) {
		
		String line = "";
		String[] print;
		int counter = 1;
		while ((line = reader.readLine()) != null) { //read CSV file line by line
		    print = line.split(",");
		    println();
		    println( counter + " : " + print[0] + " " + print[1]);
		    println();
		    printLine();
		    counter++;
		}
		
	    }
	
	catch (IOException e) {}
	
    }

    public void create() {

	String fName = "";
	String lName = "";
	String email = "";
	String phone = "";

	System.out.println("First Name");

	try {
	    fName = readVal.readLine();
	}
	catch ( IOException e) {}

	System.out.println("Last Name");
	
	try {
	    lName = readVal.readLine();
	}
	catch ( IOException e) {}
	
	System.out.println("Email");
	
	try {
	    email = readVal.readLine();
	}
	catch ( IOException e) {}
	
	
	System.out.println("Phone Number");
	
	try {
	    phone = readVal.readLine();
	}
	catch ( IOException e) {}

	//Write to fil                 
	
	try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contacts.csv", true)))) {
		out.println(fName + "," + lName + "," + email + "," + phone);
	    }
	catch (IOException e) {}
	
	System.out.println("Contact saved!");
	
    }
    
    public void details() {
	int input = 0;
	
	System.out.println( "Specify the place of the contacts (Number on Left):" );
	
	try {
	    input = Integer.parseInt( readVal.readLine() );
	}
	catch ( IOException e) {}

	try (BufferedReader reader = new BufferedReader(new FileReader("contacts.csv"))) {
		
		String line = "";
                String[] print;
                int counter = 1;
                while ((line = reader.readLine()) != null) { //while loop is better because this boolean prevents String line to be null
                    if (counter == input) {
			print = line.split(",");
			System.out.println("Name: "+ print[0] + " " + print[1] );
			System.out.println("Email: " + print[2]);
			System.out.println("Phone Number: " + print[3]);
		    }
		    counter++;
                }
	    }
	catch (IOException e) {}
    }
		
}
