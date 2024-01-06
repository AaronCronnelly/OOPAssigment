package ie.atu.sw;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LexiconConfigOption {
    private static final Scanner kb = new Scanner(System.in);
    private static List<String> selectedLexicons = new ArrayList<>();

    public static void execute() {
        System.out.println("Executing Lexicon Configuration Option");
        configureLexicons();
    }

    private static void configureLexicons() {
        System.out.println("You selected to configure lexicons.");

        while (true) {
            System.out.print("Enter the path of a lexicon file (or type 'done' to finish): ");
            String lexiconPath = kb.nextLine().trim();

            if (lexiconPath.equalsIgnoreCase("done")) {
                break;
            }

            // Validate the input
            if (isValidLexiconPath(lexiconPath)) {
                selectedLexicons.add(lexiconPath);
                System.out.println("Lexicon file specified: " + lexiconPath);
            } else {
                System.out.println("Invalid input. Please enter a valid lexicon file path.");
            }
        }

        // Debug print
        System.out.println("Debug: Lexicons configured in LexiconConfigOption: " + selectedLexicons);
        System.out.println("Debug: Current selectedLexicons: " + selectedLexicons);
    }

    private static boolean isValidLexiconPath(String path) {
        // You can implement additional validation logic here
        // For now, let's check if the path is not empty and the file exists
        return !path.isEmpty() && new File(path).exists();
    }

    public static List<String> getSelectedLexicons() {
        return selectedLexicons;
    }
}
