import java.util.*;
public class Edge {
	public Node From;
	public Node To;
	public double Weight;
	public Edge (Node n1, Node n2, double w) {
		this.From = n1;
		this.To   = n2;
		this.Weight = w;//time taken to travel this path
	}
}
