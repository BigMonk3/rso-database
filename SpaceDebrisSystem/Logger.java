import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * logger class that appends timestamped log messages to a file
 * can be used across the system to record actions performed by users
 */
public class Logger {
                                                //enter a file path to have logger saved 
    private static final String LOG_FILE_PATH = "data/logfile";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * logs a message with a timestamp and appends it to the log file
     *
     * @param message The message to log without timestamp
     */
    public static void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String entry = String.format("[%s] %s", timestamp, message);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Logger error: " + e.getMessage());
        }
    }

    /**
     * log for program exit behavior.
     */
    public static void logExit() {
        log("Program exited by user. Final debris report and logs have been saved.");
    }

    /**
     * optional method to log a blank line for visual separation.
     */
    public static void newSection() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Logger error while writing new section: " + e.getMessage());
        }
    }
}