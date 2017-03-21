package creators.exersizecreator;

import creators.excerizecreator.QuestionManager;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class QuestionManagerTest {
    private QuestionManager qm;
    
    public QuestionManagerTest() {
    }
    
    @Before
    public void setUp() {
        this.qm = new QuestionManager();
    }

    @Test
    public void managerNotNull() {
        assertNotNull(this.qm);
    }
    
    @Test
    public void managerCreatesMultiChoice() {
        this.qm.createMC("What is love", "Baby don't hurt me", new ArrayList<>());
        assertNotNull(this.qm.returnQuestions().get(0));
    }
    
    @Test
    public void managerCreatesOpenQues() {
        this.qm.createOQ("Never gonna give you up", "Never gonna let you down");
        assertNotNull(this.qm.returnQuestions().get(0));
    }
    
    @Test
    public void managerCreatesEssay() {
        this.qm.createEssay("Memes", "brilliant");
        assertNotNull(this.qm.returnQuestions().get(0));
    }
    
    @Test
    public void multiChoiceReturnsAnswer() {
        this.qm.createMC("Best Pepe", "Rarest Pepe", new ArrayList<>());
        assertNotNull(this.qm.returnQuestions().get(0).returnAnswer());
    }
    
    @Test
    public void openQuesReturnsAnswer() {
        this.qm.createOQ("Who wants to live forever", "Queen");
        assertNotNull(this.qm.returnQuestions().get(0).returnAnswer());
    }
    
    @Test
    public void essayReturnsAnswer() {
        this.qm.createEssay("Speaking of Queen", "Freddie Mercury is the best");
        assertNotNull(this.qm.returnQuestions().get(0).returnAnswer());
    }
    
    @Test
    public void multiChoiceAnsweringWorks() {
        this.qm.createMC("Can you feel the love tonight", "It is where we are", new ArrayList<>());
        this.qm.answer("Can you feel the love tonight", "No.");
        assertEquals(this.qm.returnQuestions().get(0).returnAnswered(), "No.");
    }
    
    @Test
    public void openQuesAnsweringWorks() {
        this.qm.createOQ("Do you like waffles", "Yea we like waffles");
        this.qm.answer("Do you like waffles", "Man that's an old ref");
        assertEquals(this.qm.returnQuestions().get(0).returnAnswered(), "Man that's an old ref");
    }
    
    @Test
    public void essayAnsweringWorks() {
        this.qm.createEssay("Memes?", "Memes.");
        this.qm.answer("Memes?", "Yes.");
        assertEquals(this.qm.returnQuestions().get(0).returnAnswered(), "Yes.");
    }
    
    @Test
    public void managerCorrectsCorrectly() {
        this.qm.createMC("0", "2", new ArrayList<>());
        this.qm.createMC("1", "2", new ArrayList<>());
        this.qm.createOQ("2", "3");
        this.qm.createOQ("3", "6");
        this.qm.createEssay("4", "10");
        this.qm.createEssay("5", "1");
        this.qm.answer("0", "2");
        this.qm.answer("1", "1");
        this.qm.answer("2", "3");
        this.qm.answer("3", "2");
        this.qm.answer("4", "2");
        this.qm.answer("5", "1");
        this.qm.checkIfCorrect();
        assertTrue(this.qm.returnQuestions().get(0).returnCorrect());
        assertFalse(this.qm.returnQuestions().get(1).returnCorrect());
        assertTrue(this.qm.returnQuestions().get(2).returnCorrect());
        assertFalse(this.qm.returnQuestions().get(3).returnCorrect());
        assertFalse(this.qm.returnQuestions().get(4).returnCorrect());
        assertTrue(this.qm.returnQuestions().get(5).returnCorrect());
    }
}
