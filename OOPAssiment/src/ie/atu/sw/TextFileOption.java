package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TextFileOption {
	 private static final Scanner kb = new Scanner(System.in);
	    private static String filePath;

	    public static void execute() {
	        System.out.println("Executing Text File Option");
	        specifyTextFile();
	    }

	    public static void specifyTextFile() {
	        System.out.println("You selected to specify a text file.");
	        System.out.print("Enter the path of the text file: ");
	        String filePath = kb.nextLine();

	        // Set filePath in the Runner class
	        Runner.setFilePath(filePath);

	        System.out.println("Text file specified: " + filePath);

	        // Debug print
	        System.out.println("Debug: Text file specified: " + filePath);
	        System.out.println("Debug: Current filePath: " + filePath);

	        processTextFile(filePath);
	    }

	    public static void processTextFile(String filePath) {
	        // Simulate processing the specified text file
	        System.out.println("Processing text file: " + filePath);

	        // Read and print the content of the text file
	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            List<String> lines = new ArrayList<>();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                lines.add(line);
	            }

	            // Use traditional threads to parallelize the processing of lines
	            for (String fileLine : lines) {
	                new Thread(() -> processLine(fileLine)).start();
	            }
	        } catch (IOException e) {
	            System.out.println("Error reading the text file: " + e.getMessage());
	        }
	    }

	    private static void processLine(String line) {
	        // Your logic to process each line
	        System.out.println("Processing line: " + line);
	    }

	    public static String getFilePath() {
	        return filePath;
	    }


}
