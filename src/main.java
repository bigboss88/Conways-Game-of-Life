import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class main {
	static JPanel panel;
	static JFrame frame;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Map m = new Map(20,20);
		m.getCell(2,1).resurect();
		m.getCell(2,2).resurect();
		m.getCell(2,3).resurect();
		//m.getCell(6,7).resurect();
		panel = new JPanel();
		Dimension d= new Dimension(500,500);
		panel.setPreferredSize(d);
		frame = new JFrame();
		frame.setSize(500, 500);
		Container cPane = frame.getContentPane();
		cPane.add(panel);
		frame.setVisible(true);
		int a =0;
		Graphics g = panel.getGraphics();
		Graphics(m,g);
		g.dispose();
		while(a<10){
		 g = panel.getGraphics();
		Graphics(m,g);
		m.updateMap();
		Thread.sleep(400);
		g.dispose();
		a++;
		}
		System.out.print(m.toString());

	}
	
	public static void Graphics(Map map, Graphics g){
		 int box_size=10;
		 int[] dim = map.getDim();
		 for(int i=1;i<dim[0];i++){
			 for(int j=1;j<dim[1];j++){
				 if(map.getCell(i, j).getState()==1){
					 System.out.println(map.getCell(i, j).getState());
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
