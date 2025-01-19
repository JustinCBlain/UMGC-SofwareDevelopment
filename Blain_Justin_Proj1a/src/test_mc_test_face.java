import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 

public class test_mc_test_face {

	public static void main(String[] args) {
	//Init
		LocalDateTime rn = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		
	//Output
    	System.out.println("Hello, one and all!");
    	System.out.println("Welcome back to another exciting episode of \'Hello World\'!");
    	System.out.println("I'm your humble programing language, Java\n");
    	
    	System.out.println("At the bell the time will be: " + dtf.format(rn));
	}
}
