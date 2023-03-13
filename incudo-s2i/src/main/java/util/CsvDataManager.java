package util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;

/**
 * The CsvDataManager class provides methods for loading data from a CSV file and writing data to a CSV file.
 */

public class CsvDataManager {
	
	/**
	 * Load data from a CSV file.
	 * 
	 * @param csvFile the name of the CSV file to load data from
	 * @param fieldsTable a two-dimensional array of strings containing the names of the fields to load from the CSV file
	 * @return an ArrayList of string arrays, where each string array represents a row of data from the CSV file
	 */
	
	public ArrayList<String[]> loadFromCsv(String csvFile, String[][] fieldsTable) {
			
		String csvPath = "src\\main\\resources\\" + csvFile;
        ArrayList<String[]> table = new ArrayList<String[]>();
        
        try {

            FileReader filereader = new FileReader(csvPath);
      
            CSVParser parser = new CSVParserBuilder()
            						.withSeparator(';')
            						.withQuoteChar('"')
            						.withIgnoreQuotations(false)
            						.build();
      
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                                      .withCSVParser(parser)
                                      .withSkipLines(1)
                                      .build();
      

            List<String[]> allData = csvReader.readAll();
      
            for (String[] row : allData) {

            	if (row[0] == "") continue; 
   
                String[] fieldsArr = new String[fieldsTable.length];
                
                for (int i = 0; i < fieldsTable.length; i++) {
                	fieldsArr[i] = row[i];
                }
                
                table.add(fieldsArr);
            }
           
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return table;
	}
	
	/**
	 * Write data to a CSV file.
	 * 
	 * @param headers an array of strings containing the headers for the CSV file
	 * @param data an ArrayList of string arrays containing the data to write to the CSV file
	 */
	
	public void writeCsv(String[] headers, ArrayList<String[]> data) {
		
		LocalDate lt = LocalDate.now();
		DateTimeFormatter formatterFilename = DateTimeFormatter.ofPattern("dd_MM_yyyy");
		String dateFileFormat = lt.format(formatterFilename);
		String filePath = "prenotazioni_disponibili_" + dateFileFormat + ".csv";
        
		try {
			ICSVWriter csvWriter = new CSVWriterBuilder(new FileWriter(filePath))
                    .withSeparator(';')
                    .build();
		

        // write header
        csvWriter.writeNext(headers);

        // write rows
        
        for (String[] row : data) {
        	csvWriter.writeNext(row);
        }

        csvWriter.close();
        
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
}
