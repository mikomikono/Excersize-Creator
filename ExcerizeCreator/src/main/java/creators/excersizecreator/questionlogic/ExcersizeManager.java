package creators.excersizecreator.questionlogic;

import java.util.*;

public class ExcersizeManager {
    private QuestionManager qm;

    public ExcersizeManager() {
        this.qm = new QuestionManager();
    }
    
    public void createMC() {
        
    }
    
    public void createOQ(String question, String answer, String notes) {

    }
    
    public void createEssay() {
        
    }
    
    public List<String> returnQuestion() {
        List<String> q = this.qm.returnQuestionStrings();
        return q;
    }
}
