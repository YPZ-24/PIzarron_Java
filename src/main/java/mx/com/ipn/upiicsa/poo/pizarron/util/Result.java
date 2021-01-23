package mx.com.ipn.upiicsa.poo.pizarron.util;

import java.util.List;

public class Result<T extends DtoInterface> {
	
	private int status;
	private T result;
	private List<T> results;
	private List<Error> errors; 
	
	public Result() {
		this.status = StatusCodes.SUCCESS;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> results) {
		this.results = results;
	}
	public List<Error> getErrors() {
		return errors;
	}
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
}
