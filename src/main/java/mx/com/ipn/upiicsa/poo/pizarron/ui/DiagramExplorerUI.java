package mx.com.ipn.upiicsa.poo.pizarron.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import mx.com.ipn.upiicsa.poo.pizarron.entity.Diagram;
import mx.com.ipn.upiicsa.poo.pizarron.pr.DiagramExplorerPr;

public class DiagramExplorerUI extends JFrame{

	private ArrayList<Diagram> diagrams;
	private ArrayList<JButton> diagramButtons;
	private static final long serialVersionUID = 1L;
	private int idUser;
	private JLabel titleLb;
	private JButton btnNewDiagram;
	
	public DiagramExplorerUI(int idUser) {
		this.idUser = idUser;
		initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Diagram Explorer");
		setPreferredSize(new Dimension(400, 400));
		setResizable(false);
		initializeComponents();
		buildLayout();
		initializeListeners();
		pack();
		setVisible(true);
	}
	
	private void initializeComponents() {
		btnNewDiagram = new JButton("Nuevo Diagrama");
		titleLb = new JLabel("Diagram Explorer");
		diagrams = DiagramExplorerPr.getUserDiagrams(idUser);
		diagramButtons = new ArrayList<>();
		for(Diagram d: diagrams) {
			diagramButtons.add(new JButton("Diagrama "+d.getIdDiagram()));
		}
	}
	
	private void buildLayout() {
		Container pane = getContentPane();
		
		BoxLayout bx = new BoxLayout(pane, BoxLayout.Y_AXIS);
		pane.setLayout(bx);
		pane.add(titleLb);
		pane.add(btnNewDiagram);
		for(JButton btn : diagramButtons) {
			pane.add(btn);
			btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		
		Font fontTitle = new Font("Arial", Font.BOLD, 30);
		titleLb.setFont(fontTitle);
		titleLb.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewDiagram.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.setBackground(Color.white);
	}
	
	private void initializeListeners() {
		for(int i=0; i<diagramButtons.size(); i++) {
			final Diagram diagram = diagrams.get(i);
			diagramButtons.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					PizarronUI pizarronUI = new PizarronUI(diagram, idUser);
					dispose();
				}
			});
		}
		btnNewDiagram.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PizarronUI pizarronUI = new PizarronUI(new Diagram(), idUser);
				dispose();
			}
		});
	}
	
}
