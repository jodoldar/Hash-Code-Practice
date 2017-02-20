package hashcodepractice;

import java.awt.Point;
/**
 *
 * @author Josep Dols
 */
public class Cell {
    public static final boolean TOMATOE=true;
    public static final boolean MUSHROOM=false;
    
    private final Point posicion;
    private final boolean ingrediente;
    private boolean isInSlice;
    
    public Cell(boolean ing, Point pos){
        this.ingrediente = ing;
        this.posicion = pos;
        this.isInSlice = false;
    }
    
    public boolean getIngrediente(){
        return this.ingrediente;
    }
    public Point getPosicion(){
        return this.posicion;
    }
    public int getXPosicion(){
        return (int)this.posicion.getX();
    }
    public int getYPosicion(){
        return (int)this.posicion.getY();
    }
    public boolean isInSlice(){
        return this.isInSlice;
    }
    public void setSlice(boolean esta){
        this.isInSlice = esta;
    }
    
    
}
