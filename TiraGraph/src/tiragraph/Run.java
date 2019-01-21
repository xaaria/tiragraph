
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
        
        Graph gtest = new Graph("G_Testi", Helpers.getNodesFromFile(this.nodeDataFile), new TreeSet<NotUsed_Edge>());
        
        Node root = gtest.getNodes().first(); // pick psuedorandom root node
        System.out.println( "root: " + root );
        System.out.println( gtest.findNearests(root, 8, new Vector<Node>()).toString() );
        //
        gtest.updateNearestsForEveryNode(3);
        
        // BFS TEST
        ArrayList<Node> bfs = gtest.getBFS(root);
        System.out.println("BFS: "+ bfs.toString() );
        Helpers.outputBFS(bfs);
        //
                
        System.out.println("N " + gtest.getNodes().toString() ); 
        System.out.println("E " + gtest.getEdges().toString() );
        
        //gtest.updateNearestNodes();
        
        //System.out.println("1. kaaren pituus: " + gtest.getEdges().first().getLength() );
        
      

        // TEST
        /*

        System.out.println( g.getNodesAsString() );
        
        //
        Node a = new Node("A", 0, 0);
        Node b = new Node("B", 0, 0);
        Node x = new Node("X", 0, 0);
        
        System.out.println("A vs B " + a.compareTo(b));
        System.out.println("B vs X " + a.compareTo(x));
        */
        
        System.out.println(gtest.toString());
        System.out.println("Bye!");
        
    }
    
    
}
