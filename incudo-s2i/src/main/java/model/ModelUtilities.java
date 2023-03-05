package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ModelUtilities {

	static Map<String, Boolean> itaToBoolMap = new HashMap<>();
	
	static {
		itaToBoolMap.put("SI", true);
		itaToBoolMap.put("NO", false);
	}
	
	
	static LocalDate StringToDate(String StringDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate date = LocalDate.parse(StringDate, formatter);

		return date;
	}
		
	
}
