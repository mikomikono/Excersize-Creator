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
        assertEquals("topic", this.em.returnQMs().get(0).toString());
        assertEquals("topic", this.em.getQM(1).toString());
    }
    
    @Test
    public void addingTOFWorks() {
        this.em.addQuestion("topic", 0, "wah", "wah", "true");
        assertEquals("T", this.em.returnQMs().get(0).returnQuestions().get(0).returnAnswer());
    }
    
    @Test
    public void addingOQWorks() {
        this.em.addQuestion("topic", 1, "weh", "whe", "oops");
        assertEquals("weh", this.em.returnQMs().get(0).returnQuestions().get(0).returnQuestion());
    }
    
    @Test
    public void addingEssayWorks() {
        this.em.addQuestion("topic", 2, "hah", "heh", "haaaah");
        assertEquals("hah", this.em.returnQMs().get(0).returnQuestions().get(0).returnQuestion());
    }
    
    @Test
    public void addingAnswersWorks() {
        this.em.addQuestion("topic", 1, "hello", "nah", "hello");
        this.em.addAnswer("topic", "hello", "hello");
        QuestionManager qm = new QuestionManager("hoi");
        qm.createFeedback("hoi!");
        this.em.addAnswer(qm, "hoi!", "hoi!!!");
        assertEquals("hello", this.em.returnQMs().get(0).returnQuestions().get(0).returnAnswered());
        assertEquals("hoi!!!", qm.returnQuestions().get(0).returnAnswered());
    }
    
    @Test
    public void returningQuestionsWorks() {
        this.em.addQuestion("topic", 3, "ye", "ha", "hoo");
        assertEquals("ye", this.em.returnQMsQuestions("topic").get(0));
    }
    
    @Test
    public void createsNewQMIfNewTopic() {
        this.em.addQuestion("topic2", 0, "ye", "ya", "yo");
        assertEquals("topic2", this.em.returnTopics().get(0));
    }
    
    @Test
    public void addsInfo() {
        this.em.addInfo("topic", "hello");
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        assertEquals(list, this.em.getQM().returnInfo());
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
}
