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
        assertEquals("T", this.qm.returnQuestions().get(0).returnAnswered());
        assertEquals("F", this.qm.returnQuestions().get(1).returnAnswered());
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
        assertEquals("How I'm feeling", this.qm.returnQuestions().get(0).returnNotes());
    }
    
    @Test
    public void openQuesReturnsNotes() {
        this.qm.createOQ("Don't tell me", "You're too blind", "To see");
        assertEquals("You're too blind", this.qm.returnQuestions().get(0).returnNotes());
    }
    
    @Test
    public void essayReturnsNotes() {
        this.qm.createEssay("Never gonna give you up", "Never gonna let you down", "Never gonna run around and desert you");
        assertEquals("Never gonna let you down", this.qm.returnQuestions().get(0).returnNotes());
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
        assertEquals(qs, this.qm.returnQuestionStrings());
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
        assertEquals(cs, this.qm.returnCorrects());
        assertEquals(Boolean.TRUE, this.qm.returnCorrect("Never gonna make you cry"));
        assertEquals(Boolean.FALSE, this.qm.returnCorrect("Never gonna make you cry :("));
    }
    
    @Test
    public void checkingWorks() {
        this.qm.createTOF("Never gonna", "Never gonna", Boolean.TRUE);
        this.qm.createOQ("Never gonna give", "Never gonna give", "give you up");
        this.qm.answer("Never gonna give", "give you up");
        this.qm.answer("Never gonna", "true");
        this.qm.check();
        assertEquals(Boolean.TRUE, this.qm.returnQuestions().get(0).returnCorrect());
        assertEquals(Boolean.TRUE, this.qm.returnQuestions().get(1).returnCorrect());
    }
    
    @Test
    public void returningNotesWorks() {
        this.qm.createEssay("Never gonna give", "Give you up", "Never gonna give");
        assertEquals("Give you up", this.qm.returnNotes("Never gonna give"));
    }
    
    @Test
    public void returningNotesWorksWithNoNotes() {
        assertEquals("", this.qm.returnNotes("Give you up"));
    }
    
    @Test
    public void questionsReturnCorrectTypes() {
        this.qm.createEssay("I just wanna tell you", "How I'm feeling", "Don't tell me you're too blind to see");
        this.qm.createFeedback("Never gonna give you up");
        this.qm.createOQ("Never gonna let you down", "Never gonna run around and", "Desert you");
        this.qm.createTOF("Never gonna make you cry", "Never gonna say goodbye", Boolean.FALSE);
        assertEquals(0, this.qm.returnQuestions().get(3).returnType());
        assertEquals(1, this.qm.returnQuestions().get(2).returnType());
        assertEquals(2, this.qm.returnQuestions().get(0).returnType());
        assertEquals(3, this.qm.returnQuestions().get(1).returnType());
    }
    
    @Test
    public void questionToStringsCorrect() {
        this.qm.createTOF("Never gonna give you up", "Never gonna let you down", Boolean.TRUE);
        this.qm.createOQ("Never gonna", "Run around", "And desert you");
        this.qm.createEssay("Never gonna make you cry", "Never gonna say goodbye", "Never gonna tell a lie");
        this.qm.createFeedback("Never gonna give");
        assertEquals("question: Never gonna give you up; answer: null; correct answer: true", this.qm.returnQuestions().get(0).toString());
        assertEquals("question: Never gonna; answer: null; correct answer: And desert you", this.qm.returnQuestions().get(1).toString());
        assertEquals("question: Never gonna make you cry; answer: null", this.qm.returnQuestions().get(2).toString());
        assertEquals("question: Never gonna give; answer: null", this.qm.returnQuestions().get(3).toString());
    }
    
    @Test
    public void managerToStringCorrect() {
        assertEquals("Rickroll", this.qm.toString());
    }
    
    @Test
    public void managerReturnsInfo() {
        String line = "This started as a joke but I'm in too deep now. I hope you enjoyed.";
        this.qm.addInfo(line);
        List<String> info = new ArrayList<>();
        info.add(line);
        assertEquals(this.qm.returnInfo(), info);
    }
    
    @Test
    public void feedbackWorks() {
        this.qm.createFeedback("Are you enjoying this?");
        this.qm.answer("Are you enjoying this?", "I am enjoying this immensly :)");
        assertEquals("", this.qm.returnQuestions().get(0).returnAnswer());
        assertEquals(Boolean.FALSE, this.qm.returnQuestions().get(0).returnCorrect());
        assertEquals("I am enjoying this immensly :)", this.qm.returnQuestions().get(0).returnAnswered());
        assertEquals("Thank you for your feedback!", this.qm.returnQuestions().get(0).returnNotes());
        
    }
}
