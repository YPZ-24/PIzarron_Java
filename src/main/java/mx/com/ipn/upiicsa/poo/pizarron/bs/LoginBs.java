package mx.com.ipn.upiicsa.poo.pizarron.bs;

import mx.com.ipn.upiicsa.poo.pizarron.dao.UserDao;
import mx.com.ipn.upiicsa.poo.pizarron.entity.User;
import mx.com.ipn.upiicsa.poo.pizarron.exceptions.UserNotFoundException;
import mx.com.ipn.upiicsa.poo.pizarron.exceptions.WrongLoginException;
import mx.com.ipn.upiicsa.poo.pizarron.util.Encryption;

public class LoginBs {
	public static User login(String login, String password) throws WrongLoginException, UserNotFoundException {
		User loginUser = null;
		User usuario = UserDao.findByLogin(login);
		
		if(usuario != null) {
			if(usuario.getPassword().equals(Encryption.digest(password))) {
				loginUser = usuario;
			}else {
				throw new WrongLoginException();
			}
		}else {
			throw new UserNotFoundException();
		}
		
		return loginUser;
	}
}
