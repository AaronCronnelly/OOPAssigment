package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFileOption {

    private static String filePath; // Declare filePath as a class-level variable

    public static void execute() {
        System.out.println("Executing Text File Option");
        specifyTextFile();
    }

    private static void specifyTextFile() {
        // Logic for specifying a text file
        System.out.println("You selected to specify a text file.");
        System.out.print("Enter the path of the text file: ");
        filePath = Runner.kb.nextLine();

        System.out.println("Text file specified: " + filePath);
        processTextFile();
    }

    private static void processTextFile() {
        // Simulate processing the specified text file
        System.out.println("Processing text file: " + filePath);

        // Read and print the content of the text file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("Content of the text file:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the text file: " + e.getMessage());
        }
    }

    public static String getFilePath() {
        return filePath;
    }
}
