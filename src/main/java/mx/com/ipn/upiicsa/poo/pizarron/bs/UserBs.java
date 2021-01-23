package mx.com.ipn.upiicsa.poo.pizarron.bs;

import mx.com.ipn.upiicsa.poo.pizarron.dao.UserDao;
import mx.com.ipn.upiicsa.poo.pizarron.entity.User;
import mx.com.ipn.upiicsa.poo.pizarron.exceptions.DuplicatedLoginException;
import mx.com.ipn.upiicsa.poo.pizarron.util.Encryption;

public class UserBs {
	
	public static User findById(Integer idUsuario) {
		User usuario = UserDao.findById(idUsuario);
		return usuario;
	}
	
	public static User save(User user) throws DuplicatedLoginException {
		boolean userExists = userExists(user.getId());
		//User oldUser = findById(user.getId());
		User newUser = null;
		if(!userExists) {
			user.setPassword(Encryption.digest(user.getPassword()));
			newUser = UserDao.save(user);
		}else {
			throw new DuplicatedLoginException();
		}
		return newUser;
	}
	
	public static boolean userExists(Integer idUser) {
		boolean userExists = false;
		if(idUser != null) {
			if(findById(idUser) != null) {
				userExists = true;
			}
		}
		return userExists;
	}
	
}
