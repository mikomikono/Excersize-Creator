package creators.excersizecreator.questionlogic;

import creators.excersizecreator.questions.*;
import java.util.*;

public class QuestionManager {
    public List<Question> questions;
    
    public QuestionManager() {
        this.questions = new ArrayList<>();
    }
    
    public void createTOF(String question, String notes, Boolean choices) {
        TrueOrFalse mc = new TrueOrFalse(question, notes, choices);
        this.questions.add(mc);
    }
    
    public void createOQ(String question, String notes, String answer) {
        OpenQuestion oq = new OpenQuestion(question, notes, answer);
        this.questions.add(oq);
    }
    
    public void createEssay(String question, String notes, String answer) {
        Essay es = new Essay(question, notes, answer);
        this.questions.add(es);
    }
    
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
    
    public void check() {
        for (Question q: this.questions) {
            q.returnCorrect();
        }
    }
}
