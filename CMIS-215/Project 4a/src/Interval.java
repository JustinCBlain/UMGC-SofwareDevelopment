/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 2.29.2024

What is the space between time?
Interval Class
*/

//Import
public class Interval<T extends Comparable<T>>
implements Comparable<Interval<T>>{
	
	private Object start;
	private Object end;
	
	public Interval(Object start, Object end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Object getStart() {
		return start;
	}

	public Object getEnd() {
		return end;
	}
	
	public <T> boolean within (T start end)
	

	@Override
	public int compareTo(Interval<T> o) {
		return getT().compareTo(o.getT());
	}
	
	
	// TODO generic class
	// TODO defined for any typ that imlements the comparable interface
	// TODO immutable
	// TODO no setters
	// TODO public
		// TODO constructor accepts start and end Time
		// TODO within mehtod (genwric typ parameter)
		// TODO subinterval (passed an interval)- true if entirely within super inerval
		// TODO overlaps - (passed an interval) - true if over laps
	

}
