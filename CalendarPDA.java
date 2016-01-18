import java.util.*;
import java.text.*;
import java.io.*;

public class CalendarPDA implements Printer {

    public static void main(String[] arg) {
	CalendarPDA test = new CalendarPDA();

	test.print("01");
	test.println();
	test.println();
	test.printLine();

	System.out.println(test.startMonthHere("2016-01-01"));
	System.out.println(test.startMonthHere("2016-01-19"));
	System.out.println(test.startMonthHere("2016-01-20"));
	System.out.println(test.startMonthHere("2016-01-21"));
	System.out.println(test.startMonthHere("2016-01-22"));
	System.out.println(test.startMonthHere("2016-01-23"));
	System.out.println(test.startMonthHere("2016-01-24"));

	System.out.println(test.daysOfMonth("01", 2016));
	System.out.println(test.daysOfMonth("02", 2016));
	System.out.println(test.daysOfMonth("04", 2026));

	System.out.println();
	test.displayCalendar("01", 2016);

	
	System.out.println();
	test.displayCalendar("04", 2016);
	
    }
    
    public void print(String arg) {
	System.out.print("| " + arg);
    }

    public void println(String arg) {
	System.out.println(arg);
    }

    public void println() {
	System.out.println();
    }

    public void printLine() {
	System.out.println("-----------------------------");
    }

    public String dayOfWeek(String inDate) { //The date is a string because its fomatted
	//To parse a specfic date, I had to google it.
	//Help from: http://stackoverflow.com/questions/5270272/how-to-determine-day-of-week-by-passing-specific-date
	
	SimpleDateFormat toBeParsed = new SimpleDateFormat("yyyy-MM-dd");
	Date parsedDate = new Date();
	try {
	    parsedDate = toBeParsed.parse(inDate);
	}
	
	catch (ParseException e) {}
	
	SimpleDateFormat display = new SimpleDateFormat("EE");
	String dayOfWeek = display.format(parsedDate);

	return dayOfWeek;
    }

    public int startMonthHere(String inDate) {
	String dayVal = dayOfWeek(inDate);

	if (dayVal.equals("Sun")) {
	    return 0;
	}
	else if (dayVal.equals("Mon")) {
	    return 1;
	}
	else if (dayVal.equals("Tue")) {
	    return 2;
	}
	else if (dayVal.equals("Wed")) {
	    return 3;
	}
	else if (dayVal.equals("Thu")) {
	    return 4;
	}
	else if (dayVal.equals("Fri")) {
	    return 5;
	}
	else {
	    return 6;
	}
    }

    public int daysOfMonth(String month, int year) { //Month is a string to faciliatate easier storage of long and short months.
	String longDays = "01 03 05 07 08 10 12"; //Need the 0's in order to prevent any mis-finds
	String shortDays = "04 06 09 11";
	 
	if (longDays.indexOf(month) > -1) {
	    return 31;
	}

	else if (shortDays.indexOf(month) > -1) {
	    return 30;
	}

	else {

	    //Thank you wikipedia for the rules of leap years
	    if ((year % 4) != 0) {
		return 28;
	    }

	    else if ((year % 100) != 0) {
		return 29;
	    }

	    else if ((year % 400) != 0) {
		return 28;
	    }

	    else {
		return 29;
	    }
	}
	
    }

    public void printCalBody(String month, int year) {
	
	//This formates the first date of the month/year specified because startMonthHere requires it
	String firstDate = year + "-" + month + "-" + "01";

	//Keeps track of when to go to next line (it goes from 0->6)
	int dateCtr = 0; 

	//BUG TESTS IGNORE
	//System.out.println(firstDate);
	//System.out.println(startMonthHere(firstDate));

	//This prints the empty spots before the firstDate starts
	for (int j = 0; j < startMonthHere(firstDate); j++) {
	    print("  ");
	    dateCtr++;
	}

	//This prints the date
	for (int i = 1; i <= daysOfMonth(month, year); i++) {

	    //Keeping track of when to go to next week
	    if (dateCtr > 6) { //Resets when you hit 6.
		System.out.print("|");
		println();
		printLine();
		dateCtr = 0;
	    }

	    //This is for spacing the numbers correctly
	    if (i < 10) {
		print("0" + i);
	    }
	    else {
		print("" + i);
	    }

	    //Advance the dateCtr
	    dateCtr++;
	}

	//Printing the final empty slots
	for (int j = dateCtr; j < 7; j++) {
	    print("  ");
	}

	//The last border value
	System.out.println("|");
	
    }

    public void displayCalendar(String month, int year){//Month is string so fit the daysOfMonth method better

	//This prints header
	printLine();
	println("Month: " + month + "\tYear: " +  year);
	printLine();
	println("|SUN|MON|TUE|WED|THU|FRU|SAT|");
	printLine();

	//This prints body
	printCalBody(month,year);
	
    }
     
	

}
