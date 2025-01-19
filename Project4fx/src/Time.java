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

public class Time implements Comparable<Time> {

//Init
	private int hours;
	private int mins;
	private String meridian;
	private int milHours;

//Construction
	public Time (int hours, int mins, String meridian) throws InvalidTime{
		checkTime (hours, mins, meridian);
		hoursToMil();
	}

	public Time (String inputString) throws InvalidTime{
		Scanner scanner = new Scanner(inputString);
		scanner.close();
		String[] parts = inputString.split(" ");
		String inputMeridian = parts[1];
		String[] timeParts = parts[0].split(":");
		int inputHours = Integer.valueOf(timeParts[0]);
		int inputMins = Integer.valueOf(timeParts[1]);
		checkTime (inputHours, inputMins, inputMeridian);
		hoursToMil();
	}

	public void hoursToMil() {
		if (meridian.equals("AM") && hours == 12 ){
			this.milHours = 0;
		}

		else if (meridian.equals("PM") && hours != 12) {
			this.milHours = hours + 12;
		}

		else {
			this.milHours = hours;
		}
	}

	public void checkTime (int hours, int mins, String meridian) throws InvalidTime{
		try {
			if (1 <= hours && hours <= 12 ) {
				this.hours = hours;
			}
			else {
				throw new InvalidTime("Input: \"" + hours +
										"hours\" is out of bounds.");
			}

			if ( 0 <= mins && mins <= 60 ) {
				this.mins = mins;
			}
			else {
				throw new InvalidTime("Input: \"" + mins +
										" mins\" is out of bounds.");
			}

			if ( meridian.equals("AM") || meridian.equals("PM")) {
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

//Methods
	@Override public int compareTo(Time o) {
		if(this.milHours > o.milHours) {
			return -1;
		}
		else if(this.milHours < o.milHours) {
			return 1;
		}
		else {
			if(this.mins > o.mins) {
				return -1;
			}
			else if(this.mins < o.mins) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}

	@Override public String toString() {
		String hourString = new DecimalFormat("00").format(hours);
		String minString = new DecimalFormat("00").format(mins);
		String timeString = hourString + ":" + minString + " " +
							meridian + milHours;
		return timeString;
	}
}
