/** Una domanda la cui risposta deve essere un numero valido. */
public class NumericQuestion extends Question {
    private static final double TOLERANCE = 0.01;

    /**
     * Crea una domanda numerica.
     * 
     * @param text   Il testo della domanda.
     * @param answer La risposta esatta della domanda.
     */
    public NumericQuestion(String text, String answer) {
        super(text, answer);
        validate(answer);
    }

    /**
     * Accetta solo stringhe convertibili in numeri finiti.
     * 
     * @param answer La nuova riposta esatta.
     */
    @Override
    public void setAnswer(String answer) {
        validate(answer);
        super.setAnswer(answer);
    }

    /**
     * Verifica la risposta numerica con una tolleranza di 0.01.
     * 
     * @param userAnswer La risposta data.
     */
    @Override
    public boolean checkAnswer(String userAnswer) {
        try {
            if (Math.abs(Double.parseDouble(userAnswer) - Double.parseDouble(getAnswer())) <= TOLERANCE) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException | NullPointerException exception) {
            throw new NumberFormatException("Invalid numeric answer.");
        }
    }

    private static void validate(String value) {
        try {
            if (!Double.isFinite(Double.parseDouble(value)))
                throw new IllegalArgumentException("Answer must be finite");
        } catch (NumberFormatException | NullPointerException exception) {
            throw new IllegalArgumentException("Answer must be numeric", exception);
        }
    }
}