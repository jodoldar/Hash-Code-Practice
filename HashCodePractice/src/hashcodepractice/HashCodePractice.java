import java.awt.Point;

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
        if(args.length<1){
            System.err.println("Incorrect number of arguments");
            System.exit(1);
        }else{
            String nomFich = args[0];
            File fich = new File(nomFich);
            Pizza pizza = new Pizza(fich);
            int j=0;
            for(int i = 0; i< pizza.getPizza()[0].length; i++){
                Point aux = new Point(j,i);
                Slice current = null;
                if(pizza.getPizza()[j][i].getIngrediente()){
                    current = new Slice(aux,aux,aux,aux,0,1);
                    pizza.getPizza()[j][i].setSlice(true);
                }else{
                    current = new Slice(aux,aux,aux,aux,1,0);
                    pizza.getPizza()[j][i].setSlice(true);
                }
                
                while(!current.isValid(pizza.getMinL())){
                    switch(current.getLMovement()){
                        case -1:
                        case 3:
                            if(current.getRDown().x < pizza.getPizza()[0].length - 1){
                                int vLength = current.getRDown().x - current.getRUp().x;
                                boolean free = true;
                                boolean otherIng = false;
                                for(int k = 0; k<= vLength; k++){
                                    Cell currentCell = (pizza.getPizza()[current.getRUp().x + k][current.getRUp().y + 1]);
                                    boolean inSlice = currentCell.isInSlice();
                                    boolean ingr = currentCell.getIngrediente();
                                    otherIng = otherIng | ((pizza.getPizza()[current.getRUp().x + k][current.getRUp().y])
                                                            .getIngrediente() != ingr );
                                    free = free & !inSlice;
                                }
                                if(free & otherIng){
                                    for(int k = 0; k<= vLength; k++){
                                        Cell currentCell = (pizza.getPizza()[current.getRUp().x + k][current.getRUp().y + 1]);
                                        currentCell.setSlice(true);
                                        if(currentCell.getIngrediente()){
                                            current.setNTomatoe(current.getNTomatoe() + 1);
                                        }else{
                                            current.setNMushroom(current.getNMushroom() + 1);
                                        }                                        
                                    }                                    
                                    current.setRUp(new Point (current.getRUp().x,current.getRUp().y + 1));
                                    current.setRDown(new Point (current.getRDown().x,current.getRDown().y + 1));
                                }
                            }
                            current.setLMovement(0);
                            break;
                        case 0:
                            if(current.getRDown().y < pizza.getPizza().length - 1){
                                int hLength = current.getLDown().y - current.getRDown().y;
                                boolean free = true;
                                boolean otherIng = false;
                                for(int k = 0; k<= hLength; k++){
                                    Cell currentCell = (pizza.getPizza()[current.getLDown().x + 1][current.getLDown().y + k]);
                                    boolean inSlice = currentCell.isInSlice();
                                    boolean ingr = currentCell.getIngrediente();
                                    otherIng = otherIng | ((pizza.getPizza()[current.getLDown().x][current.getLDown().y + k])
                                                            .getIngrediente() != ingr );
                                    free = free & !inSlice;
                                }
                                if(free & otherIng){
                                    for(int k = 0; k<= hLength; k++){
                                        Cell currentCell = (pizza.getPizza()[current.getLDown().x + 1][current.getLDown().y + k]);
                                        currentCell.setSlice(true);
                                        if(currentCell.getIngrediente()){
                                            current.setNTomatoe(current.getNTomatoe() + 1);
                                        }else{
                                            current.setNMushroom(current.getNMushroom() + 1);
                                        }                                        
                                    }                                    
                                    current.setLDown(new Point (current.getLDown().x+1,current.getLDown().y));
                                    current.setRDown(new Point (current.getRDown().x+1 ,current.getRDown().y));
                                }
                            }
                            current.setLMovement(1);
                            break;
                        case 1:
                        case 2:
                           
                    
                    }
                }                
            }
        }
    }
    
}
