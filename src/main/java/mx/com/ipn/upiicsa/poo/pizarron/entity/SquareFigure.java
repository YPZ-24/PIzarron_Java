package mx.com.ipn.upiicsa.poo.pizarron.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import mx.com.ipn.upiicsa.poo.pizarron.util.ToolCodes;

public class SquareFigure extends Figure{
	
	private static final int DEFAULT_SIDE = 100;
	private static final Color DEFAULT_BORDER_COLOR = new Color(255, 0, 0);
	private static final Color DEFAULT_FILL_COLOR = new Color(0, 77, 153);
	
	private int side;
	
	protected SquareFigure(int x, int y) {
		super(x,y, ToolCodes.TOOL_SQUARE, DEFAULT_BORDER_COLOR, DEFAULT_FILL_COLOR);
		side = DEFAULT_SIDE;
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(borderColor);
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawRect(x-(side/2), y-(side/2), side, side);
		g2d.setColor(fillColor);
		g2d.fillRect(x-(side/2)+1, y-(side/2)+1, side-1, side-1);
	}
	
	public static SquareFigure getDefault(int x, int y) {
		return new SquareFigure(x, y);
	}
	
	@Override
	public boolean includesPoint(int px, int py) {
		return ((px<= x+(side/2)) && (px>= x-(side/2)) && (py <= y+(side/2)) && (py>=y-(side/2)));
	}
	

}
