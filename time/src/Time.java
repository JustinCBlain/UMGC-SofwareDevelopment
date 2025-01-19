/* Paste your Exercise10_01 here and click Automatic Check.
For all programming projects, the numbers should be double 
unless it is explicitly stated as integer.
If you get a java.util.InputMismatchException error, check if 
your code used input.nextInt(), but it should be input.nextDouble().
For integers, use int unless it is explicitly stated as long. */

public class Time {

	private int hour;
	private int minute;
	private int second;
	
	public Time() {
		setTime(System.currentTimeMillis());
	}
	
	public Time(int hour, int minute, int second) {
    this.hour = hour;
    this.minute = minute;
    this.second = second;
	}
		
	public Time(long totalMS) {
		this.hour = (totalMS/1440000)%24;
		this.minute = (totalMS/60000)%60;
		this.second = (totalMS/1000)%60;
	}

	public int getHour() {
		return this.hour;
	}
		
	public int getMinute() {
		return this.minute;
	}
		
	public int getSecond() {
		return this.second;
	}
		
	public void setTime(long elapseTime) {
	 	this.hour = (elapseTime/1440000)%24;
		this.minute = (elapseTime/60000)%60;
		this.second = (elapseTime/1000)%60;
	}
		
}
