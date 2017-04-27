package creators.exercisecreator.questions;

/**
 *Class for creating a feedback form.
 */
public class Feedback implements Question {
    private final String question;
    private String answered;

    public Feedback(String question) {
        this.question = question;
    }
    
    @Override
    public String returnAnswer() {
        return "";
    }

    @Override
    public String returnQuestion() {
        return this.question;
    }

    @Override
    public String returnAnswered() {
        return this.answered;
    }

    @Override
    public Boolean returnCorrect() {
        return false;
    }

    @Override
    public String returnNotes() {
        return "Thank you for your feedback!";
    }

    @Override
    public void addAnswered(String q, String a) {
        if (this.question.equals(q)) {
            this.answered = a;
        }
    }
    
    @Override
    public int returnType() {
        return 3;
    }
    
    @Override
    public void check() {
    }
    
    @Override
    public String toString() {
        return "question: " + this.question + "; answer: " + this.answered;
    }
}
