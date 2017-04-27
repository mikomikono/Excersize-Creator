package creators.exercisecreator.questionlogic;

import creators.exercisecreator.questions.Question;
import java.util.*;

/**
 * Class for managing the QuestionManagers.
 */
public class ExerciseManager {
    private Map<String, QuestionManager> qms;
    private Map<Integer, String> order;
    public int which;
    private FileManager fm;
    private String file;

    public ExerciseManager() {
        this.qms = new HashMap<>();
        this.fm = new FileManager();
        this.order = new HashMap<>();
        this.which = 1;
        this.file = "unspecified";
    }
    
    /**
    * Method for adding a new QuestionManager with a specific topic.
    * @param topic The topic of the questions in the QuestionManager
    */
    public void addQuestionGroup(String topic) {
        this.qms.put(topic, new QuestionManager(topic));
        this.order.put(this.order.size() + 1, topic);
    }
    
    /**
     * Method for adding the filename where the answers will be saved.
     * @param file The filename
     */
    public void addFileName(String file) {
        this.file = file;
//        System.out.println(this.file);
    }

    public List<QuestionManager> getQMs() {
        List<QuestionManager> qm = new ArrayList<>();
        for (QuestionManager q: this.qms.values()) {
            qm.add(q);
        }
        return qm;
    }
    
    public List<String> getTopics() {
        List<String> ts = new ArrayList<>();
        for (String t: this.qms.keySet()) {
            ts.add(t);
        }
        return ts;
    }
    
    /**
     * Method for adding a question into a QuestionManager
     * @param topic The topic of the QuestionManager
     * @param type The type of the question
     * @param question The question
     * @param notes Any notes that the question has
     * @param answer The correct answer to the question
     */
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
            case 2:
                qm.createEssay(question, notes, answer);
                break;
            default:
                qm.createFeedback(question);
        }
    }
    /**
     * Method for adding info the the QuesionManager.
     * @param topic The topic of the QuestionManager
     * @param info The info that is added
     */
    public void addInfo(String topic, String info) {
        getQM(topic).addInfo(info);
    }
    
    /**
     * Method for answering a question with the topic of the QuestionManager.
     * @param topic The topic of the QuestionManager the question is in
     * @param question The question that is answered
     * @param answer The answer that is given
     */
    public void addAnswer(String topic, String question, String answer) {
        getQM(topic).answer(question, answer);
    }
    
    /**
     * Method for anwering a question with the QuestionManager.
     * @param qm The QuestionManager the question is in
     * @param question The question that is answered
     * @param answer The answer that is given
     */
    public void addAnswer(QuestionManager qm, String question, String answer) {
        qm.answer(question, answer);
    }
    
    public List<String> getQMsQuestions(String topic) {
        return getQM(topic).getQuestionStrings();
    }
    
    public QuestionManager getQM() {
        return this.getQM(this.order.get(this.which));
    }
    
    public QuestionManager getQM(int i) {
        return this.qms.get(this.order.get(i));
    }
    
    /**
     * Method for switching to the next QuestionManager in the list.
     */
    public void nextQM() {
        if (this.which < this.getQMs().size()) {
            this.which++;
        }
    }
    
    /**
     * Method for switching to the previous QuestionManager in the list.
     */
    public void prevQM() {
        if (this.which > 1) {
            this.which--;
        }
    }
    
    private QuestionManager getQM(String topic) {
        if (!this.qms.keySet().contains(topic)) {
            this.qms.put(topic, new QuestionManager(topic));
            this.order.put(this.order.size(), topic);
        }
        return this.qms.get(topic);
    }
    
    private QuestionManager getQM(String topic, int order) {
        if (!this.qms.keySet().contains(topic)) {
            this.qms.put(topic, new QuestionManager(topic));
            this.order.put(order, topic);
        }
        return this.qms.get(topic);
    }
    
    /**
     * Method for creating a QuestionManager from questions saved into a file
     * @param file Location of the file
     */
    public void read(String file) {
        List<String> lines = this.fm.read(file);
        for (String line: lines) {
            String[] data = line.split("~");
            if (data[1].equals("info")) {
                QuestionManager qm = getQM(data[2], Integer.parseInt(data[3]));
                for (int i = 4; i < data.length; i++) {
                    qm.addInfo(data[i]);
                }
            } else {
                QuestionManager qm = getQM(data[2], Integer.parseInt(data[1]));
                switch (data[3]) {
                    case "0":
                        Boolean b = Boolean.FALSE;
                        if (data[6].equals("true")) {
                            b = Boolean.TRUE;
                        }
                        qm.createTOF(data[4], data[5], b);
                        break;
                    case "1":
                        qm.createOQ(data[4], data[5], data[6]);
                        break;
                    case "2":
                        qm.createEssay(data[4], data[5], data[6]);
                        break;
                    default:
                        qm.createFeedback(data[4]);
                        break;
                }
            }
        }
    }
    
    /**
     * Method for saving a question onto a file.
     * @param question The question to be saved
     * @return Returns a string depending on the success of the saving
     */
    public String save(String question) {
        for (QuestionManager qm: this.getQMs()) {
            for (Question qs: qm.getQuestions()) {
                if (qs.returnQuestion().equals(question)) {
//                    System.out.println(qm + "; " + qs.toString());
                    String success = this.fm.write(qm + "; " + qs.toString(), this.file);
                    if (success.equals("Failed to save exercize.")) {
                        return success;
                    }
                }
            }
        }
        return "Saved!";
    }
}