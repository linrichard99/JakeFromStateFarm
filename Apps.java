import java.util.*;
import java.text.*;
import java.io.*;

public abstract class Apps {

    protected InputStreamReader inVal; //input stream
    protected BufferedReader readVal; //buffered input stream
    protected boolean goToMain; //flag that allows the PDA to constantly run

    public Apps(){
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
    
    public abstract void printMain();
    
    
}
