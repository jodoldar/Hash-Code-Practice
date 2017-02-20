import java.awt.Point;

public class Slice{
	Point lUp, rUp, lDown, rDown;
	int nMushroom, nTomatoe;

	public Slice(Point lu, Point ru, Point ld,Point rd, int nMush, int nTom){
		this.lUp = lu;
		this.rUp = ru;
		this.lDown = ld;
		this.rDown = rd;
		this.nMushroom = nMush;
		this.nTomatoe = nTom;
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

	public int getNTomatoe(){
		return this.nTomatoe;
	}	
}