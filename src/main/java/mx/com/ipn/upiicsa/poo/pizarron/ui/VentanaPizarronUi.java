package mx.com.ipn.upiicsa.poo.pizarron.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import mx.com.ipn.upiicsa.poo.pizarron.exception.DrawException;
import mx.com.ipn.upiicsa.poo.pizarron.model.Circle;
import mx.com.ipn.upiicsa.poo.pizarron.model.Figure;
import mx.com.ipn.upiicsa.poo.pizarron.model.Pencil;
import mx.com.ipn.upiicsa.poo.pizarron.model.Rectangle;
import mx.com.ipn.upiicsa.poo.pizarron.model.Square;
import mx.com.ipn.upiicsa.poo.pizarron.model.Triangle;

public class VentanaPizarronUi extends JFrame{

	private static final int TOOL_UNSELECT = -1;
	private static final int TOOL_CIRCLE = 1;
	private static final int TOOL_TRIANGLE = 2;
	private static final int TOOL_SQUARE = 3;
	private static final int TOOL_RECTANGLE = 4;
	private static final int TOOL_PENCIL = 8;
	
	private static final int DRAWING_ACTIVE = 1;
	private static final int DRAWING_INACTIVE = 20;
	
	private JPanel toolPanel;
	private JPanel dashboardPanel;
	private JPanel logPanel;
	private JButton btnCircle;
	private JButton btnTriangle;
	private JButton btnSquare;
	private JButton btnRectangle;
	private JButton btnPencil;
	
	private int selectedTool;
	private int drawingState;
	private Figure pencilTemp;
	
	
	private static final long serialVersionUID = 1L;

	public VentanaPizarronUi() {
		initComponents();
		selectedTool = TOOL_UNSELECT;
		drawingState = DRAWING_INACTIVE;
	}
	
	public void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		instantiateComponents();
		buildLayout();
		initializeListeners();
		
		setVisible(true);
	}

	private void initializeListeners() {
		btnCircle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedTool = TOOL_CIRCLE;
			}
		});
		btnTriangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedTool = TOOL_TRIANGLE;
			}
		});
		btnSquare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedTool = TOOL_SQUARE;
			}
		});
		btnRectangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedTool = TOOL_RECTANGLE;
			}
		});
		btnPencil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedTool = TOOL_PENCIL;				
			}
		});
		dashboardPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(drawingState == DRAWING_ACTIVE && selectedTool == TOOL_PENCIL){
					pencilTemp = null;
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(drawingState == DRAWING_ACTIVE && selectedTool == TOOL_PENCIL){
					pencilTemp = getFigure(e.getX(), e.getY());
				}
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				drawingState = DRAWING_INACTIVE;
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				drawingState = DRAWING_ACTIVE;
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(drawingState == DRAWING_ACTIVE){
					Figure figure = getFigure(e.getX(), e.getY());
				}
			}

			
		});
		
		dashboardPanel.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(drawingState == DRAWING_ACTIVE && selectedTool == TOOL_PENCIL){
					Pencil pencil = (Pencil) pencilTemp;
					pencil.addPoint(e.getX(), e.getY());
					pencil.paint(dashboardPanel.getGraphics());
				}
			}
		});
	}

	private Figure getFigureDraw(int x, int y) throws DrawException {
		Figure figure = null;
		if(selectedTool ==TOOL_UNSELECT) {
			throw new DrawException();
		}else if(selectedTool ==TOOL_CIRCLE) {
			figure = Circle.getDefault(x, y);
		}else if(selectedTool ==TOOL_TRIANGLE) {
			figure = Triangle.getDefault(x, y);
		}else if(selectedTool ==TOOL_SQUARE) {
			figure = Square.getDefault(x, y);
		}else if(selectedTool ==TOOL_RECTANGLE) {
			figure = Rectangle.getDefault(x, y);
		}else if(selectedTool ==TOOL_PENCIL) {
			figure = Pencil.getDefault(x, y);
		}
		return figure;
	}
	
	public Figure getFigure(int x, int y) {
		Figure figure= null;
		String message;
		try {
			figure = getFigureDraw(x, y);
			figure.paint(dashboardPanel.getGraphics());
			message = "Se dibujo una figura";
		} catch (Exception error) {
			message = "Excepcion";
		}finally {
			//PRINT MESSAGE
		}
		return figure;
	}
	
	private void buildLayout() {
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		
		toolPanel.setLayout(new GridLayout(5,2));
		toolPanel.add(btnCircle);
		toolPanel.add(btnTriangle);
		toolPanel.add(btnSquare);
		toolPanel.add(btnRectangle);
		toolPanel.add(btnPencil);
		pane.add(toolPanel, BorderLayout.WEST);
		dashboardPanel.setBackground(Color.WHITE);
		pane.add(dashboardPanel, BorderLayout.CENTER);
		pane.add(logPanel, BorderLayout.SOUTH);
		
	}

	private void instantiateComponents() {
		toolPanel = new JPanel();
		dashboardPanel = new JPanel();
		logPanel = new JPanel();
		try {
			btnCircle = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/cicleIcon.png");
			btnTriangle = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/triangleIcon.png");
			btnSquare = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/squareIcon.png");
			btnRectangle = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/rectangleIcon.png");
			btnPencil = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/pencilIcon.png");
		}catch(Exception e) {
			
		}
		
		
	}
}
