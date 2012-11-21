import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

/*Frame Class*/
public class Frame extends JFrame implements MouseListener{
  public static Vector<Point> points;
  public static Panel pane;

  public Frame() {
    pane = new Panel();
    points = new Vector<Point>();

    setContentPane(pane);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800,600);
    setVisible(true);

    addMouseListener(this);
	}
	
  class Panel extends JPanel {
    @Override //print all components
    public void paintComponent(Graphics g) {
      g.setColor(Color.black);
      g.fillRect(0,0,800,600);
     
      if (!points.isEmpty()) {
        for (int i = 0; i < points.size(); i++) {
          g.setColor(Color.yellow);
          points.elementAt(i).draw(g);
        }
      }
    }
  } 

  public void mouseClicked(MouseEvent e) {
    int x, y;

    x = e.getX();
    y = e.getY();

    Point point = new Point((double)x - 4, (double)y - 25);
    points.add(point);
    repaint();
  }
  public void mousePressed(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }

  /*Main Method*/
  public static void main (String args[]) {
    Frame window = new Frame();
    window.pane.repaint();
  }
}
