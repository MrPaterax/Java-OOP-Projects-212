import java.time.LocalDate;
import java.time.DateTimeException;

 class Date212 {
 	private int year;
 	private int month;
 	private int day;
 	boolean validDate;
 	String date;
 	
 	public Date212(String date) {
 		year = Integer.parseInt(date.substring(0,4)); //All strings will have the same format so we can separate them based on their position in the string using substring.
 		month = Integer.parseInt(date.substring(4,6));
 		day = Integer.parseInt(date.substring(6));
 		this.date = date;
        validDate = isItValid();
        if(!validDate && date != "0000000") throw new Date212Exception(date); //throw our Date212Exception if the date is false thus true and not our dummy node.
 	}

 	private boolean isItValid() { //Checks for validity of dates. 
 		boolean isDateValid = true;
 		try {
 			LocalDate.of(year, month, day); //Uses LocalDate class' static method to verify if date is valid, otherwise we catch the exception and know that this is not a valid date.  
 		}
 		catch(DateTimeException e){
 			isDateValid = false;
 		}
 		return isDateValid;
 	}

 	@Override
 	public String toString() {
 		String year = Integer.toString(this.year); //Take our instance variables and convert them to their appropriate strings. 
 		String month = Integer.toString(this.month);
 		String day = Integer.toString(this.day);
 		return month + "/" + day + "/" + year;
 	}

}