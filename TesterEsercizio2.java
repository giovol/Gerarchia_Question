public class TesterEsercizio2 {
    public static void main(String[] args) {
        ChoiceQuestion question = new ChoiceQuestion("test");
        try {
            question.addChoice("", false);
        } catch (InvalidChoiceException e) {
            System.out.println(e);
        }
    }
}
