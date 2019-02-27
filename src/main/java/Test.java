import java.util.Date;

public class Test {
	@SuppressWarnings("deprecation")
	public static void main(String args[]) {
		Date date = new Date();
		System.out.println(date.getSeconds()); 
		System.out.println(date.getMinutes()); 
		System.out.println(date.getHours()); 
		System.out.println(date.getDate()); 
		System.out.println(date.getMonth()); 
		
		int id = date.getSeconds()*60*24*31*12*300 + date.getMinutes()*24*31*12*300 + date.getHours()*31*12*300 + date.getDate()*12*300 + date.getMonth()*300 + date.getYear();
		System.out.println(id);
	}
}
