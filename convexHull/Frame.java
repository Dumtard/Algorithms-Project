import java.awt.*;
import javax.swing.*;
import java.math.*;

public class Frame extends JFrame {
	public static Point points[];
	public static final int numPoints = 10;
	public static Panel pane;
	public Frame () {
			pane = new Panel();
			setContentPane(pane);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(800,600);
			setVisible(true);//asdf
	}
	
	class Panel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			g.setColor(Color.black);
			g.fillRect(0,0,800,600);
			g.setColor(Color.yellow);
			for (int i = 0; i < numPoints; i++) {
				g.fillRect((int)(points[i].X), (int)(points[i].Y),4,4);
			}
		}
	}

	public static void addRandomPoints(int num) {
		for (int i = 0; i < num; i++) {
			points[i] = new Point((Math.random()*800), (Math.random()*600));
		}
	}

	/*Main Method*/
	public static void main (String args[]) {
		Frame window = new Frame();
		points = new Point[numPoints];
		addRandomPoints(numPoints);
		window.pane.repaint();
	}
}
