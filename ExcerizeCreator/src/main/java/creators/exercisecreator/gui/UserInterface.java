package creators.exercisecreator.gui;
import creators.exercisecreator.questionlogic.ExerciseManager;
import creators.exercisecreator.questionlogic.QuestionManager;
import creators.exercisecreator.questions.Question;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * Class for creating the user interface.
 */
public class UserInterface implements Runnable {

    private JFrame frame;
    private ExerciseManager em;

    public UserInterface(ExerciseManager em) {
        this.em = em;
    }

    @Override
    public void run() {
        frame = new JFrame("Exercise");
        frame.setPreferredSize(new Dimension(700, 700));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        
        QuestionManager qm = this.em.getQM();
        for (String line: qm.returnInfo()) {
            container.add(new JLabel("<html>" + line+ "</html>"));
            if (line.equals(qm.returnInfo().get(0))) {
                container.add(new JLabel(" "));
            }
        }
        if (!qm.returnQuestions().isEmpty()) {
            container.add(new JLabel(" "));
            container.add(new JLabel("<html>" + qm.toString() + "</html>"));
        }
        for (Question q: qm.returnQuestions()) {
            switch (q.returnType()) {
                case 0:
                    this.createTOF(container, q);
                    break;
                case 1:
                    container.add(this.createOQ(q));
                    break;
                case 2:
                    container.add(this.createEssay(q));
                    break;
                default:
                    container.add(this.createFeedback(q));
                    break;
            }
        }
        
        container.add(new JLabel(" "));
        
        JButton prev = new JButton("Previous");
        if (this.em.which == 0) {
            prev.setEnabled(false);
        }
        JButton next = new JButton("Next");

        SubmitListener sl = new SubmitListener(em, this.frame, next, prev);
        next.addActionListener(sl);
        prev.addActionListener(sl);
        
        JPanel panel = new JPanel();
        BoxLayout bl = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(bl);
        panel.add(prev);
        panel.add(next);
        
        container.add(panel);
    }
    
    private void createTOF(Container container, Question q) {
        container.add(new JCheckBox("<html>" + q.returnQuestion() + "</html>"));
    }
    
    private JPanel createOQ(Question q) {
        JPanel panel = new JPanel(new GridLayout(2,2));
        panel.add(new JLabel("<html>" + q.returnQuestion() + "</html>"));
        panel.add(new JLabel(""));
        panel.add(new JTextField());
        return panel;
    }
    
    private JPanel createEssay(Question q) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("<html>" + q.returnQuestion() + "</html>"));
        panel.add(new JTextArea());
        return panel;
    }
    
    private JPanel createFeedback(Question q) {
        JPanel panel = new JPanel();
        BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(bl);
        panel.add(new JLabel("<html>" + q.returnQuestion() + "</html>"));
        panel.add(new JTextField());
        panel.add(new JPanel());
        return panel;
    }
    
    public void saveData() {
        Container container = frame.getContentPane();
        
    }

    public JFrame getFrame() {
        return frame;
    }
}