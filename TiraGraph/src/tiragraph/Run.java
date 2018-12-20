
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
        
        
        Graph gtest = new Graph("G_Testi", Helpers.getNodesFromFile(this.nodeDataFile), new TreeSet<Edge>());
        //Graph gtest = new Graph("G_Testi", new TreeSet<Node>(), new TreeSet<Edge>());
        
        /*Node n1 = new Node("T1", 34, 100);
        Node n2 = new Node("T2", 34, 100);
        Node n3 = new Node("T3", 34, 100);
        
        gtest.addNode(n1);
        gtest.addNode(n2);
        gtest.addNode(n3);
        
        Edge e0 = new Edge( n1, n2 ); 
        Edge e1 = new Edge( new Node("A", 0, 0), new Node("B", -0.00, -100.000) ); 
        Edge e2 = new Edge( new Node("X", 0, 0), new Node("Z", 2.00, 2.000) ); 
        
        gtest.addEdge(e0);
        gtest.addEdge(e1);
        gtest.addEdge(e2);
        */
        Node root = gtest.getNodes().first();
        System.out.println(root + " >> " + gtest.findNearests(root, 10, new Vector<Node>()).toString() );
        //
        gtest.updateNearestsForEveryNode(5);
        
        // BFS TEST
        ArrayList<Node> bfs = gtest.getBFS(root);
        System.out.println("BFS: "+ bfs.toString() );
        Helpers.outputBFS(bfs);
        //
                
        System.out.println("N " + gtest.getNodes().toString() ); 
        System.out.println("E " + gtest.getEdges().toString() );
        
        //gtest.updateNearestNodes();
        
        //System.out.println("1. kaaren pituus: " + gtest.getEdges().first().getLength() );
        
        
        /*for(Edge e: gtest.getEdges()) {
            System.out.println( e );
        }
        */

        // TEST
        /*
        Graph g = new Graph("G", new HashSet<>(), new HashSet<>());

        g.getNodes().add( new Node("A", 0, 0) );
        g.getNodes().add( new Node("B", 2, 2) );
        g.getNodes().add( new Node("C", 4, 4) );

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
