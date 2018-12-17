package tiragraph;

import java.util.Set;

/**
 *
 * @author OP
 */
public class Graph {
    
    private String name;
    private Set<Node> nodes;
    private Set<Edge> edges;
    
    
    /**
     *
     * @param name
     * @param nodes
     * @param edges
     */
    public Graph(String name, Set<Node> nodes, Set<Edge> edges) {
        this.name = name;
        this.nodes = nodes;
        this.edges = edges;
    }
    
    /**
     * Returns (reference to) container of nodes
     * 
     * @return Set<Node>
     */
    public Set<Node> getNodes() {
        return this.nodes;
    }
    
    /**
     * Sets container of Edges
     * @return Set<Edge>
     */
    public Set<Edge> getEdges() {
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
    
    
    public boolean addEdge(Edge e) {
        return this.edges.add(e);
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
        return "Graph "+this.name+" as a string:";
    }
    
    
    
    
    
}
