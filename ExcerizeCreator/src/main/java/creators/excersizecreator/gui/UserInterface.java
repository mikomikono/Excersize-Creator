package creators.excersizecreator.gui;
import creators.excersizecreator.questionlogic.ExcersizeManager;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;

public class UserInterface implements Runnable {

    private JFrame frame;
    private ExcersizeManager em;
    private SubmitListener al;

    public UserInterface(ExcersizeManager em, SubmitListener al) {
        this.em = em;
        this.al = al;
    }

    @Override
    public void run() {
        frame = new JFrame("Excersize");
        frame.setPreferredSize(new Dimension(500, 500));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        
//        for (String q: this.em.returnQuestion()) {
//            container.add(new JLabel(q));
//        }

//        container.add(new JLabel("1. Futuurin hahmottaminen:"));
//        container.add(new JLabel("Valitse lauseet, jotka viittaavat tulevaisuudessa"));
//        container.add(new JLabel("tapahtuviin tapahtumiin."));
//        container.add(new JLabel(" "));
//        
//        container.add(new JCheckBox("We’ll have to see about it."));
//        container.add(new JCheckBox("I won’t hesitate!"));
//        container.add(new JCheckBox("Something must be done!"));
//        container.add(new JCheckBox("I will contact him immediately."));
//        container.add(new JCheckBox("I have to go to the toilet."));
//        container.add(new JCheckBox("Shall we dance?"));
//        container.add(new JCheckBox("He should look into it tomorrow, if time permits."));
//        container.add(new JLabel(" "));
//        
//        JButton submit = new JButton("Submit");
//        submit.addActionListener(al);
//        container.add(submit);
    }

    public JFrame getFrame() {
        return frame;
    }
}