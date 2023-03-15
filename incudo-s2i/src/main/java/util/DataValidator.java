package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The DataValidator class contains several static methods to validate data against
 * different types, such as Integer, Date, Alfanumeric, Disponibile, and String.
 *
 */

public class DataValidator {
	
	/**
	 * Validates if the given data matches the expected type.
	 * 
	 * @param expectedType A String representing the expected type of the data.
	 * @param data A String containing the data to be validated.
	 * @return A Boolean representing if the data is valid according to the expected type or not.
	 */
	
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
	
	/**
	 * Validates if the given data is a valid Integer equal or higher than 0.
	 * 
	 * @param data A String containing the data to be validated.
	 * @return A Boolean representing if the data is a valid Integer equal or higher than 0 or not.
	 */
	
	public static Boolean isValidInteger(String data) {
		
		Boolean valid = false;
		
			try {
				
			int num = Integer.parseInt(data);
			
			if (num >= 0) {
				valid = true;
			}
				
			} catch (NumberFormatException e) {
				
				// If data is not a number, valid will remain false.
				
				return valid;
			
			}
		
		return valid;
	}
	
	/**
	 * Validates if the given data is a valid Date in the format "dd/MM/yyyy".
	 * 
	 * @param data A String containing the data to be validated.
	 * @return A Boolean representing if the data is a valid Date or not.
	 */
	
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
	
	/**
	 * Validates if the given data is a valid Alfanumeric, meaning it only contains
	 * letters, numbers, and spaces. 
	 * 
	 * @param data A String containing the data to be validated.
	 * @return A Boolean representing if the data is a valid Alfanumeric or not.
	 */
	
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
	
	/**
	 * Validates if the given data is a valid Disponibile, meaning it is either "SI" or "NO".
	 *
	 * @param data A String containing the data to be validated.
	 * @return A Boolean representing if the data is a valid Disponibile or not.
	 */
	
	public static Boolean isValidDisponibile(String data) {
		
		Boolean valid = false;
		
		if (data.equals("SI") || data.equals("NO")) {
			
			valid = true;
		
		}
		
		return valid;
	}
	
	/**
	 * Validates if the given data is a valid String, meaning it is not null or blank.
	 * 
	 * @param data A String containing the data to be validated.
	 * @return A Boolean representing if the data is a valid String or not.
	 */
	
	public static Boolean isValidString(String data) {
		
		// Normal strings are not checked for errors as of now.
		
		return true;
	}
}
