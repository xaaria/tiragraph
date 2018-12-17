package tiragraph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.UUID;

/**
 *
 * @author Okko
 */
public class Helpers {
    
    
    
    public static String getRandomString(int len) {
        return UUID.randomUUID().toString().substring(0, len+1).toUpperCase();
    }
    
    
    
    /**
    * Loads Nodes from text file and replaces.
    * TreeSet: Ordered Set
    */
    public static HashSet<Node> getNodesFromFile(String filename) throws FileNotFoundException, IOException, Exception {
        
        //File file_ = null;
        HashSet<Node> nodes = new HashSet<>(); // result
        BufferedReader br = new BufferedReader(new FileReader(filename));
     
        System.out.println(String.format("Reading Node data from '%s'",filename));
        String line;
        
        while((line = br.readLine()) != null) {
            
            String[] coords = line.split(",");
            
            // lolwut
            double[] coords_ = new double[2];
            
            try {
                coords_[0] = Double.parseDouble(coords[0]);
                coords_[1] = Double.parseDouble(coords[1]);
            }
            catch(NumberFormatException | NullPointerException ex) {
                throw new Exception("An Error occured while converting coordinates from string to numeric... " + ex);
            }
            
            //System.out.println(coords.length);
            System.out.println( String.format("'%s' >> Found coordinates (x=%f, y=%f)", line, coords_[0], coords_[1]) );
            
            // Create a Node object with given coordinates. Name will be random.
            // Add Node also to result set
            Node n = new Node(Helpers.getRandomString(2), coords_[0], coords_[1]);
            nodes.add(n);
            System.out.println( String.format("Node object %s created ", n) );
        }
        // End of while() :: All nodes are in nodes -set
        System.out.println(String.format("Found %d Nodes", nodes.size() ) );
        
        
        // Finally return set of Nodes
        return nodes;
    }
    
    
}
