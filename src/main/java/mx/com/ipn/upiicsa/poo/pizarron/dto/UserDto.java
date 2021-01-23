package mx.com.ipn.upiicsa.poo.pizarron.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import mx.com.ipn.upiicsa.poo.pizarron.entity.User;
import mx.com.ipn.upiicsa.poo.pizarron.util.DtoInterface;

public class UserDto implements DtoInterface{
	private Integer id;
	private String name;
	private String lastName;
	private String secondLastName;
	private String login;
	private String password;
	private String confirmPassword;
	
	public UserDto() {
		super();
	}

	public UserDto(String name, String lastName, String secondLastName, String login, String password,
			String confirmPassword) {
		this.name = name;
		this.lastName = lastName;
		this.secondLastName = secondLastName;
		this.login = login;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotEmpty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotEmpty
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@NotEmpty
	public String getSecondLastName() {
		return secondLastName;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}
	
	@NotEmpty
	@Email
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotEmpty
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public static UserDto fromEntity(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setLastName(user.getLastName());
		userDto.setSecondLastName(user.getSecondLastName());
		userDto.setLogin(user.getLogin());
		return userDto;
	}
	
	public User toEntity() {
		User user = new User();
		user.setId(this.getId());
		user.setName(this.getName());
		user.setLastName(this.getLastName());
		user.setSecondLastName(this.getSecondLastName());
		user.setLogin(this.getLogin());
		user.setPassword(this.getPassword());
		return user;
	}
	
}
