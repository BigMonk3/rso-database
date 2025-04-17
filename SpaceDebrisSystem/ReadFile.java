import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ReadFile {
    HashMap<String, String> dictionary = new HashMap<String, String>();{
    
    try{
        File file = new File("rso_metrics.csv");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            if(parts.length == 2){

            } 
        }
        scanner.close();
        }catch (FileNotFoundException e){
        System.out.println("File not found");
        e.printStackTrace();
        }
    }
}