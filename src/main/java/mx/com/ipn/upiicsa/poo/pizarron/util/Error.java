package mx.com.ipn.upiicsa.poo.pizarron.util;

public class Error {
	
	private TypeErrorEnum type;
	private String path;
	private String message;
	
	public Error(String path) {
		this.path = path;
	}

	public Error(TypeErrorEnum type, String path, String message) {
		this.type = type;
		this.path = path;
		this.message = message;
	}
	
	public Error(TypeErrorEnum type, String message) {
		this.type = type;
		this.message = message;
	}

	public enum TypeErrorEnum{
		FIELD(1),OPERATION(2);
		private int id;
		
		private TypeErrorEnum(int id){
			this.setId(id);
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}

	public TypeErrorEnum getType() {
		return type;
	}

	public void setType(TypeErrorEnum type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Error other = (Error) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	
}
