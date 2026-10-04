import java.io.IOException;
import java.util.Objects;

/** Una domanda con un testo e una risposta corretta. */
public class Question {
    private String text;
    private String answer;

    /**
     * Restituisce il testo della domanda.
     * 
     * @return Il testo della domanda.
     */
    public String getText() {
        return text;
    }

    /**
     * Restituisce la risposta corretta.
     * 
     * @return La risposta corretta.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Modifica la risposta corretta.
     * 
     * @param answer La nuova risposta corretta.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Modifica il testo della domanda.
     * 
     * @param text Il nuovo testo della domanda.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Crea una domanda.
     * 
     * @param text   Testo della domanda.
     * @param answer Risposta esatta della domanda.
     */
    public Question(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    /**
     * Crea una copia di una domanda.
     * 
     * @param question La domanda da copiare.
     */
    public Question(Question question) {
        this(question.getText(), question.getAnswer());
    }

    /** Crea una domanda vuota. */
    public Question() {
        this.text = "";
        this.answer = "";
    }

    /** Mostra il testo della domanda. */
    public void display() {
        System.out.println("Question: " + text);
    }

    /**
     * Mostra se {@code userAnswer} coincide, ignorando maiuscole e minuscole.
     * 
     * @param userAnswer La risposta controllare
     * @return {@code true} se la risposta è corretta; altrimenti {@code false}
     */
    public boolean checkAnswer(String userAnswer) {
        if (userAnswer != null && answer != null && userAnswer.equalsIgnoreCase(answer)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Due domande sono uguali solo se tipo concreto e valori coincidono.
     * 
     * @param obj La domanda da confrontare.
     * @return {@code true} se le domande sono uguali; altrimenti {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Question question = (Question) obj;
        return Objects.equals(text, question.text) && Objects.equals(answer, question.answer);
    }

    /**
     * Restituisce un codice hash coerente con {@link #equals(Object)}.
     * 
     * @return Il codice hash descritto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), text, answer);
    }

    public static Question caricaDomandaDaFile(String path) throws IOException {
        return null; // implementazione non richiesta in questo esercizio
    }
}