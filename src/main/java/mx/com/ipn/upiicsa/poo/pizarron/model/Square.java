package mx.com.ipn.upiicsa.poo.pizarron.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Square extends Figure{
	
	private static final int DEFAULT_SIDE = 100;
	private static final Color DEFAULT_BORDER_COLOR = new Color(0, 77, 153);
	private static final Color DEFAULT_FILL_COLOR = new Color(0, 77, 153);
	
	private int side;
	
	protected Square(int x, int y) {
		super(x,y, DEFAULT_BORDER_COLOR, DEFAULT_FILL_COLOR);
		side = DEFAULT_SIDE;
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(borderColor);
		g2d.drawRect(x, y, side, side);
		g2d.setColor(fillColor);
		g2d.fillRect(x+1, y+1, side-1, side-1);
	}
	
	public static Square getDefault(int x, int y) {
		return new Square(x, y);
	}
	
	

}
