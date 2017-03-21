package creators.excerizecreator;

import java.util.*;

public class MultipleChoice implements Question {
    private boolean correct;
    private String question;
    private String answer;
    private List<String> choices;
    private String answered;
    
    public MultipleChoice(String question, String answer, List<String> choices) {
//        this.correct = false;
        this.question = question;
        this.answer = answer;
        this.choices = choices;
        this.choices.add(answer);
        this.answered = "";
    }
    
    @Override
    public void addAnswered(String answered) {
        this.answered = answered;
    }
    
    @Override
    public String returnAnswered() {
        return this.answered;
    }

    @Override
    public String returnAnswer() {
        return this.answer;
    }

    @Override
    public String returnQuestion() {
        return this.question;
    }
    
    @Override
    public boolean returnCorrect() {
        return this.correct;
    }

    @Override
    public void correct() {
        this.correct = true;
    }
    
    @Override
    public void incorrect() {
        this.correct = false;
    }
}
