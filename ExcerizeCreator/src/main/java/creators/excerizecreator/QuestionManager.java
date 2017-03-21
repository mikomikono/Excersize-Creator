package creators.excerizecreator;

import java.util.*;

public class QuestionManager {
    public List<Question> questions;
    
    public QuestionManager() {
        this.questions = new ArrayList<>();
    }
    
    public void createMC(String question, String answer, List<String> choices) {
        MultipleChoice mc = new MultipleChoice(question, answer, choices);
        this.questions.add(mc);
    }
    
    public void createOQ(String question, String answer) {
        OpenQuestion oq = new OpenQuestion(question, answer);
        this.questions.add(oq);
    }
    
    public void createEssay(String question, String answer) {
        Essay es = new Essay(question, answer);
        this.questions.add(es);
    }
    
    public void answer(String question, String answered) {
        for (Question q : this.questions) {
            if (q.returnQuestion().equals(question)) {
                q.addAnswered(answered);
            }
        }
    }
    
    public List<Question> returnQuestions() {
        return this.questions;
    }
    
    public void checkIfCorrect() {
        for (Question q : this.questions) {
            if (q.returnAnswer().equals(q.returnAnswered())) {
                q.correct();
            } else {
                q.incorrect();
            }
        }
    }
}
