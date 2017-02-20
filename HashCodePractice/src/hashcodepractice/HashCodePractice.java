package hashcodepractice;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import hashcodepractice.Slice;
import java.util.Set;

/**
 *
 * @author Josep Dols
 */
public class HashCodePractice {
    
    private static ArrayList slices;
    
    public void firstSlices(Pizza pizza){
        //Es necesario recorrer cada Cell de la Pizza para ver si es factible crear un Slice
        for(int nFil=0; nFil<pizza.getNumFilas(); nFil++){
            for(int nCol=0; nCol<pizza.getNumColumnas(); nCol++){
                if(pizza.piz[nFil][nCol].isInSlice()){
                    continue;
                }else{
                    Point puntoIni = new Point(nFil,nCol);
                    Slice current;
                    if(pizza.piz[nFil][nCol].getIngrediente()){
                        current = new Slice(puntoIni,puntoIni,puntoIni,puntoIni,0,1);
                        pizza.piz[nFil][nCol].setSlice(true);
                    }else{
                        current = new Slice(puntoIni,puntoIni,puntoIni,puntoIni,1,0);
                        pizza.piz[nFil][nCol].setSlice(true);
                    }
                    
                    //Comprobaciones para evitar que se quede iterando la expansion
                    //boolean stopIzq=false,stopDer=false,stopArriba=false,stopAbajo=false;
                    
                    while(!current.isValid(pizza.getMinL()) && current.totalCells()<=pizza.getMaxH()){
                        //Hay que comprobar primero si hay alguna prioridad a la hora de expandir
                        boolean izqSI=false,abajoSI=false,derSI=false,arribaSI=false;
                        //Comprobaciones para si es posible expandir hacia alguna direccion
                        boolean izqAvail=true,derAvail=true,arribaAvail=true,abajoAvail=true;
                        
                        
                        //Comprobacion de la derecha
                        int column = current.getRUp().y;
                        if(column<pizza.getNumColumnas()-1){
                            for(int pos=current.getRUp().x; pos<=current.getRDown().x; pos++){
                                if(pizza.piz[pos][column].getIngrediente()!= pizza.piz[pos][column+1].getIngrediente()){
                                    derSI = true;
                                }
                                if(pizza.piz[pos][column+1].isInSlice()){
                                    derAvail = false;
                                }
                            }
                        }
                        
                        //Comprobacion de abajo
                        int fila = current.getLDown().x;
                        if(fila<pizza.getNumFilas()-1){
                            for(int pos = current.getLDown().y; pos<=current.getRDown().y; pos++){
                                if(pizza.piz[fila][pos].getIngrediente() != pizza.piz[fila+1][pos].getIngrediente()){
                                    abajoSI = true;
                                }
                                if(pizza.piz[fila+1][pos].isInSlice()){
                                    abajoAvail = false;
                                }
                            }
                        }
                        
                        //Comprobacion de la izquierda
                        column = current.getLUp().y;
                        if(column>0){
                            for(int pos=current.getLUp().x; pos<=current.getLDown().x; pos++){
                                if(pizza.piz[pos][column].getIngrediente() != pizza.piz[pos][column-1].getIngrediente()){
                                    izqSI = true;
                                }
                                if(pizza.piz[pos][column-1].isInSlice()){
                                    izqAvail = false;
                                }
                            }
                        }
                        
                        //Comprobacion de arriba
                        fila = current.getLUp().x;
                        if(fila>0){
                            for(int pos=current.getLUp().y; pos<=current.getRUp().y; pos++){
                                if(pizza.piz[fila][pos].getIngrediente() != pizza.piz[fila-1][pos].getIngrediente()){
                                    arribaSI = true;
                                }
                                if(pizza.piz[fila-1][pos].isInSlice()){
                                    arribaAvail = false;
                                }
                            }
                        }
                        
                        if(!derAvail || !abajoAvail || !izqAvail || !arribaAvail){
                            break;
                        }
                        
                        if(derSI && derAvail){
                            //Expandir a la derecha
                        }else if(abajoSI && abajoAvail){
                            //Expandir abajo
                        }else if(izqSI && izqAvail){
                            //Expandir a la izquierda
                        }else if(arribaSI && arribaAvail){
                            //Expandir arriba
                        }else{
                            //Expandir por historial
                            switch (current.getLMovement()){
                                case -1:
                                case 0:
                                    //El ultimo movimiento ha sido hacia arriba o es el movimiento inicial
                                    if(derAvail){
                                        //Expandir a la derecha
                                    }
                                    current.setLMovement(1);
                                    break;
                                case 1:
                                    //El ultimo movimiento ha sido hacia la derecha
                                    if(abajoAvail){
                                        //Expandir abajo
                                    }
                                    current.setLMovement(2);
                                    break;
                                case 2:
                                    //El ultimo movimiento ha sido hacia abajo
                                    if(izqAvail){
                                        //Expandir a la izquierda
                                    }
                                    current.setLMovement(3);
                                    break;
                                case 3:
                                    //El ultimo movimiento ha sido hacia la izquierda
                                    if(arribaAvail){
                                        //Expandir arriba
                                    }
                                    current.setLMovement(0);
                                    break;
                            }
                        }
                        
                        
                    }
                }
            }
        }
    }
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
            slices = new ArrayList();
            
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
