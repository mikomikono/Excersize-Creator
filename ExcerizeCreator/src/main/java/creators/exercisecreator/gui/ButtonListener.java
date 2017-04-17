package creators.exercisecreator.gui;

import creators.exercisecreator.questionlogic.ExerciseManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

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

            if (this.em.which == this.em.returnQMs().size() + 1) {
                this.save.setEnabled(false);
            } else {
                this.save.setEnabled(true);
            }
            this.prev.setEnabled(true);
            this.next.setEnabled(false);

            this.cl.next(parts);
//            System.out.println(this.em.which);
        } else if (e.getSource().equals(prev)) {
            this.em.prevQM();

            this.next.setEnabled(true);
            if (this.em.which == 0) {
                this.prev.setEnabled(false);
            }
            this.save.setEnabled(false);

            this.cl.previous(parts);
//            System.out.println(this.em.which);
        } else if (e.getSource().equals(save)) {            
            if (this.em.which != this.em.returnQMs().size() + 1) {
                this.next.setEnabled(true);
            }
                this.save.setEnabled(false);

            Component component = this.parts.getComponent(this.em.which); //current part

            JPanel panel = (JPanel) component; //made into a panel

            Component[] components = panel.getComponents(); //components of current part
            
            JLabel jl = new JLabel();
            JTextField tf = new JTextField();
            JCheckBox jcb = new JCheckBox();
            
//            System.out.println("0");
            
            if (components.length > 1) { //if it has only 1 comp, it's an info page and doesn't need saving
                if (components[1].getClass() == tf.getClass()) { //if it's the 1st page, after
                    JTextField t = (JTextField) components[1]; //the 1st panel there's only a
                    if (t.getText().isEmpty()) { //jtextfield w/the name for the answer file
                        this.next.setEnabled(false); //it has to have sth in it to proceed
                        this.save.setEnabled(true);
                    }
                    this.em.addFileName(t.getText());
//                    System.out.println("1");
                }
                for (int x = 2; x < components.length; x++) {
                    if (components[x].getClass() == panel.getClass()) { //the rest are jpanels
                        JPanel j = (JPanel) components[x];
                        Component c = j.getComponent(0);
                        
                        if (c.getClass() == jcb.getClass()) { //tof has checkboxes
                            JCheckBox cb = (JCheckBox) c;
                            JLabel label = (JLabel) j.getComponent(1);
                            checkTOF(cb, label);
//                            System.out.println("2");
                        } else if (c.getClass() == jl.getClass()) {
                            JLabel l = (JLabel) c;
                            JTextField t = (JTextField) j.getComponent(1);
                            JLabel l2 = (JLabel) j.getComponent(2);
                            check(l, t, l2);
//                            System.out.println("3");
                        }
                    }
                }
            }

        }
    }
    
    public void check(JLabel l, JTextField tf, JLabel notes) {
        String question = l.getText();
        String answer = tf.getText();
//        System.out.println(question);
//        System.out.println(answer);
        
        this.em.addAnswer(this.em.getQM(), question, answer);
//        System.out.println(this.em.getQM().returnCorrect(question));
        
        if (!this.em.getQM().returnCorrect(question)) {
            notes.setText("<html>" + this.em.getQM().returnNotes(question) + "</html>");
            notes.setForeground(Color.red);
        } else {
            notes.setText("Correct!");
            notes.setForeground(Color.GREEN);
        }
        this.em.save(question);
    }
    
    public void checkTOF(JCheckBox c, JLabel notes) {
        String question = c.getText();
        String answer = "false";
        if (c.getModel().isSelected()) {
            answer = "true";
        }
//        System.out.println(question);
//        System.out.println(answer);
        
        this.em.addAnswer(this.em.getQM(), question, answer);
//        System.out.println(this.em.getQM().returnCorrect(question));
        if (!this.em.getQM().returnCorrect(question)) {
            notes.setText("<html>" + this.em.getQM().returnNotes(question) + "</html>");
            notes.setForeground(Color.red);
        } else {
            notes.setText("Correct!");
            notes.setForeground(Color.GREEN);
        }
        this.em.save(question);
    }
}
