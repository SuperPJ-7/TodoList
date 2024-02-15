import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
public class AddTask {
	public AddTask(String task) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String dateTime = currentDateTime.format(formatter);
		try {
			MyConnection mc = new MyConnection();
			Connection cnn = mc.getConnection();
			String query = "Insert into tasks(description,date) values(?,?)";
			PreparedStatement pstm = cnn.prepareStatement(query);
			pstm.setString(1,task);
			pstm.setString(2, dateTime);
			int rowsAffected = pstm.executeUpdate();
			cnn.close();
			pstm.close();
			if(rowsAffected>0) {
				JOptionPane.showMessageDialog(null,"Successfully added");
			}
			
			
			
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
}
