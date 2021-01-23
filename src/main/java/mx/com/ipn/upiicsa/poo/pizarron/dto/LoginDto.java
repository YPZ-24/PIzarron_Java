package mx.com.ipn.upiicsa.poo.pizarron.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class LoginDto {
	private String login;
	private String password;
	
	public LoginDto(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	@NotEmpty(message = "Escribe el login")
	@Email(message = "Escribe un correo electronico valido")
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	@NotEmpty(message = "Escribe la contraseña")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
