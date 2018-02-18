import java.util.ArrayList;

public class Cell {
private int state; // This is to determine is the cell is alive or dead. 1 for alive 0 for dead

public Cell(){
	state =0; //Cell is dead when created
}
public void die(){
	state=0; //Kills the cell;
}

public int getState(){
	return state;
}

public void resurect(){
	state=1; //Gives cell life
}

public void updateLife(ArrayList<Cell> neighbors){

	
	int count = 0;
	for(int i =0;i<neighbors.size();i++){
		if(neighbors.get(i).getState()==1){
			count++;
		}
	}
	
	if(count ==3){state=1;} //Reproduction case
	else if(count>1 && count<4){state=1;}//Doesn't die case
	else{state=0;}
}

}
