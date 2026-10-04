import java.util.Scanner;

public class QuestionLoaderDemo {
	public static void main(String[] args) {
		QuestionLoader loader = new QuestionLoader();
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.print("Inserisci domanda: ");
			String inputString = input.nextLine();
			try {
				FillInQuestion question = loader.caricaDomanda(inputString);
				question.display();
				break;
			} catch(BadQuestionDataException | IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
