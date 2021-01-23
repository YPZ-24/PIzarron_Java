package mx.com.ipn.upiicsa.poo.pizarron.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Triangle extends Figure{
	
	private static final int DEFAULT_B = 100;
	private static final int DEFAULT_H = 100;
	private static final Color DEFAULT_BORDER_COLOR = new Color(153, 0, 0);
	private static final Color DEFAULT_FILL_COLOR = new Color(153, 0, 0);
	
	private int h;
	private int b;
	
	protected Triangle(int x, int y) {
		super(x, y, DEFAULT_BORDER_COLOR, DEFAULT_FILL_COLOR);
		h = DEFAULT_H;
		b = DEFAULT_B;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(borderColor);
		Polygon polygon = new Polygon();
		polygon.addPoint(x, y);
		polygon.addPoint(x+(b/2), y+h);
		polygon.addPoint(x-(b/2), y+h);
		g2d.drawPolygon(polygon);
		
		g2d.setColor(fillColor);
		g2d.fillPolygon(polygon);
	}
	
	public static Triangle getDefault(int x, int y) {
		return new Triangle(x, y);
	}

}
