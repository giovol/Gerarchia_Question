public class TesterEsercizio4 {
    public static void main(String[] args) {
        try {
            FillInQuestion fillInQuestion = new FillInQuestion("Testo senza underscore");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            NumericQuestion numericQuestion = new NumericQuestion("Not a Number", "Nan");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            FillInQuestion fillInQuestion = new FillInQuestion("Testo senza underscore");
            NumericQuestion numericQuestion = new NumericQuestion("Not a Number", "Nan");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e);
        }
    }
}
