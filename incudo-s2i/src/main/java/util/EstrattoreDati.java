package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EstrattoreDati {
	
	public ArrayList<String[]> caricaDaCsv(String csvFile, String[] campiTabella) {
		// TODO Auto-generated method stub
		
		System.out.println("Caricamento");
		
		String csvPath = "src\\main\\resources\\" + csvFile;
        String line = "";
        String csvDelimiter = ";";
        String[] data;
        ArrayList<String[]> tabella = new ArrayList<>();
        int pos = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
        	while ((line = br.readLine()) != null) {

        		List<String> chunkDati = new ArrayList<>();

        		while (chunkDati.size() < campiTabella.length) {
        			data = line.split(csvDelimiter);

        			for (String field : data) {
        				chunkDati.add(field);
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
