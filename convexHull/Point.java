import java.awt.*;
import java.util.*;

public class Point {
	private double x, y;
  private boolean inHull;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
    inHull = false;
	}

  public double getX() {
    return x;
  }
  public double getY() {
    return y;
  }

  public void updatePoint(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public void draw(Graphics g) {
    g.fillOval((int)(x - 5), (int)(y - 5), 10, 10);
  }
}
