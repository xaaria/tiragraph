
package tiragraph;

import java.io.IOException;
import java.util.HashSet;

/**
 *
 * @author Okko
 */
public class Run {
    
    private String nodeDataFile;
    
    
    public Run(String nodeDataFile) throws IOException, Exception {
    
        this.nodeDataFile = nodeDataFile;
        
        
        
        Graph gtest = new Graph("G_Testi", Helpers.getNodesFromFile(this.nodeDataFile), null);


        // TEST
        Graph g = new Graph("G", new HashSet<>(), new HashSet<>());

        g.getNodes().add( new Node("A", 0, 0) );
        g.getNodes().add( new Node("B", 2, 2) );
        g.getNodes().add( new Node("C", 4, 4) );

        System.out.println( g.getNodesAsString() );
    
    }
    
    
}
