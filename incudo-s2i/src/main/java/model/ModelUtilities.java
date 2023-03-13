package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for the models.
 */

public class ModelUtilities {

	/**
     * Map used by the models to convert Italian SI or NO found in the tables data to its boolean counterpart.
     */	
	
	static Map<String, Boolean> itaToBoolMap = new HashMap<>();
	
	static {
		itaToBoolMap.put("SI", true);
		itaToBoolMap.put("NO", false);
	}
	
	/**
     * Converts a string to a date.
     *
     * @param stringDate the date in string format
     * @return the converted date
     */
	
	static LocalDate StringToDate(String StringDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate date = LocalDate.parse(StringDate, formatter);

		return date;
	}
		
	
}
