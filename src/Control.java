import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.concurrent.TimeUnit;

public class Control{
    public static class Grid extends JPanel{
        private List<Point> cells;

        public Grid(Map map){
            cells = new ArrayList<>(map.getX() * map.getY()); // Creates the grid of squares from the map
        }

        protected void paintComponent(Graphics g){
            super.paintComponent(g);

            for(int i=0;i<cells.size();i++){
                int cellX = 10+(cells.get(i).x*10);
                int cellY = 10+(cells.get(i).y*10);
                g.setColor(Color.ORANGE);
                g.fillRect(cellX, cellY, 10, 10);
            }

            g.setColor(Color.BLACK);
            g.drawRect(10, 10, 800, 500);

            for (int i = 10; i <= 800; i += 10) {
                g.drawLine(i, 10, i, 510);
            }

            for (int i = 10; i <= 500; i += 10) {
                g.drawLine(10, i, 810, i);
            }
        }

        public void fill(int x,int y){
            cells.add(new Point(x,y));
            //System.out.println("Painting Cell: "+x+","+y);
            repaint();
        }

        public void clear(){
            cells.clear();
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
        MapLoader load = new MapLoader("./in.txt");
        Map map = load.read();
        Grid grid =  new Grid(map);
        JFrame window = new JFrame();
        window.setSize(map.getY()+100, map.getY()+100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(grid);
        window.setVisible(true);

        while(true){
            Thread.sleep(1000);
            grid.clear();
            for(int i = 0; i< map.getX();i++){
                for(int j =0;j<map.getY();j++){
                    if(map.getCell(i, j).alive()){
                        grid.fill(i,j);
                    }
                }
            }
            map.updateMap();

        }
    }

}