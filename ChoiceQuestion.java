import java.util.Arrays;

/** Una domanda con una sola scelta corretta. */
public class ChoiceQuestion extends Question {
    private String[] choices;
    private int correctChoiceIndex;

    /**
     * Crea una domanda a scelta singola usando un indice corretto a partire da
     * zero.
     * 
     * @param text               Il testo della domanda.
     * @param answer             Il testo della risposta esatta.
     * @param choices            Array che contiene le scelte della domanda.
     * @param correctChoiceIndex L'indice della risposta esatta.
     */
    public ChoiceQuestion(String text, String answer, String[] choices, int correctChoiceIndex) {
        super(text, answer);
        if (choices == null || correctChoiceIndex < 0 || correctChoiceIndex >= choices.length) {
            throw new IllegalArgumentException("Invalid choices or correct choice index");
        }
        this.choices = choices.clone();
        this.correctChoiceIndex = correctChoiceIndex;
    }

    /**
     * Crea una domanda a scelta inizialmente vuota.
     * 
     * @param text Il testo della domanda.
     */
    public ChoiceQuestion(String text) {
        super(text, "");
        this.choices = new String[0];
        this.correctChoiceIndex = -1;
    }

    /** Restituisce una copia delle scelte disponibili. */
    public String[] getChoices() {
        return choices.clone();
    }

    /**
     * Aggiunge una scelta e la imposta come risposta se {@code correct} vale true.
     * 
     * @param choice  Il testo della scelta.
     * @param correct {@code true} se è corretta, altrimenti {@code false}.
     */
    public void addChoice(String choice, boolean correct) {
        if (choice == null || choice.trim().length() < 2) {
            throw new InvalidChoiceException(
                    "La scelta non può essere null, vuota o inferiore a 2 caratteri (esclusi gli spazi).");
        }

        choices = Arrays.copyOf(choices, choices.length + 1);
        choices[choices.length - 1] = choice;
        if (correct) {
            correctChoiceIndex = choices.length - 1;
            setAnswer(choice);
        }
    }

    @Override
    public void display() {
        super.display();
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i + 1) + ". " + choices[i]);
        }
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        try {
            int userChoiceIndex = Integer.parseInt(userAnswer) - 1;
            if (userChoiceIndex >= 0 && userChoiceIndex < choices.length
                    && userChoiceIndex == correctChoiceIndex) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException | NullPointerException e) {
            throw new NumberFormatException("Invalid input. Please enter a number corresponding to your choice.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && Arrays.equals(choices, ((ChoiceQuestion) obj).choices);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Arrays.hashCode(choices);
    }
}
