import java.lang.Math;
import java.util.*;

public class ConvexHull {
	private int numberPoints;
	private Vector<Point> points;

	public ConvexHull() {
    points = new Vector<Point>();
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
				Point temp2 = points.get(i);
				points.set(i, points.get(j));
				points.set(j, temp2);
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
	
	private double ccw (Point p1, Point p2, Point p3) {
		return ((p2.getX() - p1.getX())*(p3.getY() - p1.getY()) - (p2.getY() - p1.getY())*(p3.getX()- p1.getX()));
	}

	public Vector<Point> calculateHull(Vector<Point> pts) {
    points.setSize(pts.size());
    numberPoints = pts.size();
		Collections.copy(points, pts);

    if (numberPoints < 3) {
      return points;
    }

		Point lowesty = points.get(0);
		int lowestlocation = 0;
		
		/*Finds smallest y value and swaps it to start of array*/
		for (int i = 0; i < numberPoints; i++) {
			if (points.get(i).getY() > lowesty.getY()) {
				lowesty = points.get(i);
				lowestlocation = i;
			}
		}
		Point temp = points.get(1);
		points.set(1, lowesty);
		points.set(lowestlocation, temp);

		float anglesToLowest[] = new float[numberPoints];

		/*Makes array of all angles to lowest point*/
		for (int i = 0; i < numberPoints; i++) {
			/*Gets angle starting at the right*/
			float angle = (float) Math.toDegrees(Math.atan2(points.get(i).getX() - points.get(1).getX(), points.get(i).getY() - points.get(1).getY()));
			angle -= 90;
			if (angle < 0) {
				angle += 360;
			}
			anglesToLowest[i] = angle;
		}

    /* Sort points by angles to lowest point */
    sortArray(anglesToLowest, 0, numberPoints-1);
    for (int i = 0; i < 2; i++) {
      points.add(0, points.get(numberPoints-1));
    }

    for (int i = 0; i < numberPoints; i++) {
      points.get(i).setInHull(false);
    }

    points.get(0).setInHull(true);
    points.get(1).setInHull(true);
    points.get(2).setInHull(true);
    
    int first = 0, second = 1, third = 2;
    int inRow = 0;
    for (third = 2; third <= numberPoints-1; third++) {
      if (ccw(points.get(first), points.get(second), points.get(third)) <= 0) {
        points.get(first).setInHull(true);
        points.get(second).setInHull(true);
        points.get(third).setInHull(true);
        System.out.println(first + "," + second + "," + third + " neg");

        first = second;
        second = third;
        inRow++;
      } else { 
        points.get(second).setInHull(false);
        System.out.println(first + "," + second + "," + third + " pos");

        first--;
        while (!points.get(first).isInHull()) {
          first--;
        }
        second--;
        while (!points.get(second).isInHull()) {
          second--;
        }
        third--;
        inRow = 0;
      }
    }
    

    while (ccw(points.get(first), points.get(second), points.get(0)) > 0) {
      if (ccw(points.get(first), points.get(second), points.get(0)) > 0) {
        points.get(second).setInHull(false);
      } else {
        points.get(second).setInHull(true);
      }

      first--;
      while (!points.get(first).isInHull()) {
        first--;
      }
      second--;
      while (!points.get(second).isInHull()) {
        second--;
      }
    }
    
    return points;
	}
}
