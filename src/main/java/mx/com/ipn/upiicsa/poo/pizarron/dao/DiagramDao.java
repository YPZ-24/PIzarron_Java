package mx.com.ipn.upiicsa.poo.pizarron.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.com.ipn.upiicsa.poo.pizarron.entity.Diagram;
import mx.com.ipn.upiicsa.poo.pizarron.entity.Figure;
import mx.com.ipn.upiicsa.poo.pizarron.entity.FigureDb;

public class DiagramDao {
	
	public static Diagram findDiagramById(int id) {
		Diagram diagram = null;
		try {
			Connection con = Db.getConnection();
			PreparedStatement st = con.prepareStatement("select * from diagrama where id_diagrama=?");
			st.setInt(1, id);
			boolean executed = st.execute();
			if(executed) {
				ResultSet rs = st.getResultSet();
				rs.next();
				diagram = new Diagram();
				diagram.setIdDiagram(rs.getInt(1));
				diagram.setIdUser(rs.getInt(2));
				diagram.setFhCreation(rs.getTimestamp(3));
				diagram.setFhUpdate(rs.getTimestamp(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return diagram;
	}
	
	public static ArrayList<FigureDb> getDiagramFigures(int idDiagram) {
		ArrayList<FigureDb> figuresDb = new ArrayList<>();
		try {
			Connection con = Db.getConnection();
			PreparedStatement st = con.prepareStatement("select * from figura where id_diagrama=?");
			st.setInt(1, idDiagram);
			boolean executed = st.execute();
			if(executed) {
				ResultSet rs = st.getResultSet();
				while(rs.next()) {
					FigureDb figureDb = new FigureDb();
					figureDb.setIdFigure(rs.getInt(1));
					figureDb.setIdDiagram(idDiagram);
					figureDb.setTool(rs.getInt(4));
					figureDb.setTxJson(rs.getString(3));
					figuresDb.add(figureDb);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return figuresDb;
	}
	
	public static ArrayList<Diagram> findUserDiagrams(int idUser) {
		ArrayList<Diagram> diagrams = new ArrayList<>();
		try {
			Connection con = Db.getConnection();
			PreparedStatement st = con.prepareStatement("select * from diagrama where id_usuario=?");
			st.setInt(1, idUser);
			boolean executed = st.execute();
			if(executed) {
				ResultSet rs = st.getResultSet();
				while(rs.next()) {
					Diagram diagram = new Diagram();
					diagram.setIdDiagram(rs.getInt(1));
					diagram.setIdUser(rs.getInt(2));
					diagram.setFhCreation(rs.getTimestamp(3));
					diagram.setFhUpdate(rs.getTimestamp(4));
					diagrams.add(diagram);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return diagrams;
	}
	
	public static Diagram create(Diagram diagram) {
		Diagram diagramSaved = null;
		try {
			Connection con = Db.getConnection();
			PreparedStatement st = con.prepareStatement("insert into diagrama(id_usuario, fh_creacion, fh_modificacion) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, diagram.getIdUser());
			st.setTimestamp(2, diagram.getFhCreation());
			st.setTimestamp(3, diagram.getFhUpdate());
			int afectedRows = st.executeUpdate();
			if(afectedRows==1) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int idDiagram = rs.getInt(1);
					diagramSaved = new Diagram();
					diagramSaved.setIdDiagram(idDiagram);
					diagramSaved.setIdUser(diagram.getIdUser());
					diagramSaved.setFhCreation(diagram.getFhCreation());
					diagramSaved.setFhUpdate(diagram.getFhUpdate());					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return diagramSaved;
	}

	public static Diagram updateDiagram(Diagram diagram) {
		Diagram diagramSaved = null;
		try {
			Connection con = Db.getConnection();
			PreparedStatement st = con.prepareStatement("update diagrama set id_usuario=?, fh_creacion=?, fh_modificacion=? where id_diagrama=?");
			st.setInt(1, diagram.getIdUser());
			st.setTimestamp(2, diagram.getFhCreation());
			st.setTimestamp(3, diagram.getFhUpdate());
			st.setInt(4, diagram.getIdDiagram());
			int afectedRows = st.executeUpdate();
			if(afectedRows==1) {
				diagramSaved = new Diagram();
				diagramSaved.setIdDiagram(diagram.getIdDiagram());
				diagramSaved.setIdUser(diagram.getIdUser());
				diagramSaved.setFhCreation(diagram.getFhCreation());
				diagramSaved.setFhUpdate(diagram.getFhUpdate());	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return diagramSaved;
	}
	
	public static FigureDb addFigure(FigureDb figure) {
		FigureDb figureSaved = null;
		try {
			Connection con = Db.getConnection();
			PreparedStatement st = con.prepareStatement("insert into figura(id_diagrama, tx_json, tool) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, figure.getIdDiagram());
			st.setString(2, figure.getTxJson());
			st.setInt(3, figure.getTool());
			int afectedRows = st.executeUpdate();
			if(afectedRows==1) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int idFigure = rs.getInt(1);
					figureSaved = new FigureDb();
					figureSaved.setIdFigure(idFigure);
					figureSaved.setIdDiagram(figure.getIdDiagram());
					figureSaved.setTool(figure.getTool());		
					figureSaved.setTxJson(figure.getTxJson());					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return figureSaved;
	}
	
	public static boolean deleteFigures(int idDiagram) {
		boolean deleted = false;
		try {
			PreparedStatement pst = Db.getConnection().prepareStatement("delete from figura where id_diagrama=?");
			pst.setInt(1, idDiagram);
			int afectedRows = pst.executeUpdate();
			deleted = (afectedRows == 1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return deleted;
	}
	
	public static boolean deleteDiagram(int idDiagram) {
		boolean deleted = false;
		try {
			PreparedStatement pst = Db.getConnection().prepareStatement("delete from diagrama where id_diagrama=?");
			pst.setInt(1, idDiagram);
			int afectedRows = pst.executeUpdate();
			deleted = (afectedRows == 1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return deleted;
	}
	
}
