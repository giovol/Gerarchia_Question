import java.util.Arrays;

/**
 * Una domanda a scelta multipla in cui bisogna selezionare tutte le scelte
 * corrette.
 */
public class MultiChoiceQuestion extends ChoiceQuestion {
    /** Array che contiene gli indici delle risposte esatte. */
    private int[] correctChoiceIndices;

    /**
     * Crea una domanda a scelta multipla usando indici corretti a partire da zero.
     * 
     * @param text                 Il testo della domanda.
     * @param answer               Il testo della risposta esatta.
     * @param choices              Array che contiene le scelte.
     * @param correctChoiceIndices Array che indica gli indici delle risposte
     *                             giuste.
     */
    public MultiChoiceQuestion(String text, String answer, String[] choices, int[] correctChoiceIndices) {
        super(text, answer, choices, firstCorrectIndex(correctChoiceIndices));
        this.correctChoiceIndices = correctChoiceIndices.clone();
        validateIndices(this.correctChoiceIndices, choices.length);
    }

    /**
     * Crea una domanda a scelta multipla inizialmente vuota.
     *
     * @param text Il testo della domanda.
     */
    public MultiChoiceQuestion(String text) {
        super(text);
        correctChoiceIndices = new int[0];
    }

    /**
     * Aggiunge una scelta, accumulando tutte quelle indicate come corrette.
     * 
     * @param choice  Il testo della scelta.
     * @param correct {@code true} se è corretta, altrimenti {@code false}
     */
    @Override
    public void addChoice(String choice, boolean correct) {
        super.addChoice(choice, correct);
        if (correct) {
            correctChoiceIndices = Arrays.copyOf(correctChoiceIndices, correctChoiceIndices.length + 1);
            correctChoiceIndices[correctChoiceIndices.length - 1] = getChoices().length - 1;
            setAnswer(String.join(", ", correctAnswers()));
        }
    }

    /**
     * Verifica esattamente gli indici corretti, a partire da uno e nell'ordine
     * definito.
     * 
     * @param userAnswer La risposta da controllare.
     */
    @Override
    public boolean checkAnswer(String userAnswer) {
        int[] selected = parseIndices(userAnswer);
        if (selected != null && Arrays.equals(selected, toOneBased(correctChoiceIndices))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Array con le risposte corrette.
     * 
     * @return Array di stringhe con le risposte corrette.
     */
    private String[] correctAnswers() {
        String[] choices = getChoices();
        String[] answers = new String[correctChoiceIndices.length];
        for (int i = 0; i < answers.length; i++)
            answers[i] = choices[correctChoiceIndices[i]];
        return answers;
    }

    private static int firstCorrectIndex(int[] indices) {
        if (indices == null || indices.length == 0)
            throw new IllegalArgumentException("At least one correct choice is required");
        return indices[0];
    }

    private static void validateIndices(int[] indices, int length) {
        for (int index : indices)
            if (index < 0 || index >= length)
                throw new IllegalArgumentException("Invalid correct choice index");
    }

    private static int[] toOneBased(int[] indices) {
        int[] result = indices.clone();
        for (int i = 0; i < result.length; i++)
            result[i]++;
        return result;
    }

    private static int[] parseIndices(String value) {
        if (value == null || value.trim().isEmpty())
            return null;
        String[] tokens = value.trim().split("\\s*,\\s*|\\s+");
        int[] result = new int[tokens.length];
        try {
            for (int i = 0; i < tokens.length; i++)
                result[i] = Integer.parseInt(tokens[i]);
        } catch (NumberFormatException exception) {
            return null;
        }
        return result;
    }
}
