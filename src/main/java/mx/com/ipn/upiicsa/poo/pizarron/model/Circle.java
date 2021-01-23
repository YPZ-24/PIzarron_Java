package mx.com.ipn.upiicsa.poo.pizarron.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Circle extends Figure{

	private static final int DEFAULT_RADIUS = 100;
	private static final Color DEFAULT_BORDER_COLOR = new Color(153, 0, 77);
	private static final Color DEFAULT_FILL_COLOR = new Color(153, 0, 77);

	private int radius;
	
	protected Circle(int x, int y) {
		super(x, y, DEFAULT_BORDER_COLOR, DEFAULT_FILL_COLOR);
		radius = DEFAULT_RADIUS;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(borderColor);
		g2d.drawOval(x, y, this.radius, this.radius);
		g2d.setColor(fillColor);
		g2d.fillOval(x, y, radius, radius);
	}
	
	public static Circle getDefault(int x, int y) {
		return new Circle(x, y);
	}

}
