package hashcodepractice; 

import static hashcodepractice.Cell.*;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.awt.Point;
import java.io.FileNotFoundException;


public class Pizza{
    private Cell[][] piz;
    private int numCol, numFil, minL, maxH;
    
    /**
     * Constructor de la clase Pizza. Inicializa un objeto Pizza con todas sus
     * celdas creadas con el ingrediente correspondiente
     * @param f Archivo a traves del cual se crea el objeto Pizza
     */
    public Pizza(File f){
        Scanner entrada = null;
        try{
            entrada = new Scanner(f);
            numFil = entrada.nextInt();
            numCol = entrada.nextInt();
            minL = entrada.nextInt();
            maxH = entrada.nextInt();
            entrada.nextLine();
            piz = new Cell[numFil][numCol];
            int fila = 0;
            String s = "";
            while(entrada.hasNext()){
                s = entrada.nextLine();
                for(int col = 0; col<s.length(); col++){
                   if(s.charAt(col) == 'T'){
                       piz[fila][col] = new Cell(TOMATOE, new Point(fila,col));
                   }else{
                       piz[fila][col] = new Cell(MUSHROOM, new Point(fila,col));
                   }
                }
                fila++;
            }
        }catch(FileNotFoundException e){
            System.out.println("El fichero no se puede abrir.");
        }finally{
            if(entrada!=null){
                entrada.close();
            }
        }
    }

    public Cell[][] getPizza(){
        return this.piz;
    }
    public int getNumFilas(){
        return this.numFil;
    }
    public int getNumColumnas(){
        return this.numCol;
    }
    public int getMinL(){
        return this.minL;
    }
    public int getMaxH(){
        return this.maxH;
    }
    
}