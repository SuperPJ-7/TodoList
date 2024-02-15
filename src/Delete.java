import java.sql.*;



public class Delete {
	
	

	public int deleteTask(int id) {
		int rowsAffected = 0;
		
		try {						
			Connection cnn = new MyConnection().getConnection();
			String query = "Delete from tasks where id = ?";
			PreparedStatement pstm = cnn.prepareStatement(query);
			pstm.setInt(1, id);
			rowsAffected = pstm.executeUpdate();
			cnn.close();
			pstm.close();
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return rowsAffected;
	}
}
