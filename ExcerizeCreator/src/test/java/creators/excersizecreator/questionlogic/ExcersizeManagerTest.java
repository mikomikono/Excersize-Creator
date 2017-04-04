package creators.excersizecreator.questionlogic;

import creators.excersizecreator.questionlogic.ExcersizeManager;
import org.junit.*;
import static org.junit.Assert.*;

public class ExcersizeManagerTest {
    private ExcersizeManager em;
    
    public ExcersizeManagerTest() {
    }
    
    @Before
    public void setUp() {
        this.em = new ExcersizeManager();
    }
    
    @Test
    public void managerNotNull() {
        assertNotNull(this.em);
    }
    
//    @Test
//    public void openQuesCreationWorks() {
//        this.em.createOQ("hello", "hello", "hello");
//        assertNotNull(this.em.returnQuestion().get(0));
//    }
}
