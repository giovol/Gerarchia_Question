import java.util.Arrays;

/** Una domanda a scelta multipla in cui bisogna selezionare tutte le scelte corrette. */
public class MultiChoiceQuestion extends ChoiceQuestion {
    private int[] correctChoiceIndices;

    /** Crea una domanda a scelta multipla usando indici corretti a partire da zero. */
    public MultiChoiceQuestion(String text, String answer, String[] choices, int[] correctChoiceIndices) {
        super(text, answer, choices, firstCorrectIndex(correctChoiceIndices));
        this.correctChoiceIndices = correctChoiceIndices.clone();
        validateIndices(this.correctChoiceIndices, choices.length);
    }

    /** Crea una domanda a scelta multipla inizialmente vuota. */
    public MultiChoiceQuestion(String text) {
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

    /** Verifica esattamente gli indici corretti, a partire da uno e nell'ordine definito. */
    @Override
    public void checkAnswer(String userAnswer) {
        int[] selected = parseIndices(userAnswer);
        if (selected != null && Arrays.equals(selected, toOneBased(correctChoiceIndices))) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answers are: " + getAnswer());
        }
    }
    private String[] correctAnswers() {
        String[] choices = getChoices();
        String[] answers = new String[correctChoiceIndices.length];
        for (int i = 0; i < answers.length; i++) answers[i] = choices[correctChoiceIndices[i]];
        return answers;
    }

    private static int firstCorrectIndex(int[] indices) {
        if (indices == null || indices.length == 0) throw new IllegalArgumentException("At least one correct choice is required");
        return indices[0];
    }

    private static void validateIndices(int[] indices, int length) {
        for (int index : indices) if (index < 0 || index >= length) throw new IllegalArgumentException("Invalid correct choice index");
    }

    private static int[] toOneBased(int[] indices) {
        int[] result = indices.clone();
        for (int i = 0; i < result.length; i++) result[i]++;
        return result;
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
