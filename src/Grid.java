import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
public  class Grid extends JPanel{
    private List<Point> cells;
    private int xm;
    private int ym;
    public Grid(Map map){
        cells = new ArrayList<>(map.getX() * map.getY()); // Creates the grid of squares from the map
        xm = map.getX()*10;
        ym = map.getY()*10;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        for(int i=0;i<cells.size();i++){ //for each alive cell
            int cellX = 10+(cells.get(i).x*10); //get the x and y pos then scale them
            int cellY = 10+(cells.get(i).y*10);
            g.setColor(Color.ORANGE); // set the colour of the rectangle
            g.fillRect(cellX, cellY, 10, 10); // draw the rectangle 
        }

        g.setColor(Color.BLACK);
        g.drawRect(10, 10, xm, ym); //Start with outer rectangle 

        for (int i = 10; i <= xm; i += 10) { //Draw the x lines, spaced out by 10,
            g.drawLine(i, 10, i, ym+10);
        }

        for (int i = 10; i <= ym; i += 10) { // Y lines
            g.drawLine(10, i, xm+10, i);
        }
    }

    public void fill(int x,int y){
        cells.add(new Point(x,y)); //Add that point to the list of cells 
    }

    public void clear(){
        cells.clear(); // clears out everything in the list
    }

    public String toString(){
        String out = "";
        for(int i =0;i<this.cells.size();i++){
            out+=""+this.cells.get(i).x +", "+this.cells.get(i).y+"\n";
        }
        return out;
    }
    
}