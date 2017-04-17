package creators.exercisecreator.questionlogic;

import creators.exercisecreator.questions.Essay;
import creators.exercisecreator.questions.Feedback;
import creators.exercisecreator.questions.TrueOrFalse;
import creators.exercisecreator.questions.OpenQuestion;
import creators.exercisecreator.questions.Question;
import java.util.*;

/**
 * Class for managing Questions with the same topic.
 */
public class QuestionManager {
    private List<Question> questions;
    private List<String> info;
    private String topic;
    
    public QuestionManager(String topic) {
        this.questions = new ArrayList<>();
        this.info = new ArrayList<>();
        this.topic = topic;
    }
    
    /**
    * Method for creating a new TrueOrFalse question
    * 
    * @param question The true or false statement
    * @param notes A string to be shown if the question is answered incorrectly
    * @param answer The truthvalue of the statement
    */
    public void createTOF(String question, String notes, Boolean answer) {
        this.questions.add(new TrueOrFalse(question, notes, answer));
    }

    /**
     * Method for additional info on the excersize
     * 
     * @param info The info
     */
    public void addInfo(String info) {
        this.info.add(info);
    }
    
    /**
    * Method for creating a new OpenQuestion question
    * 
    * @param question The question that needs to be answered
    * @param notes A string to be shown if the question is answered incorrectly
    * @param answer The example answer of the question
    */    
    public void createOQ(String question, String notes, String answer) {
        this.questions.add(new OpenQuestion(question, notes, answer));
    }
    
    /**
    * Method for creating a new Essay question
    * 
    * @param question The topic of the essay
    * @param notes A string to be shown after submitting an answer
    * @param answer Things that the answer should have
    */
    public void createEssay(String question, String notes, String answer) {
        this.questions.add(new Essay(question, notes, answer));
    }
    
    /**
     * Method for creating a new feedback question
     * 
     * @param question The question to get feedback on
     */
    public void createFeedback(String question) {
        this.questions.add(new Feedback(question));
    }
    
    /**
     * Method for answering a question.
     * 
     * @param question The question that is being answered
     * @param answered The answer that is being given
     */
    public void answer(String question, String answered) {
        for (Question q : this.questions) {
            if (q.returnQuestion().equals(question)) {
                q.addAnswered(question, answered);
            }
        }
    }
    
    
    public List<Question> returnQuestions() {
        return this.questions;
    }
    
    public List<String> returnQuestionStrings() {
        List<String> qs = new ArrayList<>();
        for (Question q : this.questions) {
            qs.add(q.returnQuestion());
        }
        return qs;
    }
    
    public List<Boolean> returnCorrects() {
        List<Boolean> cs = new ArrayList<>();
        for (Question q : this.questions) {
            cs.add(q.returnCorrect());
        }
        return cs;
    }
    
    public Boolean returnCorrect(String question) {
        Question q = null;
        for (Question qs: this.questions) {
            if (qs.returnQuestion().equals(question)) {
                q = qs;
            }
        }
        if (q == null) {
            return false;
        }
        return q.returnCorrect();
    }
    
    public List<String> returnInfo() {
        return this.info;
    }
    
    public String returnNotes(String question) {
        Question q = null;
        for (Question qs: this.questions) {
            if (qs.returnQuestion().equals(question)) {
                q = qs;
            }
        }
        if (q == null) {
            return "";
        }
        return q.returnNotes();
    }
    
    /**
     * Method for checking if the questions have been answered correctly.
     */
    public void check() {
        for (Question q: this.questions) {
            q.returnCorrect();
        }
    }
    
    @Override
    public String toString() {
        return this.topic;
    }
}
