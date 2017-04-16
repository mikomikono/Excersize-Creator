package creators.exercisecreator.gui;

import creators.exercisecreator.questionlogic.ExerciseManager;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Class for the actions of the submit button.
 */
public class ButtonListener implements ActionListener {
    private ExerciseManager em;
    private CardLayout cl;
    private JButton next;
    private JButton prev;
    private JButton save;
    private Container parts;

    public ButtonListener(ExerciseManager em, CardLayout cl, JButton next, JButton prev, JButton save, Container parts) {
        this.em = em;
        this.cl = cl;
        this.next = next;
        this.prev = prev;
        this.save = save;
        this.parts = parts;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(next)) {
            this.em.nextQM();
            this.prev.setEnabled(true);
            if (this.em.which == this.em.returnQMs().size() + 1) {
                this.next.setEnabled(false);
            }
            this.cl.next(parts);
        } else if (e.getSource().equals(prev)) {
            this.em.prevQM();
            this.next.setEnabled(true);
            if (this.em.which == 0) {
                this.prev.setEnabled(false);
            }
            this.cl.previous(parts);
        } else if (e.getSource().equals(save)) {
            //saving?????
        }
        System.out.println(this.em.which);
    }
    
}
