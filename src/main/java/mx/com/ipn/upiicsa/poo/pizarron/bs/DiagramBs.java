package mx.com.ipn.upiicsa.poo.pizarron.bs;

import java.sql.Timestamp;
import java.util.ArrayList;

import mx.com.ipn.upiicsa.poo.pizarron.dao.DiagramDao;
import mx.com.ipn.upiicsa.poo.pizarron.entity.Diagram;
import mx.com.ipn.upiicsa.poo.pizarron.entity.Figure;
import mx.com.ipn.upiicsa.poo.pizarron.entity.FigureDb;

public class DiagramBs {
	public static Diagram createDiagramWithFigures(int idUser, ArrayList<Figure> figuras) {
		Diagram diagramSaved = null;
		Diagram diagram = new Diagram();
		Timestamp createdAt = new Timestamp(System.currentTimeMillis());
		
		diagram.setIdUser(idUser);
		diagram.setFhCreation(createdAt);
		diagram.setFhUpdate(createdAt);
		
		diagramSaved = DiagramDao.create(diagram);
		if(diagramSaved!=null) {
			for(Figure f: figuras) {
				FigureDb fdb = new FigureDb(diagramSaved.getIdDiagram(), f);
				DiagramDao.addFigure(fdb);
			}
		}
		
		return diagramSaved;
	}
	
	public static Diagram updateDiagram(Diagram diagram, ArrayList<Figure> figuras) {
		Diagram diagramUpdated = null;
		Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
		
		diagram.setFhUpdate(updatedAt);
		diagramUpdated = DiagramDao.updateDiagram(diagram);
		
		if(diagramUpdated!=null) {
			DiagramDao.deleteFigures(diagramUpdated.getIdDiagram());
			for(Figure f: figuras) {
				FigureDb fdb = new FigureDb(diagramUpdated.getIdDiagram(), f);
				DiagramDao.addFigure(fdb);
			}
		}
		
		return diagramUpdated;
	}

	public static boolean diagramExists(Diagram diagram) {
		boolean diagramExists = false;
		Integer idDiagram = diagram.getIdDiagram();
		if(idDiagram!=null) {
			if(DiagramDao.findDiagramById(idDiagram)!=null) {
				diagramExists = true;
			}
		}
		return diagramExists;
	}
	
	public static ArrayList<Diagram> getUserDiagrams(int idUser){		
		ArrayList<Diagram> diagrams = DiagramDao.findUserDiagrams(idUser);
		return diagrams;
	}
	
	public static boolean deleteDiagram(Diagram diagram) {
		DiagramDao.deleteFigures(diagram.getIdDiagram());
		boolean wasDiagramDeleted = DiagramDao.deleteDiagram(diagram.getIdDiagram());
		return wasDiagramDeleted;
	}
	
	public static ArrayList<Figure> getDiagramFigures(Diagram diagram){
		ArrayList<Figure> figures = new ArrayList<>();
		if(diagram.getIdDiagram() != null) {
			ArrayList<FigureDb> figuresDb = DiagramDao.getDiagramFigures(diagram.getIdDiagram());
			for(FigureDb fdb : figuresDb) {
				Figure figure = fdb.getFigure();				
				figures.add(figure);
			}
		}
		return figures;
	}

}
