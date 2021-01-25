package mx.com.ipn.upiicsa.poo.pizarron.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import mx.com.ipn.upiicsa.poo.pizarron.entity.CircleFigure;
import mx.com.ipn.upiicsa.poo.pizarron.entity.Diagram;
import mx.com.ipn.upiicsa.poo.pizarron.entity.Figure;
import mx.com.ipn.upiicsa.poo.pizarron.entity.ImageFigure;
import mx.com.ipn.upiicsa.poo.pizarron.entity.PencilFigure;
import mx.com.ipn.upiicsa.poo.pizarron.entity.RectangleFigure;
import mx.com.ipn.upiicsa.poo.pizarron.entity.SquareFigure;
import mx.com.ipn.upiicsa.poo.pizarron.entity.TextFigure;
import mx.com.ipn.upiicsa.poo.pizarron.entity.TriangleFigure;
import mx.com.ipn.upiicsa.poo.pizarron.exception.DrawException;
import mx.com.ipn.upiicsa.poo.pizarron.pr.PizarronPr;
import mx.com.ipn.upiicsa.poo.pizarron.util.DrawingStates;
import mx.com.ipn.upiicsa.poo.pizarron.util.ToolCodes;

public class PizarronUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Figure> figuras;
	private Figure figureSelected;
	private File imageSelected;
	private Diagram diagram;
	private int idUser;
	
	private JPanel optionsPanel;
	private JPanel toolPanel;
	private JPanel dashboardPanel;
	private JButton btnCircle;
	private JButton btnTriangle;
	private JButton btnSquare;
	private JButton btnRectangle;
	private JButton btnPencil;
	private JButton btnCursor;
	private JButton btnText;
	private JButton btnImage;
	private JButton btnExit;
	private JButton btnDelete;
	private JButton btnSave;
	
	
	private JFileChooser imageFileChooser;
	
	private JButton btnFillColor;
	private JButton btnBorderColor;
	private JTextField strokeInput;
	private JButton btnStroke;
	private JButton btnRefresh;
	
	private int selectedTool;
	private int drawingState;
	private Figure pencilTemp;

	public PizarronUI(Diagram d, int idUser) {
		
		this.diagram = d;
		this.idUser = idUser;
		this.diagram.setIdUser(idUser);
		selectedTool = ToolCodes.TOOL_UNSELECT;
		drawingState = DrawingStates.DRAWING_INACTIVE;
		
		initComponents();
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
	
	private Color showColorChooser(){
		return JColorChooser.showDialog(this,"Selecciona el color", Color.BLACK);
	}
	
	private File showImageFileChooser() {
		File file = null;
		int returnValue = imageFileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = imageFileChooser.getSelectedFile();
        }
        return file;
	}
	
	private void showAlert(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	private void initializeListeners() {
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int input = JOptionPane.showConfirmDialog(null, "Seguro que quieres borrarlo?");
				// 0=yes, 1=no, 2=cancel
				if(input==0) {
					boolean wasDeleted = PizarronPr.deleteDiagram(diagram);
					if(wasDeleted) {
						showAlert("Fue borrado");
						dispose();
						DiagramExplorerUI diagramExplorerUI = new DiagramExplorerUI(idUser);
					}else {
						showAlert("No se pudo borrar");
					}
				}
			}
		});
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				DiagramExplorerUI diagramExplorerUI = new DiagramExplorerUI(idUser);
			}
		});
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Diagram diagramSaved = PizarronPr.saveDiagram(diagram, figuras);
				if(diagramSaved!=null) {
					diagram = diagramSaved;
					showAlert("Guardado con exito");
				}
			}
		});
		btnFillColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color color = showColorChooser();
				if(figureSelected != null) {
					Figure figureUpdated = PizarronPr.changeFillColor(color, figureSelected);
					figureUpdated.paint(dashboardPanel.getGraphics());
				}
			}
		});
		btnBorderColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color color = showColorChooser();
				if(figureSelected != null) {
					Figure figureUpdated = PizarronPr.changeBorderColor(color, figureSelected);
					figureUpdated.paint(dashboardPanel.getGraphics());
				}
			}
		});
		btnStroke.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int stroke = Integer.parseInt(strokeInput.getText());
				if(figureSelected != null) {
					Figure figureUpdated = PizarronPr.changeStroke(stroke, figureSelected);
					figureUpdated.paint(dashboardPanel.getGraphics());
				}
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				drawFigures();
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
		btnText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedTool = ToolCodes.TOOL_TEXT;				
			}
		});
		btnImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				imageSelected = showImageFileChooser();
				if(imageSelected!=null) {
					selectedTool = ToolCodes.TOOL_IMAGE;
				}
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
					PencilFigure pencil = (PencilFigure) pencilTemp;
					pencil.addPoint(e.getX(), e.getY());
					pencil.paint(dashboardPanel.getGraphics());
				}else if(drawingState == DrawingStates.DRAWING_ACTIVE && selectedTool == ToolCodes.TOOL_UNSELECT) {
					int x = e.getX(); 
					int y = e.getY();
					Integer indexFigureWherePointer = PizarronPr.indexFigureWherePointer(figuras, x, y);
					if(indexFigureWherePointer != null) {
						//AQUÍ DEBEMOS REPINTAR LA FIGURA

						figuras.get(indexFigureWherePointer).repaint(dashboardPanel.getGraphics(), x, y);
						Figure figureUpdated = figuras.get(indexFigureWherePointer);
						figuras.set(indexFigureWherePointer, figureUpdated);
						
						//AQUÍ DEBEMOS ELIMINAR LA FIGURA QUE QUEDO EN EL RASTRO
						
						Graphics2D g2d = (Graphics2D)(dashboardPanel.getGraphics());
						g2d.clearRect(0, 0, dashboardPanel.getWidth(), dashboardPanel.getHeight());						
						drawFigures();
					}
				}
			}
		});
	}
	
	private void drawFigures() {
		for(Figure f:figuras) {
			f.paint(dashboardPanel.getGraphics());
		}
	}

	private Figure getFigureDraw(int x, int y) throws DrawException {
		Figure figure = null;
		if(selectedTool == ToolCodes.TOOL_UNSELECT) {
			throw new DrawException();
		}else if(selectedTool == ToolCodes.TOOL_CIRCLE) {
			figure = CircleFigure.getDefault(x, y);
		}else if(selectedTool == ToolCodes.TOOL_TRIANGLE) {
			figure = TriangleFigure.getDefault(x, y);
		}else if(selectedTool == ToolCodes.TOOL_SQUARE) {
			figure = SquareFigure.getDefault(x, y);
		}else if(selectedTool == ToolCodes.TOOL_RECTANGLE) {
			figure = RectangleFigure.getDefault(x, y);
		}else if(selectedTool == ToolCodes.TOOL_PENCIL) {
			figure = PencilFigure.getDefault(x, y);
		}else if(selectedTool == ToolCodes.TOOL_TEXT) {
			String text = JOptionPane.showInputDialog("Ingresa un texto");
			figure = TextFigure.getDefault(x, y, text);
		}else if(selectedTool == ToolCodes.TOOL_IMAGE) {
			figure = ImageFigure.getDefault(x, y, imageSelected);
		}
		return figure;
	}
	
	public Figure getFigure(int x, int y) {
		Figure figure= null;
		try {
			figure = getFigureDraw(x, y);
			figure.paint(dashboardPanel.getGraphics());
		} catch (Exception error) {
			error.printStackTrace();
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
		toolPanel.add(btnText);
		toolPanel.add(btnImage);
		toolPanel.add(btnExit);
		toolPanel.add(btnDelete);
		toolPanel.add(btnSave);
		pane.add(toolPanel, BorderLayout.WEST);
		
		dashboardPanel.setBackground(Color.WHITE);
		pane.add(dashboardPanel, BorderLayout.CENTER);
		
		optionsPanel.add(btnFillColor);
		optionsPanel.add(btnBorderColor);
		optionsPanel.add(strokeInput);
		optionsPanel.add(btnStroke);
		optionsPanel.add(btnRefresh);
		pane.add(optionsPanel, BorderLayout.SOUTH);	
		
		optionsPanel.setBackground(new Color(191, 191, 191));
	}

	private void instantiateComponents() {
		toolPanel = new JPanel();
		dashboardPanel = new JPanel();
		
		figuras = new ArrayList<>();
		figuras = PizarronPr.getDiagramFigures(diagram);
		
		//figuras = new ArrayList<>();
		optionsPanel = new JPanel();
		btnFillColor = new JButton("Fill");
		btnBorderColor = new JButton("Border");
		strokeInput = new JTextField("10", 10);
		btnStroke = new JButton("Change Stoke");
		btnRefresh = new JButton("Refresh");
		
		imageFileChooser = new JFileChooser();
		
		try {
			btnCircle = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/cicleIcon.png");
			btnTriangle = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/triangleIcon.png");
			btnSquare = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/squareIcon.png");
			btnRectangle = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/rectangleIcon.png");
			btnPencil = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/pencilIcon.png");
			btnCursor = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/cursorIcon.png");
			btnText = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/textIcon.png");
			btnImage = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/imageIcon.png");
			btnExit = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/exitIcon.png");
			btnDelete = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/deleteIcon.png");
			btnSave = ImageJButton.getImageButton("src/main/java/mx/com/ipn/upiicsa/poo/pizarron/resources/icons/saveIcon.png");
		}catch(Exception e) {
			
		}
		
		
	}
}
