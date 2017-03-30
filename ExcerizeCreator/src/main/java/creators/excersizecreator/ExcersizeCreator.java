package creators.excersizecreator;

import creators.excersizecreator.questionlogic.ExcersizeManager;
import creators.excersizecreator.gui.UserInterface;
import creators.excersizecreator.gui.SubmitListener;
import javax.swing.SwingUtilities;

public class ExcersizeCreator {
    public static void main(String[] args) {
        ExcersizeManager em = new ExcersizeManager();
        SubmitListener al = new SubmitListener();
        
        em.createOQ("Matkustamme pian Amerikkaan.", "We will travel to America soon.", " ");
        em.createOQ("Etkö tule huomenna kouluun.", "Won't you come to school tomorrow?", " ");
        em.createOQ("Katson usein televisiota.", "I watch TV often.", " ");
        em.createOQ("Teidän täytyy kiirehtiä!", "You must hurry!", " ");
        em.createOQ("Tuleeko hän kuulemaan tästä?", "Will she hear about this?", " ");
        
        UserInterface ui = new UserInterface(em, al);
        SwingUtilities.invokeLater(ui);
    }
    
}
