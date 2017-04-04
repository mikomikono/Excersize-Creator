package creators.excersizecreator.questions;

public interface Question {
    public String returnAnswer();
    public String returnQuestion();
    public String returnAnswered();
    public Boolean returnCorrect();
    public String returnNotes();
    public void addAnswered(String q, String a);
}
