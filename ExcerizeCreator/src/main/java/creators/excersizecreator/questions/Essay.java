package creators.excersizecreator.questions;

import creators.excersizecreator.questions.Question;
import java.util.*;

public class Essay implements Question {
    private List<Boolean> correct;
    private String question;
    private String notes;
    private List<String> answer;
    private List<String> answered;

    public Essay(String question, String notes, List<String> answer) {
        this.question = question;
        this.notes = notes;
        this.answer = answer;
        this.answered = new ArrayList<>();
    }

    @Override
    public List<String> returnAnswers() {
        return this.answer;
    }

    @Override
    public String returnQuestion() {
        return this.question;
    }

    @Override
    public void addAnswered(String q, String a) {
        if (this.question.equals(q)) {
            this.answered.add(a);
        }
    }

    @Override
    public List<String> returnAnswered() {
        return this.answered;
    }
    
    @Override
    public String returnNotes() {
        return this.notes;
    }
    
    @Override
    public List<Boolean> returnCorrect() {
        check();
        return this.correct;
    }
    
    @Override
    public void check() {
        this.correct = new ArrayList<>();
    }
}
