package view;

/**
 * The ViewUtils class provides utility methods for formatting and displaying text.
 */

public class ViewUtils {
	
	/**
	 * Formats a string to fit within a specified width by adding line breaks.
	 * 
	 * @param input The input string to format.
	 * @param limite The maximum width of each line in characters.
	 * @return A formatted string with line breaks added to fit within the specified width.
	 */
	
	static String formattaPerBox(String input, int limite) {
	    String[] parole = input.split(" ");
	    StringBuilder testoFormattato = new StringBuilder();
	    
	    int lunghezzaLinea = 0;
	    for (String parola : parole) {
	        if (lunghezzaLinea + parola.length() + 1 > limite) {
	            testoFormattato.append(System.lineSeparator());
	            lunghezzaLinea = 0;
	        }
	        testoFormattato.append(parola).append(" ");
	        lunghezzaLinea += parola.length() + 1;
	    }
	    
	    
	    
	    return testoFormattato.toString();
	}
	
	/**
	 * Prints a horizontal bar of asterisks (*) with the specified length.
	 * @param lunghezza The length of the bar in characters.
	 */
	
	static void printBarra(int lunghezza) {
		
		StringBuilder barra = new StringBuilder();
	    
	    for (int i = 0; i < lunghezza; i++) {
	    	barra.append('*');
	    }
	    
	    System.out.println(barra);
	}
	
	/**
	 * Prints a section of text surrounded by horizontal bars of asterisks (*).
	 * @param testo The text to print within the section.
	 * @param limite The maximum width of each line in characters. This also determines the length of the horizontal bars.
	 */
	
	static void printSezione(String testo, int limite) {
		
		String testoFormattato = formattaPerBox(testo, 128);
		
		printBarra(limite);
		System.out.println();
		System.out.println(testoFormattato);
		System.out.println();
		printBarra(limite);
	}
}
