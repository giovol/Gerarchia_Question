import java.io.IOException;

public class TesterEsercizio3 {
    public static void main(String[] args) throws IOException {
        Question question = new Question();
        // Errore di compilazione: Unhandled exception type IOException
        question.caricaDomandaDaFile(null);
    }
}
