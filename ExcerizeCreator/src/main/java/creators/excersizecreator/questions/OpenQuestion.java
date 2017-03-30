package creators.excersizecreator.questions;

import java.util.*;

public class OpenQuestion implements Question {
    private List<Boolean> correct;
    private String question;
    private String notes;
    private List<String> answer;
    private List<String> answered;

    public OpenQuestion(String question, String notes, List<String> answer) {
//        this.correct = false;
        this.question = question;
        this.notes = notes;
        this.answer = answer;
        this.answered = new ArrayList<>();
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
    public List<String> returnAnswers() {
        return this.answer;
    }

    @Override
    public String returnQuestion() {
        return this.question;
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
        if (this.answered.get(0).equals(this.answer.get(0))) {
            this.correct.add(Boolean.TRUE);
        } else {
            this.correct.add(Boolean.FALSE);
        }
    }
}
