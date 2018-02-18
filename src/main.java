import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class main {
	static JPanel panel;
	static JFrame frame;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Map m = new Map(20,20);
		m.getCell(10,10).resurect();
		m.getCell(10,11).resurect();
		m.getCell(10,12).resurect();
		m.getCell(9,11).resurect();
		m.getCell(11,11).resurect();
		System.out.println(m.getNear(3,3));
		panel = new JPanel();
		Dimension d= new Dimension(400,400);
		panel.setPreferredSize(d);
		frame = new JFrame();
		frame.setSize(400, 400);
		Container cPane = frame.getContentPane();
		cPane.add(panel);
		frame.setVisible(true);
		int a =0;
		Graphics g = panel.getGraphics();
		Graphics(m,g);
		Thread.sleep(150);
		g.dispose();
		while(a<1000){
		g = panel.getGraphics();
		Graphics(m,g);
		Thread.sleep(150);
		m.updateMap();
		g.dispose();
		a++;
		}
		//System.out.print(m.toString());

	}
	
	public static void Graphics(Map map, Graphics g){
		 int box_size=10;
		 int[] dim = map.getDim();
		 for(int i=1;i<dim[0];i++){
			 for(int j=1;j<dim[1];j++){
				 if(map.getCell(i, j).alive()){
					 g.setColor(Color.BLACK);
					 g.fillRect(i*box_size, j*box_size, box_size, box_size);
				 }
				 else{
					 g.setColor(Color.WHITE);
					 g.fillRect(i*box_size, j*box_size, box_size, box_size);
				 }
			 }
		 }
		
	}

}
