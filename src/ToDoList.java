import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoList extends JFrame{
	private JPanel leftContainer = new JPanel();
	private JPanel rightContainer = new JPanel();
	private JButton btnAdd = new JButton("Add");
	private JTextArea txt = new JTextArea();
	private JScrollPane scrollTasks = new JScrollPane(rightContainer);
	private JScrollPane scroll = new JScrollPane(txt);
	public ToDoList() {
		this.setTitle("Todo List");
		this.setLayout(new GridLayout(1,2));
		this.add(leftContainer);
		
		this.add(scrollTasks);
		new Display(rightContainer);
		leftContainer.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
		this.setSize(800,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		txt.setPreferredSize(new Dimension(350,100));
		txt.setLineWrap(true);
		leftContainer.add(scroll);
		
		
		leftContainer.add(btnAdd);
		btnAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnAdd) {
					String task = txt.getText().trim();
					if(task.equals("")) {
						
						JOptionPane.showMessageDialog(null, "Enter a task first!");
					}
					else {
						new AddTask(task);
					}
					txt.setText(null);
				}
			}
		});
	}
	public static void main(String[] args) {
		new ToDoList();
	}
}
