package UI;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import data.Student;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class StudentInfoPanel extends JPanel {
	static MyButtonSolid btnChangePhoto;
	private Student currentStudent;
	
	/**
	 * Create the panel.
	 */
	public StudentInfoPanel(Student currentStudent) {
		setBorder(new LineBorder(new Color(154, 205, 50)));
		this.currentStudent = currentStudent;

		initialize();
	}
	private void initialize(){
		setBackground(new Color(255, 250, 250));
		setLayout(null);

		JLabel lblStudentName = new JLabel(" Student Name:");
		lblStudentName.setForeground(Color.BLACK);
		lblStudentName.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblStudentName.setBounds(41, 202, 114, 26);
		add(lblStudentName);

		JLabel lblStudentId = new JLabel(" Student ID:");
		lblStudentId.setForeground(Color.BLACK);
		lblStudentId.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblStudentId.setBounds(41, 233, 126, 26);
		add(lblStudentId);

		JLabel name = new JLabel("name");
		name.setFont(new Font("Calibri", Font.PLAIN, 16));
		name.setText(currentStudent.getName());
		name.setBounds(168, 207, 126, 15);
		add(name);

		JLabel id = new JLabel("ID");
		id.setFont(new Font("Calibri", Font.PLAIN, 16));
		id.setText(currentStudent.getID());
		id.setBounds(168, 238, 126, 15);
		add(id);

		JLabel lblStudentPhoto = new JLabel("Student Photo");
		lblStudentPhoto.setForeground(new Color(154, 205, 50));
		lblStudentPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentPhoto.setHorizontalTextPosition(SwingConstants.CENTER);
		lblStudentPhoto.setBorder(new LineBorder(new Color(154, 205, 50), 1, true));
		lblStudentPhoto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStudentPhoto.setBounds(12, 12, 108, 24);
		add(lblStudentPhoto);

		JLabel lblStudentInfo = new JLabel("Student Info");
		lblStudentInfo.setForeground(new Color(154, 205, 50));
		lblStudentInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentInfo.setBorder(new LineBorder(new Color(154, 205, 50), 1, true));
		lblStudentInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStudentInfo.setBounds(12, 164, 97, 24);
		add(lblStudentInfo);
		
		Image studentPhoto = currentStudent.getPhoto();
		JLabel photoLabel = new JLabel(new ImageIcon(studentPhoto));
		photoLabel.setBounds(42, 50, 114, 102);
		add(photoLabel);
		
		btnChangePhoto = new MyButtonSolid("Change Photo");
		btnChangePhoto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnChangePhoto.setBounds(169, 123, 114, 28);
		add(btnChangePhoto);
		
		JTable semOne = new JTable(currentStudent.generateCourseTableData(1), new String[]{"Course"});
		JScrollPane scrollPane = new JScrollPane(semOne);
		scrollPane.setBounds(41, 286, 81, 102);
		add(scrollPane);

		JTable semTwo = new JTable(currentStudent.generateCourseTableData(2), new String[]{"Course"});
		JScrollPane scrollPane_1 = new JScrollPane(semTwo);
		scrollPane_1.setBounds(178, 286, 81, 102);
		add(scrollPane_1);

		JLabel lblSemOne = new JLabel("Sem One");
		lblSemOne.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblSemOne.setForeground(new Color(0, 0, 0));
		lblSemOne.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemOne.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSemOne.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSemOne.setBounds(41, 265, 81, 20);
		add(lblSemOne);

		JLabel lblSemTwo = new JLabel("Sem Two");
		lblSemTwo.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblSemTwo.setForeground(new Color(0, 0, 0));
		lblSemTwo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemTwo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSemTwo.setBounds(178, 265, 81, 20);
		add(lblSemTwo);
	}
}
