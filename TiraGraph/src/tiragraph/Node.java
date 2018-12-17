package tiragraph;

import java.util.Comparator;

/**
 *
 * @author OP
 */
public class Node
    implements Comparable<Node> {
    
    /* Name is like a key, identifier for this node */
    private String key;
    private double x;
    private double y;
    
    
    public Node(String key, double x, double y) {
        this.key = key;
        this.x = x;
        this.y = y;
    }
    
    
    public void setKey(String k) {
        this.key = k;
    }
    public String getKey() {
        return this.key;
    }
    
    public double getX(double x) {
        return this.x;
    }
    public double getY(double y) {
        return this.y;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    
    
    /**
     * Returns euclidean distance between two points (>= 0.0)
     * @param n
     * @return double
     */
    public double getDistance(Node n) {
        return Math.sqrt( Math.pow(this.x-n.x,2)+ Math.pow(this.y-n.y,2) );
    }
    
    
    @Override
    public String toString() {
       return String.format("%s (%f, %f)", this.key, this.x, this.y);
    }

    
    
    /**
     * CompareTo implementation.
     * Two nodes will be ordered based on their keys.
     * Since key is String, use natural order of strings (note used locale).
     * https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html#compareTo-T-
     * 
     * @param o
     * @return negative int if THIS smaller, 0 if equal, >= 0 if larger
     */
    @Override
    public int compareTo(Node o) {
        return this.getKey().compareTo(o.getKey());
    }
    
    
    
}
