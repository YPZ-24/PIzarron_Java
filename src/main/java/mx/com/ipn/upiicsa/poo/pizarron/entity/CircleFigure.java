package mx.com.ipn.upiicsa.poo.pizarron.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class CircleFigure extends Figure{

	private static final int DEFAULT_RADIUS = 50;
	private static final Color DEFAULT_BORDER_COLOR = new Color(255, 0, 0);
	private static final Color DEFAULT_FILL_COLOR = new Color(153, 0, 77);

	private int radius;
	
	protected CircleFigure(int x, int y) {
		super(x, y, DEFAULT_BORDER_COLOR, DEFAULT_FILL_COLOR);
		radius = DEFAULT_RADIUS;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(borderColor);
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawOval(x-this.radius, y-this.radius, this.radius*2, this.radius*2);
		g2d.setColor(fillColor);
		g2d.fillOval(x-this.radius, y-this.radius, radius*2, radius*2);
	}
	
	public static CircleFigure getDefault(int x, int y) {
		return new CircleFigure(x, y);
	}

	@Override
	public boolean includesPoint(int px, int py) {
		int h = x;
		int k = y;
		Double d = Math.sqrt(Math.pow(px-h, 2)+Math.pow(py-k, 2));
		return (d<=radius);
	}

	
	
}
