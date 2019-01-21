package tiragraph;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Vector;

/**
 *
 * @author OP
 */
public class Graph {
    
    private String name;
    private TreeSet<Node> nodes;
    private TreeSet<NotUsed_Edge> edges;
    private HashMap<Node, Vector<Node>> nearests;
    
    
    /**
     *
     * @param name
     * @param nodes
     * @param edges
     */
    public Graph(String name, TreeSet<Node> nodes, TreeSet<NotUsed_Edge> edges) {
        this.name = name;
        this.nodes = nodes;
        this.edges = edges;
        this.nearests = new HashMap<>();
    }
    
    /**
     * Getter for name
     * 
     * @return String
     */
    public String getName() {
        return this.name;
    }
    
    public void setName(String n) {
        if(n == null || n.length() == 0) { throw new IllegalArgumentException(); } 
        this.name = n;
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
     * Setter for nodes
     * 
     * @param ns
     * @return void
     */
    public void setNodes(TreeSet<Node> ns) {
        this.nodes = ns;
    }
    
    /**
     * Gets Edges
     * 
     * @return Set<Edge>
     */
    public TreeSet<NotUsed_Edge> getEdges() {
        return this.edges;
    }
    
    /**
     * Setter for edges
     * 
     * @param es
     * @return void
     */
    public void setEdges(TreeSet<NotUsed_Edge> es) {
        this.edges = es;
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
            return true;
        } else {
            return this.nodes.add(n);
        }
    }
    
    /**
     * Adds NotUsed_Edge (if not already) to the Graph. Nodes must also be included to 
     * Graphs storage
     * 
     * Update (2019-01-21): Not used / should not be used
     * 
     * @param e
     * @return boolean
     */
    public boolean addEdge(NotUsed_Edge e) {
        
        boolean alr = this.getEdges().add(e); // Add NotUsed_Edge e to Graphs storage, if not already
        
        // Each Node must be part of graph's Node storage
        for(Node n: e.getNodes() ) {
            if(this.addNode(n)) {
                System.out.println(String.format("Added %s to Graph while adding an Edge. Node was not part of Graph.", n) );
            }
        }
        
        return alr; // returns was NotUsed_Edge e already included
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
     * Method will go through every node that is NOT in cloud or the same as root and
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
        
        System.out.println("[Find nearests] Nodes left: " + amount);
        
        double dist = Double.MAX_VALUE;
        
        if(amount >= 1) {
            
            Node nearest = null;
           
            // Go through every Node, if not already in result Vector, calc. dist.
            // smallest node will be added to the result vector
            for(Node n : this.nodes) {
                if(! nearests.contains(n) && n != root) {
                    
                    // fix: if equal, lower key first!
                        
                    if(root.getDistance(n) < dist) {
                        // Current found new min dist is updated
                        dist = root.getDistance(n);

                        // update 'nearest node' that is about to be added.
                        nearest = n;
                    }
                    // Rare(?) edge case, distance exactly the same
                    // If current node has smaller key, update nearests
                    // that is about to be added. Otherwise keep current 'nearest'
                    else if (root.getDistance(n) == dist) {
                        System.out.println(String.format("[Finding nearest Node :: Equal dist.] (Cur. Node <> cur. nearest) :: %s <> %s (smaller key has higher priority)", n, nearest) );
                        if( n.compareTo(nearest) == -1 ) {
                            nearest = n;
                            System.out.println(String.format(".... Cur. Found node had smaller key with same dist. Nearest changed to: %s", n) );
                        } else {
                            System.out.println(String.format(".... (Cur. Node) %s had larger key. Nearest Node not changed!", n) );
                        }
                    }
                    // Else. distance to current Node is not LOE. Will not be added as
                    // nearest node. Continue to next Node.
                    else {
                        // ...
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
        if(amount <= 0) { throw new InvalidParameterException("param amount <= 0"); }
        
        /* Recalculate nearests for each Node */
        for(Node n : this.getNodes()) {
            
            if(!this.nearests.containsKey(n)) {
                this.nearests.put(n, new Vector<>());
            } else {
                this.nearests.get(n).clear();
            }
            this.nearests.get(n).addAll( this.findNearests(n, amount, new Vector<>()) );
        }
    
    }
    
    
    
    
    /**
     * Commits Breadth-First Seach -traversal and returns discovered Nodes.
     * Goes through every Node (in Queue) and each connection to the nearests Nodes 
     * [Stored in this.nearests].
     * If discovered Node is not already in result array, 
     * add it to "level" Queue so it can be checked.
     * <br>
     * <br>
     * This procedure will continue as long as there are Nodes to examine
     * in Queue var. level.
     * <br>
     * <br>
     * Firs Node in Queue is the root Node (start Node)
     * <br>
     * <br>
     * Each found Node is added to the 'trav' variable that is returned at 
     * the end.
     * 
     * 
     * 
     * @param root Start BFS from this Node
     * @return ArrayList<Node> Result of BFS. First element is root. Minimum size 1.
     */
    public ArrayList<Node> getBFS(Node root) {
    
        System.out.println("---\nStarting BFS-traversal. Root Node: " + root);
        
        ArrayList<Node> trav = new ArrayList<>();       // Actual result
        LinkedList<Node> level = new LinkedList<>();    // Nodes from each "level". Implements Java Queue
        trav.add(root);
        level.add(root);
        // For each node in current level
        while(!level.isEmpty()) {
            System.out.println("Upcoming Nodes to examine - Queue: " + level.toString());
            Node n = level.remove();
            System.out.println( String.format("  [#] In node %s", n) );
            // For every Node n, go trough every connection and see if it's unexplored.
            // Those "Edges" are stores in this.nearests
            for(Node nex : this.nearests.get(n)) {
                System.out.println( String.format("  Check # -> %s", nex) );
                if(! trav.contains(nex) ) {
                    level.add(nex);
                    trav.add(nex);
                    System.out.println("    NEW discovery!");
                } else {
                     System.out.println("    Already discovered!");
                }
            }
        }
        System.out.println("BFS has nothing left to explore. Finished!");
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
     * 
     * 
     * @return String
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
