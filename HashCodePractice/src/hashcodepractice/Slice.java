package hashcodepractice;   

import java.awt.Point;

public class Slice{
	private Point lUp, rUp, lDown, rDown;
	private int nMushroom, nTomatoe, lMovement;

	public Slice(Point lu, Point ru, Point ld,Point rd, int nMush, int nTom){
		this.lUp = lu;
		this.rUp = ru;
		this.lDown = ld;
		this.rDown = rd;
		this.nMushroom = nMush;
		this.nTomatoe = nTom;
		this.lMovement = -1;
	}

	public Point getLUp(){
		return this.lUp;
	}

	public void setLUp(Point nPos){
		this.lUp = nPos;
	}

	public Point getRUp(){
		return this.rUp;
	}

	public void setRUp(Point nPos){
		this.rUp = nPos;
	}

	public Point getLDown(){
		return this.lDown;
	}

	public void setLDown(Point nPos){
		this.lDown = nPos;
	}

	public Point getRDown(){
		return this.rDown;
	}

	public void setRDown(Point nPos){
		this.rDown = nPos;
	}

	public int getNMushroom(){
		return this.nMushroom;
	}

	public void setNMushroom(int nNMush){
		this.nMushroom = nNMush;
	}

	public int getNTomatoe(){
		return this.nTomatoe;
	}

	public void setNTomatoe(int nNTom){
		this.nTomatoe = nNTom;
	}

	public int getLMovement(){
		return this.lMovement;
	}

	public void setLMovement(int lMov){
		this.lMovement = lMov;
	}

	public boolean isValid(int l){
		return nMushroom >= l && nTomatoe >= l;
	}	
}