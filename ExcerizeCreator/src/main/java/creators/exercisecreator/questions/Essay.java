package creators.exercisecreator.questions;

/**
 * Class for essay questions.
 */
public class Essay implements Question {
    private final Boolean correct;
    private final String question;
    private final String notes;
    private final String answer;
    private String answered;

    public Essay(String question, String notes, String answer) {
        this.correct = Boolean.FALSE;
        this.question = question;
        this.notes = notes;
        this.answer = answer;
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
    public String returnNotes() {
        return this.notes;
    }
    
    @Override
    public Boolean returnCorrect() {
        return this.correct;
    }
    
    @Override
    public int returnType() {
        return 2;
    }
    
    @Override
    public void check() {
    }
    
    @Override
    public String toString() {
        return "question: " + this.question + "; answer: " + this.answered;
    }
}
