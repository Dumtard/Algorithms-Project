import java.lang.Math;
import java.util.Stack;

public class ConvexHull {
	private int numberPoints;
	private Point points[];

	public ConvexHull(Point points[]) {
		numberPoints = points.length;	
		this.points = points;
	}

	private void sortArray(float angles[], int low, int high) {
		int i = low, j = high;
		float pivot = angles[(low + high) / 2];
		
		if (i >= high) {
			return;
		}

		while (i <= j) {
			while (angles[i] < pivot) {
				i++;
			}
			while (angles[j] > pivot) {
				j--;
			}
			if (i <= j) {
				float temp = angles[i];
				angles[i] = angles[j];
				angles[j] = temp;
				Point temp2 = points[i];
				points[i] = points[j];
				points[j] = temp2;
				i++;
				j--;
			}
		}
		
		if (low < j) {
			sortArray(angles, low, j);
		}
		if (i < high) {
			sortArray(angles, i, high);
		}
	}
	
	private int ccw (Point p1, Point p2, Point p3) {
		return (int)((p2.x - p1.x)*(p3.y - p1.y) - (p2.y - p1.y)*(p3.x - p1.x));
	}

	public Stack<Point> calculateHull() {
		Point lowesty = points[0];
		int lowestlocation = 0;
		
		/*Finds smallest y value and swaps it to start of array*/
		for (int i = 0; i < numberPoints; i++) {
			if (points[i].y > lowesty.y) {
				lowesty = points[i];
				lowestlocation = i;
			}
		}
		Point temp = points[1];
		points[1] = lowesty;
		points[lowestlocation] = temp;

		float anglesToLowest[] = new float[10];

		/*Makes array of all angles to lowest point*/
		for (int i = 0; i < 10; i++) {
			/*Gets angle starting at the right*/
			float angle = (float) Math.toDegrees(Math.atan2(points[i].x - points[1].x, points[i].y - points[1].y));
			angle -= 90;
			if (angle < 0) {
				angle += 360;
			}
			anglesToLowest[i] = angle;
		}


		/*Sorts the points array by angle to lowest point*/
		sortArray(anglesToLowest, 0, 9);
		float temp2;
		temp = points[1];
		points[1] = points[9];
		points[9] = temp;
		temp = points[0];
		points[0] = points[8];
		points[8] = temp;
		temp2 = anglesToLowest[1];
		anglesToLowest[1] = anglesToLowest[9];
		anglesToLowest[9] = temp2;
		temp2 = anglesToLowest[0];
		anglesToLowest[0] = anglesToLowest[8];
		anglesToLowest[8] = temp2;
		sortArray(anglesToLowest, 2, 9);

		Stack<Point> stackPoints = new Stack<Point>();
		Point top;

		stackPoints.push(points[0]);
		stackPoints.push(points[1]);

		for (int i = 2; i < 10; i++) {
			top = stackPoints.pop();
			while (ccw(stackPoints.peek(), top, points[i]) > 0) {	
				top = stackPoints.pop();
			}

			stackPoints.push(top);
			stackPoints.push(points[i]);
		}
		return stackPoints;
	}
}
/*
3 4 5

3 5 6
*/
