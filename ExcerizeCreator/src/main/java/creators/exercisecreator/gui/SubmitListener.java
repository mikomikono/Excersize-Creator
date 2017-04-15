package creators.exercisecreator.gui;

import creators.exercisecreator.questionlogic.ExerciseManager;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Class for the actions of the submit button.
 */
public class SubmitListener implements ActionListener {
    private ExerciseManager em;
    private Container container;
    private JButton next;
    private JButton prev;

    public SubmitListener(ExerciseManager em, Container container, JButton next, JButton prev) {
        this.em = em;
        this.container = container;
        this.next = next;
        this.prev = prev;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(next)) {
            this.em.nextQM();
            this.prev.setEnabled(true);
            System.out.println(this.em.which);
        } else if (e.getSource().equals(prev)) {
            this.em.prevQM();
            if (this.em.which == 0) {
                this.prev.setEnabled(false);
            }
            System.out.println(this.em.which);
        }
    }
    
}
