import java.awt.*;
import javax.swing.*;
import java.math.*;
import java.util.*;

/*Frame Class*/
public class Frame extends JFrame {
	public static Point points[];
	public static final int numPoints = 10;
  int hullSize;
  public static Stack<Point> sizeOfHull;
  public static Stack<Point> sizeOfHull2;
  public static Panel pane;

	public Frame() {
			pane = new Panel();
      Stack<Point> sizeOfHull = new Stack<Point>();
      Stack<Point> sizeOfHull2 = new Stack<Point>();
      hullSize = 0;
			setContentPane(pane);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(800,600);
			setVisible(true);
	}
	
	class Panel extends JPanel {
		@Override //print all components
		public void paintComponent(Graphics g) {
      sizeOfHull = sizeOfHull2.addAll();
      sizeOfHull2 = sizeOfHull.addAll();

      g.setColor(Color.black);
		  g.fillRect(0,0,800,600);
       g.setColor(Color.yellow);
			 for (int i = 0; i < numPoints; i++) { 
			 	g.fillOval((int)(points[i].x) - 5, (int)(points[i].y) - 5, 10, 10);
       }

      //Point first = sizeOfHull.peek();
      for (Point i : sizeOfHull) {
        hullSize++;
      }

      if (!sizeOfHull.empty()) {
        for (int i = 0; i < hullSize; i++) {
          Point current = sizeOfHull.pop();
         //g.drawLine((int)(current.x), (int)(current.y), (int)(first.x), (int)(first.y));
          g.setColor(Color.red);
          g.fillOval((int)(current.x) - 5, (int)(current.y) - 5, 10, 10);
        }
      }
		}
	}

	public static void addRandomPoints(int num) {
    points = new Point[num];
		for (int i = 0; i < num; i++) {
			points[i] = new Point((Math.random()*800), (Math.random()*600));
		}
	}

	/*Main Method*/
	public static void main (String args[]) {
		Frame window = new Frame();
		addRandomPoints(numPoints);
    ConvexHull hull = new ConvexHull(points); 
    sizeOfHull = hull.calculateHull();
    sizeOfHull2 = sizeOfHull.addAll();
    window.pane.repaint();
	}
}
