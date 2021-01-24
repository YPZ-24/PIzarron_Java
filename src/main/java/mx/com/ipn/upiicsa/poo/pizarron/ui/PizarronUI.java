package mx.com.ipn.upiicsa.poo.pizarron.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import mx.com.ipn.upiicsa.poo.pizarron.entity.Circle;
import mx.com.ipn.upiicsa.poo.pizarron.entity.Figure;
import mx.com.ipn.upiicsa.poo.pizarron.entity.Pencil;
import mx.com.ipn.upiicsa.poo.pizarron.entity.Rectangle;
import mx.com.ipn.upiicsa.poo.pizarron.entity.Square;
import mx.com.ipn.upiicsa.poo.pizarron.entity.Triangle;
import mx.com.ipn.upiicsa.poo.pizarron.exception.DrawException;
import mx.com.ipn.upiicsa.poo.pizarron.pr.PizarronPr;
import mx.com.ipn.upiicsa.poo.pizarron.util.DrawingStates;
import mx.com.ipn.upiicsa.poo.pizarron.util.ToolCodes;

public class PizarronUI extends JFrame{
	
	private ArrayList<Figure> figuras;
	private Figure figureSelected;
	
	private JPanel optionsPanel;
	private JPanel toolPanel;
	private DashboardPanel dashboardPanel;
	private JButton btnCircle;
	private JButton btnTriangle;
	private JButton btnSquare;
	private JButton btnRectangle;
	private JButton btnPencil;
	private JButton btnCursor;
	/*
	private Color fillColor;
	private Color borderColor;
	private int stroke;*/
	private JButton fillColorButton;
	private JButton borderColorButton;
	private JColorChooser fillColorChooser;
	private JColorChooser borderColorChooser;
	private JTextField strokeInput;
	
	private int selectedTool;
	private int drawingState;
	private Figure pencilTemp;
	
	
	private static final long serialVersionUID = 1L;

	public PizarronUI() {
		initComponents();
		selectedTool = ToolCodes.TOOL_UNSELECT;
		drawingState = DrawingStates.DRAWING_INACTIVE;
	}
	
	public void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(500, 500));
		
		instantiateComponents();
		buildLayout();
		initializeListeners();
		
		setVisible(true);
	}
	
	private Color showColorChooser(JColorChooser chooser){
		return chooser.showDialog(this,"Selecciona el color", Color.BLACK);
	}

	private void initializeListeners() {
		fillColorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color color = showColorChooser(fillColorChooser);
				if(figureSelected != null) {
					System.out.println("Si hay figura");
					Figure figureUpdated = PizarronPr.changeFillColor(color, figureSelected);
					figureUpdated.paint(dashboardPanel.getGraphics());
				}else {
					System.out.println("NO hay figura");
				}
			}
		});
		borderColorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color c = showColorChooser(borderColorChooser);
			}
		});
		
		btnCircle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedTool = ToolCodes.TOOL_CIRCLE;
			}
		});
		btnTriangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedTool = ToolCodes.TOOL_TRIANGLE;
			}
		});
		btnSquare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedTool = ToolCodes.TOOL_SQUARE;
			}
		});
		btnRectangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedTool = ToolCodes.TOOL_RECTANGLE;
			}
		});
		btnPencil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedTool = ToolCodes.TOOL_PENCIL;				
			}
		});
		btnCursor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedTool = ToolCodes.TOOL_UNSELECT;				
			}
		});
		dashboardPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(drawingState == DrawingStates.DRAWING_ACTIVE && selectedTool == ToolCodes.TOOL_PENCIL){
					pencilTemp = null;
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(drawingState == DrawingStates.DRAWING_ACTIVE && selectedTool == ToolCodes.TOOL_PENCIL){
					pencilTemp = getFigure(e.getX(), e.getY());
				}
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				drawingState = DrawingStates.DRAWING_INACTIVE;
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				drawingState = DrawingStates.DRAWING_ACTIVE;
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(drawingState == DrawingStates.DRAWING_ACTIVE){
					if(selectedTool == ToolCodes.TOOL_UNSELECT) {
						figureSelected = PizarronPr.figureWherePointer(figuras, e.getX(), e.getY());
					}else {
						Figure figure = getFigure(e.getX(), e.getY());
						figuras.add(figure);
					}
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
				if(drawingState == DrawingStates.DRAWING_ACTIVE && selectedTool == ToolCodes.TOOL_PENCIL){
					Pencil pencil = (Pencil) pencilTemp;
					pencil.addPoint(e.getX(), e.getY());
					pencil.paint(dashboardPanel.getGraphics());
				}else if(drawingState == DrawingStates.DRAWING_ACTIVE && selectedTool == ToolCodes.TOOL_UNSELECT) {
					int x = e.getX(); 
					int y = e.getY();
					Integer indexFigureWherePointer = PizarronPr.indexFigureWherePointer(figuras, x, y);
					if(indexFigureWherePointer != null) {
						//AQUÍ DEBEMOS REPINTAR LA FIGURA

						Figure figureWherePointer = figuras.get(indexFigureWherePointer);
						figuras.get(indexFigureWherePointer).repaint(dashboardPanel.getGraphics(), x, y);
						Figure figureUpdated = figuras.get(indexFigureWherePointer);
						figuras.set(indexFigureWherePointer, figureUpdated);
						
						//AQUÍ DEBEMOS ELIMINAR LA FIGURA QUE QUEDO EN EL RASTRO
						
					}
				}
			}
		});
	}

	private Figure getFigureDraw(int x, int y) throws DrawException {
		Figure figure = null;
		if(selectedTool == ToolCodes.TOOL_UNSELECT) {
			throw new DrawException();
		}else if(selectedTool == ToolCodes.TOOL_CIRCLE) {
			figure = Circle.getDefault(x, y);
		}else if(selectedTool == ToolCodes.TOOL_TRIANGLE) {
			figure = Triangle.getDefault(x, y);
		}else if(selectedTool == ToolCodes.TOOL_SQUARE) {
			figure = Square.getDefault(x, y);
		}else if(selectedTool == ToolCodes.TOOL_RECTANGLE) {
			figure = Rectangle.getDefault(x, y);
		}else if(selectedTool == ToolCodes.TOOL_PENCIL) {
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
		toolPanel.add(btnCursor);
		pane.add(toolPanel, BorderLayout.WEST);
		
		dashboardPanel.setBackground(Color.WHITE);
		pane.add(dashboardPanel, BorderLayout.CENTER);
		
		optionsPanel.add(fillColorButton);
		optionsPanel.add(borderColorButton);
		optionsPanel.add(strokeInput);
		pane.add(optionsPanel, BorderLayout.SOUTH);
		
	}

	private void instantiateComponents() {
		
		figuras = new ArrayList<>();
		optionsPanel = new JPanel();
		fillColorButton = new JButton("Fill");
		borderColorButton = new JButton("Border");
		fillColorChooser = new JColorChooser();
		borderColorChooser = new JColorChooser();
		strokeInput = new JTextField("1", 10);
				
		toolPanel = new JPanel();
		dashboardPanel = new DashboardPanel();
		try {
			btnCircle = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/cicleIcon.png");
			btnTriangle = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/triangleIcon.png");
			btnSquare = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/squareIcon.png");
			btnRectangle = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/rectangleIcon.png");
			btnPencil = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/pencilIcon.png");
			btnCursor = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/cursorIcon.png");
		}catch(Exception e) {
			
		}
		
		
	}
}
