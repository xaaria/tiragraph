package tiragraph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author OP
 */
public class Graph {
    
    private String name;
    private TreeSet<Node> nodes;
    private TreeSet<Edge> edges;
    
    
    /**
     *
     * @param name
     * @param nodes
     * @param edges
     */
    public Graph(String name, TreeSet<Node> nodes, TreeSet<Edge> edges) {
        // name
        this.name = name;
        
        // nodes
        this.nodes = nodes;
        
        // eges
        this.edges = edges;
    }
    
    
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns (reference to) container of nodes
     * 
     * @return Set<Node>
     */
    public TreeSet<Node> getNodes() {
        return this.nodes;
    }
    
    /**
     * Gets Edges
     * @return Set<Edge>
     */
    public TreeSet<Edge> getEdges() {
        return this.edges;
    }
    
    
    
    /**
     * https://docs.oracle.com/javase/8/docs/api/java/util/Set.html#add-E-
     * 
     * @param n Node to be added to this graph
     * @return boolean True if Node was added, false otherwise
     */
    public boolean addNode(Node n) {
        return this.nodes.add(n);
    }
    
    /**
     * Adds Edge (if not already) to the Graph. Nodes must also be included to 
     * Graphs storage
     */
    public boolean addEdge(Edge e) {
        
        boolean alr = this.getEdges().add(e); // Add Edge e to Graphs storage, if not already
        
        // Each Node must be part of graph's Node storage
        for(Node n: e.getNodes() ) {
            if(this.addNode(n)) {
                System.out.println(String.format("Added %s to Graph while adding an Edge. Node was not part of Graph.", n) );
            }
        }
        
        return alr; // returns was Edge e already included
    }
    
    
    /* Helper methods */
    
    /**
     * Return Graph's Nodes as String
     * @return 
    */
    public String getNodesAsString() {
        
        StringBuilder sb;
        sb = new StringBuilder(String.format("Graph (%s) nodes [no order]:\n", this.name));
        
        for(Node n : this.nodes) {
            sb.append( String.format("%s\n", n.toString() ) );
        }
        
        return sb.toString();
    } 
    
    @Override
    public String toString() {
        return String.format("===\nGraph name: '%s'\n- %d Nodes\n- %d Edges\n===", 
            this.getName(), 
            this.getNodes().size(), 
            this.getEdges().size()
        );
    }
    
    
    
    
    
}
