import java.util.*;
import java.text.*;
import java.io.*;

public class Scheduler {
    
    private InputStreamReader inVal;
    private BufferedReader readVal;
    private boolean goToMain;

    public void run() {
        printMain();
	
        while (goToMain) {
	    
            String input = "";
	    
            try {
                input = readVal.readLine();
            }
	    
            catch ( IOException e) {}

	    if (input.equals("Create")) {
		System.out.println("What date is the event? (YYYY-MM-DD)");
		
		try {
		    input = readVal.readLine();
		}

		catch ( IOException e) {}

	}
    }

    public void printMain() {
        printLine();
        println();
        println();
        println();
        println();
        println();
        println();
        println();
        println();
        println();
        println();
        println();
        println();
        println();
        printLine();
    }