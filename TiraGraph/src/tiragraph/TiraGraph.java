package tiragraph;

import java.io.IOException;
import java.util.Locale;

/**
 *
 * @author OP
 */
public class TiraGraph {
    
    public static final String APP_NAME = "TiraGraph";
    public static final String DEFAULT_NODE_DATA_SOURCE_FILE = "Tdata.txt"; //"gridtest3x3.txt";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        String fn;
        try {
            fn = args[0]; // If command line p. not given
        } catch(Exception e) {
            fn = DEFAULT_NODE_DATA_SOURCE_FILE;
            System.out.println(String.format("Given filename not found. Using default: '%s'.", fn) );
            
        }

        System.out.println(String.format("Welcome to %s\nLocale: %s\n", APP_NAME, Locale.getDefault() ));
        Run run = new Run(fn); 
    }
    

    
}
