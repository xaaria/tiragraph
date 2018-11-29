package tiragraph;

/**
 *
 * @author OP
 */
public class Node {
    
    /* Name is like a key, identifier for this node */
    private String name;
    private double x;
    private double y;
    
    
    public Node(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
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
     * @param n
     * @return double
     */
    public double getCartesianDistance(Node n) {
        return Math.sqrt( Math.pow(this.x-n.x,2)+ Math.pow(this.y-n.y,2) );
    }
    
    
    @Override
    public String toString() {
       return String.format("  %s (%f, %f)", this.name, this.x, this.y);
    }
    
}
