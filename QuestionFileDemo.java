import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionFileDemo {
    public static void main(String[] args) {
        QuestionFileLoader loader = new QuestionFileLoader();

        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                System.out.print("Inserisci il nome del file (es. domande.txt): ");
                if (!input.hasNextLine()) {
                    return;
                }

                String nomeFile = input.nextLine().trim();
                File file = new File(nomeFile);
                if (!file.isAbsolute() && !file.exists()) {
                    file = new File("FillInQuestions", nomeFile);
                }

                try {
                    ArrayList<FillInQuestion> questions = loader.caricaDomande(file.getPath());
                    for (FillInQuestion question : questions) {
                        question.display();
                    }
                    return;
                } catch (FileNotFoundException e) {
                    System.out.println("File non trovato: " + nomeFile);
                } catch (BadQuestionDataException e) {
                    System.out.println("Dati delle domande non validi: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Errore di I/O durante la lettura del file: " + e.getMessage());
                }
            }
        }
    }
}
