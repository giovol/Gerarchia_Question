/** Una domanda creata da una frase con una risposta racchiusa tra trattini bassi. */
public class FillInQuestion extends Question {
    /** Estrae la risposta racchiusa tra la prima coppia di trattini bassi. */
    public FillInQuestion(String sentence) {
        super(extractText(sentence), extractAnswer(sentence));
    }

    private static String extractAnswer(String sentence) {
        int start = sentence == null ? -1 : sentence.indexOf('_');
        int end = start < 0 ? -1 : sentence.indexOf('_', start + 1);
        if (start < 0 || end <= start + 1 || sentence.indexOf('_', end + 1) >= 0) {
            throw new IllegalArgumentException("The sentence must contain exactly one answer between underscores");
        }
        return sentence.substring(start + 1, end);
    }

    private static String extractText(String sentence) {
        int start = sentence == null ? -1 : sentence.indexOf('_');
        int end = start < 0 ? -1 : sentence.indexOf('_', start + 1);
        if (start < 0 || end <= start + 1 || sentence.indexOf('_', end + 1) >= 0) {
            throw new IllegalArgumentException("The sentence must contain exactly one answer between underscores");
        }
        return sentence.substring(0, start) + sentence.substring(end + 1);
    }
}