package creators.excersizecreator.questionlogic;

import java.util.*;

public class ExcersizeManager {
    private List<QuestionManager> qms;
    private FileManager fm;

    public ExcersizeManager() {
        this.qms = new ArrayList<>();
        this.fm = new FileManager();
    }
    
    public void addQuestionGroup() {
        this.qms.add(new QuestionManager());
    }
    
    public List<QuestionManager> returnQMs() {
        return this.qms;
    }
    
    public void addQuestion(int i, int type, String question, String notes, String answer) {
        QuestionManager qm = getQM(i);
        switch (type) {
            case 0:
                Boolean b = Boolean.FALSE;
                if (answer.equals("true")) {
                    b = Boolean.TRUE;
                }
                qm.createTOF(question, notes, b);
                break;
            case 1:
                qm.createOQ(question, notes, answer);
                break;
            default:
                qm.createEssay(question, notes, answer);
                break;
        }
    }
    
    public void addAnswer(int i, String question, String answer) {
        QuestionManager qm = getQM(i);
        qm.answer(question, answer);
    }
    
    public List<String> returnQMsQuestions(int i) {
        QuestionManager qm = getQM(i);
        return qm.returnQuestionStrings();
    }
    
    private QuestionManager getQM(int i) {
        QuestionManager qm = null;
        if (i < qms.size()) {
            qm = qms.get(i);
        } else {
            qm = new QuestionManager();
            qms.add(qm);
        }
        return qm;
    }
}