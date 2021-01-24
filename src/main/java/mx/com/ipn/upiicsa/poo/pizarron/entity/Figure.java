package mx.com.ipn.upiicsa.poo.pizarron.entity;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figure {
	
	private static final int DEFAULT_STROKE = 10;
	
	protected int x;
	protected int y;
	protected int stroke;
	protected Color borderColor;
	protected Color fillColor;
	
	protected Figure(int x, int y, Color borderColor, Color fillColor) {
		this.x = x;
		this.y = y;
		this.stroke = DEFAULT_STROKE;
		this.borderColor = borderColor;
		this.fillColor = fillColor;
	}
	
	public abstract void paint(Graphics g);
	public abstract boolean includesPoint(int px, int py);
	
	public void repaint(Graphics g, int x, int y) {
		setX(x);
		setY(y);
		paint(g);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getStroke() {
		return stroke;
	}
	public void setStroke(int stroke) {
		this.stroke = stroke;
	}
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public Color getFillColor() {
		return fillColor;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	
	
}
