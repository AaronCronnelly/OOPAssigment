package ie.atu.sw;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {

    private static final Scanner kb = new Scanner(System.in);
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static String filePath;

    public static void main(String[] args) throws Exception {
    	Scanner kb = new Scanner(System.in);
        while (true) {
            printHeader();
            displayMenu();
            int choice = getUserChoice();
            if (choice == 7) {
                System.out.println("Exiting program. Goodbye!");
                break;
            }

            handleMenuOption(choice,kb);
        }
        OutputFileOption.shutdownExecutorService();
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

    public static int getUserChoice() {
        int choice = 0;

        while (true) {
            System.out.print("Select Option [1-7]: ");
            String input = kb.nextLine().trim();

            if (input.matches("\\d+")) {
                choice = Integer.parseInt(input);
                break;
            } else {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return choice;
    }




    private static void handleMenuOption(int choice, Scanner kb) {
        executorService.submit(() -> {
            try {
                switch (choice) {
                    case 1:
                        specifyTextFile(kb);
                        break;
                    case 2:
                        //there was a url link here but issue with trying to get it to work and use of added twiiter donwload
                        break;
                    case 3:
                        specifyOutputFile(AnalysisOption.performAnalysisAndReport());
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void specifyTextFile(Scanner kb) {
        TextFileOption.specifyTextFile();
        filePath = TextFileOption.getFilePath();
    }

    private static void specifyOutputFile(String sentimentAnalysisReport) {
        OutputFileOption.execute(sentimentAnalysisReport);
    }

    private static void configureLexicons() {
        LexiconConfigOption.execute();
    }

    private static void executeAnalyseAndReport() {
        AnalysisOption.execute();
    }

	public static void setFilePath(String path) {
		filePath = path;
	}

    public static String getFilePath() {
        return filePath;
    }



    // ... rest of the code for progress meter



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
