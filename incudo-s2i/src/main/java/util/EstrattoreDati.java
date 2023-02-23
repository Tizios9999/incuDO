package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class EstrattoreDati {
	
	public ArrayList<String[]> caricaDaCsv(String csvFile, String[] campiTabella) {
		// TODO Auto-generated method stub
		
		System.out.println("Caricamento");
		
		String csvPath = "src\\main\\resources\\" + csvFile;
        String line = "";
        String csvDelimiter = ";";
        String[] data;
        ArrayList<String> dataList = new ArrayList<String>();
        ArrayList<String[]> tabella = new ArrayList<String[]>();
        int pos = 0;
        
//        try {
//        	
//        
//        CSVParser parser = new CSVParser(new FileReader(csvPath), CSVFormat.DEFAULT.withHeader());
//
////        List<CSVRecord> records = parser.getRecords();
//        
////        for (int i = 0; i < records.size(); i++) {
////            CSVRecord record = records.get(i);
////            
////            for (int j = 0; j < campiTabella.length; j++) {
////            	dataList.add(record.get(campiTabella[j]));
////            }
//        for (CSVRecord record : parser) {
//            String id = record.get("ID");
//            String nome = record.get("Nome");
//            String desc = record.get("Descrizione");
//            String dataCorso = record.get("Data");
//            String durata = record.get("Durata");
//            String luogo = record.get("Luogo");
//            String disp = record.get("Disp");
//            System.out.println("Name: " + nome + ", Luogo " + luogo);
//        
//            tabella.add(dataList.toArray(new String[0]));
//            System.out.println(tabella);
//        }
//        
//        
//
//        parser.close();
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
        	while ((line = br.readLine()) != null) {
        		
        		List<String> chunkDati = new ArrayList<String>();

        		int posDati;
        		
        		while (chunkDati.size() < campiTabella.length) {
        			posDati = 0;
        			data = line.split(csvDelimiter);

        			if (data.length == 0) break;
        			
        			for (int i = 0; i < data.length; i++) {
        				chunkDati.add(data[posDati]);
        				posDati++;
        			}
        			
//        			for (String field : data) {
//        				chunkDati.add(field);
//        			}
        			if (chunkDati.size() < campiTabella.length) {
        				line = br.readLine();
        			}
        		
        		}

        		if (pos > 0) {
        			
        			tabella.add(chunkDati.toArray(new String[0]));
        			
        		}
        		pos++;
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        } 
        
        return tabella;
	}
	
}
