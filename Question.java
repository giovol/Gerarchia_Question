import java.util.Objects;

/** Una domanda con un testo e una risposta corretta. */
public class Question {
    private String text;
    private String answer;

    /** Restituisce il testo della domanda. */
    public String getText() {
        return text;
    }

    /** Restituisce la risposta corretta. */
    public String getAnswer() {
        return answer;
    }

    /** Modifica la risposta corretta. */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /** Modifica il testo della domanda. */
    public void setText(String text) {
        this.text = text;
    }

    /** Crea una domanda. */
    public Question(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    /** Crea una copia di una domanda. */
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

    /** Mostra se {@code userAnswer} coincide, ignorando maiuscole e minuscole. */
    public void checkAnswer(String userAnswer) {
        if (userAnswer != null && answer != null && userAnswer.equalsIgnoreCase(answer)) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answer is: " + answer);
        }
    }

    /** Due domande sono uguali solo se tipo concreto e valori coincidono. */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Question question = (Question) obj;
        return Objects.equals(text, question.text) && Objects.equals(answer, question.answer);
    }

    /** Restituisce un codice hash coerente con {@link #equals(Object)}. */
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), text, answer);
    }
}