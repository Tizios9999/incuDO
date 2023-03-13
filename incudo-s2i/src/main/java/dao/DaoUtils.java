package dao;

import java.util.ArrayList;
import java.util.List;

import util.CsvDataManager;
import util.DataValidator;

public class DaoUtils {

	public static List<String[]> loadDataFromCsvTable(String csvFile, String[][] tableHeaders) {
        CsvDataManager dataProvider = new CsvDataManager();
        ArrayList<String[]> dataTable = dataProvider.loadFromCsv(csvFile, tableHeaders);

        for (String[] row : dataTable) {
            for (int i = 0; i < tableHeaders.length; i++) {
                String type = tableHeaders[i][1];
                if (!DataValidator.isValidData(type, row[i])) {
                    int numRiga = dataTable.indexOf(row) + 1;
                    String errore = "Errore durante la lettura del file " + csvFile + " nella colonna "
                            + tableHeaders[i][0] + " alla riga " + numRiga
                            + ". Ricontrollare il file e riprovare a riavviare l'applicazione.";
                    throw new RuntimeException(errore);
                }
            }
        }
        return dataTable;
    }
}
