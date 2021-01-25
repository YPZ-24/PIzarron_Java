package mx.com.ipn.upiicsa.poo.pizarron.pr;

import java.awt.Color;
import java.util.ArrayList;

import mx.com.ipn.upiicsa.poo.pizarron.bs.DiagramBs;
import mx.com.ipn.upiicsa.poo.pizarron.entity.Diagram;
import mx.com.ipn.upiicsa.poo.pizarron.entity.Figure;

public class PizarronPr {
	/*
	public static boolean isPointerOnFigure(ArrayList<Figure> figuras, int px, int py) {
		boolean isOnFigure = false;
		for(Figure f : figuras) {
			if(f.includesPoint(px, py)) {
				isOnFigure = true;
			}
		}
		return isOnFigure;
	}
	*/
	
	public static Integer indexFigureWherePointer(ArrayList<Figure> figuras, int px, int py) {
		Integer index = null;;
		
		for(int i=0; i<figuras.size(); i++) {
			if(figuras.get(i).includesPoint(px, py)) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	public static Figure figureWherePointer(ArrayList<Figure> figuras, int px, int py) {
		Figure figure = null;;
		
		for(Figure f : figuras) {
			if(f.includesPoint(px, py)) {
				figure = f;
				break;
			}
		}
		
		return figure;
	}
	
	public static Figure changeFillColor(Color color, Figure figure) {
		figure.setFillColor(color);
		return figure;
	}
	
	public static Figure changeBorderColor(Color color, Figure figure) {
		figure.setBorderColor(color);
		return figure;
	}
	
	public static Figure changeStroke(int stroke, Figure figure) {
		figure.setStroke(stroke);
		return figure;
	}
	
	public static boolean deleteDiagram(Diagram diagram) {
		return DiagramBs.deleteDiagram(diagram);
	}
	
	public static Diagram saveDiagram(Diagram diagram, ArrayList<Figure> figuras) {	
		boolean diagramExists = DiagramBs.diagramExists(diagram);
		Diagram diagramSaved = null;
		if(diagramExists) {
			//Actualizar
			diagramSaved = DiagramBs.updateDiagram(diagram, figuras);
		}else {
			//Crear
			diagramSaved = DiagramBs.createDiagramWithFigures(diagram.getIdUser(), figuras);
		}
		return diagramSaved;
	}
	
	public static ArrayList<Figure> getDiagramFigures(Diagram d){
		return DiagramBs.getDiagramFigures(d);
	}
}
