package mx.com.ipn.upiicsa.poo.pizarron.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mx.com.ipn.upiicsa.poo.pizarron.entity.User;

public class UserDao {
	public static List<User> findAll(){
		List<User> users = new ArrayList<User>();
		
		try {
			Connection con = Db.getConnection();
			PreparedStatement st = con.prepareStatement("select * from usuario");
			boolean executed = st.execute();
			if(executed) {
				ResultSet rs = st.getResultSet();
				while(rs.next()) {
					User usr = new User();
					usr.setId(rs.getInt(1));
					usr.setName(rs.getString(2));
					usr.setLastName(rs.getString(3));
					usr.setSecondLastName(rs.getString(4));
					usr.setLogin(rs.getString(5));
					usr.setPassword(rs.getString(6));
					users.add(usr);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public static User findById(Integer idUsuario){
		User usr = null;
		try {
			Connection con = Db.getConnection();
			PreparedStatement st = con.prepareStatement("select * from usuario where id_usuario=?");
			st.setInt(1, idUsuario);
			boolean executed = st.execute();
			if(executed) {
				ResultSet rs = st.getResultSet();
				rs.next();
				usr = new User();
				usr.setId(rs.getInt(1));
				usr.setName(rs.getString(2));
				usr.setLastName(rs.getString(3));
				usr.setSecondLastName(rs.getString(4));
				usr.setLogin(rs.getString(5));
				usr.setPassword(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usr;
	}
	
	public static User findByLoginAndPassword(String login, String password){
		User usr = null;
		try {
			Connection con = Db.getConnection();
			PreparedStatement st = con.prepareStatement("select * from usuario where tx_login=? and tx_password=?");
			st.setString(1, login);
			st.setString(2, password);
			boolean executed = st.execute();
			if(executed) {
				ResultSet rs = st.getResultSet();
				rs.next();
				usr = new User();
				usr.setId(rs.getInt(1));
				usr.setName(rs.getString(2));
				usr.setLastName(rs.getString(3));
				usr.setSecondLastName(rs.getString(4));
				usr.setLogin(rs.getString(5));
				usr.setPassword(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usr;
	}
	
	public static User findByLogin(String login){
		User usr = null;
		try {
			Connection con = Db.getConnection();
			PreparedStatement st = con.prepareStatement("select * from usuario where tx_login=?");
			st.setString(1, login);
			boolean executed = st.execute();
			if(executed) {
				ResultSet rs = st.getResultSet();
				rs.next();
				usr = new User();
				usr.setId(rs.getInt(1));
				usr.setName(rs.getString(2));
				usr.setLastName(rs.getString(3));
				usr.setSecondLastName(rs.getString(4));
				usr.setLogin(rs.getString(5));
				usr.setPassword(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usr;
	}
	
	public static User save(User usr) {
		User usrSaved = null;
		try {
			Connection con = Db.getConnection();
			PreparedStatement st = con.prepareStatement("insert into usuario (tx_nombre, tx_primer_apellido, tx_segundo_apellido, tx_login, tx_password) values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, usr.getName());
			st.setString(2, usr.getLastName());
			st.setString(3, usr.getSecondLastName());
			st.setString(4, usr.getLogin());
			st.setString(5, usr.getPassword());
			int afectedRows = st.executeUpdate();
			if(afectedRows==1) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int idUsuario = rs.getInt(1);
					usrSaved = new User();
					usrSaved.setId(idUsuario);
					usrSaved.setName(usr.getName());
					usrSaved.setLastName(usr.getLastName());
					usrSaved.setSecondLastName(usr.getSecondLastName());
					usrSaved.setLogin(usr.getLogin());
					usrSaved.setPassword(usr.getPassword());					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usrSaved;
	}
	
	public static boolean update(User usr) {
		boolean updated = false;
		try {
			Connection con = Db.getConnection();
			PreparedStatement st = con.prepareStatement("update usuario set tx_nombre=?, tx_primer_apellido=?, tx_segundo_apellido=?, tx_login=?, tx_password=? where id_usuario=?");
			st.setString(1, usr.getName());
			st.setString(2, usr.getLastName());
			st.setString(3, usr.getSecondLastName());
			st.setString(4, usr.getLogin());
			st.setString(5, usr.getPassword());
			st.setInt(6, usr.getId());
			int afectedRows = st.executeUpdate();
			if(afectedRows==1) {
				updated = true;					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updated;
	}
	
	public static boolean delete(Integer idUsuario) {
		boolean deleted = false;
		try {
			Connection con = Db.getConnection();
			PreparedStatement st = con.prepareStatement("delete from usuario where id_usuario=?");
			st.setInt(1, idUsuario);
			int afectedRows = st.executeUpdate();
			if(afectedRows==1) {
				deleted = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return deleted;
	}
	
}
