import java.sql.*;
import java.util.*;

public class UpdateId {
	public UpdateId() {
		int id = 0;
		try {
			MyConnection mc = new MyConnection();
			Connection cnn = mc.getConnection();
			Map<Integer,Integer> idMap = new HashMap<>();
			String query = "Select *from tasks";
			Statement stm = cnn.createStatement();
			ResultSet set = stm.executeQuery(query);
			while(set.next()) {
				idMap.put(id, set.getInt("id"));
				id++;
			}
			
			stm.close();
			set.close();
			for(int i = 0;i<idMap.size();i++) {
				String updateQuery = "update tasks set id=? where id=?";
				PreparedStatement pstm = cnn.prepareStatement(updateQuery);
				pstm.setInt(1, i+1);
				pstm.setInt(2, idMap.get(i));
				pstm.executeUpdate();
				
			}
			cnn.close();
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		
	}
}
