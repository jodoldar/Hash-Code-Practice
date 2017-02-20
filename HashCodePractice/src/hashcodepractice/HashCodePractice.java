package hashcodepractice;

import java.io.File;

/**
 *
 * @author Josep
 */
public class HashCodePractice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length<3){
            System.err.println("Incorrect number of arguments");
            System.exit(1);
        }else{
            String nomFich = args[2];
            File fich = new File(nomFich);
            Pizza pizza = new Pizza(fich);
        }
    }
    
}
