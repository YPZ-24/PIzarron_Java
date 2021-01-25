package mx.com.ipn.upiicsa.poo.pizarron.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import mx.com.ipn.upiicsa.poo.pizarron.util.ToolCodes;

public class TextFigure extends Figure{
	
	private static final int DEFAULT_TEXT_SIZE = 20;
	private static final Color DEFAULT_BORDER_COLOR = new Color(255, 0, 0);
	private static final Color DEFAULT_FILL_COLOR = new Color(0, 0, 0);
	
	int textSize;
	String text;
	
	protected TextFigure(int x, int y, String text) {
		super(x,y, ToolCodes.TOOL_TEXT, DEFAULT_BORDER_COLOR, DEFAULT_FILL_COLOR);
		textSize = DEFAULT_TEXT_SIZE;
		this.text = text;
	}
	
	public static TextFigure getDefault(int x, int y, String text) {
		return new TextFigure(x, y, text);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font("Arial", Font.PLAIN, textSize);
		g2d.setFont(font); 
		g2d.setColor(fillColor);
		g2d.drawString(text, x, y); 
	}

	@Override
	public boolean includesPoint(int px, int py) {
		//boolean t = ((px<= x+(base/2)) && (px>= x-(base/2)) && (py <= y+(altura/2)) && (py>=y-(altura/2)));
		return false;
	}

}
