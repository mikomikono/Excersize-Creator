package creators.exercisecreator.questionlogic;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
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
        InputStream is = getClass().getClassLoader().getResourceAsStream(file);
        try (Scanner reader = new Scanner(is)) {
            while (reader.hasNextLine()) {
                lines.add(reader.nextLine());
            }
        } catch (Exception e) {
            lines.add("﻿~info~Error~1~No Exercizes found.");
        }

        
        return lines;
    }
    
    /**
     * Method for saving a question into a file.
     * 
     * @param line String of the line that needs to be saved
     * @param file Location of the file that the saving is done on
     * @return Returns a string depending on whether or not saving was successful
     */
    public String write(String line, String file) {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(line + "\n");
            writer.close();
            return "Saved!";
        } catch (Exception e) {
            return "Failed to save exercize.";
        }
    }
}
