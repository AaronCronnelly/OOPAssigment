package ie.atu.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class OutputFileOption {
	 private static final Scanner kb = new Scanner(System.in);
	private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

	public static void execute(String sentimentAnalysisReport) {
		System.out.println("Executing Output File Option");
		System.out.println("Sentiment Analysis Report:\n" + sentimentAnalysisReport);

		// Ask the user if they want to save the report to a file
		System.out.print("Do you want to save the report to a file? (yes/no): ");
		String saveToFileChoice = kb.nextLine().toLowerCase();

		if (saveToFileChoice.equals("yes")) {
			Future<?> writeTask = executorService.submit(() -> specifyOutputFile(sentimentAnalysisReport));
			try {
				writeTask.get(); // Wait for the completion of the write task
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	private static void specifyOutputFile(String sentimentAnalysisReport) {
		// Logic for specifying an output file
		System.out.print("Enter the path of the output file (default: ./SentimentAnalysisReport.txt): ");
		String outputFile = kb.nextLine();

		if (outputFile.isEmpty()) {
			outputFile = "./SentimentAnalysisReport.txt"; // Default output file path
		}

		final String finalOutputFile = outputFile; // Create a final variable

		Future<?> writeTask = executorService.submit(() -> writeReportToFile(sentimentAnalysisReport, finalOutputFile));
		try {
			writeTask.get(); // Wait for the completion of the write task
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("Sentiment analysis report written to: " + finalOutputFile);
	}

	private static void writeReportToFile(String sentimentAnalysisReport, String outputFile) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
			writer.write(sentimentAnalysisReport);
		} catch (IOException e) {
			System.out.println("Error writing to the output file: " + e.getMessage());
		}
	}

	public static void shutdownExecutorService() {
		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
	}
}
