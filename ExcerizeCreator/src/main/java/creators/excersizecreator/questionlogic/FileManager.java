package creators.excersizecreator.questionlogic;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class FileManager {
    
    public FileManager() {
    }
    
    public List<String> read(String file) {
        List<String> lines = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(file))) {
            while (reader.hasNextLine()) {
                lines.add(reader.nextLine());
            }
        } catch (Exception e) {
            System.out.println("No excersize found.");
        }
        return lines;
    }
    
    public void write(String line, String file) {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(line);
            writer.close();
        } catch (Exception e) {
            System.out.println("Failed to save excersize.");
        }
    }
}
