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
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;



public class EstrattoreDati {
	
	public ArrayList<String[]> caricaDaCsv(String csvFile, String[] campiTabella) {
		// TODO Auto-generated method stub
		
		String csvPath = "src\\main\\resources\\" + csvFile;
        ArrayList<String[]> tabella = new ArrayList<String[]>();
        
        
        try {
            // Create an object of file reader class with CSV file as a parameter.
            FileReader filereader = new FileReader(csvPath);
      
            // create csvParser object with
            // custom separator semi-colon
            CSVParser parser = new CSVParserBuilder()
            						.withSeparator(';')
            						.withQuoteChar('"')
            						.withIgnoreQuotations(false)
            						.build();
      
            // create csvReader object with parameter
            // filereader and parser
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                                      .withCSVParser(parser)
                                      .withSkipLines(1)
                                      .build();
      
            // Read all data at once
            List<String[]> allData = csvReader.readAll();
      
            // Print Data.
            for (String[] row : allData) {

            	if (row[0] == "") continue; 
   
                String[] fieldsArr = new String[campiTabella.length];
                
                for (int i = 0; i < campiTabella.length; i++) {
                	fieldsArr[i] = row[i];
                }
                
                tabella.add(fieldsArr);
            }
           
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return tabella;
	}
	
	public void scriviCsv(String[] headers, ArrayList<String[]> dati) {
		
		LocalDate lt = LocalDate.now();
		DateTimeFormatter formatterFilename = DateTimeFormatter.ofPattern("dd_MM_yyyy");
		String dataFormatoFile = lt.format(formatterFilename);
		
		String filePath = "prenotazioni_disponibili_" + dataFormatoFile + ".csv";
        
		try {
			ICSVWriter csvWriter = new CSVWriterBuilder(new FileWriter(filePath))
                    .withSeparator(';')
                    .build();
		

        // write header
        csvWriter.writeNext(headers);

        // write rows
        
        for (String[] riga : dati) {
        	csvWriter.writeNext(riga);
        }

        csvWriter.close();
        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Scritto file in data " + lt);
	}
	
}
