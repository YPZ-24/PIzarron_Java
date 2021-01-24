package mx.com.ipn.upiicsa.poo.pizarron.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class TriangleFigure extends Figure{
	
	private static final int DEFAULT_BASE = 100;
	private static final int DEFAULT_ALTURA = 100;
	private static final Color DEFAULT_BORDER_COLOR = new Color(255, 0, 0);
	private static final Color DEFAULT_FILL_COLOR = new Color(153, 0, 0);
	
	private int altura;
	private int base;
	
	protected TriangleFigure(int x, int y) {
		super(x, y, DEFAULT_BORDER_COLOR, DEFAULT_FILL_COLOR);
		altura = DEFAULT_ALTURA;
		base = DEFAULT_BASE;
	}

	@Override
	public void paint(Graphics g) {
		
		
		Graphics2D g2d = (Graphics2D) g;		
		g2d.setColor(borderColor);
		g2d.setStroke(new BasicStroke(stroke));
		Polygon polygon = new Polygon();
		polygon.addPoint(x, y-(altura/2));
		polygon.addPoint(x+(base/2), y+(altura/2));
		polygon.addPoint(x-(base/2), y+(altura/2));
		g2d.drawPolygon(polygon);
		g2d.setColor(fillColor);
		g2d.fillPolygon(polygon);
		
	}
	
	public static TriangleFigure getDefault(int x, int y) {
		return new TriangleFigure(x, y);
	}
	
	@Override
	public boolean includesPoint(int px, int py) {
		int ax=x, ay=y-(altura/2);
		int bx=x+(base/2), by=y+(altura/2);
		int cx=x-(base/2), cy=y+(altura/2);
		
		boolean orientacion1 = validaOrientacion(px, py, ax, ay, bx, by);
		boolean orientacion2 = validaOrientacion(px, py, bx, by, cx, cy);
		boolean orientacion3 = validaOrientacion(px, py, cx, cy, ax, ay);
		
		return (orientacion1 && orientacion2 && orientacion3) || (!orientacion1 && !orientacion2 && !orientacion3);
	}
	
	private boolean validaOrientacion(int x1, int y1, int x2, int y2, int x3, int y3) {
		return ((x2-x1)*(y3-y1))-((y2-y1)*(x3-x1)) > 0;
	}
	
	
	

}
