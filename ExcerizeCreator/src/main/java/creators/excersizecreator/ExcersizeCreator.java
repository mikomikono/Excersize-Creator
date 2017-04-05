package creators.excersizecreator;

import creators.excersizecreator.questionlogic.ExcersizeManager;
import creators.excersizecreator.gui.UserInterface;
import creators.excersizecreator.gui.SubmitListener;
import creators.excersizecreator.questionlogic.QuestionManager;
import javax.swing.SwingUtilities;

public class ExcersizeCreator {
    public static void main(String[] args) {
        ExcersizeManager em = new ExcersizeManager();
        SubmitListener al = new SubmitListener();
        
        QuestionManager qm = new QuestionManager();
        qm.createOQ("hello", "hello hello", "hi");
        qm.createEssay("wee", "woo", "waa");
        qm.createTOF("ya", "ye", Boolean.TRUE);
        System.out.println(qm.returnQuestions());
        
        qm.answer("hello", "hey");
        qm.answer("wee", "wapadapadoo");
        qm.answer("ya", "true");
        System.out.println(qm.returnQuestions());
        System.out.println(qm.returnCorrects());
        System.out.println(qm.returnQuestions());
        
        UserInterface ui = new UserInterface(em, al);
        SwingUtilities.invokeLater(ui);
    }
    
}
