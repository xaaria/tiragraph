package tiragraph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

/**
 *
 * @author OP
 */
public class Graph {
    
    private String name;
    private TreeSet<Node> nodes;
    private TreeSet<Edge> edges;
    private HashMap<Node, Vector<Node>> nearests;
    
    
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
        
        // nearest nodes
        this.nearests = new HashMap<>();
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
        if(!this.nearests.containsKey(n)) {
            this.nearests.put(n, new Vector<>());
        }
        return this.nodes.add(n);
    }
    
    /**
     * Adds Edge (if not already) to the Graph. Nodes must also be included to 
     * Graphs storage
     * 
     * @param e
     * @return boolean
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

    
   
    /**
     * Recursive method that receives X nearest Nodes for ROOT NODE.
     * 
     * Method reveives start Node (root) as a param, how many nearest Nodes
     * should be returned (eg: 1 = get only nearest).
     * <br>
     * <br>
     * Third param nerests is a Vector ("cloud") containing Nodes ALREADY included to
     * result.
     * <br>
     * <br>
     * Methdo will go throug every node that is NOT in cloud or the same as root and
     * will calculate the dist. If distance is smallest know at that point, 
     * store it (Node) and dist for later use.
     * Finally add to "cloud" found Node with smallest cartesian distance and continue 
     * (if amount >= 1).
     * 
     * Result is Vector of nodes. First one is the nearest, second is the 2. nearest and so on...
     * Vector size is between [0, amount of Nodes in Graph]
     * <br>
     * <br>
     * To be fixed:
     *      - What if same distance? Compare by keys.
     *      - Inefficiency due of multiple (redundant) comparisons
     * 
     * @param root 
     * @param amount How many Nodes
     * @param nearests Result
     * @return Vector<Node>
     * @throws java.lang.Exception 
     * 
     */
    public Vector<Node> findNearests(final Node root, int amount, Vector<Node> nearests) throws Exception {
        
        // Do not try to call more than possible
        if(amount > this.nodes.size()-1 ) {
            amount = this.nodes.size()-1;
        } 
        
        if(amount >= 1) {
            
            double dist = Double.MAX_VALUE;
            Node nearest = null;
           
            // Go through every Node, if not already in result Vector, calc. dist.
            // smallest node will be added to the result vector
            for(Node n : this.nodes) {
                if(! nearests.contains(n) && n != root) {
                    
                    // fix: if equal, lower key firs!
                    if(root.getDistance(n) < dist) {
                        //System.out.print("");
                        dist = root.getDistance(n);
                        nearest = n;
                    }
                    
                }
            }
            
            
            nearests.add( nearest );
            return this.findNearests(root, amount-1, nearests);
        }
        return nearests; // do no add enything, just return at the 0 level
        
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
        return String.format("===\nGraph name: '%s'\n- %d Nodes\n- %d Edges\nNearest Nodes %s \n===", 
            this.getName(), 
            this.getNodes().size(), 
            this.getEdges().size(),
            this.nearests.toString()
        );
    }

    
    
    
    
    
}
