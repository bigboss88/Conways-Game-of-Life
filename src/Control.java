import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.*;
import javax.swing.JScrollPane;
public class Control{
    public static JButton start = new JButton("Start");
    public static JButton stop = new JButton("Stop");
    public static int state = 1;
    public static Grid grid;
    public static Map map;
    public static  class buttonActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) { 
            if(e.getSource() == start){
                state =1;
                System.out.println("Start pressed");
            }

            else if(e.getSource() == stop){
                state =0;
                System.out.println("Stop pressed");
            }
          } 
    }

    public static class mouseListener extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            Point point= e.getPoint(); //Get mouse location at click
            System.out.println(point);
            int x = (int)Math.floor((point.x/10)-1); //Basically What i'm doing is getting the pixel value
                                                     // And converting it back into map coordinates
            int y = (int)Math.floor((point.y/10)-1);
            
            map.getCell(x,y).resurect(); //Bring clicked cell back to life
            System.out.println(x+","+y);
        }
    }

    public static void updateGrid(Map m, Grid g){
        for(int i = 0; i< map.getX();i++){
            for(int j =0;j<map.getY();j++){
                if(map.getCell(i, j).alive()){ //Go through all cells in map and see if they are alive
                    grid.fill(i,j); //if so draw them
                }
            }
        }
    }

    //I know this is ugly
    public static void main(String[] args) throws InterruptedException {
        //Get map and dimensions
        MapLoader load = new MapLoader("./in.txt");
        map = load.read(); // load in map from file
        int x = map.getX()*10; int y = map.getY()*10;
        //Set up grid
        grid =  new Grid(map); // Create grid from the map
        grid.setPreferredSize(new Dimension(x+20,y+20));
        grid.addMouseListener(new mouseListener());
        start.addActionListener(new buttonActionListener());
        stop.addActionListener(new buttonActionListener());
        //Set up panels and components
        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();
        panel.setLayout(new BorderLayout());
        JFrame window = new JFrame();
        
        panel.add(grid,BorderLayout.PAGE_START);
        buttonPanel.add(start);
        buttonPanel.add(stop);
        panel.add(buttonPanel,BorderLayout.WEST);
        panel.repaint();
        JLabel frames = new JLabel();
        buttonPanel.add(frames);

        //Set up window
        window.setSize(x+50,y+100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(panel);
        
        window.setVisible(true);
        int frameNum=0;
        System.out.println(state);
        while(true){
            state=state; // This needs to happen for some reason for it to pick up that the states switched
            if(state==1){
                Thread.sleep(500); // Sleep for half a second
                grid.clear(); // clear grid so it can be refreshed
                updateGrid(map,grid);
                map.updateMap(); //update map
                frames.setText("Frame #:"+frameNum);
                //System.out.println(map.toString());
                frameNum++;
                grid.repaint();
            }
            else{
                frames.setText("Stopped");
                Thread.sleep(500);
                grid.repaint();
                //grid.clear(); // clear grid so it can be refreshed
                updateGrid(map,grid);
            }
        }

        
    }

}
