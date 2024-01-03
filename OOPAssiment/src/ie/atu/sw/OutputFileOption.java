package ie.atu.sw;

public class OutputFileOption {
	public static void execute() {
		System.out.println("Executing Output File Option");
		specifyOutputFile();
	}

	private static void specifyOutputFile() {
		// Logic for specifying an output file
		System.out.println("You selected to specify an output file.");
		System.out.print("Enter the path of the output file (default: ./out.txt): ");
		String outputFile = Runner.kb.nextLine();

		
		System.out.println("Output file specified: " + outputFile);
	}
}
