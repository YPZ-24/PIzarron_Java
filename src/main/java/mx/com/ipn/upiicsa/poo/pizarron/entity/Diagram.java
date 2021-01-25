package mx.com.ipn.upiicsa.poo.pizarron.entity;

import java.sql.Timestamp;

public class Diagram {
	Integer idDiagram;
	int idUser;
	Timestamp fhCreation;
	Timestamp fhUpdate;
	
	public Diagram() {}

	
		
	public Integer getIdDiagram() {
		return idDiagram;
	}
	public void setIdDiagram(Integer idDiagram) {
		this.idDiagram = idDiagram;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public Timestamp getFhCreation() {
		return fhCreation;
	}
	public void setFhCreation(Timestamp fhCreation) {
		this.fhCreation = fhCreation;
	}
	public Timestamp getFhUpdate() {
		return fhUpdate;
	}
	public void setFhUpdate(Timestamp fhUpdate) {
		this.fhUpdate = fhUpdate;
	}
	
	
	
}
