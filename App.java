import java.util.*;
import java.text.*;
import java.io.*;

public abstract class App {

    protected InputStreamReader inVal; //input stream
    protected BufferedReader readVal; //buffered input stream
    protected boolean goToMain; //flag that allows the PDA to constantly run

    protected boolean mischief = false; //This is for when users try to input things that aren't allowed. In these cases, we have to print an error message. Sometimes, due to the fact that our code clears the screen each time the main screen is shown, that error message isn't seen. This way, we can still reprimand the user for trying to mess with the code.
    
    public App(){
	inVal = new InputStreamReader(System.in);
        readVal = new BufferedReader(inVal);
        goToMain = true;
    }
    
    public void print(String arg) {
	System.out.print(arg);
    }
    
    public void println(String arg) {
	System.out.println(arg);
    }
    
    public void println() {
	System.out.println();
    }
    
    public void printLine() {
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void clear() {
	System.out.println("\u001b[2J\u001b[H"); //Clears screen. Creds to Shantanu
    }

    public abstract void run();
    
    public void printMain() { //This isn't abstract since some of the subclasses' printMain() overload it instead of overriding it. This means they would have to change the parameters, which causes a problem is it is abstract (since it doesn't allow for it).
	System.out.println("Override/overload this method please!");
    }


    /*
      THE FOLLOWING ARE METHODS USED BY DIFFERENT APPS THAT DON'T MERIT THEIR OWN SUPERCLASS SINCE ONLY A FEW DIFFERENT APPS USE THEM
    */
    
    //This is used in displaying text that is perhaps too long
    public String truncate(String arg, int finVal) {
	if (arg.length() < finVal) {
	    return arg;
	}
	else {
	    return arg.substring(0,finVal-3) + "...";
	}
    }

    public Date stringToDate(String inDate) { //The date is a string because its fomatted                             
        //To parse a specfic date, I had to google it.                          
        //Help from: http://stackoverflow.com/questions/5270272/how-to-determine-day-of-week-by-passing-specific-date
	
        SimpleDateFormat toBeParsed = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = new Date();
        try {
            parsedDate = toBeParsed.parse(inDate); //Translates string format into an actual Date object
	    
        }
	
        catch (ParseException e) {}
	
        return parsedDate;
    }

    public boolean checkDate(String inDate) {
	
        //The pull from events.csv part
	
        ArrayList<String> listDates = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader("events.csv"))) {

                String line = "";
                String[] values;
                while ((line = reader.readLine()) != null) {
                    values = line.split("#@!!");
                    listDates.add( values[0] );
                }
            }
        catch (IOException e) {}

        //The linear search part
	for (int i = 0; i < listDates.size(); i++) {
            if (inDate.equals(listDates.get(i))) {
                return true;
            }
        }

        return false;
    }

    
}
