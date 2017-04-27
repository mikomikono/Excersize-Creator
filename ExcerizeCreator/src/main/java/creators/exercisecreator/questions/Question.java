package creators.exercisecreator.questions;

/**
 * Interface for the different question types.
 */
public interface Question {
    public String returnAnswer();
    public String returnQuestion();
    public String returnAnswered();
    public Boolean returnCorrect();
    public String returnNotes();
    public int returnType();
    public void addAnswered(String q, String a);
    public void check();
}
