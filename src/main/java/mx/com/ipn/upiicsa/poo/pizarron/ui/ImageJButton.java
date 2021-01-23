package mx.com.ipn.upiicsa.poo.pizarron.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageJButton extends JButton{
	
	private static final long serialVersionUID = 1L;
	private static final Color backColorHover = new Color(77, 77, 77);
	private static final Color backColor = new Color(13, 13, 13);

	public static JButton getImageButton(String filePath) throws IOException {
		File file = new File(filePath);
		Image image = ImageIO.read(file);
		image = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		final JButton btnImage = new JButton(new ImageIcon(image));
		btnImage.setBackground(backColor);
		btnImage.setBorderPainted(false);
		btnImage.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnImage.setBackground(backColor);
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnImage.setBackground(backColorHover);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		return btnImage;
	}
	
}