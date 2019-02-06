import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;

import javax.swing.*;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
import java.util.concurrent.TimeUnit;

public class Control{
    public static class Grid extends JPanel{
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
            //System.out.println("Painting Cell: "+x+","+y);
            repaint(); //repaint the grid 
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

    public static void main(String[] args) throws InterruptedException {

        //Get map and dimensions
        MapLoader load = new MapLoader("./in.txt");
        Map map = load.read(); // load in map from file
        int x = map.getX()*10; int y = map.getY()*10;

        //Set up grid
        Grid grid =  new Grid(map); // Create grid from the map
        grid.setPreferredSize(new Dimension(x+20,y+20));

        //Set up panels and components
        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();
        panel.setLayout(new BorderLayout());
        JFrame window = new JFrame();
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        panel.add(grid,BorderLayout.PAGE_START);
        buttonPanel.add(start);
        buttonPanel.add(stop);
        panel.add(buttonPanel,BorderLayout.WEST);
        panel.repaint();

        //Set up window
        window.setSize(x+50,y+100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(panel);
        
        window.setVisible(true);

        while(true){
            Thread.sleep(500); // Sleep for half a second
            grid.clear(); // clear grid so it can be refreshed
            for(int i = 0; i< map.getX();i++){
                for(int j =0;j<map.getY();j++){
                    if(map.getCell(i, j).alive()){ //Go through all cells in map and see if they are alive
                        grid.fill(i,j); //if so draw them
                    }
                }
            }
            map.updateMap(); //update map

        }
    }

}