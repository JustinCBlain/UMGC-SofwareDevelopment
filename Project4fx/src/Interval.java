/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 2.29.2024

What is the space between time?
Interval Class
*/

public class Interval<T extends Comparable<T>>{

//Init
	private T start;
	private T end;

//Constructor
	public Interval(T start, T end) {
		this.start = start;
		this.end = end;
	}

//Methods
	public boolean within (T test) {
		 return start.compareTo(test) >= 0 && end.compareTo(test) <= 0;
	}

	public boolean subInterval (Interval<T> test) {
		return this.within(test.start) && this.within(test.end);
	}

	public boolean overlaps (Interval<T> test) {
		return this.within(test.start) || this.within(test.end);
	}
}
