package creators.excersizecreator.questionlogic;

import creators.excersizecreator.questions.*;
import java.util.*;

/**
 * Class for managing Questions with the same topic.
 */
public class QuestionManager {
    public List<Question> questions;
    
    public QuestionManager() {
        this.questions = new ArrayList<>();
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
    
    /**
     * Method for checking if the questions have been answered correctly.
     */
    public void check() {
        for (Question q: this.questions) {
            q.returnCorrect();
        }
    }
}
