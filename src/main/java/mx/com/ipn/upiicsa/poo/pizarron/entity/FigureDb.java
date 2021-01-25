package mx.com.ipn.upiicsa.poo.pizarron.entity;

import com.google.gson.Gson;

import mx.com.ipn.upiicsa.poo.pizarron.util.ToolCodes;

public class FigureDb {
	private int idFigure;
	private int idDiagram;
	private String txJson;
	private Figure figure;
	private int tool;
	
	public FigureDb() {
		super();
	}
	
	public FigureDb(int idDiagram, Figure figure) {
		this.idDiagram = idDiagram;
		this.figure = figure;
		this.tool = figure.getTool();
	}
	public int getIdFigure() {
		return idFigure;
	}
	public void setIdFigure(int idFigure) {
		this.idFigure = idFigure;
	}
	public int getIdDiagram() {
		return idDiagram;
	}
	public void setIdDiagram(int idDiagram) {
		this.idDiagram = idDiagram;
	}
	public String getTxJson() {
		//Convertir la figura en json
		Gson gson = new Gson();
	    
	    //Staff obj = new Staff();

	    // 1. Java object to JSON file
	    //gson.toJson(obj, new FileWriter("C:\\projects\\staff.json"));
	    
	    // 2. Java object to JSON string
		String t = gson.toJson(figure);
		return t;
	}
	public void setTxJson(String txJson) {
		this.txJson = txJson;
	}
	
	public String getJson() {
		return this.txJson;
	}
	
	public Figure getFigure() {
		Figure figure = null;
		Gson gson = new Gson();
		switch (tool) {
			case ToolCodes.TOOL_CIRCLE:
				figure = gson.fromJson(txJson, CircleFigure.class);
				break;
			case ToolCodes.TOOL_TRIANGLE:
				figure = gson.fromJson(txJson, TriangleFigure.class);
				break;
			case ToolCodes.TOOL_SQUARE:
				figure = gson.fromJson(txJson, SquareFigure.class);
				break;
			case ToolCodes.TOOL_RECTANGLE:
				figure = gson.fromJson(txJson, RectangleFigure.class);
				break;
			case ToolCodes.TOOL_PENCIL:
				figure = gson.fromJson(txJson, PencilFigure.class);
				break;
			case ToolCodes.TOOL_TEXT:
				figure = gson.fromJson(txJson, TextFigure.class);
				break;
			case ToolCodes.TOOL_IMAGE:
				figure = gson.fromJson(txJson, ImageFigure.class);
				break;
			default:
				break;
		}
		return figure;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}

	public int getTool() {
		return tool;
	}

	public void setTool(int tool) {
		this.tool = tool;
	}
	
	
	
	
	
}
