import java.util.Arrays;

/** Una domanda a scelta multipla in cui è sufficiente selezionare una scelta corretta. */
public class AnyCorrectChoiceQuestion extends ChoiceQuestion {
    private int[] correctChoiceIndices;

    /** Crea una domanda a scelta multipla usando indici corretti a partire da zero. */
    public AnyCorrectChoiceQuestion(String text, String answer, String[] choices, int[] correctChoiceIndices) {
        super(text, answer, choices, firstCorrectIndex(correctChoiceIndices));
        this.correctChoiceIndices = correctChoiceIndices.clone();
        for (int index : this.correctChoiceIndices) if (index < 0 || index >= choices.length) throw new IllegalArgumentException("Invalid correct choice index");
    }

    /** Crea una domanda a scelta multipla inizialmente vuota. */
    public AnyCorrectChoiceQuestion(String text) {
        super(text);
        correctChoiceIndices = new int[0];
    }

    /** Aggiunge una scelta, accumulando tutte quelle indicate come corrette. */
    @Override
    public void addChoice(String choice, boolean correct) {
        super.addChoice(choice, correct);
        if (correct) {
            correctChoiceIndices = Arrays.copyOf(correctChoiceIndices, correctChoiceIndices.length + 1);
            correctChoiceIndices[correctChoiceIndices.length - 1] = getChoices().length - 1;
            setAnswer(String.join(", ", correctAnswers()));
        }
    }

    /** Verifica se almeno uno degli indici forniti, a partire da uno, è corretto. */
    @Override
    public void checkAnswer(String userAnswer) {
        int[] selected = parseIndices(userAnswer);
        boolean correct = false;
        if (selected != null) for (int choice : selected) {
            for (int index : correctChoiceIndices) if (choice - 1 == index) correct = true;
        }
        if (correct) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answers are: " + getAnswer());
        }
    }

    /** Mostra l'avviso aggiuntivo specifico di questo tipo di domanda. */
    @Override
    public void display() {
        super.display();
        System.out.println("Choose any one of the correct answers.");
    }

    private String[] correctAnswers() {
        String[] choices = getChoices(), answers = new String[correctChoiceIndices.length];
        for (int i = 0; i < answers.length; i++) answers[i] = choices[correctChoiceIndices[i]];
        return answers;
    }

    private static int firstCorrectIndex(int[] indices) {
        if (indices == null || indices.length == 0) throw new IllegalArgumentException("At least one correct choice is required");
        return indices[0];
    }

    private static int[] parseIndices(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        String[] tokens = value.trim().split("\\s*,\\s*|\\s+");
        int[] result = new int[tokens.length];
        try { for (int i = 0; i < tokens.length; i++) result[i] = Integer.parseInt(tokens[i]); }
        catch (NumberFormatException exception) { return null; }
        return result;
    }
}
