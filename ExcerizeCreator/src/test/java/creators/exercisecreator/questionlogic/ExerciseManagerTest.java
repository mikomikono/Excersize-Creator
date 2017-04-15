package creators.exercisecreator.questionlogic;

import creators.exercisecreator.questionlogic.ExerciseManager;
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
        assertNotNull(this.em.returnQMs().get(0));
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
        assertEquals("hello", this.em.returnQMs().get(0).returnQuestions().get(0).returnAnswered());
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
}
