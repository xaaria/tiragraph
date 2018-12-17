package tiragraph;

import java.util.Set;

/**
 *
 * @author OP
 */
public class Edge {
    
    /* 
    - Not directed Edges (A to B == B to A)
    - LOOPS NOT ALLOWED */
    
    /* Lenght of this edge */
    private double len;
    private Set<Node> nodes;
    
    
    public Edge(Node n1, Node n2) {
        //
    }
    
    /**
     * When nodes change, lengt/dist. must also be updated!
     * @param n1
     * @param n2
     * @return lenght of the new Edge to N1 to N2 
    */
    public double setNodes(Node n1, Node n2) {
        
        return 0;
    }
    
    
    
    
}
