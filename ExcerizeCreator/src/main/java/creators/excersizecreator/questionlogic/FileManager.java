package creators.excersizecreator.questionlogic;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * Class for reading and writing questions from files.
 */
public class FileManager {
    
    public FileManager() {
    }
    
    /**
     * Method for getting questions that have been saved into a file.
     * 
     * @param file The location of the file that has the info of the questions
     * @return List of the questions from the file
     */
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
    
    /**
     * Method for saving a question into a file.
     * 
     * @param line String of the line that needs to be saved
     * @param file Location of the file that the saving is done on
     */
    public void write(String line, String file) {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(line);
            writer.close();
        } catch (Exception e) {
            System.out.println("Failed to save excersize.");
        }
    }
}
