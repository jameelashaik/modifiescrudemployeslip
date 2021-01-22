package in.apcfss.dbplugin;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbPlugin {

	
	public static Connection getConnection() throws Exception
	{
		 Connection con = null;
		 Class.forName("org.postgresql.Driver");
		 con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "password");
		 System.out.println("Database opened successfully......");
		 return con;
	}
	
}

