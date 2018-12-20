package tiragraph;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
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
    public Vector<Node> findNearests(final Node root, int amount, Vector<Node> nearests) {
        
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
    
    /**
     * Calculates nearest Nodes for every Node in Graph.
     * Result is stored in this.nearests (Key, Value) < Node, Vector >
     * 
     * @param amount How many Nodes should be included
     * @return void
     */
    public void updateNearestsForEveryNode(int amount) {
        if(amount <= 0) {
            throw new InvalidParameterException("param amount <= 0");
        }
        
        /* Recalculate nearests for each Node */
        for(Node n : this.getNodes()) {
            
            if(!this.nearests.containsKey(n)) {
                this.nearests.put(n, new Vector<>());
            }
            else {
                this.nearests.get(n).clear();
            }
            this.nearests.get(n).addAll( this.findNearests(n, amount, new Vector<>()) );
        }
    
    }
    
    
    
    
    /**
     * Commits Breadth-First Seach -traversal and return the order as Vector.
     * 
     * @param root Start BFS here
     * @return Vector<Node> Result of BFS. Index 0 contains root.
     */
    public ArrayList<Node> getBFS(Node root) {
    
        ArrayList<Node> trav = new ArrayList<>();
        LinkedList<Node> level = new LinkedList<>(); // Nodes from each level. Implements Java Queue
        
        trav.add(root);
        level.add(root);
        
        // For each node in current level
        // for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
        while(!level.isEmpty()) {
            System.out.println("-- Queue. Next Nodes to examine :: " + level.toString());
            Node n = level.remove();
            System.out.println( String.format("  In node %s", n) );
            
            // Create a new container for new Nodes
            ArrayList<Node> tmp = new ArrayList<>(); // empty
            // For every Node n, go trough every Edge and see if it's unexplored.
            // Those "Edges" are stores in this.nearests
            for(Node nex : this.nearests.get(n)) {
                System.out.println( String.format("  Check -> %s", nex) );
                if(! trav.contains(nex) ) {
                    tmp.add(nex);
                    System.out.println("    NEW discovery!");
                } else {
                     System.out.println("    Already discovered!");
                }
            }
            trav.addAll(tmp);
            level.addAll(tmp); // add more stuff to go trough... Edges we just discovered!
            
            
        
        }
        System.out.println("BFS has nothing to explore. Finished!");
        
        
        
        return trav;
    }
    
    
    
    /**
     * Commits Depth-First Search -traversal. 
     * Returns order of Nodes in Vector.
     * 
     * You may want to update this.nearests -storage before running this.
     * 
     */
    /*public Vector<Node> getDFS() {
    
        
        return;
    }*/
    
    
    
    
    
    
    
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
