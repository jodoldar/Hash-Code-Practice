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
    
    public static void firstSlices(Pizza pizza){
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
                    System.out.println(current.getLUp().toString() +""+ current.getRDown().toString());
                    //Comprobaciones para evitar que se quede iterando la expansion
                    //boolean stopIzq=false,stopDer=false,stopArriba=false,stopAbajo=false;
                    
                    while(!current.isValid(pizza.getMinL()) && current.totalCells()<=pizza.getMaxH()){
                        //Hay que comprobar primero si hay alguna prioridad a la hora de expandir
                        boolean izqSI=false,abajoSI=false,derSI=false,arribaSI=false;
                        //Comprobaciones para si es posible expandir hacia alguna direccion
                        boolean izqAvail=true,derAvail=true,arribaAvail=true,abajoAvail=true;
                        
                        System.out.println(current.getLUp().toString() +""+ current.getRDown().toString());
                        
                        //Comprobacion de la derecha
                        int column = current.getRUp().y;
                        if(column<pizza.getNumColumnas()-1){
                            for(int pos=current.getRUp().x; pos<=current.getRDown().x; pos++){
                                if(pizza.piz[pos][column].getIngrediente()!= pizza.piz[pos][column+1].getIngrediente()){
                                    derSI = derSI||true;
                                }
                                if(pizza.piz[pos][column+1].isInSlice()){
                                    derAvail = derAvail&&false;
                                }
                            }
                        }else{
                           derAvail = derAvail&&false;
                        }
                        System.out.print("Derecha ");
                        //Comprobacion de abajo
                        int fila = current.getLDown().x;
                        if(fila<pizza.getNumFilas()-1){
                            for(int pos = current.getLDown().y; pos<=current.getRDown().y; pos++){
                                if(pizza.piz[fila][pos].getIngrediente() != pizza.piz[fila+1][pos].getIngrediente()){
                                    abajoSI = abajoSI||true;
                                }
                                if(pizza.piz[fila+1][pos].isInSlice()){
                                    abajoAvail = abajoAvail&&false;
                                }
                            }
                        }else{
                            abajoAvail = abajoAvail&&false;
                        }
                        System.out.print("Abajo ");
                        //Comprobacion de la izquierda
                        column = current.getLUp().y;
                        if(column>0){
                            for(int pos=current.getLUp().x; pos<=current.getLDown().x; pos++){
                                if(pizza.piz[pos][column].getIngrediente() != pizza.piz[pos][column-1].getIngrediente()){
                                    izqSI = izqSI||true;
                                }
                                if(pizza.piz[pos][column-1].isInSlice()){
                                    izqAvail = izqAvail&&false;
                                }
                            }
                        }else{
                            izqAvail = izqAvail&&false;
                        }
                        System.out.print("Izquierda ");
                        //Comprobacion de arriba
                        fila = current.getLUp().x;
                        if(fila>0){
                            for(int pos=current.getLUp().y; pos<=current.getRUp().y; pos++){
                                if(pizza.piz[fila][pos].getIngrediente() != pizza.piz[fila-1][pos].getIngrediente()){
                                    arribaSI = arribaSI||true;
                                }
                                if(pizza.piz[fila-1][pos].isInSlice()){
                                    arribaAvail = arribaAvail&&false;
                                }
                            }
                        }else{
                            arribaAvail = arribaAvail&&false;
                        }
                        System.out.println("Arriba ");
                        System.out.println(derSI + ","+ abajoSI +","+ izqSI + ","+ arribaSI);
                        System.out.println(derAvail + ","+ abajoAvail + ","+ izqAvail + ","+ arribaAvail);
                        if(!(arribaAvail|derAvail|abajoAvail|izqAvail)){
                            break;
                        }
                        System.out.println("Se pasa");
                        if(derSI && derAvail){
                            //Expandir a la derecha
                            current.setRUp(new Point(current.getRUp().x+1,current.getRUp().y));
                            current.setRDown(new Point(current.getRDown().x+1,current.getRDown().y));
                            for(int locCel=current.getRUp().y; locCel<=current.getRDown().y;locCel++){
                                pizza.piz[current.getRUp().x][locCel].setSlice(true);
                            }
                        }else if(abajoSI && abajoAvail){
                            //Expandir abajo
                            current.setLDown(new Point(current.getLDown().x,current.getLDown().y+1));
                            current.setRDown(new Point(current.getRDown().x,current.getRDown().y+1));
                            for(int locCel=current.getLDown().x;locCel<=current.getRDown().x;locCel++){
                                pizza.piz[locCel][current.getLDown().y].setSlice(true);
                            }
                        }else if(izqSI && izqAvail){
                            //Expandir a la izquierda
                            current.setLUp(new Point(current.getLUp().x-1,current.getLUp().y));
                            current.setLDown(new Point(current.getLDown().x-1,current.getLDown().y));
                            for(int locCel=current.getLUp().y;locCel<=current.getLDown().y;locCel++){
                                pizza.piz[current.getLUp().x][locCel].setSlice(true);
                            }
                        }else if(arribaSI && arribaAvail){
                            //Expandir arriba
                            current.setLUp(new Point(current.getLUp().x,current.getLUp().y-1));
                            current.setRUp(new Point(current.getRUp().x,current.getRUp().y-1));
                            for(int locCel=current.getLUp().x;locCel<=current.getRUp().x;locCel++){
                                pizza.piz[locCel][current.getRUp().y].setSlice(true);
                            }
                        }else{
                            //Expandir por historial
                            switch (current.getLMovement()){
                                case -1:
                                case 0:
                                    //El ultimo movimiento ha sido hacia arriba o es el movimiento inicial
                                    if(derAvail){
                                        //Expandir a la derecha
                                        current.setRUp(new Point(current.getRUp().x+1,current.getRUp().y));
                                        current.setRDown(new Point(current.getRDown().x+1,current.getRDown().y));
                                        for(int locCel=current.getRUp().x; locCel<=current.getRDown().x;locCel++){
                                            pizza.piz[locCel][current.getRUp().x].setSlice(true);
                                        }
                                    }
                                    current.setLMovement(1);
                                    break;
                                case 1:
                                    //El ultimo movimiento ha sido hacia la derecha
                                    if(abajoAvail){
                                        //Expandir abajo
                                        current.setLDown(new Point(current.getLDown().x,current.getLDown().y+1));
                                        current.setRDown(new Point(current.getRDown().x,current.getRDown().y+1));
                                        for(int locCel=current.getLDown().y;locCel<=current.getRDown().y;locCel++){
                                            pizza.piz[current.getLDown().y][locCel].setSlice(true);
                                        }
                                    }
                                    current.setLMovement(2);
                                    break;
                                case 2:
                                    //El ultimo movimiento ha sido hacia abajo
                                    if(izqAvail){
                                        //Expandir a la izquierda
                                        System.out.println("Izquierda mov: x=" + current.getLUp().x);
                                        current.setLUp(new Point(current.getLUp().x-1,current.getLUp().y));
                                        current.setLDown(new Point(current.getLDown().x-1,current.getLDown().y));
                                        for(int locCel=current.getLUp().x;locCel<=current.getLDown().x;locCel++){
                                            pizza.piz[locCel][current.getLUp().x].setSlice(true);
                                        }
                                    }
                                    current.setLMovement(3);
                                    break;
                                case 3:
                                    //El ultimo movimiento ha sido hacia la izquierda
                                    if(arribaAvail){
                                        //Expandir arriba
                                        current.setLUp(new Point(current.getLUp().x,current.getLUp().y-1));
                                        current.setRUp(new Point(current.getRUp().x,current.getRUp().y-1));
                                        for(int locCel=current.getLUp().y;locCel<=current.getRUp().y;locCel++){
                                            pizza.piz[current.getRUp().y][locCel].setSlice(true);
                                        }
                                    }
                                    current.setLMovement(0);
                                    break;
                            }
                        }   
                    }
                    //Acaba el while
                    System.out.println(current.getLUp().toString() +""+ current.getRDown().toString());

                    if(!current.isValid(pizza.getMinL())){
                        //Deseleccionar las celdas 
                        System.out.println("Un slice no es valido");
                        for(int xLoc=current.getLUp().x;xLoc<=current.getLDown().x;xLoc++){
                            for(int yLoc=current.getLUp().y;yLoc<=current.getRUp().y;yLoc++){
                                pizza.piz[xLoc][yLoc].setSlice(false);
                            }
                        }
                    }else{
                        //Añadir al array del slices
                        System.out.println("Se añade un slice");
                        slices.add(current);
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
            
            firstSlices(pizza);
            System.out.println(slices.toString());
        }
    }
    
}
