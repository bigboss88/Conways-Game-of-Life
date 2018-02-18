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

public int getNear(int i,int j){
	int count =0;
	if(cl[i+1][j].alive()){count++;}//right
	if(cl[i-1][j].alive()){count++;}//left
	if(cl[i][j+1].alive()){count++;}//down
	if(cl[i][j-1].alive()){count++;}//up
	if(cl[i+1][j-1].alive()){count++;}//up right corner
	if(cl[i+1][j+1].alive()){count++;}//down right corner
	if(cl[i-1][j-1].alive()){count++;}//up left corner
	if(cl[i-1][j+1].alive()){count++;}//down left corner
	return count;

}


public void updateMap(){
	ArrayList<Cell> toLive = new ArrayList<Cell>();
	ArrayList<Cell> toDie = new ArrayList<Cell>();
	for(int i=1;i<xm;i++){
		for(int j=1;j<ym;j++){
			if(cl[i][j].shouldLive(getNear(i,j))){
				toLive.add(cl[i][j]);
			}
			else{toDie.add(cl[i][j]);}
		}
	}
	for(int i=0;i<toLive.size();i++){
		toLive.get(i).resurect();
	}
	for(int i=0;i<toDie.size();i++){
		toDie.get(i).die();
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
		out+=" "+cl[i][j].alive();	
		}
		out+="\n";
	}
	return out;
}

}
