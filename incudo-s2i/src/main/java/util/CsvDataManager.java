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



public class CsvDataManager {
	
	public ArrayList<String[]> loadFromCsv(String csvFile, String[] fieldsTable) {
		// TODO Auto-generated method stub
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
