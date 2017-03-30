package creators.excersizecreator.questionlogic;

import creators.excersizecreator.questions.OpenQuestion;
import creators.excersizecreator.questions.MultipleChoice;
import creators.excersizecreator.questions.Question;
import creators.excersizecreator.questions.Essay;
import java.util.*;

public class QuestionManager {
    public List<Question> questions;
    
    public QuestionManager() {
        this.questions = new ArrayList<>();
    }
    
    public void createMC(String question, String notes, Map<String, Boolean> choices) {
        MultipleChoice mc = new MultipleChoice(question, notes, choices);
        this.questions.add(mc);
    }
    
    public void createOQ(String question, String notes, List<String> answer) {
        OpenQuestion oq = new OpenQuestion(question, notes, answer);
        this.questions.add(oq);
    }
    
    public void createEssay(String question, String notes, List<String> answer) {
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
    
    public void checkIfCorrect() {
        for (Question q : this.questions) {
            q.check();
        }
    }
}
