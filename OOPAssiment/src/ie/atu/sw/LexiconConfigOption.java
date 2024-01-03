package ie.atu.sw;

import java.util.ArrayList;
import java.util.List;

public class LexiconConfigOption {

    private static List<String> selectedLexicons = new ArrayList<>();

    public static void execute() {
        System.out.println("Executing Lexicon Configuration Option");
        configureLexicons();
    }

    private static void configureLexicons() {
        System.out.println("You selected to configure lexicons.");

        // Add your logic here for configuring lexicons

        // Example: Prompt the user for lexicon configuration
        System.out.print("Enter the path of a lexicon file (or type 'done' to finish): ");
        String lexiconPath;
        while (!(lexiconPath = Runner.kb.nextLine()).equalsIgnoreCase("done")) {
            selectedLexicons.add(lexiconPath);
            System.out.println("Lexicon file specified: " + lexiconPath);
            System.out.print("Enter the path of another lexicon file (or type 'done' to finish): ");
        }

        // You can now use the lexicon paths for further processing
    }

    public static List<String> getSelectedLexicons() {
        return selectedLexicons;
    }
}
