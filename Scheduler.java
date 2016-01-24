import java.util.*;
import java.text.*;
import java.io.*;

public class Scheduler extends App implements ListApp{
    
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
		
		//User reprimandation
		if (mischief) {
		    System.out.println("Follow instructions please"); //Slightly passive agressive message to really get the point across.
		    mischief = false;
		}
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

	order();

	//read CSV file and display date and summary of event

	try (BufferedReader reader = new BufferedReader(new FileReader("events.csv"))) {
		
		String line = "";
		String[] print; //Not an array list because split() requires an actual array.
		int counter = 1;
		while ((line = reader.readLine()) != null) { //read CSV file line by line. We stop when the line hits a null. 
		    print = line.split("#@!!"); //This is to prevent user tampering. It's not entirely fool proof, but unless the reader reads our code, they statistically will probably never type #@!!
		    
		    
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

    
    public void selectionSortV( ArrayList<String> data ) //helper for order()
    {
        for ( int index = 0 ; index < data.size() - 1 ; index++ ) {
            String smallest = data.get(index);
            int indexOfSmallest = index;
            for ( int index2 = index + 1 ; index2 < data.size() ; index2++ ) { //search for smallest element
		Date date1 = stringToDate(data.get(index2).substring(0,10)); //Date is a Comparable. How nice!
		if ( date1.compareTo(stringToDate(smallest.substring(0,10))) < 0 ) {
		    smallest = data.get(index2);
                    indexOfSmallest = index2;
                }
            }
            String temp = data.get(index); //swaperoo
	    
            data.set(index,smallest);
            data.set(indexOfSmallest,temp);
        }
    }//end selectionSort 

    /*We decided not to make order() an inherited method since it utilizes selectionSortV(), which is different between the two apps that utilize order(). Thus, in order to make order() an inherited method, we would also have to make an abstract selectionSortV(). If we put that abstract method in App.java, it would force all the other apps to implement it, even though they don't need it. The other option would be to make another superclass below Apps, but above this. That would overcomplciate the tree for essentially just a single method, which is not worth it. 
     */
    public void order() { //rearranges information in csv file according to date
	
        ArrayList<String> temp = new ArrayList<String>(); //this temp arraylist will hold everything in the old file (file to be replaced)

	//This gets the old file into temp
        try (BufferedReader reader = new BufferedReader(new FileReader("events.csv"))) {

                String line = "";
                int counter = 1;
                while ((line = reader.readLine()) != null) {
		    temp.add(line);
		}
		counter++;
	    }
        catch ( IOException e) {}

	//Sorting temp
	selectionSortV( temp );

	//And now we print into a new file the sorted values
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("events.csv", false)))) {
		
                for (int index = 0;index < temp.size();index++) {
                    out.println(temp.get(index)); //transfer elements from temp to new file line by line                                                                                                                                                                      
                }
            }
        catch ( IOException e) {}
    }


    public void create() {

	String date = "";
	String summary = "";
	String deetz = "";

	System.out.println("What date is the event? (YYYY-MM-DD)");
	System.out.print("> ");
	
	try {
	    date = readVal.readLine();

	    //This doesn't serve any purpose other than seeing if the user actually entered a Date. It utilizes a snippet of code from the checkDate() method in App.java.
	    
	    SimpleDateFormat toBeParsed = new SimpleDateFormat("yyyy-MM-dd");
	    Date check = new Date();
            check = toBeParsed.parse(date); //Essentially, if the date can't be parsed because it isn't formatted correctly, the catch block is triggered    
	}
	
	catch ( IOException|ParseException e) {
	    mischief = true; //This turns the mischief on
	    return; //And breaks out of the loop.
	}

	System.out.println("Summary of the event: (15 chars max)");
	System.out.print("> ");
	
	try {
	    summary = readVal.readLine();
	}
	catch ( IOException e) {}
	
	System.out.println("Details of the event: (Write as much as you want)");
	System.out.print("> ");
	
	try {
	    deetz = readVal.readLine();
	}
	catch ( IOException e) {}
	
	//Write to file
	
	try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("events.csv", true)))) {
		out.println(date + "#@!!" + summary + "#@!!" + deetz);
	    }
	catch (IOException e) {}
	
	System.out.println("Event created!");
	
    }
    
    public void delete() {
	
        int input = 0;

        System.out.println( "Specify the number of an event:" );
	System.out.print("> ");

        try {
            input = Integer.parseInt( readVal.readLine() );
        }
        catch ( IOException e) {}

	//Very similar to ordering, except instead of sorting the temp file, we remove one line.
	
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
	System.out.print("> ");
	
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
			print = line.split("#@!!");
			System.out.println("Details of event " + input + ": " + print[2] );
		    }
		    counter++;
                }

	    }
	catch (IOException e) {}
    }
		
}
