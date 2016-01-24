import java.util.*;
import java.text.*;
import java.io.*;

public class Contacts extends App implements ListApp{

    private final String VALIDPHONE = "0123456789#"; //Valid phone number values
    private final String ALPHABET = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz"; //The alphabet in alphabetical order
    
    public void run() {
	
	printMain();
	
        while (goToMain) {

	    System.out.println("Available commands: Create, Delete, Details, Exit");
	    System.out.print("> ");
	    
	    String input = "";
	    	    
            try {
                input = readVal.readLine();
            }
	    
            catch ( IOException e) {}

	    if (input.equals("Create")) { //to create an event, a bunch of prompts will be asked
		create();
		printMain(); //This goes after so you can see the new event
		if (mischief) {
		    System.out.println("Input data properly");
		}
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
	println( "Contacts" );
	println();
	printLine();

	order();

	//read CSV file and display contacts

	try (BufferedReader reader = new BufferedReader(new FileReader("contacts.csv"))) {
		
		String line = "";
		String[] print;
		int counter = 1;
		while ((line = reader.readLine()) != null) { //read CSV file line by line
		    print = line.split("#@!!");

		    String fName = truncate(print[0], 11);
		    String lName = truncate(print[1], 11);		    
			
		    println();
		    println( counter + " : " + fName + " " + lName);
		    println();
		    printLine();
		    counter++;
		}
		
	    }
	
	catch (IOException e) {}
	
    }

    public void selectionSortV( ArrayList<String> data ) { //helper for order()
	
        for ( int index = 0 ; index < data.size() - 1 ; index++ ) {
            String smallest = data.get(index);
            int indexOfSmallest = index;
            for ( int index2 = index + 1 ; index2 < data.size() ; index2++ ) { //search for smallest element
		
                String letter1 = data.get(index2).substring(0,1);
                if ( ALPHABET.indexOf(letter1) < ALPHABET.indexOf(smallest.substring(0,1)) ) { //We utilize the index of the letters in ALPHABET to compare values. Interestingly, if there are symbols, then they go all the way in the front (index value of -1), which is a fast and clean way to deal with them. 
                    smallest = data.get(index2);
                    indexOfSmallest = index2;
                }
            }
            String temp = data.get(index); //swaperoo
	    
            data.set(index,smallest);
            data.set(indexOfSmallest,temp);
        }
    }//end selectionSort -- Gotta love using old hw

    /*We decided not to make order() an inherited method since it utilizes selectionSortV(), which is different between the two apps that utilize order(). Thus, in order to make order() an inherited method, we would also have to make an abstract selectionSortV(). If we put that abstract method in App.java, it would force all the other apps to implement it, even though they don't need it. The other option would be to make another superclass below Apps, but above this. That would overcomplciate the tree for essentially just a single method, which is not worth it. 
     */
    
    public void order() { //rearranges information in csv file according to alphabet
	
        ArrayList<String> temp = new ArrayList<String>(); //this temp arraylist will hold everything in the old file (file to be replaced)
	
        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.csv"))) {

                String line = "";
                int counter = 1;
                while ((line = reader.readLine()) != null) {
                    temp.add(line);
                }
                counter++;
            }
        catch ( IOException e) {}

        selectionSortV( temp );
	
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contacts.csv", false)))) {

                for (int index = 0;index < temp.size();index++) {
                    out.println(temp.get(index)); //transfer elements from temp to new file line by line \
		    
                }
            }
        catch ( IOException e) {}
    }

    public void create() {

	String fName = "";
	String lName = "";
	String email = "";
	String phone = "";

	System.out.println("First Name");
	System.out.print("> ");

	try {
	    fName = readVal.readLine();
	}
	catch ( IOException e) {}

	System.out.println("Last Name");
	System.out.print("> ");
	
	try {
	    lName = readVal.readLine();
	}
	catch ( IOException e) {}
	
	System.out.println("Email");
	System.out.print("> ");
	
	try {
	    email = readVal.readLine();
	    
	    if ((email.indexOf("@") < 0) || !(email.substring(email.length()-4, email.length()-3).equals("."))) { //Checks if email has @ symbol and domain
		mischief = true;
		return;
	    }
	}
	catch ( IOException e) {}
	
	
	System.out.println("Phone Number");
	System.out.print("> ");
	try {
	    phone = readVal.readLine();
	    for (int i = 0; i < phone.length(); i++) {
		if (VALIDPHONE.indexOf(phone.substring(i,i+1)) < 0) {
		    mischief = true;
		    return;
		}
	    }
	}
	catch ( IOException e) {}

	//Write to file                 
	
	try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contacts.csv", true)))) {
		out.println(fName + "#@!!" + lName + "#@!!" + email + "#@!!" + phone);
	    }
	catch (IOException e) {}
	
	System.out.println("Contact saved!");
	
    }
    
    public void delete() {

        int input = 0;

        System.out.println( "Specify the number of a contact:" );
	System.out.print("> ");

        try {
            input = Integer.parseInt( readVal.readLine() );
        }
        catch ( IOException e) {}

        ArrayList<String> temp = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.csv"))) {

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

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contacts.csv", false)))) {

                for (int index = 0;index < temp.size();index++) {
                    out.println(temp.get(index));
                }
            }
        catch ( IOException e) {}
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
			print = line.split("#@!!");
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
