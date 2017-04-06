package creators.excersizecreator.questionlogic;

import java.util.*;

/**
 * Class for managing the QuestionManagers.
 */
public class ExcersizeManager {
    private Map<String, QuestionManager> qms;
    private FileManager fm;

    public ExcersizeManager() {
        this.qms = new HashMap<>();
        this.fm = new FileManager();
    }
    
    /**
    * Method for adding a new QuestionManager with a specific topic.
    * 
    * @param topic The topic of the questions in the QuestionManager
    */
    public void addQuestionGroup(String topic) {
        this.qms.put(topic, new QuestionManager());
    }

    public List<QuestionManager> returnQMs() {
        List<QuestionManager> qm = new ArrayList<>();
        for (QuestionManager q: this.qms.values()) {
            qm.add(q);
        }
        return qm;
    }
    
    public List<String> returnsTopics() {
        List<String> ts = new ArrayList<>();
        for (String t: this.qms.keySet()) {
            ts.add(t);
        }
        return ts;
    }
    
    public void addQuestion(String topic, int type, String question, String notes, String answer) {
        QuestionManager qm = getQM(topic);
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
    
    public void addAnswer(String topic, String question, String answer) {
        QuestionManager qm = getQM(topic);
        qm.answer(question, answer);
    }
    
    public List<String> returnQMsQuestions(String topic) {
        QuestionManager qm = getQM(topic);
        return qm.returnQuestionStrings();
    }
    
    private QuestionManager getQM(String topic) {
        if (!this.qms.keySet().contains(topic)) {
            this.qms.put(topic, new QuestionManager());
        }
        return this.qms.get(topic);
    }
    
    /**
     * Method for creating a QuestionManager from questions saved into a file
     * 
     * @param file Location of the file
     */
    public void read(String file) {
        
    }
    
    /**
     * Method for saving a question onto a file.
     * 
     * @param question The question to be saved
     * @param file The location of the file
     */
    public void save(String question, String file) {
        
    }
}