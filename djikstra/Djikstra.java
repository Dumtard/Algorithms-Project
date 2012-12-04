import java.util.*;

public class Djikstra {
	public Djikstra() {}
	public Vector<Edge> calculate (Vector<Node> nodes, Vector<Edge> edges, Node start, Node end) {
		Vector<Node> n = new Vector<Node>();
		n.add(start);
		do {
		Vector<Node> n2 = new Vector<Node>();//node to update dist, and path to.
			Vector<Node> newNodes = new Vector<Node>();//current nodes being checked for new paths
			for (int i = 0; i < edges.size(); i++) {//go through all edges
				if (n.contains(edges.get(i).From)) {//if the edge is connected to a node being checked
					Node n3 = nodes.get(nodes.indexOf(edges.get(i).To));//set n3 to be the destination of this path
					if (edges.get(i).Weight < n3.dist) {//if the new path is shorter
						n3.dist = edges.get(i).Weight + edges.get(i).From.dist;//update the path with the new values
						n3.path = new Vector<Edge>();
						for (int j = 0; j < edges.get(i).From.path.size(); j++) {
							n3.path.add(edges.get(i).From.path.get(j));//update the path of the newly connected intersection
						}
						n3.path.add(edges.get(i));
						newNodes.add(n3);//add this newly connected intersection the list being checked
					}
				}
			}	
			n = newNodes;
			
		} while (end.dist == 99999);

		//for (int i = 0; i < end.path.size(); i++) {
		//	System.out.println(end.path.get(i).From.x + "\t" + end.path.get(i).From.y + "\t" + end.path.get(i).To.x + "\t" + end.path.get(i).To.y);
		//}

		return end.path;
	}
}
