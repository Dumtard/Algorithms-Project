import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.math.*;

/*Frame Class*/
public class Frame extends JFrame implements ActionListener, MouseListener {
  public static Panel pane;
  public static JButton buttonRand;
  public static JButton buttonNew;
  public static Vector<Point> points;
  public static Vector<Point> hull;
  public static ConvexHull convexHull;
  public static Container btns;

  public Frame() {
    pane = new Panel();
    pane.setLayout(new BorderLayout());
    Container btns = new Container();
    btns.setLayout(new FlowLayout());
    buttonRand = new JButton("Randomize");
    buttonNew  = new JButton("Clear Points");
    points = new Vector<Point>();
    hull = new Vector<Point>();
    convexHull = new ConvexHull();

    setContentPane(pane);
    pane.add(btns, BorderLayout.SOUTH);
    btns.add(buttonNew);
    btns.add(buttonRand);
    //pane.add(buttonNew, BorderLayout.SOUTH); 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800,650);
    setVisible(true);
    setResizable(false);

    addMouseListener(this);

    //Add action listener to button
    buttonRand.addActionListener(this);
    buttonNew.addActionListener(this);
  }
	
  class Panel extends JPanel {
    @Override //print all components
    public void paintComponent(Graphics g) {
      g.setColor(Color.black);
      g.fillRect(0,0,800,700);
    
      if (!points.isEmpty()) {
        for (int i = 0; i < points.size(); i++) {
          g.setColor(Color.yellow);
          points.get(i).draw(g);
        }
      }
      if (!hull.isEmpty()) {
        for (int i = 0; i < hull.size(); i++) {
          g.setColor(Color.yellow);
          if (hull.get(i).isInHull()) {
            g.setColor(Color.red);
          }
          hull.get(i).draw(g);
        }
        if (hull.size() > 2) {
          double x1, x2, y1, y2;
          x1 = hull.get(0).getX();
          y1 = hull.get(0).getY();
          x2 = hull.get(1).getX();
          y2 = hull.get(1).getY();
          for (int i = 2; i < hull.size(); i++) {
            g.setColor(Color.red);
            g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
            if (hull.get(i).isInHull()) {
              x1 = x2;
              y1 = y2;
              x2 = hull.get(i).getX();
              y2 = hull.get(i).getY();
            }
          }
          g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
          g.drawLine((int)x2, (int)y2, (int)hull.get(0).getX(), (int)hull.get(0).getY());

        }
      }
    }
  }

  public void mouseClicked(MouseEvent e) {
    int x, y;

    x = e.getX();
    y = e.getY();

    if (points.isEmpty()) {
        Point point = new Point((double)x - 4, (double)y - 25);
        points.add(point);
        repaint();
    } else {
      boolean unique = true;
      for (int i = 0; i < points.size(); i++) {
        if ((x-4) == (int)points.get(i).getX() && (y-25) == (int)points.get(i).getY()) {
          unique = false;
          break;
        }
      }
      if (unique) {
        Point point = new Point((double)x - 4, (double)y - 25);
        points.add(point);
        hull = convexHull.calculateHull(points);
        repaint();
      }
    }
  }

  public void actionPerformed(ActionEvent e) {
    //Execute when button is pressed
    Object src = e.getSource();
    if (src == buttonRand) {
      for (int i = 0; i < points.size(); i++) {
        points.get(i).updatePoint((int)(Math.random()*800), (int)(Math.random()*600));
      }
    } else if (src == buttonNew) {
      points = new Vector<Point>();
    }
    hull = convexHull.calculateHull(points);
    repaint();
  }

  public void mousePressed(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }

  /*Main Method*/
  public static void main (String args[]) {
    Frame window = new Frame();
    if (!points.isEmpty()) {
      hull = convexHull.calculateHull(points);
    }
    window.pane.repaint();
  }
}
