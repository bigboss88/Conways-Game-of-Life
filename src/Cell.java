import java.util.ArrayList;

public class Cell {
private int state; // This is to determine is the cell is alive or dead. 1 for alive 0 for dead

public Cell(){
	state =0; //Cell is dead when created
}
public void die(){
	state=0; //Kills the cell;
}

public boolean alive(){
	return state==1;
}

public void resurect(){
	state=1; //Gives cell life
}

public void updateLife(int numNear){
 if(state==0){
	 if(numNear==3){
		 this.resurect();
	 }
 }
 else{
	 if(numNear!=2 ||  numNear!=3){
		 this.die();
	 }
 }
 
 }
public boolean shouldLive(int numNear){
	 if(state==0){
		 if(numNear==3){
			 return true;
		 }
		 return false;
	 }
	 else{
		 if(numNear==2 || numNear==3){
			 return true;
		 }
		 return false;
	 }
}
}


