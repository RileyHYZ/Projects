package UI;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import data.Teacher;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TeacherInfoPanel extends JPanel {
	private Teacher teacher;
	public static MyButtonSolid btnChangeTeacherPhoto;
	private JTable semOne;
	private JTable semTwo;
	/**
	 * Create the panel.
	 */
	public TeacherInfoPanel(Teacher teacher) {
		this.teacher = teacher;
		
		setBackground(new Color(255, 250, 250));
		setBorder(new LineBorder(new Color(154, 205, 50), 1, true));
		setLayout(null);
		
		JLabel lblTeacherPhoto = new JLabel("Teacher Photo");
		lblTeacherPhoto.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTeacherPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacherPhoto.setForeground(new Color(154, 205, 50));
		lblTeacherPhoto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTeacherPhoto.setBorder(new LineBorder(new Color(154, 205, 50), 1, true));
		lblTeacherPhoto.setBounds(12, 24, 108, 26);
		add(lblTeacherPhoto);
		
		
		JLabel teacherPhoto = new JLabel(new ImageIcon(teacher.getPhoto()));
		teacherPhoto.setBounds(41, 47, 114, 102);
		add(teacherPhoto);
		
		btnChangeTeacherPhoto = new MyButtonSolid("Change Photo");
		btnChangeTeacherPhoto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnChangeTeacherPhoto.setBounds(159, 135, 114, 28);
		add(btnChangeTeacherPhoto);
		
		JLabel lblTeacherInfo = new JLabel("Teacher Info");
		lblTeacherInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacherInfo.setForeground(new Color(154, 205, 50));
		lblTeacherInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTeacherInfo.setBorder(new LineBorder(new Color(154, 205, 50), 1, true));
		lblTeacherInfo.setBounds(12, 187, 97, 26);
		add(lblTeacherInfo);
		
		JLabel lblTeacherName = new JLabel(" Teacher Name:");
		lblTeacherName.setForeground(Color.BLACK);
		lblTeacherName.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblTeacherName.setBounds(32, 227, 114, 26);
		add(lblTeacherName);
		
		JLabel lblAge = new JLabel(" Age: ");
		lblAge.setForeground(Color.BLACK);
		lblAge.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblAge.setBounds(32, 258, 126, 26);
		add(lblAge);
		
		JLabel lblGender = new JLabel(" Gender: ");
		lblGender.setForeground(Color.BLACK);
		lblGender.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblGender.setBounds(33, 299, 126, 26);
		add(lblGender);
		
		JLabel name = new JLabel(teacher.getName());
		name.setFont(new Font("Calibri", Font.PLAIN, 16));
		name.setBounds(159, 232, 140, 15);
		add(name);
		
		JLabel age = new JLabel(Integer.toString(teacher.getAge()));
		age.setFont(new Font("Calibri", Font.PLAIN, 16));
		age.setBounds(159, 263, 140, 15);
		add(age);	
		
		JLabel gender = new JLabel(teacher.getGender());
		gender.setFont(new Font("Calibri", Font.PLAIN, 16));
		gender.setBounds(159, 303, 140, 15);
		add(gender);
		
		semOne = new JTable(teacher.generateCourseTableData(1), new String[]{"Course Code", "Student Limit"});
//		semOneCourse.setRowHeight(40);
		JScrollPane scrollPane = new JScrollPane(semOne);
		scrollPane.setBounds(390, 57, 126, 115);
		add(scrollPane);
		
		semTwo = new JTable(teacher.generateCourseTableData(2), new String[]{"Course Code", "Student Limit"});
		JScrollPane scrollPane_1 = new JScrollPane(semTwo);
		scrollPane_1.setBounds(390, 220, 126, 115);
		add(scrollPane_1);
		
		JLabel label = new JLabel("Sem One");
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(154, 205, 50));
		label.setFont(new Font("Calibri", Font.PLAIN, 14));
		label.setBorder(new LineBorder(new Color(154, 205, 50)));
		label.setBounds(343, 34, 81, 20);
		add(label);
		
		JLabel label_1 = new JLabel("Sem Two");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(154, 205, 50));
		label_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		label_1.setBorder(new LineBorder(new Color(154, 205, 50)));
		label_1.setBounds(343, 197, 81, 20);
		add(label_1);

	}
}
