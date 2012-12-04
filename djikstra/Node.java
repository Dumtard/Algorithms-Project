import java.util.*;
public class Node {
	public int x, y;
	public double dist;
	public Vector<Edge> path;
	public Node(int posx, int posy) {
		this.x = posx;//x and y positions for displaying
		this.y = posy;
		this.dist = 99999;//distance to this node. starts at ~infinity
		path = new Vector<Edge>();//the optimal path to this node
	}
}
