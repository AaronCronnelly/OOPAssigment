package ie.atu.sw;

import java.util.Scanner;

public class Runner {

	public static Scanner kb = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		while (true) {
			printHeader();
			displayMenu();
			int choice = getUserChoice();
			if (choice == 7) {
				System.out.println("Exiting program. Goodbye!");
				break;
			}

			handleMenuOption(choice);
		}
		
		kb.close();
	}

	private static void printHeader() {
		System.out.println(ConsoleColour.WHITE);
		System.out.println("************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*                                                          *");
		System.out.println("*             Virtual Threaded Sentiment Analyser          *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
	}

	private static void displayMenu() {
		System.out.println("1. Specify Text File");
	    System.out.println("2. Specify URL");
	    System.out.println("3. Specify Output File");
	    System.out.println("4. Configure Lexicons");
	    System.out.println("5. Execute Analysis and Report");
	    System.out.println("7. Exit");
	}

	private static int getUserChoice() {
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT + "Select Option [1-7]> ");
		int choice = -1;

		try {
			choice = Integer.parseInt(kb.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a number.");
		}

		return choice;
	}

	private static void handleMenuOption(int choice) throws InterruptedException {
		switch (choice) {
		case 1:
			specifyTextFile();
			break;
		case 2:
			specifyURL();
			break;
		case 3:
			specifyOutputFile();
			break;
		case 4:
			configureLexicons();
			break;
		case 5:
			showProgressMeter();
			executeAnalyseAndReport();
			break;
		default:
			System.out.println("Invalid choice. Please select a valid option.");
		}
	}

	// Implement methods for each menu option
	private static void specifyTextFile() {
		TextFileOption.execute();
	}

	private static void specifyURL() {
		URLOption.execute();
	}

	private static void specifyOutputFile() {
		OutputFileOption.execute();
	}

	private static void configureLexicons() {
		LexiconConfigOption.execute();
	}

	private static void executeAnalyseAndReport() {
		AnalysisOption.execute();
	}

	
	/*
	 * Terminal Progress Meter ----------------------- You might find the progress
	 * meter below useful. The progress effect works best if you call this method
	 * from inside a loop and do not call System.out.println(....) until the
	 * progress meter is finished.
	 * 
	 * Please note the following carefully:
	 * 
	 * 1) The progress meter will NOT work in the Eclipse console, but will work on
	 * Windows (DOS), Mac and Linux terminals.
	 * 
	 * 2) The meter works by using the line feed character "\r" to return to the
	 * start of the current line and writes out the updated progress over the
	 * existing information. If you output any text between calling this method,
	 * i.e. System.out.println(....), then the next call to the progress meter will
	 * output the status to the next line.
	 * 
	 * 3) If the variable size is greater than the terminal width, a new line escape
	 * character "\n" will be automatically added and the meter won't work properly.
	 * 
	 * 
	 */
// Progress meter
	private static void showProgressMeter() throws InterruptedException {
		System.out.print(ConsoleColour.YELLOW); // Change the colour of the console text

		int size = 100; // The size of the meter. 100 equates to 100%
		for (int i = 0; i < size; i++) {
			printProgress(i + 1, size); // After each (some) steps, update the progress meter
			Thread.sleep(10); // Slows things down so the animation is visible
		}
	}

	
	 private static void printProgress(int index, int total) {
	        if (index > total)
	            return; // Out of range
	        int size = 50; // Must be less than console width
	        char done = '█'; // Change to whatever you like.
	        char todo = '░'; // Change to whatever you like.

	        // Compute basic metrics for the meter
	        int complete = (100 * index) / total;
	        int completeLen = size * complete / 100;

	        // StringBuilder for string concatenation inside a loop
	        StringBuilder sb = new StringBuilder();
	        sb.append("[");
	        for (int i = 0; i < size; i++) {
	            sb.append((i < completeLen) ? done : todo);
	        }

	        // The line feed escape character "\r" returns the cursor to the
	        // start of the current line. Calling print(...) overwrites the
	        // existing line and creates the illusion of an animation.
	        System.out.print("\r" + sb + "] " + complete + "%");

	        // Once the meter reaches its max, move to a new line.
	        if (done == total)
	            System.out.println("\n");
	    }
}