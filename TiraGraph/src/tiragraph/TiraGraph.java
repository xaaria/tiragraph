package tiragraph;

import java.io.IOException;
import java.util.Locale;

/**
 *
 * @author OP
 */
public class TiraGraph {
    
    public static final String APP_NAME = "TiraGraph";
    public static final String DEFAULT_NODE_DATA_SOURCE_FILE = "Tdata.txt";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
        String fn;
        try {
            fn = args[0]; // If command line p. not given
        } catch(Exception e) {
            System.out.println(String.format("Given filename not found. Using default.") );
            fn = DEFAULT_NODE_DATA_SOURCE_FILE;
        }
        
        // Test_ Load nodes from file
        System.out.println(String.format("Welcome to %s\nLocale: %s", APP_NAME, Locale.getDefault() ));
        Run run = new Run(fn);
        
        
        
    }
    

    
}
