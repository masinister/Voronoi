import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import java.util.Vector;

class Node {

	public double x,y;
	public Color color;
	public Node(double x, double y, Color c){
		this.x = x;
		this.y = y;
		color = c;
	}
}

public class Voronoi {

	GraphicsContext g;
	Vector<Node> nodes = new Vector<>();
	double width = 1.0;
	double height = 1.0;
	double screenW, screenH;
	public double distexp = .01;

	public Voronoi(GraphicsContext graphics){
		g = graphics;
		screenW = graphics.getCanvas().getWidth();
		screenH = graphics.getCanvas().getHeight();
	}

	public void addNode(double x, double y, Color c){
		nodes.add(new Node(x,y,c));
	}

	public void clear(){
		nodes.clear();
	}

	public void draw(){
		PixelWriter p = g.getPixelWriter();
		for(int i = 0; i < screenW; i++){
			for(int j = 0; j < screenH; j++){
				double x = i * width / screenW;
				double y = j * height / screenH;
				p.setColor(i,j,getColor(x,y));
			}
		}
		for (Node n : nodes) {
			g.setFill(Color.BLACK);
			g.fillOval(n.x/4, n.y/4, 4, 4);
		}
	}

	public Color getColor(double x, double y){
		Color c = Color.WHITE;
		double min = Double.MAX_VALUE;
		for (Node node : nodes) {
			double d = dist(getX(node.x), getY(node.y), x, y);
			if(d < min) {
				c = node.color;
				min = d;
			}
		}
		return c;
	}

	public double getX(double x) {
		return x * width / screenW / 4;
	}

	public double getY(double y) {
		return y * height / screenH / 4;
	}

	public double dist(double x1, double y1, double x2, double y2){
		return dn(x1,y1,x2,y2,distexp);
//		return d(x1,y1,x2,y2);
	}

	public double dn(double x1, double y1, double x2, double y2, double n){
		return n < 20 ? Math.pow(Math.abs(x1 - x2) , n) + Math.pow(Math.abs(y1 - y2), n) : dinf(x1,x2,y1,y2);
	}

	public double dinf(double x1, double x2, double y1, double y2){
		return Math.abs(x1-x2) > Math.abs(y1-y2) ? Math.abs(x1-x2) :Math.abs(y1-y2);
	}

	public double d(double x1, double x2, double y1, double y2){
		return 2 * Math.abs(x1 * x2 + y1 * y2) / (x1 * x1 + y1 * y1 + x2 * x2 + y2 *y2);
	}

	public double pow(double x, int e){
		if(e == 0) { return 1; }
		else return x * pow(x, e - 1);
	}
}
