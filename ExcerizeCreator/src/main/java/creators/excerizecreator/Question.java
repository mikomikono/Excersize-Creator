package creators.excerizecreator;

public interface Question {
    public String returnAnswer();
    public String returnQuestion();
    public String returnAnswered();
    public boolean returnCorrect();
    public void addAnswered(String answered);
    public void correct();
    public void incorrect();
}
