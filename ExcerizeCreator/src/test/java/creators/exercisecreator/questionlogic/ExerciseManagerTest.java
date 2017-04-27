package creators.exercisecreator.questionlogic;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

public class ExerciseManagerTest {
    private ExerciseManager em;
    
    public ExerciseManagerTest() {
    }
    
    @Before
    public void setUp() {
        this.em = new ExerciseManager();
        this.em.addQuestionGroup("topic");
    }
    
    @Test
    public void managerNotNull() {
        assertNotNull(this.em);
    }
    
    @Test
    public void managerAddsNewQM() {
        assertEquals("topic", this.em.getQMs().get(0).toString());
        assertEquals("topic", this.em.getQM(1).toString());
    }
    
    @Test
    public void addingTOFWorks() {
        this.em.addQuestion("topic", 0, "wah", "wah", "true");
        assertEquals("T", this.em.getQMs().get(0).getQuestions().get(0).returnAnswer());
    }
    
    @Test
    public void addingOQWorks() {
        this.em.addQuestion("topic", 1, "weh", "whe", "oops");
        assertEquals("weh", this.em.getQMs().get(0).getQuestions().get(0).returnQuestion());
    }
    
    @Test
    public void addingEssayWorks() {
        this.em.addQuestion("topic", 2, "hah", "heh", "haaaah");
        assertEquals("hah", this.em.getQMs().get(0).getQuestions().get(0).returnQuestion());
    }
    
    @Test
    public void addingAnswersWorks() {
        this.em.addQuestion("topic", 1, "hello", "nah", "hello");
        this.em.addAnswer("topic", "hello", "hello");
        QuestionManager qm = new QuestionManager("hoi");
        qm.createFeedback("hoi!");
        this.em.addAnswer(qm, "hoi!", "hoi!!!");
        assertEquals("hello", this.em.getQMs().get(0).getQuestions().get(0).returnAnswered());
        assertEquals("hoi!!!", qm.getQuestions().get(0).returnAnswered());
    }
    
    @Test
    public void returningQuestionsWorks() {
        this.em.addQuestion("topic", 3, "ye", "ha", "hoo");
        assertEquals("ye", this.em.getQMsQuestions("topic").get(0));
    }
    
    @Test
    public void createsNewQMIfNewTopic() {
        this.em.addQuestion("topic2", 0, "ye", "ya", "yo");
        assertEquals("topic2", this.em.getTopics().get(0));
    }
    
    @Test
    public void addsInfo() {
        this.em.addInfo("topic", "hello");
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        assertEquals(list, this.em.getQM().getInfo());
    }
    
    @Test
    public void nextQMWorks() {
        this.em.addQuestionGroup("ya");
        this.em.nextQM();
        assertEquals(2, this.em.which);
    }
    
    @Test
    public void prevQMWorks() {
        this.em.addQuestionGroup("ye");
        this.em.nextQM();
        this.em.prevQM();
        assertEquals(1, this.em.which);
    }
    
    @Test
    public void whichDoesntGoUpIfNoNext() {
        this.em.nextQM();
        assertEquals(1, this.em.which);
    }
    
    @Test
    public void whichDoesntGoDownIfNoPrev() {
        this.em.prevQM();
        assertEquals(1, this.em.which);
    }
    
    @Test
    public void readsFileCorrectly() {
        this.em.read("test.txt");
        assertEquals("This is info", this.em.getQM(5).getInfo().get(0));
        assertEquals("Would you?", this.em.getQM(5).getQuestions().get(0).returnQuestion());
        assertEquals("A question can be open, right?", this.em.getQM(5).getQuestions().get(1).returnQuestion());
        assertEquals("Essays, am I right?", this.em.getQM(5).getQuestions().get(2).returnQuestion());
        assertEquals("Would you care to feedback?", this.em.getQM(5).getQuestions().get(3).returnQuestion());
//        assertEquals(this.em.getQM(5));
    }
    
    @Test
    public void readsFileIncorrectly() {
        this.em.read("testi.txt");
        assertEquals("No Exercizes found.", this.em.getQM(1).getInfo().get(0));
    }
    
    @Test
    public void savesFileCorrectly() {
        this.em.addQuestion("topic", 0, "Yes?", "Yes.", "true");
        this.em.addAnswer(this.em.getQM(), "Yes?", "true");
        this.em.addFileName("testanswer.txt");
        String saved = this.em.save("Yes?");
        assertEquals("Saved!", saved);
    }
    
    //I don't know how to get an exception in the filemanager lol orz
//    @Test
//    public void savesFileIncorrectly() {
//        this.em.addQuestion("topic", 0, "Yes?", "Yes.", "true");
//        this.em.addAnswer(this.em.getQM(), "Yes?", "true");
//        this.em.addFileName("testanswer");
//        String saved = this.em.save("Yes?");
//        assertEquals("Failed to save exercize." , saved);
//    }
}
