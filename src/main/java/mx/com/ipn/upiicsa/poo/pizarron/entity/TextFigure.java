package mx.com.ipn.upiicsa.poo.pizarron.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

public class TextFigure extends Figure{
	
	private static final int DEFAULT_TEXT_SIZE = 20;
	private static final String DEFAULT_TEXT = "Hello...!";
	private static final Color DEFAULT_BORDER_COLOR = new Color(255, 0, 0);
	private static final Color DEFAULT_FILL_COLOR = new Color(0, 0, 0);
	
	int textSize;
	String text;
	
	protected TextFigure(int x, int y) {
		super(x,y, DEFAULT_BORDER_COLOR, DEFAULT_FILL_COLOR);
		textSize = DEFAULT_TEXT_SIZE;
		text = DEFAULT_TEXT;
	}
	
	public static TextFigure getDefault(int x, int y) {
		return new TextFigure(x,y);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font("Arial", Font.PLAIN, textSize);
		g2d.setFont(font); 
		g2d.setColor(fillColor);
		g2d.drawString(text, x, y+(textSize/2)); 
		g2d.setColor(fillColor);
	}

	@Override
	public boolean includesPoint(int px, int py) {
		// TODO Auto-generated method stub
		return false;
	}

}
