public class TesterEsercizio5 {
    public static void main(String[] args) {
        NumericQuestion numericQuestion = new NumericQuestion("test", "1");
        try {
            numericQuestion.setAnswer("NaN");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        } finally {
            System.out.println("Controllo terminato.");
        }

        try {
            numericQuestion.setAnswer("3.14");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        } finally {
            System.out.println("Controllo terminato.");
        }
    }
}
