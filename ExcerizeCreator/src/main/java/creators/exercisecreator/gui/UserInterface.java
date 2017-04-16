package creators.exercisecreator.gui;
import creators.exercisecreator.questionlogic.ExerciseManager;
import creators.exercisecreator.questionlogic.QuestionManager;
import creators.exercisecreator.questions.Question;
import java.awt.*;
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
        frame.setPreferredSize(new Dimension(500, 700));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        container.setLayout(new BorderLayout());

        CardLayout cl = new CardLayout();
        JPanel parts = new JPanel(cl);
        
        JPanel beginning = new JPanel();
        beginning.setLayout(new BoxLayout(beginning, BoxLayout.X_AXIS));
        beginning.add(new JLabel("Please enter your class: "));
        beginning.add(new JTextField());
        
        parts.add(beginning, "first page");
        
        for (int i = 0; i < this.em.returnQMs().size(); i++) {
            QuestionManager qm = this.em.getQM(i);
            JPanel part = new JPanel();
            part.setLayout(new BoxLayout(part, BoxLayout.Y_AXIS));
            
            for (String line: qm.returnInfo()) {
                part.add(new JLabel("<html>" + line+ "</html>"));
                if (line.equals(qm.returnInfo().get(0))) {
                    part.add(new JLabel(" "));
                }
            }
            
            if (!qm.returnQuestions().isEmpty()) {
                part.add(new JLabel(" "));
                part.add(new JLabel("<html>" + qm.toString() + "</html>"));
            }
            
            for (Question q: qm.returnQuestions()) {
                switch (q.returnType()) {
                    case 0:
                        part.add(createTOF(q));
                        break;
                    case 1:
                        part.add(this.createOQ(q));
                        break;
                    case 2:
                        part.add(this.createEssay(q));
                        break;
                    default:
                        part.add(this.createFeedback(q));
                        break;
                }
            }
            
            parts.add(part, this.em.which + "");
        }
        
        JPanel last = new JPanel();
        last.add(new JLabel("Thank you for answering. Please remember to save! :)"));
        parts.add(last);
        
        cl.first(parts);
        
        container.add(parts, BorderLayout.CENTER);
        
//        container.add(new JLabel(" "));
//        container.add(new JLabel(" "));
        
        JButton prev = new JButton("Previous");
        if (this.em.which == 0) {
            prev.setEnabled(false);
        }
        JButton next = new JButton("Next");
        JButton save = new JButton("Save");

        ButtonListener butt = new ButtonListener(em, cl, next, prev, save, parts);
        next.addActionListener(butt);
        prev.addActionListener(butt);
        
        JPanel panel = new JPanel();
        BoxLayout bl = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(bl);
        panel.add(prev);
        panel.add(save);
        panel.add(next);
        
        container.add(panel, BorderLayout.SOUTH);
    }
    
    private JPanel createTOF(Question q) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new JCheckBox("<html>" + q.returnQuestion() + "</html>"));
        return panel;
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
        panel.add(new JTextField());
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
    
//    public void saveData() {
//        Container container = frame.getContentPane();
//        
//    }

    public JFrame getFrame() {
        return frame;
    }
}