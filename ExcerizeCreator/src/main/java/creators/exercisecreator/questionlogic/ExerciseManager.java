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
        this.which = 0;
        this.file = "unspecified";
    }
    
    /**
    * Method for adding a new QuestionManager with a specific topic.
    * 
    * @param topic The topic of the questions in the QuestionManager
    */
    public void addQuestionGroup(String topic) {
        this.qms.put(topic, new QuestionManager(topic));
        this.order.put(this.order.size() + 1, topic);
    }
    
    public void addFileName(String file) {
        this.file = file;
//        System.out.println(this.file);
    }

    public List<QuestionManager> returnQMs() {
        List<QuestionManager> qm = new ArrayList<>();
        for (QuestionManager q: this.qms.values()) {
            qm.add(q);
        }
        return qm;
    }
    
    public List<String> returnTopics() {
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
            case 2:
                qm.createEssay(question, notes, answer);
                break;
            default:
                qm.createFeedback(question);
        }
    }
    
    public void addInfo(String topic, String info) {
        getQM(topic).addInfo(info);
    }
    
    public void addAnswer(String topic, String question, String answer) {
        getQM(topic).answer(question, answer);
    }
    
    public void addAnswer(QuestionManager qm, String question, String answer) {
        qm.answer(question, answer);
    }
    
    public List<String> returnQMsQuestions(String topic) {
        return getQM(topic).returnQuestionStrings();
    }
    
    public QuestionManager getQM() {
        return this.getQM(this.order.get(this.which));
    }
    
    public QuestionManager getQM(int i) {
        return this.qms.get(this.order.get(i));
    }
    
    public void nextQM() {
        if (this.which < this.returnQMs().size()) {
            this.which++;
        }
    }
    
    public void prevQM() {
        if (this.which > 0) {
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
     * 
     * @param file Location of the file
     */
    public void read(String file) {
        List<String> lines = this.fm.read(file);
        for (String line: lines) {
            String[] data = line.split("~");
            if (data[1].equals("info")){
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
     * 
     * @param question The question to be saved
     */
    public void save(String question) {
        for (QuestionManager qm: this.returnQMs()) {
            for (Question qs: qm.returnQuestions()) {
                if (qs.returnQuestion().equals(question)) {
//                    System.out.println(qm + "; " + qs.toString());
                    this.fm.write(qm + "; " + qs.toString(), this.file + ".txt");
                }
            }
        }
    }
}