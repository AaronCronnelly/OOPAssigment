package ie.atu.sw;

public class LexiconConfigOption {
	public static void execute() {
		System.out.println("Executing Lexicon Configuration Option");
		configureLexicons();
	}

	private static void configureLexicons() {
		System.out.println("You selected to configure lexicons.");

		System.out.print("Enter the path of the positive lexicon file: ");
		String positiveLexiconPath = Runner.kb.nextLine();
		System.out.println("Positive Lexicon file specified: " + positiveLexiconPath);

		System.out.print("Enter the path of the negative lexicon file: ");
		String negativeLexiconPath = Runner.kb.nextLine();
		System.out.println("Negative Lexicon file specified: " + negativeLexiconPath);

	}
}
