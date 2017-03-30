package creators.excersizecreator.questions;

import java.util.List;

public interface Question {
    public List<String> returnAnswers();
    public String returnQuestion();
    public List<String> returnAnswered();
    public List<Boolean> returnCorrect();
    public String returnNotes();
    public void check();
    public void addAnswered(String q, String a);
}
