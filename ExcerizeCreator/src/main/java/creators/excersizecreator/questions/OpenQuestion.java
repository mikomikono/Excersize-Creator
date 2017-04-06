package creators.excersizecreator.questions;

/**
 * Class for questions that can be answered with anything.
 */
public class OpenQuestion implements Question {
    private Boolean correct;
    private final String question;
    private final String notes;
    private final String answer;
    private String answered;

    public OpenQuestion(String question, String notes, String answer) {
//        this.correct = false;
        this.question = question;
        this.notes = notes;
        this.answer = answer;
    }

    @Override
    public void addAnswered(String q, String a) {
        if (this.question.equals(q)) {
            this.answered = a;
        }
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
    public String returnNotes() {
        return this.notes;
    }
    
    @Override
    public Boolean returnCorrect() {
        check();
        return this.correct;
    }
    
    public void check() {
        if (this.answered.contains(this.answer)) {
            this.correct = Boolean.TRUE;
        } else {
            this.correct = Boolean.FALSE;
        }
    }
    
    @Override
    public String toString() {
        return this.question + "|" + this.notes + "|" + this.answer + "|" + this.answered + "|" + this.correct;
    }
}
