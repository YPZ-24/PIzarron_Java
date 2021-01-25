package mx.com.ipn.upiicsa.poo.pizarron.pr;

import java.util.ArrayList;

import mx.com.ipn.upiicsa.poo.pizarron.bs.DiagramBs;
import mx.com.ipn.upiicsa.poo.pizarron.entity.Diagram;
import mx.com.ipn.upiicsa.poo.pizarron.entity.Figure;

public class DiagramExplorerPr {
	
	public static ArrayList<Diagram> getUserDiagrams(int idUser) {
		return DiagramBs.getUserDiagrams(idUser);
	}	
	
}
