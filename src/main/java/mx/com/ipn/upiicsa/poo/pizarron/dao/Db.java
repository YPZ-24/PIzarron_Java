package mx.com.ipn.upiicsa.poo.pizarron.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Db { 
	//private Connection con;
	//private PreparedStatement st;
	
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	static {
		try {
			cpds.setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver            
			cpds.setJdbcUrl( "jdbc:postgresql://localhost/pizarron" );
			cpds.setUser("ypz");                                  
			cpds.setPassword("pass");  
			cpds.setMinPoolSize(5);
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(20);
		}catch(PropertyVetoException e) {
			
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return cpds.getConnection();
	}
	
	private void Db() {}
	
	
	
}
