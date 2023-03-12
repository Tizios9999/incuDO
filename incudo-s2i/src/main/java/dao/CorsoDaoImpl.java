package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Corso;
import util.CsvDataManager;
import util.DataValidator;

public class CorsoDaoImpl implements CorsoDao {
	
	private String[][] tableHeaders = {
			{"Id", "Integer"}, 
			{"Nome", "String"},
			{"Descrizione", "String"}, 
			{"Data", "Date"}, 
			{"Durata", "Integer"},
			{"Luogo", "String"}, 
			{"Disponibile", "Disponibile"}
			};
	
	private List<Corso> corsiList = new ArrayList<>();
	
	public void loadCorsoTable(String csvFile) {
		
		CsvDataManager dataProvider = new CsvDataManager();
		
		ArrayList<String[]> dataTable = dataProvider.loadFromCsv(csvFile, tableHeaders);
		
		for (String[] row : dataTable) {
			
			for (int i = 0; i < tableHeaders.length; i++) {
				
				String type = tableHeaders[i][1];
				
				if (!DataValidator.isValidData(type, row[i])) {
					
					int numRiga = dataTable.indexOf(row) + 1;
					
					String errore = "Errore durante la lettura del file " + csvFile + " nella colonna " + tableHeaders[i][0] + " alla riga " + numRiga +". Ricontrollare il file e riprovare a riavviare l'applicazione.";
					
					throw new RuntimeException(errore);
				}
				
			}
			
			Corso corso = new Corso.CorsoBuilder(row).build();
			corsiList.add(corso);
		}
		
	}
	
	public Corso searchCorsoById(Integer id) {
		
		for (Corso corso : this.corsiList) {
			
			if (id == corso.getId()) {
				return corso;
			}
		}
		
		return null;
	}
	
	public void setDisponibileCorso(Integer id, Boolean available) {
		
		for (Corso corso : this.corsiList) {
			
			if (id == corso.getId()) {
				corso.setDisponibile(available);
				break;
			}
		}
		
	}
	
	public void exportCsv() {
		
		ArrayList<String[]> listToExport = new ArrayList<String[]>();
		
		String[] headers = {"Id", "Nome", "Descrizione", "Data", "Durata", "Luogo"};
		
		for (Corso corso : this.corsiList) {
			
			if (corso.isDisponibile()) {
				
				String[] values = new String[headers.length];
				values[0] = String.valueOf(corso.getId());
				values[1] = corso.getNome();
				values[2] = corso.getDescrizione();
				values[3] = String.valueOf(corso.getDataCorso());
				values[4] = String.valueOf(corso.getDurata());
				values[5] = corso.getLuogo();
				listToExport.add(values);
			
			}
		 
		}
		
		CsvDataManager providerDati = new CsvDataManager();
		providerDati.writeCsv(headers, listToExport);
	}

	public List<Corso> getListaCorsi() {
		
		return corsiList;
	}

	public void setListaCorsi(List<Corso> corsiList) {
		this.corsiList = corsiList;
	}
	
	
	
}
