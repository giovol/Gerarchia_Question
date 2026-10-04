import java.util.Scanner;

public class QuestionLoader {
	public FillInQuestion caricaDomanda(String contenuto) throws BadQuestionDataException {
		// costruisci uno Scanner su &quot;contenuto&quot; e richiama leggiDomanda
		Scanner input = new Scanner(contenuto); 
		return leggiDomanda(input);
	}
	
	private FillInQuestion leggiDomanda(Scanner in) throws BadQuestionDataException {
		// se in.hasNextLine() è falso, lancia BadQuestionDataException
		// altrimenti richiama leggiTesto e costruisci una FillInQuestion
		// con il risultato
		if(!in.hasNextLine()) {
			throw new BadQuestionDataException("Il contenuto della domanda è vuoto.");
		} else {
			return new FillInQuestion(leggiTesto(in));
		}
	}
	
	private String leggiTesto(Scanner in) throws BadQuestionDataException {
		// leggi una riga con in.nextLine()
		// se non contiene il carattere underscore &#39;_&#39;, lancia
		// BadQuestionDataException con un messaggio a tua scelta
		// altrimenti restituisci la riga
		String riga = in.nextLine();
		if(!riga.contains("_")) {
			throw new BadQuestionDataException("Errore: la domanda non contiene il carattere underscore '_'.");
		} else {
			return riga;
		}
	}
}
