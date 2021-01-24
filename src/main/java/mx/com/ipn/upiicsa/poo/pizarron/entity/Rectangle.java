package mx.com.ipn.upiicsa.poo.pizarron.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rectangle extends Figure{
	private static final int DEFAULT_SIDE_A = 150;
	private static final int DEFAULT_SIDE_B = 80;
	private static final Color DEFAULT_BORDER_COLOR = new Color(255, 0, 0);
	private static final Color DEFAULT_FILL_COLOR = new Color(115, 0, 153);
	
	private int base;
	private int altura;
	
	protected Rectangle(int x, int y) {
		super(x,y, DEFAULT_BORDER_COLOR, DEFAULT_FILL_COLOR);
		base = DEFAULT_SIDE_A;
		altura = DEFAULT_SIDE_B;
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(stroke));
		g2d.setColor(borderColor);
		g2d.drawRect(x-(base/2), y-(altura/2), base, altura);
		g2d.setColor(fillColor);
		g2d.fillRect((x-(base/2))+1, (y-(altura/2))+1, base-1, altura-1);
	}
	
	public static Rectangle getDefault(int x, int y) {
		return new Rectangle(x, y);
	}

	@Override
	public boolean includesPoint(int px, int py) {
		return ((px<= x+(base/2)) && (px>= x-(base/2)) && (py <= y+(altura/2)) && (py>=y-(altura/2)));
	}
	
}
