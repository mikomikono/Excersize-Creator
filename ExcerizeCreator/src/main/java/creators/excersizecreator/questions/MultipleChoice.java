package creators.excersizecreator.questions;

import java.util.*;

public class MultipleChoice implements Question {
    private List<Boolean> correct;
    private String question;
    private String notes;
    private Map<String, Boolean> choices;
    private Map<String, Boolean> answered;
    
    public MultipleChoice(String question, String notes, Map<String, Boolean> choices) {
//        this.correct = false;
        this.question = question;
        this.notes = notes;
        this.choices = choices;
        this.answered = new HashMap<>();
    }
    
    @Override
    public void addAnswered(String q, String a) {
//        doesn't actually work atm but by god i need a break
        Boolean b = Boolean.FALSE;
        for (String c: this.choices.keySet()) {
            if (a.equals(c)) {
                b = Boolean.TRUE;
            }
        }
        this.answered.put(a, b);
    }
    
    @Override
    public List<String> returnAnswered() {
        List<String> answers = new ArrayList<>();
        for (Boolean answer: this.answered.values()) {
            if (answer) {
                answers.add("T");
            } else {
                answers.add("F");
            }
        }
        return answers;
    }

    @Override
    public List<String> returnAnswers() {
        List<String> answers = new ArrayList<>();
        for (String choice: this.choices.keySet()) {
            answers.add(choice);
        }
        return answers;
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
        if (this.correct.isEmpty()) {
            this.check();
        }
        return this.correct;
    }
    
    @Override
    public void check() {
        this.correct = new ArrayList<>();
        for (String a: this.answered.keySet()) {
            for (String q: this.choices.keySet()) {
                if (a.equals(q) && this.answered.get(a).equals(this.choices.get(q))) {
                    this.correct.add(Boolean.TRUE);
                } else if (a.equals(q)) {
                    this.correct.add(Boolean.FALSE);
                }
            }
        }
    }
}
