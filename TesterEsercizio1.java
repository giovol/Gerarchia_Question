public class TesterEsercizio1 {
    public static void main(String[] args) {
        ChoiceQuestion question = new ChoiceQuestion("test");
        // Prova con null
        try {
            question.addChoice(null, true);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getClass().getName() + ": è stato inserito null");
        }

        // Prova con stringa vuota
        try {
            question.addChoice("", false);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getClass().getName() + ": è stato inserito \"\"");
        }

        // Prova con spazio
        try {
            question.addChoice(" ", false);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getClass().getName() + ": è stato inserito \" \"");
        }
    }
}
