package mx.com.ipn.upiicsa.poo.pizarron.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageFigure extends Figure{
	
	private static final int DEFAULT_ALTO = 150;
	private static final int DEFAULT_ANCHO = 200;
	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private static final Color DEFAULT_FILL_COLOR = Color.WHITE;
	
	private File file;
	private int ancho, alto;
	
	protected ImageFigure(int x, int y, File file) {
		super(x, y, DEFAULT_BORDER_COLOR, DEFAULT_FILL_COLOR);
		this.file = file;
		alto = DEFAULT_ALTO;
		ancho = DEFAULT_ANCHO;
	}
	
	public static ImageFigure getDefault(int x, int y, File file) {
		return new ImageFigure(x,y,file);
	}
	
	@Override
	public void paint(Graphics g) {
		
			Graphics2D g2d = (Graphics2D) g;
			//BufferedImage imagen = ImageIO.read(file);
			Image imagen = Toolkit.getDefaultToolkit().getImage(file.getPath());
			g2d.drawImage(imagen, x-(ancho/2), y-(alto/2), ancho, alto, null); 
		
	}

	@Override
	public boolean includesPoint(int px, int py) {
		return ((px<= x+(ancho/2)) && (px>= x-(ancho/2)) && (py <= y+(alto/2)) && (py>=y-(alto/2)));
	}

}
