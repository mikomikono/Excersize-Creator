package creators.exercisecreator.questionlogic;

import creators.exercisecreator.questionlogic.QuestionManager;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class QuestionManagerTest {
    private QuestionManager qm;
    
    public QuestionManagerTest() {
    }
    
    @Before
    public void setUp() {
        this.qm = new QuestionManager("Rickroll");
    }

    @Test
    public void managerNotNull() {
        assertNotNull(this.qm);
    }
    
    @Test
    public void managerCreatesTOF() {
        this.qm.createTOF("We're no strangers to love", "You know the rules, and so do I", Boolean.TRUE);
        assertNotNull(this.qm.returnQuestions().get(0));
    }
    
    @Test
    public void managerCreatesOpenQues() {
        this.qm.createOQ("A full commitment's what I'm thinking of", "You wouldn't get this from any other guy", "I just wanna tell you how I'm feeling");
        assertNotNull(this.qm.returnQuestions().get(0));
    }
    
    @Test
    public void managerCreatesEssay() {
        this.qm.createEssay("Gotta make you understand", "Never gonna give you up", "Never gonna let you down");
        assertNotNull(this.qm.returnQuestions().get(0));
    }
    
    @Test
    public void tOFReturnsAnswer() {
        this.qm.createTOF("Never gonna run around", "And desert you", Boolean.TRUE);
        this.qm.createTOF("Never gonna make you cry", "Never gonna say goodbye", Boolean.FALSE);
        assertEquals(this.qm.returnQuestions().get(0).returnAnswer(), "T");
        assertEquals(this.qm.returnQuestions().get(1).returnAnswer(), "F");
    }
    
    @Test
    public void openQuesReturnsAnswer() {
        this.qm.createOQ("Never gonna give", "Never gonna give", "give you up");
        assertNotNull(this.qm.returnQuestions().get(0).returnAnswer());
    }
    
    @Test
    public void essayReturnsAnswer() {
        this.qm.createEssay("Nevet gonna give", "Never gonna give", "Give you up");
        assertNotNull(this.qm.returnQuestions().get(0).returnAnswer());
    }
    
    @Test
    public void tOFAnsweringWorks() {
        this.qm.createTOF("We've known eachother", "for so long", Boolean.TRUE);
        this.qm.createTOF("We've known eachother2", "for so long", Boolean.TRUE);
        this.qm.createTOF("We've known eachother3", "for so long", Boolean.TRUE);
        this.qm.answer("We've known eachother", "true");
        this.qm.answer("We've known eachother2", "false");
        assertEquals(this.qm.returnQuestions().get(0).returnAnswered(), "T");
        assertEquals(this.qm.returnQuestions().get(1).returnAnswered(), "F");
        assertNull(this.qm.returnQuestions().get(2).returnAnswered());
    }
    
    @Test
    public void openQuesAnsweringWorks() {
        this.qm.createOQ("Your heart's been aching but", "You're too shy to say it", "Inside we both know");
        this.qm.answer("Your heart's been aching but", "What's been going on");
        assertNotNull(this.qm.returnQuestions().get(0).returnAnswered());
    }
    
    @Test
    public void essayAnsweringWorks() {
        this.qm.createEssay("We know the game", "And we're gonna", "Play it");
        this.qm.answer("We know the game", ":)");
        assertNotNull(this.qm.returnQuestions().get(0).returnAnswered());
    }
    
    @Test
    public void tOFReturnsNotes() {
        this.qm.createTOF("And if you ask me", "How I'm feeling", Boolean.TRUE);
        assertEquals(this.qm.returnQuestions().get(0).returnNotes(), "How I'm feeling");
    }
    
    @Test
    public void openQuesReturnsNotes() {
        this.qm.createOQ("Don't tell me", "You're too blind", "To see");
        assertEquals(this.qm.returnQuestions().get(0).returnNotes(), "You're too blind");
    }
    
    @Test
    public void essayReturnsNotes() {
        this.qm.createEssay("Never gonna give you up", "Never gonna let you down", "Never gonna run around and desert you");
        assertEquals(this.qm.returnQuestions().get(0).returnNotes(), "Never gonna let you down");
    }
    
    @Test
    public void tOFCheckWorks() {
        this.qm.createTOF("Never gonna make you cry", "Never gonna say goodbye", Boolean.TRUE);
        this.qm.createTOF("Never gonna tell a lie", "And hurt you", Boolean.FALSE);
        this.qm.answer("Never gonna make you cry", "true");
        this.qm.answer("Never gonna tell a lie", "true");
        assertTrue(this.qm.returnQuestions().get(0).returnCorrect());
        assertFalse(this.qm.returnQuestions().get(1).returnCorrect());
    }
    
    @Test
    public void openQuesCheckWorks() {
        this.qm.createOQ("Never gonna give", "Never gonna give", "give you up");
        this.qm.createOQ("Never gonna give you up", "Never gonna let you down", "Never gonna run around and desert you!");
        this.qm.answer("Never gonna give", "give you up");
        this.qm.answer("Never gonna give you up", "Never gonna give");
        assertTrue(this.qm.returnQuestions().get(0).returnCorrect());
        assertFalse(this.qm.returnQuestions().get(1).returnCorrect());
    }
    
    @Test
    public void essayCheckWorks() {
        this.qm.createEssay("Never gonna make you cry", "Never gonna say goodbye", "Never gonna tell a lie and hurt you");
        this.qm.answer("Never gonna make you cry", "Never gonna give you up");
        assertFalse(this.qm.returnQuestions().get(0).returnCorrect());
    }
    
    @Test
    public void managerReturnsQuestionStrings() {
        this.qm.createEssay("We've known eachother", "For so long", "Your heart's been aching");
        this.qm.createOQ("But you're too shy to say it", "Inside we both know", "What's been going on");
        this.qm.createTOF("We know the game and", "We're gonna play it", Boolean.TRUE);
        List<String> qs = new ArrayList<>();
        qs.add("We've known eachother");
        qs.add("But you're too shy to say it");
        qs.add("We know the game and");
        assertEquals(this.qm.returnQuestionStrings(), qs);
    }
    
    @Test
    public void managerReturnsCorrects() {
        this.qm.createEssay("And if you ask me", "How I'm feeling", "Don't tell me you're too blind to see");
        this.qm.createOQ("Never gonna give you up", "Never gonna let you down", "Never gonna run around and desert you");
        this.qm.createTOF("Never gonna make you cry", "Never gonna say goodbye", Boolean.TRUE);
        this.qm.answer("And if you ask me", "How I'm feeling");
        this.qm.answer("Never gonna give you up", "give you up");
        this.qm.answer("Never gonna make you cry", "true");
        List<Boolean> cs = new ArrayList<>();
        cs.add(Boolean.FALSE);
        cs.add(Boolean.FALSE);
        cs.add(Boolean.TRUE);
        assertEquals(this.qm.returnCorrects(), cs);
    }
    
    @Test
    public void checkingWorks() {
        this.qm.createEssay("Never gonna", "Never gonna", "give you up");
        this.qm.createOQ("Never gonna give", "Never gonna give", "give you up");
        this.qm.answer("Never gonna give", "give you up");
        this.qm.answer("Never gonna", "give you up");
        this.qm.check();
        assertEquals(Boolean.FALSE, this.qm.returnQuestions().get(0).returnCorrect());
        assertEquals(Boolean.TRUE, this.qm.returnQuestions().get(1).returnCorrect());
    }
    
//    @Test
//    public void tOFtoStringCorrect() {
//        this.qm.createTOF("Never gonna give you up", "Never gonna let you down", Boolean.TRUE);
//        assertEquals("Never gonna give you up~Never gonna let you down~true~null~null", this.qm.returnQuestions().get(0).toString());
//    }
//    
//    @Test
//    public void oQtoStringCorrect() {
//        this.qm.createOQ("Never gonna", "Run around", "And desert you");
//        assertEquals("Never gonna~Run around~And desert you~null~null", this.qm.returnQuestions().get(0).toString());
//    }
//    
//    @Test
//    public void essaytoStringCorrect() {
//        this.qm.createEssay("Never gonna make you cry", "Never gonna say goodbye", "Never gonna tell a lie");
//        assertEquals("Never gonna make you cry~Never gonna say goodbye~Never gonna tell a lie~null~false", this.qm.returnQuestions().get(0).toString());
//    }
}
