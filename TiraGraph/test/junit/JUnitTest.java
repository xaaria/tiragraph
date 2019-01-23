package junit;

import java.util.TreeSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiragraph.Graph;
import tiragraph.Helpers;

/**
 *
 * @author OP
 */
public class JUnitTest {
    
    
    static Graph gtest;
    
    @Test
    public void nodesLoadedTest() {
        int actual = JUnitTest.gtest.getNodes().size();
        assertTrue("Loaded 0 Nodes!", actual > 0);
    }
    
    
    
    
    
        
        
    public JUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("setUpClass() called");
        JUnitTest.gtest = new Graph("G_Testi", Helpers.getNodesFromFile("testdata2.txt"));
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        
        
    }
    
    @After
    public void tearDown() {
        this.gtest = null;
    }

}
