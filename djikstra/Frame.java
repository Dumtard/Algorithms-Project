import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.math.*;
import java.io.File;
import java.awt.Image.*;
import java.awt.image.BufferedImage.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/*Frame Class*/
public class Frame extends JFrame implements MouseListener {
	public static Panel pane;
	public static Vector<Node> intersections;
	public static Vector<Edge> connections;
	public static Vector<Edge> bestPath;
	public static boolean hasStart;
	public static boolean hasPath;
	public Node start;
	public Node dest;

	public Frame() {
		pane = new Panel();
		intersections = new Vector<Node>(10);

		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(689, 597);
		setVisible(true);
		setResizable(false);

	addMouseListener(this);
	}
	
	class Panel extends JPanel {
		@Override //print all components
		public void paintComponent(Graphics g) {
			Image  img = new ImageIcon("map.png").getImage();
			g.drawImage(img, 0, 0, null);
			//print all intersections
			g.setColor(Color.green);
			for (int i = 0; i < intersections.size(); i++) {
				int x = intersections.get(i).x;
				int y = intersections.get(i).y;
				g.fillOval(x-5, y-35, 10, 10);
				g.drawString(Integer.toString(i), x-5, y-35);
			}
			//print all roads
			for (int i = 0; i < connections.size(); i++) {
				int x1 = connections.get(i).From.x;
				int y1 = connections.get(i).From.y;
				int x2 = connections.get(i).To.x;
				int y2 = connections.get(i).To.y;
				g.drawLine(x1, y1-30, x2, y2-30);
			}
			//print out the desired path
			g.setColor(Color.red);
			if (hasPath) {
				for (int i = 0; i < bestPath.size(); i++) {
					int x1 = bestPath.get(i).From.x;
					int y1 = bestPath.get(i).From.y;
					int x2 = bestPath.get(i).To.x;
					int y2 = bestPath.get(i).To.y;
					g.drawLine(x1, y1-30, x2, y2-30);
				}
			}
		}
	}

  public void mouseClicked(MouseEvent e) {
    int x, y;
    x = e.getX();
    y = e.getY();
		if (!hasStart) {//set start position
			for (int i = 0; i < intersections.size(); i++) {
				if (Math.abs(x-intersections.get(i).x) < 5 && 
						Math.abs(y-intersections.get(i).y) < 5) {
					start = intersections.get(i);
					hasStart = true;
				}
			}
		} else if (!hasPath) {//set destination
			for (int i = 0; i < intersections.size(); i++) {
				if (Math.abs(x-intersections.get(i).x) < 5 && 
						Math.abs(y-intersections.get(i).y) < 5) {
					dest = intersections.get(i);
					Djikstra d = new Djikstra();
					start.dist = 0;
					bestPath = d.calculate(intersections, connections, start, dest);
					hasPath = true;
					repaint();
					break;
				}
			}
		} else if (hasStart && hasPath) {//reset
			bestPath = new Vector<Edge>();
			init();
		}
  }

  public void mousePressed(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }



	public static void init() {
		hasStart = false;
		hasPath = false;
		intersections = new Vector<Node>();//create intersections (x, y)
		intersections.add(new Node(106, 134));//0
		intersections.add(new Node(159, 116));//1
		intersections.add(new Node(123, 292));//2
		intersections.add(new Node(181,  86));//3
		intersections.add(new Node(235, 267));//4
		intersections.add(new Node(182, 282));//5
		intersections.add(new Node(296, 247));//6
		intersections.add(new Node(346, 230));//7
		intersections.add(new Node(396, 217));//8
		intersections.add(new Node(376, 151));//9
		intersections.add(new Node(151, 377));//10
		intersections.add(new Node(205, 363));//11
		intersections.add(new Node(261, 348));//12
		intersections.add(new Node(188, 499));//13
		intersections.add(new Node(242, 487));//14
		intersections.add(new Node(296, 472));//15
		intersections.add(new Node(197, 529));//16
		intersections.add(new Node(250, 512));//17
		intersections.add(new Node(306, 498));//18
		intersections.add(new Node(272, 383));//19
		intersections.add(new Node(280, 410));//20
		intersections.add(new Node(301, 374));//21
		intersections.add(new Node(308, 402));//22
		intersections.add(new Node(331, 367));//23
		intersections.add(new Node(320, 431));//24
		intersections.add(new Node(348, 422));//25
		intersections.add(new Node(366, 481));//26
		intersections.add(new Node(416, 466));//27
		intersections.add(new Node(468, 451));//28
		intersections.add(new Node(515, 435));//29
		intersections.add(new Node(426, 135));//30
		intersections.add(new Node(470, 122));//31
		intersections.add(new Node(512, 111));//32
		intersections.add(new Node(567, 124));//33
		intersections.add(new Node(598, 221));//34
		intersections.add(new Node(649, 392));//35
		intersections.add(new Node(573, 419));//36
		intersections.add(new Node(502, 178));//37
		intersections.add(new Node(498, 379));//38
		intersections.add(new Node(525, 372));//39
		intersections.add(new Node(522, 356));//40
		intersections.add(new Node(532, 389));//41
		connections = new Vector<Edge>();//Connect intersections via Edge class
		connections.add(new Edge(intersections.get(0),  intersections.get(1),  9 ));//0
		connections.add(new Edge(intersections.get(1),  intersections.get(0),  9 ));//1
		connections.add(new Edge(intersections.get(1),  intersections.get(2),  43));//2
		connections.add(new Edge(intersections.get(2),  intersections.get(1),  43));//3
		connections.add(new Edge(intersections.get(1),  intersections.get(3),  6 ));//4
		connections.add(new Edge(intersections.get(3),  intersections.get(1),  6 ));//5
		connections.add(new Edge(intersections.get(4),  intersections.get(3),  4 ));//6
		connections.add(new Edge(intersections.get(4),  intersections.get(5),  10));//6
		connections.add(new Edge(intersections.get(5),  intersections.get(4),  10));//7
		connections.add(new Edge(intersections.get(2),  intersections.get(5),  10));//8
		connections.add(new Edge(intersections.get(5),  intersections.get(2),  10));//9
		connections.add(new Edge(intersections.get(6),  intersections.get(4),  11));//10
		connections.add(new Edge(intersections.get(5),  intersections.get(11), 15));//11
		connections.add(new Edge(intersections.get(12), intersections.get(4),  15));//12
		connections.add(new Edge(intersections.get(12), intersections.get(11), 10));//13
		connections.add(new Edge(intersections.get(11), intersections.get(10), 9 ));//14
		connections.add(new Edge(intersections.get(11), intersections.get(14), 21));//15
		connections.add(new Edge(intersections.get(2),  intersections.get(10), 38));//16
		connections.add(new Edge(intersections.get(10), intersections.get(2),  38));//17
		connections.add(new Edge(intersections.get(10), intersections.get(13), 25));//18
		connections.add(new Edge(intersections.get(13), intersections.get(10), 25));//19
		connections.add(new Edge(intersections.get(13), intersections.get(16), 5 ));//20
		connections.add(new Edge(intersections.get(16), intersections.get(13), 5 ));//21
		connections.add(new Edge(intersections.get(16), intersections.get(17), 10));//22
		connections.add(new Edge(intersections.get(17), intersections.get(16), 10));//23
		connections.add(new Edge(intersections.get(18), intersections.get(17), 10));//24
		connections.add(new Edge(intersections.get(17), intersections.get(18), 10));//25
		connections.add(new Edge(intersections.get(13), intersections.get(14), 10));//26
		connections.add(new Edge(intersections.get(14), intersections.get(13), 10));//27
		connections.add(new Edge(intersections.get(14), intersections.get(17), 5 ));//28
		connections.add(new Edge(intersections.get(15), intersections.get(14), 5 ));//29
		connections.add(new Edge(intersections.get(18), intersections.get(15), 5 ));//30
		connections.add(new Edge(intersections.get(15), intersections.get(20), 11));//31
		connections.add(new Edge(intersections.get(20), intersections.get(19), 5 ));//32
		connections.add(new Edge(intersections.get(19), intersections.get(12), 6 ));//33
		connections.add(new Edge(intersections.get(20), intersections.get(22), 5 ));//34
		connections.add(new Edge(intersections.get(22), intersections.get(20), 5 ));//35
		connections.add(new Edge(intersections.get(19), intersections.get(21), 5 ));//36
		connections.add(new Edge(intersections.get(21), intersections.get(19), 5 ));//37
		connections.add(new Edge(intersections.get(21), intersections.get(22), 5 ));//38
		connections.add(new Edge(intersections.get(22), intersections.get(21), 5 ));//39
		connections.add(new Edge(intersections.get(22), intersections.get(24), 5 ));//40
		connections.add(new Edge(intersections.get(24), intersections.get(22), 5 ));//41
		connections.add(new Edge(intersections.get(24), intersections.get(25), 5 ));//42
		connections.add(new Edge(intersections.get(25), intersections.get(24), 5 ));//43
		connections.add(new Edge(intersections.get(21), intersections.get(23), 5 ));//44
		connections.add(new Edge(intersections.get(23), intersections.get(21), 5 ));//45
		connections.add(new Edge(intersections.get(6),  intersections.get(4),  10));//46
		connections.add(new Edge(intersections.get(6),  intersections.get(23), 20));//47
		connections.add(new Edge(intersections.get(23), intersections.get(25), 10));//48
		connections.add(new Edge(intersections.get(25), intersections.get(26), 10));//49
		connections.add(new Edge(intersections.get(26), intersections.get(18), 11));//50
		connections.add(new Edge(intersections.get(18), intersections.get(26), 11));//51
		connections.add(new Edge(intersections.get(7),  intersections.get(6),  9 ));//52
		connections.add(new Edge(intersections.get(7),  intersections.get(8),  9 ));//53
		connections.add(new Edge(intersections.get(8),  intersections.get(7),  9 ));//54
		connections.add(new Edge(intersections.get(8),  intersections.get(9),  12));//55
		connections.add(new Edge(intersections.get(9),  intersections.get(8),  12));//56
		connections.add(new Edge(intersections.get(8),  intersections.get(28), 44));//57
		connections.add(new Edge(intersections.get(28), intersections.get(8),  44));//58
		connections.add(new Edge(intersections.get(28), intersections.get(27), 12));//59
		connections.add(new Edge(intersections.get(27), intersections.get(28), 12));//60
		connections.add(new Edge(intersections.get(26), intersections.get(27), 8 ));//61
		connections.add(new Edge(intersections.get(27), intersections.get(26), 8 ));//62
		connections.add(new Edge(intersections.get(29), intersections.get(28), 9 ));//63
		connections.add(new Edge(intersections.get(28), intersections.get(29), 9 ));//64
		connections.add(new Edge(intersections.get(9),  intersections.get(30), 8 ));//65
		connections.add(new Edge(intersections.get(30), intersections.get(9),  8 ));//66
		connections.add(new Edge(intersections.get(30), intersections.get(38), 43));//67
		connections.add(new Edge(intersections.get(38), intersections.get(30), 43));//68
		connections.add(new Edge(intersections.get(38), intersections.get(29), 10));//69
		connections.add(new Edge(intersections.get(29), intersections.get(38), 10));//70
		connections.add(new Edge(intersections.get(38), intersections.get(39), 5 ));//71
		connections.add(new Edge(intersections.get(39), intersections.get(38), 5 ));//72
		connections.add(new Edge(intersections.get(39), intersections.get(40), 5 ));//73
		connections.add(new Edge(intersections.get(40), intersections.get(39), 5 ));//74
		connections.add(new Edge(intersections.get(39), intersections.get(41), 5 ));//75
		connections.add(new Edge(intersections.get(41), intersections.get(39), 5 ));//76
		connections.add(new Edge(intersections.get(29), intersections.get(36), 10));//77
		connections.add(new Edge(intersections.get(36), intersections.get(29), 10));//78
		connections.add(new Edge(intersections.get(36), intersections.get(37), 42));//79
		connections.add(new Edge(intersections.get(37), intersections.get(36), 42));//80
		connections.add(new Edge(intersections.get(35), intersections.get(36), 13));//81
		connections.add(new Edge(intersections.get(36), intersections.get(35), 13));//82
		connections.add(new Edge(intersections.get(35), intersections.get(34), 35));//83
		connections.add(new Edge(intersections.get(34), intersections.get(35), 35));//84
		connections.add(new Edge(intersections.get(33), intersections.get(34), 22));//85
		connections.add(new Edge(intersections.get(34), intersections.get(33), 22));//86
		connections.add(new Edge(intersections.get(33), intersections.get(32), 12));//87
		connections.add(new Edge(intersections.get(32), intersections.get(33), 12));//88
		connections.add(new Edge(intersections.get(30), intersections.get(31), 8 ));//89
		connections.add(new Edge(intersections.get(31), intersections.get(30), 8 ));//90
		connections.add(new Edge(intersections.get(31), intersections.get(32), 10));//91
		connections.add(new Edge(intersections.get(32), intersections.get(31), 10));//92
		connections.add(new Edge(intersections.get(7),  intersections.get(27), 42));//93
		connections.add(new Edge(intersections.get(27), intersections.get(7),  42));//94
		pane.repaint();
	}

  /*Main Method*/
  public static void main (String args[]) {
    Frame window = new Frame();
		init();
  }
}
