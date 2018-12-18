package tiragraph;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author OP
 */
public class Edge implements Comparable<Edge> {
    
    /* 
    - Not directed Edges (A to B == B to A)
    - LOOPS NOT ALLOWED */
    
    private TreeSet<Node> nodes;
    
    /**
     * Constructor for Efge.
     * NOTE: Node here does NOT automatically added to the Graph's storage!
     */
    public Edge(Node n1, Node n2) throws Exception {
        
        this.nodes = new TreeSet<>();
        
        // No duplicates allowed
        if( !n1.equals(n2) ) {
            this.nodes.add(n1);
            this.nodes.add(n2);
        } else {
            throw new Exception("Trying to create an Edge with same start and end Node!\nNo loops [duplicate Nodes] allowed in Edge-object!");
        }
        
    }
    
    
    public TreeSet<Node> getNodes() {
        return this.nodes;
    }
    
    public double getLength() {
        return this.getNodes().first().getDistance( this.getNodes().last() );
    }
    
    
    public boolean addNode(Node n) {
        if(this.getNodes().size() >= 2) {
            return false;
            //throw new Exception("Edge already has two Nodes! Cannot add more!");
        }
        return this.getNodes().add(n);
        
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

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public String toString() {
        TreeSet<Node> n = this.getNodes(); 
        return String.format("E<%s, %s> (Len: ~%.2f)", n.first(), n.last(), this.getLength() );
    }
    
    
    /**
     * See if two Edges have the same content : same two nodes.
     * (Or if param o is the same as this)
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if(o instanceof Edge) {
            // Does Edge o contain same nodes? .first() in set && .last() in set?
            return ( 
                ((Set) o).contains( this.getNodes().first()) && 
                ((Set) o).contains( this.getNodes().last()) 
            );
            
        }
        
        // Otherwise
        return false;
        
    }

    
    /**
     * Order between two Edges is defined by comparing Nodes they have.
     * Edge's Nodes are ordered.
     * See Node :: compareTo()
     * (A,B) > (A,D) > (Z,Z) == (Z,Z)
     */
    @Override
    public int compareTo(Edge o) {
        
        int comp = this.getNodes().first().compareTo( o.getNodes().first() );
        
        if(comp > 0 ) {
            return comp;
        } else if(comp == 0) {
            
            // First Nodes are the same, check second Node keys
            return this.getNodes().last().compareTo( o.getNodes().last() );
            
        } 
        return comp; // negative at this point
        
    }
    
    
    
    
}
