import java.util.Arrays;

/** Una domanda con una sola scelta corretta. */
public class ChoiceQuestion extends Question {
    private String[] choices;
    private int correctChoiceIndex;

    /** Crea una domanda a scelta singola usando un indice corretto a partire da zero. */
    public ChoiceQuestion(String text, String answer, String[] choices, int correctChoiceIndex) {
        super(text, answer);
        if (choices == null || correctChoiceIndex < 0 || correctChoiceIndex >= choices.length) {
            throw new IllegalArgumentException("Invalid choices or correct choice index");
        }
        this.choices = choices.clone();
        this.correctChoiceIndex = correctChoiceIndex;
    }

    /** Crea una domanda a scelta inizialmente vuota. */
    public ChoiceQuestion(String text) {
        super(text, "");
        this.choices = new String[0];
        this.correctChoiceIndex = -1;
    }

    /** Restituisce una copia delle scelte disponibili. */
    public String[] getChoices() {
        return choices.clone();
    }

    /** Aggiunge una scelta e la imposta come risposta se {@code correct} vale true. */
    public void addChoice(String choice, boolean correct) {
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

    /** Verifica un numero di scelta a partire da uno. */
    @Override
    public void checkAnswer(String userAnswer) {
        try {
            int userChoiceIndex = Integer.parseInt(userAnswer) - 1;
            if (userChoiceIndex >= 0 && userChoiceIndex < choices.length
                    && userChoiceIndex == correctChoiceIndex) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer is: " + getAnswer());
            }
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Invalid input. Please enter a number corresponding to your choice.");
        }
    }
    /** Confronta anche le scelte, oltre ai valori ereditati. */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && Arrays.equals(choices, ((ChoiceQuestion) obj).choices);
    }

    /** Restituisce un codice hash che include le scelte. */
    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Arrays.hashCode(choices);
    }
}
