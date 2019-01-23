package tiragraph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;
import java.util.UUID;

/**
 *
 * @author OP
 */
public class Helpers {
    
    
    /**
     * Genereates a random uppercase string.
     * Length between 6-10 should be enough.
     * 
     * @param len : lenght of the final string (actual len. is len-1)
     */
    public static String getRandomString(int len) {
        if(len < 5) { len = 5; }    // too small
        if(len > 9) { len = 9; }    // too large
        return UUID.randomUUID().toString().substring(0, len).toUpperCase();
    }
    
    
    /**
    * Loads Nodes from text file and replaces.
    * TreeSet: Ordered Set
    */
    public static TreeSet<Node> getNodesFromFile(String filename) throws FileNotFoundException, IOException, Exception {
        
        TreeSet<Node> nodes = new TreeSet<>();               // result
        BufferedReader br = new BufferedReader(new FileReader(filename));
     
        System.out.println(String.format("Reading Node data from '%s'",filename));
        String line;
        
        while((line = br.readLine()) != null) {
            
            String[] coords = line.split(",");
            double[] coords_ = new double[2];
            
            try {
                coords_[0] = Double.parseDouble(coords[0]);
                coords_[1] = Double.parseDouble(coords[1]);
            } catch(NumberFormatException | NullPointerException ex) {
                throw new Exception("An Error occured while converting coordinates from string to numeric... " + ex);
            }
            
            System.out.println( String.format("'%s' >> Found coordinates (x=%f, y=%f)", line, coords_[0], coords_[1]) );
            
            // Create a Node object with given coordinates. Name will be random.
            // Add Node also to result set
            Node n = new Node(Helpers.getRandomString(6), coords_[0], coords_[1]);
            boolean wasAdded = nodes.add(n);
            System.out.println( String.format("    Node %s created. Added: %b", n, wasAdded) );
        }
        // End of while() :: All nodes are in nodes -set
        System.out.println(String.format("Found %d Nodes. Function end\n---", nodes.size() ) );
        
        
        // Finally return set of Nodes
        return nodes;
    }
    
    
    /**
    * Writes BFS's result to a textfile.
    * Result is given as parameter as a list of nodes (ordered)
    * 
    * @param al
    */
    public static void outputBFS(ArrayList<Node> al) {
    
        if(al.isEmpty()) { return; } // any better way to handle this?
        final String outputfile = "BFS.txt"; 
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputfile));
            bw.write( String.format("%s\nBreadth First Search (BFS) started from root node (uppermost) \nDiscovered Nodes %d \n---\n" , new Date(), al.size() ) );
            for(Node n : al) {
                bw.write( String.format("%s\n" , n.toString()) );
            }
            bw.close();
            System.out.println( String.format("Result output in [old file overwritten (if such existed)]: %s", outputfile) );
            
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        
        
    }
    
    
}
