
package tiragraph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Vector;

/**
 *
 * @author OP
 */
public class Run {
    
    private String nodeDataFile;
    
    
    public Run(String nodeDataFile) throws IOException, Exception {
        this.nodeDataFile = nodeDataFile;
        
        Graph gtest = new Graph("G_Testi", Helpers.getNodesFromFile(this.nodeDataFile));
        
        // Print Graph details
        System.out.println(gtest.toString());
        
        Node root = gtest.getNodes().first(); // pick random root node
        System.out.println("ROOT (lowest key, keys are randomized): " + root );
        System.out.println( gtest.findNearests(root, 8, new Vector<Node>()).toString() );
        
        //
        int n = 4;
        gtest.updateNearestsForEveryNode(n);
        System.out.println( String.format("Nearests (%d): %s", n, gtest.getNearests() ) );
        
        // BFS
        ArrayList<Node> bfs = gtest.getBFS(root);
        System.out.println("BFS: "+ bfs.toString() );
        Helpers.outputBFS(bfs);
        //
                
        System.out.println("Nodes: " + gtest.getNodes().toString() ); 
        //System.out.println("E " + gtest.getEdges().toString() );
        
        
        
        System.out.println("Bye!");
        
    }
    
    
}
