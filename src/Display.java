import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
public class Display implements ActionListener{
	
	MyConnection mc = null;
	Connection cnn = null;
	Statement stm = null;
	JPanel container;
	int numberOfTasks;
	ArrayList<JButton> delete = new ArrayList<>();
	Map<Integer,Integer> idMap = new HashMap<>();
	
	GridBagConstraints gbc = new GridBagConstraints();
	public ResultSet getResultSet() {
		try {
			mc = new MyConnection();
			cnn = mc.getConnection();
			stm = cnn.createStatement();
			String query = "SELECT *FROM tasks ";
			ResultSet resultSet = stm.executeQuery(query);
			return resultSet;
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return null;
	}
	public void displayTask(){
		container.setLayout(new GridBagLayout());
		try {
			int key = 0;
			ResultSet resultSet = getResultSet();
			ArrayList <String> task = new ArrayList<>();	
			ArrayList <String> date = new ArrayList<>();
			while(resultSet.next())
			{
				idMap.put(key,resultSet.getInt("id"));
				task.add(resultSet.getString("description"));
				date.add(resultSet.getString("date"));
				key++;
			}
			cnn.close();
			
			resultSet.close();
			numberOfTasks = task.size();
			//JTextArea[] taskField = new JTextField[rowCount];
			ArrayList<JTextArea> taskArea = new ArrayList<>();
//			ArrayList<JButton> delete = new ArrayList<>();
			//delete = new JButton[numberOfTasks];
			for(int i=0;i<task.size();i++) {
				
				taskArea.add(new JTextArea());
				//delete[i] = new JButton();
				delete.add(new JButton("Delete"));
				
				taskArea.get(i).setEditable(false);
				taskArea.get(i).setWrapStyleWord(true);
				taskArea.get(i).setLineWrap(true);
				taskArea.get(i).setPreferredSize(new Dimension(300,100));
				gbc.gridx = 0;
				gbc.gridy = i;
				container.add(taskArea.get(i),gbc);
				delete.get(i).setPreferredSize(new Dimension(75,25));
				gbc.gridx = 1;
				gbc.gridy = i;
				container.add(delete.get(i),gbc);

				delete.get(i).addActionListener(this);
				taskArea.get(i).setText("Date Added: "+date.get(i)+"\n\nTask: "+task.get(i));
			}
			JButton refresh = new JButton("refresh");
			gbc.gridx = 0;
			gbc.gridy = numberOfTasks;
			container.add(refresh,gbc);
			refresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					refreshData();
				}
			});
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<numberOfTasks;i++) {
			if(e.getSource() == delete.get(i)) {
				if(new Delete().deleteTask(idMap.get(i))>0) {
					JOptionPane.showMessageDialog(container, "Deleted Successfully");
					
					refreshData();
				}
			}
		}
	}
	public void refreshData() {
		container.removeAll();
		container.revalidate();
		container.repaint();
		new Display(container);
		container.revalidate();
		container.repaint();
	}
	public Display(JPanel container) {
		this.container = container;		
			displayTask();			
			
			//refreshData();
			
			
		
		
	}
	
	

}
