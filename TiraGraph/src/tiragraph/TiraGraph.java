package tiragraph;

import java.util.HashSet;

/**
 *
 * @author OP
 */
public class TiraGraph {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // TEST
        Graph g = new Graph("G", new HashSet<>(), new HashSet<>());
        
        g.getNodes().add( new Node("A", 0, 0) );
        g.getNodes().add( new Node("B", 2, 2) );
        g.getNodes().add( new Node("C", 4, 4) );
        
        System.out.println( g.getNodesAsString() );
        
    }
    
}
