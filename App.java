import java.util.*;
import java.text.*;
import java.io.*;

public abstract class App {

    protected InputStreamReader inVal; //input stream
    protected BufferedReader readVal; //buffered input stream
    protected boolean goToMain; //flag that allows the PDA to constantly run

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

    public abstract void run();
    
    public void printMain() { //This isn't abstract since some of the subclasses' printMain() overload it instead of overriding it. This means they would have to change the parameters, which causes a problem is it is abstract (since it doesn't allow for it).
	System.out.println("Override/overload this method please!");
    }

    //This is used in displaying text that is perhaps too long
    public String truncate(String arg, int finVal) {
	if (arg.length() < finVal) {
	    return arg;
	}
	else {
	    return arg.substring(0,finVal-3) + "...";
	}
    }
    
}
