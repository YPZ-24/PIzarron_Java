package mx.com.ipn.upiicsa.poo.pizarron.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rectangle extends Figure{
	private static final int DEFAULT_SIDE_A = 150;
	private static final int DEFAULT_SIDE_B = 80;
	private static final Color DEFAULT_BORDER_COLOR = new Color(115, 0, 153);
	private static final Color DEFAULT_FILL_COLOR = new Color(115, 0, 153);
	
	private int sideA;
	private int sideB;
	
	protected Rectangle(int x, int y) {
		super(x,y, DEFAULT_BORDER_COLOR, DEFAULT_FILL_COLOR);
		sideA = DEFAULT_SIDE_A;
		sideB = DEFAULT_SIDE_B;
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(borderColor);
		g2d.drawRect(x, y, sideA, sideB);
		g2d.setColor(fillColor);
		g2d.fillRect(x+1, y+1, sideA-1, sideB-1);
	}
	
	public static Rectangle getDefault(int x, int y) {
		return new Rectangle(x, y);
	}
}
