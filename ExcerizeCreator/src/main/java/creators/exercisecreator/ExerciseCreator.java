package creators.exercisecreator;

import creators.exercisecreator.questionlogic.ExerciseManager;
import creators.exercisecreator.gui.UserInterface;
import javax.swing.SwingUtilities;

/**
 * Main class for setting up the creator.
 */
public class ExerciseCreator {
    public static void main(String[] args) {
        ExerciseManager em = new ExerciseManager();
        em.read("futuuri.txt");
        
        UserInterface ui = new UserInterface(em);
        SwingUtilities.invokeLater(ui);

    }
    
}
