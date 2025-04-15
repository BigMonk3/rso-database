import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private String logFilePath = "system_log.txt";

    public void log(String message) {
        try(PrintWriter out = new PrintWriter(new FileWriter(logFilePath, true))){
            out.println("[" + java.time.LocalDateTime.now() + "]" + message);
        } catch(IOException e){
            System.err.println("Logging Failed: " + e.getMessage());
        }
    }
}