package hashcodepractice; 

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.awt.Point;
import java.io.FileNotFoundException;


public class Pizza{
    private Cell[][] piz;
    private int columnas, filas, minL, maxH;
    public Pizza(File f){
        Scanner entrada = null;
        try{
            entrada = new Scanner(f);
            filas = entrada.nextInt();
            columnas = entrada.nextInt();
            minL = entrada.nextInt();
            maxH = entrada.nextInt();
            entrada.nextLine();
            piz = new Cell[filas][columnas];
            int j = 0;
            String s = "";
            while(entrada.hasNext()){
                s = entrada.nextLine();
                for(int i = 0; i<s.length(); i++){
                   if(s.charAt(i) == 'T'){
                       piz[j][i] = new Cell(true, new Point(j,i));
                   }else{
                       piz[j][i] = new Cell(false, new Point(j,i));
                   }
                }
                j++;
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
    public int getFilas(){
        return this.filas;
    }
    public int getColumnas(){
        return this.columnas;
    }
    public int getMinL(){
        return this.minL;
    }
    public int getMaxH(){
        return this.maxH;
    }
}