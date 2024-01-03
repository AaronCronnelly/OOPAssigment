package ie.atu.sw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LexiconConfigOption {
	 private static final Scanner kb = new Scanner(System.in);
	private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
	private static List<String> selectedLexicons = new ArrayList<>();

	public static void execute() {
		System.out.println("Executing Lexicon Configuration Option");
		executorService.submit(LexiconConfigOption::configureLexicons);
	}

	private static void configureLexicons() {
		System.out.println("You selected to configure lexicons.");

		while (true) {
			System.out.print("Enter the path of a lexicon file (or type 'done' to finish): ");
			String lexiconPath = kb.nextLine().trim();

			if (lexiconPath.equalsIgnoreCase("done")) {
				break;
			}

			selectedLexicons.add(lexiconPath);
			System.out.println("Lexicon file specified: " + lexiconPath);
		}

		// Debug print
		System.out.println("Debug: Lexicons configured in LexiconConfigOption: " + selectedLexicons);
		System.out.println("Debug: Current selectedLexicons: " + selectedLexicons);

	}

	public static List<String> getSelectedLexicons() {
		return selectedLexicons;
	}
}
