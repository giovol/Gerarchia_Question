/** Una domanda la cui risposta deve essere un numero valido. */
public class NumericQuestion extends Question {
    private static final double TOLERANCE = 0.01;

    /** Crea una domanda numerica. */
    public NumericQuestion(String text, String answer) {
        super(text, answer);
        validate(answer);
    }

    /** Accetta solo stringhe convertibili in numeri finiti. */
    @Override
    public void setAnswer(String answer) {
        validate(answer);
        super.setAnswer(answer);
    }

    /** Verifica la risposta numerica con una tolleranza di 0.01. */
    @Override
    public void checkAnswer(String userAnswer) {
        try {
            if (Math.abs(Double.parseDouble(userAnswer) - Double.parseDouble(getAnswer())) <= TOLERANCE) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer is: " + getAnswer());
            }
        } catch (NumberFormatException | NullPointerException exception) {
            System.out.println("Invalid numeric answer.");
        }
    }

    private static void validate(String value) {
        try {
            if (!Double.isFinite(Double.parseDouble(value))) throw new IllegalArgumentException("Answer must be finite");
        } catch (NumberFormatException | NullPointerException exception) {
            throw new IllegalArgumentException("Answer must be numeric", exception);
        }
    }
}