package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataValidator {
	
	public static Boolean isValidData(String expectedType, String data) {
		
		Boolean valid = false;
		
		if (data != null && !data.isBlank()) {
			
			switch (expectedType) {
			
			case "Integer":

				valid =	isValidInteger(data);
				break;
				
			case "Date":

				valid =	isValidDate(data);
				break;
						
			case "Alfanumeric":

				valid =	isValidAlfanumeric(data);
				break;
			
			case "Disponibile":

				valid =	isValidDisponibile(data);
				break;
			
			case "String":

				valid =	isValidString(data);
				break;
			
			default:
				valid = false;
			}
			
		} 
		
		return valid;
		
	}
	
	public static Boolean isValidInteger(String data) {
		
		Boolean valid = false;
		
			try {
				
			int num = Integer.parseInt(data);
			
			if (num > 0) {
				valid = true;
			}
				
			} catch (NumberFormatException e) {
				
				// If data is not a number, valid will remain false.
				
				return valid;
			
			}
		
		return valid;
	}
	
	public static Boolean isValidDate(String data) {
		
		Boolean valid = false;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			
			// If the following date is not parsed correctly, I will catch the error
			
			LocalDate dateToCheck = LocalDate.parse(data, formatter); 
				
			valid = true;
		
		} catch(DateTimeParseException e) {
			
			valid = false;
			
		} 
		
		return valid;
	}
	
	public static Boolean isValidAlfanumeric(String data) {
		
		Boolean valid = false;
		
		boolean hasSpecialCharacters = data.matches(".*[^a-zA-Z0-9 ].*");
		
		if (hasSpecialCharacters) {
			valid = false;
		} else {
			valid = true;
		}
		
		return valid;
	}
	
	public static Boolean isValidDisponibile(String data) {
		
		Boolean valid = false;
		
		if (data.equals("SI") || data.equals("NO")) {
			
			valid = true;
		
		}
		
		return valid;
	}
	
	public static Boolean isValidString(String data) {
		
		// Normal strings are not checked for errors as of now.
		
		return true;
	}
}
