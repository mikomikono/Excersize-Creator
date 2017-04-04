package creators.excersizecreator.questions;

public class TrueOrFalse implements Question {
    private Boolean correct;
    private final String question;
    private final String notes;
    private final Boolean answer;
    private Boolean answered;
    
    public TrueOrFalse(String question, String notes, Boolean correct) {
        this.question = question;
        this.notes = notes;
        this.answer = correct;
    }
    
    @Override
    public void addAnswered(String q, String a) {
        if (a.equals("true")) {
            this.answered = Boolean.TRUE;
        } else {
            this.answered = Boolean.FALSE;
        }
    }
    
    @Override
    public String returnAnswered() {
        if (this.answered == null) {
            return null;
        } else if (this.answered) {
            return "T";
        } else {
            return "F";
        }
    }

    @Override
    public String returnAnswer() {
        if (this.answer) {
            return "T";
        } else {
            return "F";
        }
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
        if (this.correct == null) {
            this.check();
        }
        return this.correct;
    }
    
    public void check() {
        if (this.answer.equals(this.answered)) {
            this.correct = Boolean.TRUE;
        } else {
            this.correct = Boolean.FALSE;
        }
    }
}
