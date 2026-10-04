import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class QuestionFileLoader {
    public ArrayList<FillInQuestion> caricaDomande(String nomeFile) throws IOException {
        try (Scanner in = new Scanner(new File(nomeFile))) {
            return leggiDomande(in);
        }
    }

    private ArrayList<FillInQuestion> leggiDomande(Scanner in)
            throws BadQuestionDataException {
        ArrayList<FillInQuestion> questions = new ArrayList<FillInQuestion>();
        int n = leggiNumero(in);
        for (int i = 1; i <= n; i++) {
            questions.add(leggiDomanda(in, i));
        }

        if (in.hasNext()) {
            throw new BadQuestionDataException("There is more text below the last question.");
        }
        return questions;
    }

    private int leggiNumero(Scanner in)
            throws BadQuestionDataException {
        if (!in.hasNextLine()) {
            throw new BadQuestionDataException("File is empty.");
        }

        try {
            int n = Integer.parseInt(in.nextLine().trim());
            if (n < 0) {
                throw new BadQuestionDataException("The number of questions cannot be negative.");
            }
            return n;
        } catch (NumberFormatException e) {
            throw new BadQuestionDataException("The first line does not contain a valid number.");
        }
    }

    private FillInQuestion leggiDomanda(Scanner in, int numero)
            throws BadQuestionDataException {
        if (!in.hasNextLine()) {
            throw new BadQuestionDataException(
                    "File ends before question " + numero + ".");
        }

        String lineaDomanda = in.nextLine();
        try {
            return new FillInQuestion(lineaDomanda);
        } catch (IllegalArgumentException e) {
            throw new BadQuestionDataException(
                    "Invalid format for question " + numero + ": " + e.getMessage());
        }
    }
}