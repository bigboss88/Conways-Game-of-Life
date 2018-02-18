import java.awt.Rectangle;
import java.util.ArrayList;

public class Map {
private Cell[][] cl;
private int xm,ym;

public Map(int x,int y){
	xm=x;
	ym=y;
	cl = new Cell[x+2][y+2];
	for(int i=0;i<x+1;i++){
		for(int j =0;j<y+1;j++){
			cl[i][j]=new Cell();
		}
	}
}

public ArrayList<Cell> getNear(Cell c){
	ArrayList<Cell> neighbors = new ArrayList<Cell>();
	for(int i =1;i<xm;i++){
		for(int j=1;j<ym;j++){
			if(cl[i][j]==c){
				neighbors.add(cl[i+1][j]);//right
				neighbors.add(cl[i-1][j]);//left
				neighbors.add(cl[i][j+1]);//down
				neighbors.add(cl[i][j-1]);//up
				neighbors.add(cl[i+1][j-1]);//up right corner
				neighbors.add(cl[i+1][j+1]);//down right corner
				neighbors.add(cl[i-1][j+1]);//up left corner
				neighbors.add(cl[i-1][j-1]);//down left corner
				return neighbors;
			}
		}
	}
	neighbors.add(new Cell());
	return neighbors;
}


public void updateMap(){
	for(int i=1;i<xm;i++){
		for(int j=1;j<ym;j++){
			cl[i][j].updateLife(getNear(cl[i][j]));
		}
	}
}
public int[] getDim(){
	int[] out = new int[2];
	out[0]=xm;
	out[1]=ym;
	return out;
}


public Cell getCell(int x,int y){
	return cl[x][y];
}

public String toString(){
	String out="";
	for(int i=1;i<xm;i++){
		for(int j =1;j<ym;j++){
		out+=" "+cl[i][j].getState();	
		}
		out+="\n";
	}
	return out;
}
}
