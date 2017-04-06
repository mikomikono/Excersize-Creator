package creators.excersizecreator.questions;

/**
 * Interface for the different question types.
 */
public interface Question {
    public String returnAnswer();
    public String returnQuestion();
    public String returnAnswered();
    public Boolean returnCorrect();
    public String returnNotes();
    public void addAnswered(String q, String a);
}
