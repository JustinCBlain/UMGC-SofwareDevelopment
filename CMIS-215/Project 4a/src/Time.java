/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 2.29.2024

What is time?
Time Class
*/

//Import
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Time implements Comparable<Time> {
			
//Init		
	private int hours;
	private int mins;
	private String meridian;

	public Time (int hours, int mins, String meridian) throws InvalidTime{
		try {
			if (1 <= hours && hours <= 12 ) {
				this.hours = hours;
			}
			else {
				throw new InvalidTime("Input: \"" + hours + "hours\" is out of bounds.");
			}
			
			if ( 0 <= mins && mins <= 60 ) {
				this.mins = mins;
			}
			else {
				throw new InvalidTime("Input: \"" + mins + " mins\" is out of bounds.");
			}
			
			if ( meridian == "AM" || meridian == "PM" ) {
				this.meridian = meridian;
			}
			else {
				throw new InvalidTime("Input: \"" + meridian + "\"is invalid.");
			}
		
		}
		catch (InvalidTime ex) {
			ex.printStackTrace();
		}
	}
	
	public Time (String inputString) throws InvalidTime{
		Scanner scanner = new Scanner(inputString);
		try	{
			int inputHours = scanner.nextInt();
			scanner.skip(Pattern.compile(":"));
			int inputmins = scanner.nextInt();
			String inputMeridian = scanner.nextLine();
			//call othe constructor
		}
		//throw error
		catch (InvalidTime ex) {
			ex.printStackTrace();
		}
	}
		
		
		
	public int getHours() {
		return hours;
	}

	public int getMins() {
		return mins;
	}

	public String getMeridian() {
		return meridian;
	}
	
	@Override public int compareTo(Time o) {
		if(this.meridian == "AM" && o.meridian == "PM") {
			return 1;
		}
		else if(this.meridian == "PM" && o.meridian == "AM") {
			return -1;
		}
		else {
			if(this.hours > o.hours) {
				return 1;
			}
			else if(this.hours < o.hours) {
				return -1;
			}
			else {
				if(this.mins > o.mins) {
					return 1;
				}
				else if(this.mins < o.mins) {
					return -1;
				}
				else {
					return 0;
				}
			}
		}
	}
	
	@Override public String toString() {
		String hourString = new DecimalFormat("00").format(hours);
		String minString = new DecimalFormat("00").format(mins);
		String timeString = hourString + ":" + minString + " " + meridian;
		return timeString;
	}
	// TODO implement comparable interface

	// TODO public
		// TODO constructor for int int str - cheif in beteen 1-12 and 0-59  and am or pm, thro invalid time time error with specific reason
		// TODO constructor for string (HH:MM AM)- call the above after breaking down input hours and minutes are numeric
		// TODO to string (hh:mm am)
		// TODO 

		}
