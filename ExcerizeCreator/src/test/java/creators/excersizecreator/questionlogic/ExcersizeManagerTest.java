package creators.excersizecreator.questionlogic;

import org.junit.*;
import static org.junit.Assert.*;

public class ExcersizeManagerTest {
    private ExcersizeManager em;
    
    public ExcersizeManagerTest() {
    }
    
    @Before
    public void setUp() {
        this.em = new ExcersizeManager();
        this.em.addQuestionGroup();
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
        this.em.addQuestion(0, 0, "wah", "wah", "true");
        assertEquals(this.em.returnQMs().get(0).questions.get(0).returnAnswer(), "T");
    }
    
    @Test
    public void addingOQWorks() {
        this.em.addQuestion(0, 1, "weh", "whe", "oops");
        assertNotNull(this.em.returnQMs().get(0).questions.get(0));
    }
    
    @Test
    public void addingEssayWorks() {
        this.em.addQuestion(0, 2, "hah", "heh", "haaaah");
        assertNotNull(this.em.returnQMs().get(0).questions.get(0));
    }
    
    @Test
    public void addingAnswersWorks() {
        this.em.addQuestion(0, 1, "hello", "nah", "hello");
        this.em.addAnswer(0, "hello", "hello");
        assertNotNull(this.em.returnQMs().get(0).questions.get(0).returnAnswered());
    }
    
    @Test
    public void returningQuestionsWorks() {
        this.em.addQuestion(0, 3, "ye", "ha", "hoo");
        assertNotNull(this.em.returnQMsQuestions(0).get(0));
    }
    
    @Test
    public void createsNewQMIfIndexTooBig() {
        this.em.addQuestion(1, 0, "ye", "ya", "yo");
        assertNotNull(this.em.returnQMs().get(1));
    }
}
