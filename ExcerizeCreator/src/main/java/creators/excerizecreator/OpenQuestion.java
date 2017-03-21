package creators.excerizecreator;

public class OpenQuestion implements Question {
    private boolean correct;
    private String question;
    private String answer;
    private String answered;

    public OpenQuestion(String question, String answer) {
//        this.correct = false;
        this.question = question;
        this.answer = answer;
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
