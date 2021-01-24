package mx.com.ipn.upiicsa.poo.pizarron.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class PencilFigure extends Figure{

	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private static final Color DEFAULT_FILL_COLOR = Color.WHITE;
	
	private List<Point> points;
	
	
	protected PencilFigure(int x, int y) {
		super(x, y, DEFAULT_BORDER_COLOR, DEFAULT_FILL_COLOR);
		points = new ArrayList<Point>();
		points.add(new Point(x,y));
	}
	
	@Override
	public void paint(Graphics g) {
		if(points.size()>2) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(borderColor);
			g2d.setStroke(new BasicStroke(stroke/2));
			for(int i=1; i<points.size(); i++) {
				Point a = points.get(i-1);
				Point b = points.get(i);
				g2d.drawLine((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
			}
		}
	}
	
	public static PencilFigure getDefault(int x, int y) {
		return new PencilFigure(x,y);
	}
	
	public void addPoint(int x, int y) {
		points.add(new Point(x,y));
	}

	@Override
	public boolean includesPoint(int px, int py) {
		return false;
	}

}
