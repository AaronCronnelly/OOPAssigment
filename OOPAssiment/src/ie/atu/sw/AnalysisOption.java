package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;

public class AnalysisOption {

	public static void execute() {
		System.out.println("You selected to execute, analyze, and report.");

		List<String> selectedLexicons = LexiconConfigOption.getSelectedLexicons();
		String filePath = TextFileOption.getFilePath();

		// Debug print
		System.out.println("Debug: Lexicons in AnalysisOption: " + selectedLexicons);
		System.out.println("Debug: File path in AnalysisOption: " + filePath);

		// Load lexicons from specified paths
		Map<String, Integer> lexiconMap = loadLexicons(selectedLexicons);

		int totalSentimentScore = 0;
		StringBuilder detailedReport = new StringBuilder();

		// logic for executing, analyzing, and reporting
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			// Debug print
			System.out.println("Debug: Reading file in AnalysisOption");

			String line;
			int positiveCount = 0;
			int negativeCount = 0;

			while ((line = reader.readLine()) != null) {
				String[] words = line.toLowerCase().split("\\s+");

				for (String word : words) {
					if (lexiconMap.containsKey(word)) {
						int sentimentScore = lexiconMap.get(word);
						totalSentimentScore += sentimentScore;

						if (sentimentScore > 0) {
							positiveCount += sentimentScore;
						} else if (sentimentScore < 0) {
							negativeCount -= sentimentScore;
						}
					}
				}
			}

			// Generate a detailed report based on sentiment analysis
			detailedReport.append("Sentiment Analysis Report:\n");
			detailedReport.append("Positive Count: ").append(positiveCount).append("\n");
			detailedReport.append("Negative Count: ").append(negativeCount).append("\n");
			detailedReport.append("Overall Sentiment: ").append(determineOverallSentiment(totalSentimentScore))
					.append("\n");

			// Include the lexicons used in the analysis
			detailedReport.append("Lexicons Used:\n");
			for (String lexicon : selectedLexicons) {
				detailedReport.append("- ").append(lexicon).append("\n");
			}

		} catch (IOException e) {
			System.out.println("Error reading the text file: " + e.getMessage());
		}

		// Pass the detailed report to OutputFileOption for writing to a file
		OutputFileOption.execute(detailedReport.toString());
	}

	public static String performAnalysisAndReport() {
		System.out.println("Executing Analysis Option");
		List<String> selectedLexicons = LexiconConfigOption.getSelectedLexicons();
		String filePath = TextFileOption.getFilePath();

		// Load lexicons from specified paths
		Map<String, Integer> lexiconMap = loadLexicons(selectedLexicons);

		int totalSentimentScore = 0;
		StringBuilder detailedReport = new StringBuilder();

		// logic for executing, analyzing, and reporting
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			int positiveCount = 0;
			int negativeCount = 0;

			while ((line = reader.readLine()) != null) {
				String[] words = line.toLowerCase().split("\\s+");

				for (String word : words) {
					if (lexiconMap.containsKey(word)) {
						int sentimentScore = lexiconMap.get(word);
						totalSentimentScore += sentimentScore;

						if (sentimentScore > 0) {
							positiveCount += sentimentScore;
						} else if (sentimentScore < 0) {
							negativeCount -= sentimentScore;
						}
					}
				}
			}

			// Generate a detailed report based on sentiment analysis
			detailedReport.append("Sentiment Analysis Report:\n");
			detailedReport.append("Positive Count: ").append(positiveCount).append("\n");
			detailedReport.append("Negative Count: ").append(negativeCount).append("\n");
			detailedReport.append("Overall Sentiment: ").append(determineOverallSentiment(totalSentimentScore))
					.append("\n");

			// Include the lexicons used in the analysis
			detailedReport.append("Lexicons Used:\n");
			for (String lexicon : selectedLexicons) {
				detailedReport.append("- ").append(lexicon).append("\n");
			}

		} catch (IOException e) {
			System.out.println("Error reading the text file: " + e.getMessage());
		}

		return detailedReport.toString();
	}

	private static String determineOverallSentiment(int sentimentScore) {
		// Determine overall sentiment based on the total sentiment score
		return (sentimentScore > 0) ? "Positive" : "Negative";
	}

	private static Map<String, Integer> loadLexicons(List<String> lexiconPaths) {
		Map<String, Integer> lexiconMap = new HashMap<>();

		for (String lexiconPath : lexiconPaths) {
			try (BufferedReader reader = new BufferedReader(new FileReader(lexiconPath))) {
				String line;
				while ((line = reader.readLine()) != null) {
					// Assuming each line in the lexicon file contains a word and its sentiment
					// score separated by a comma
					String[] parts = line.split(",");
					if (parts.length == 2) {
						String word = parts[0].trim();
						int sentimentScore = Integer.parseInt(parts[1].trim());
						lexiconMap.put(word, sentimentScore);
					}
				}
			} catch (IOException | NumberFormatException e) {
				System.out.println("Error loading lexicon from file " + lexiconPath + ": " + e.getMessage());
			}
		}

		return lexiconMap;
	}
}
