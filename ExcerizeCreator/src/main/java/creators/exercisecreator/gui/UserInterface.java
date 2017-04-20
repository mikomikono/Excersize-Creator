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
        frame.setPreferredSize(new Dimension(600, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        container.setLayout(new BorderLayout());
        
        CardLayout cl = new CardLayout();
        JPanel parts = new JPanel(cl);
        parts.setPreferredSize(new Dimension(400, 800));

        JScrollPane jsp = new JScrollPane(parts);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        JPanel beginning = new JPanel();
        beginning.setLayout(new BoxLayout(beginning, BoxLayout.X_AXIS));
        beginning.add(new JLabel("Please enter your class: "));
        JTextField addClass = new JTextField();
        beginning.add(addClass);
        
        parts.add(beginning, "first page");
        
        for (int i = 1; i < this.em.returnQMs().size() + 1; i++) {
            QuestionManager qm = this.em.getQM(i);
            JPanel part = new JPanel();
            part.setLayout(new BoxLayout(part, BoxLayout.Y_AXIS));
            
            JPanel info = new JPanel();
            info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
            for (String line: qm.returnInfo()) {
                info.add(new JLabel("<html>" + line + "</html>"));
                if (line.equals(qm.returnInfo().get(0))) {
                    info.add(new JLabel(" "));
                }
            }
            part.add(info);
            
            if (!qm.returnQuestions().isEmpty()) {
                JPanel assignment = new JPanel();
                assignment.setLayout(new BoxLayout(assignment, BoxLayout.Y_AXIS));
                assignment.add(new JLabel(" "));
                assignment.add(new JLabel("<html>" + qm.toString() + "</html>"));
                part.add(assignment);
            }
            
            for (Question q: qm.returnQuestions()) {
                switch (q.returnType()) {
                    case 0:
                        part.add(createTOF(q));
                        break;
                    case 1:
                        part.add(createOQ(q));
                        break;
                    case 2:
                        part.add(createEssay(q));
                        break;
                    default:
                        part.add(createFeedback(q));
                        break;
                }
            }
            
            parts.add(part, this.em.which + "");
        }
        
        JPanel last = new JPanel();
        last.add(new JLabel("Thank you for answering. :)"));
        parts.add(last);
        
        cl.first(parts);
        
        container.add(jsp, BorderLayout.CENTER);
        
//        container.add(new JLabel(" "));
//        container.add(new JLabel(" "));
        
        JButton prev = new JButton("Previous");
        prev.setEnabled(false);
        JButton next = new JButton("Next");
        next.setEnabled(false);
        JButton save = new JButton("Save");

        ButtonListener butt = new ButtonListener(em, cl, next, prev, save, parts);
        next.addActionListener(butt);
        prev.addActionListener(butt);
        save.addActionListener(butt);
        
        JPanel panel = new JPanel();
        BoxLayout bl = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(bl);
        panel.add(prev);
        panel.add(save);
        panel.add(next);
        
        container.add(panel, BorderLayout.SOUTH);
    }
    
    private JPanel createTOF(Question q) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JCheckBox(q.returnQuestion()));
        panel.add(new JLabel(""));
        return panel;
    }
    
    private JPanel createOQ(Question q) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel(q.returnQuestion()));
        panel.add(new JTextField());
        panel.add(new JLabel(""));
        return panel;
    }
    
    private JPanel createEssay(Question q) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(q.returnQuestion()));
        panel.add(new JTextField());
        panel.add(new JLabel(""));
        return panel;
    }
    
    private JPanel createFeedback(Question q) {
        JPanel panel = new JPanel();
        BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(bl);
        panel.add(new JLabel(q.returnQuestion()));
        panel.add(new JTextField());
        panel.add(new JLabel());
        return panel;
    }

    public JFrame getFrame() {
        return frame;
    }
}