package creators.exersizecreator.questionlogic;

import creators.excersizecreator.questionlogic.QuestionManager;
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
        this.qm.createMC("We're no strangers to love", "You know the rules, and so do I", new HashMap<>());
        assertNotNull(this.qm.returnQuestions().get(0));
    }
    
    @Test
    public void managerCreatesOpenQues() {
        this.qm.createOQ("A full commitment's what I'm thinking of", "You wouldn't get this from any other guy", new ArrayList<>());
        assertNotNull(this.qm.returnQuestions().get(0));
    }
    
    @Test
    public void managerCreatesEssay() {
        this.qm.createEssay("I just wanna tell you how I'm feeling", "Gotta make you understand", new ArrayList<>());
        assertNotNull(this.qm.returnQuestions().get(0));
    }
    
    @Test
    public void multiChoiceReturnsAnswer() {
        this.qm.createMC("Never gonna give you up", "Never gonna let you down", new HashMap<>());
        assertNotNull(this.qm.returnQuestions().get(0).returnAnswers());
    }
    
    @Test
    public void openQuesReturnsAnswer() {
        this.qm.createOQ("Never gonna run around", "And desert you", new ArrayList<>());
        assertNotNull(this.qm.returnQuestions().get(0).returnAnswers());
    }
    
    @Test
    public void essayReturnsAnswer() {
        this.qm.createEssay("Never gonna make you cry", "Never gonna say goodbye", new ArrayList<>());
        assertNotNull(this.qm.returnQuestions().get(0).returnAnswers());
    }
    
    @Test
    public void multiChoiceAnsweringWorks() {
//        this test is currently useless bc the method it's testing doesn't work the way it should lol
        Map<String, Boolean> rickAstley = new HashMap<>();
        rickAstley.put("Never gonna tell a lie and hurt you", Boolean.FALSE);
        rickAstley.put("Never gonna give", Boolean.TRUE);
        this.qm.createMC("Never gonna give", "Never gonna give", rickAstley);
        this.qm.answer("Never gonna give", "Never gonna give");
        assertEquals(this.qm.returnQuestions().get(0).returnAnswered().get(0), "T");
        this.qm.answer("Never gonna give", "give you up");
        assertEquals(this.qm.returnQuestions().get(0).returnAnswered().get(0), "F");
    }
    
    @Test
    public void openQuesAnsweringWorks() {
        this.qm.createOQ("We've known eachother", "For so long", new ArrayList<>());
        this.qm.answer("We've known eachother", "Are you...");
        assertEquals(this.qm.returnQuestions().get(0).returnAnswered().get(0), "Are you...");
    }
    
    @Test
    public void essayAnsweringWorks() {
        this.qm.createEssay("Your heart's been aching", "But you're too shy to say it", new ArrayList<>());
        this.qm.answer("Your heart's been aching", "...enjoying this? :)");
        assertEquals(this.qm.returnQuestions().get(0).returnAnswered().get(0), "...enjoying this? :)");
    }
    
    @Test
    public void multiChoiceReturnsNotes() {
        this.qm.createMC("Inside we both know", "What's been going on", new HashMap<>());
        assertEquals(this.qm.returnQuestions().get(0).returnNotes(), "What's been going on");
    }
    
    @Test
    public void openQuesReturnsNotes() {
        this.qm.createOQ("We know the game and we're", "Gonna play it", new ArrayList<>());
        assertEquals(this.qm.returnQuestions().get(0).returnNotes(), "Gonna play it");
    }
    
    @Test
    public void essayReturnsNotes() {
        this.qm.createEssay("And if you ask me how I'm feeling", "Don't tell me you're to blind to see", new ArrayList<>());
        assertEquals(this.qm.returnQuestions().get(0).returnNotes(), "Don't tell me you're to blind to see");
    }
    
    @Test
    public void multiChoiceCheckWorks() {
//        this test is kinda pointless as well atm bc the multichoice answering is still whack
//        also pretty sure it doesn't work at all??? idk man, i've been working for almost 7h i need a break
        Map<String, Boolean> ch = new HashMap<>();
        ch.put("Never gonna give you up", Boolean.TRUE);
        ch.put("Never gonna let you down", Boolean.TRUE);
        ch.put("Never gonna run around and desert you", Boolean.TRUE);
        ch.put("Never gonna make you cry", Boolean.FALSE);
        this.qm.createMC("Never gonna say goodbye", "Never gonna tell a lie and hurt you", ch);
        this.qm.answer("Never gonna say goodbye", "Never gonna give you up");
        this.qm.answer("Never gonna say goodbye", "Never gonna let you down");
        this.qm.answer("Never gonna say goodbye", "Never gonna make you cry");
        this.qm.answer("Never gonna say goodbye", "Never gonna make you cry");
        this.qm.checkIfCorrect();
        List<Boolean> an = new ArrayList<>();
        an.add(Boolean.TRUE);
        an.add(Boolean.TRUE);
        an.add(Boolean.FALSE);
        assertEquals(an, this.qm.questions.get(0).returnCorrect());
    }
//    I don't have tests for the other checking methods yet
}
