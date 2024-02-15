import java.sql.*;
public class MyConnection {
	private String username = "root";
	private String password = "";
	private String url = "jdbc:mysql://localhost/todolist";
	private Connection cnn = null;
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cnn = DriverManager.getConnection(url,username,password);
			
			
		}
		catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return cnn;
		
	}

}
