package util;

import java.sql.*;

public class ModuloConexao {

	public static Connection conector(){
		
		java.sql.Connection conexao = null;
                String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/netflix";
		String user = "root";
		String senha = "12345678";
		
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url,user,senha);
			return conexao;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}