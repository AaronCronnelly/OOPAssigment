package ie.atu.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFileOption {
    public static void execute(String sentimentAnalysisReport) {
        System.out.println("Executing Output File Option");
        System.out.println("Sentiment Analysis Report:\n" + sentimentAnalysisReport);

        // Ask the user if they want to save the report to a file
        System.out.print("Do you want to save the report to a file? (yes/no): ");
        String saveToFileChoice = Runner.kb.nextLine().toLowerCase();

        if (saveToFileChoice.equals("yes")) {
            specifyOutputFile(sentimentAnalysisReport);
        }
    }

    private static void specifyOutputFile(String sentimentAnalysisReport) {
        // Logic for specifying an output file
        System.out.print("Enter the path of the output file (default: ./SentimentAnalysisReport.txt): ");
        String outputFile = Runner.kb.nextLine();

        if (outputFile.isEmpty()) {
            outputFile = "./SentimentAnalysisReport.txt"; // Default output file path
        }

        writeReportToFile(sentimentAnalysisReport, outputFile);
        System.out.println("Sentiment analysis report written to: " + outputFile);
    }

    private static void writeReportToFile(String sentimentAnalysisReport, String outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(sentimentAnalysisReport);
        } catch (IOException e) {
            System.out.println("Error writing to the output file: " + e.getMessage());
        }
    }
}
