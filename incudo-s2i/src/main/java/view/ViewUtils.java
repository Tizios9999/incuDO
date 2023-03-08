package view;

public class ViewUtils {
	
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
	
	static void printBarra(int lunghezza) {
		
		StringBuilder barra = new StringBuilder();
	    
	    for (int i = 0; i < lunghezza; i++) {
	    	barra.append('*');
	    }
	    
	    System.out.println(barra);
	}
	
	static void printSezione(String testo, int limite) {
		
		String testoFormattato = formattaPerBox(testo, 128);
		
		printBarra(limite);
		System.out.println();
		System.out.println(testoFormattato);
		System.out.println();
		printBarra(limite);
		
	}
	
}
