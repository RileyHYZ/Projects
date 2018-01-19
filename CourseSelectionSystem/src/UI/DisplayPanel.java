package UI;

import javax.swing.JPanel;
import data.*;
import data.Class;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.UIManager;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;

public class DisplayPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public DisplayPanel(Student student) {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(154, 205, 50)));
		panel.setPreferredSize(new Dimension(10, 40));
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1, BorderLayout.WEST);

		JLabel lblAllStudents = new JLabel(" ALL Students");
		lblAllStudents.setForeground(new Color(154, 205, 50));
		lblAllStudents.setFont(new Font("Times New Roman", Font.BOLD, 25));
		panel_1.add(lblAllStudents);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2, BorderLayout.EAST);

		MyButtonSolid btnBack = new MyButtonSolid("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppMainWindow.contentPane.removeAll();
				AppMainWindow.contentPane.add(AppMainWindow.mainPanel);
				AppMainWindow.contentPane.updateUI();
			}
		});
		panel_2.add(btnBack);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		Component rigidArea = Box.createRigidArea(new Dimension(760, 35));
		panel_3.add(rigidArea, BorderLayout.NORTH);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(67, 441));
		panel_3.add(rigidArea_1, BorderLayout.WEST);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(760, 45));
		panel_3.add(rigidArea_2, BorderLayout.SOUTH);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(63, 416));
		panel_3.add(rigidArea_3, BorderLayout.EAST);

		JTable studentTable = new JTable(Database.getStudentTable(), new String[]{"Student Name", "Student ID", "Sem 1 Course", "Sem 2 Course"});
		JScrollPane scrollPane = new JScrollPane(studentTable);
		panel_3.add(scrollPane, BorderLayout.CENTER);

	}

	public DisplayPanel(Class c){
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(154, 205, 50)));
		panel.setPreferredSize(new Dimension(10, 40));
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1, BorderLayout.WEST);

		JLabel lblAllStudents = new JLabel(" ALL Classes");
		lblAllStudents.setForeground(new Color(154, 205, 50));
		lblAllStudents.setFont(new Font("Times New Roman", Font.BOLD, 25));
		panel_1.add(lblAllStudents);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2, BorderLayout.EAST);

		MyButtonSolid btnBack = new MyButtonSolid("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppMainWindow.contentPane.removeAll();
				AppMainWindow.contentPane.add(AppMainWindow.mainPanel);
				AppMainWindow.contentPane.updateUI();
			}
		});
		panel_2.add(btnBack);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		Component rigidArea = Box.createRigidArea(new Dimension(760, 35));
		panel_3.add(rigidArea, BorderLayout.NORTH);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(67, 441));
		panel_3.add(rigidArea_1, BorderLayout.WEST);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(760, 45));
		panel_3.add(rigidArea_2, BorderLayout.SOUTH);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(63, 416));
		panel_3.add(rigidArea_3, BorderLayout.EAST);

		JTable classTable = new JTable(Database.getClassTable(), new String[]{"Course Code", "Semester", "Period", "Teacher", "Students"});
		JScrollPane scrollPane = new JScrollPane(classTable);
		panel_3.add(scrollPane, BorderLayout.CENTER);
	}
	
	public DisplayPanel(Teacher t) {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(154, 205, 50)));
		panel.setPreferredSize(new Dimension(10, 40));
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1, BorderLayout.WEST);

		JLabel lblAllStudents = new JLabel(" ALL Teachers");
		lblAllStudents.setForeground(new Color(154, 205, 50));
		lblAllStudents.setFont(new Font("Times New Roman", Font.BOLD, 25));
		panel_1.add(lblAllStudents);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2, BorderLayout.EAST);

		MyButtonSolid btnBack = new MyButtonSolid("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppMainWindow.contentPane.removeAll();
				AppMainWindow.contentPane.add(AppMainWindow.mainPanel);
				AppMainWindow.contentPane.updateUI();
			}
		});
		panel_2.add(btnBack);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		Component rigidArea = Box.createRigidArea(new Dimension(760, 35));
		panel_3.add(rigidArea, BorderLayout.NORTH);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(67, 441));
		panel_3.add(rigidArea_1, BorderLayout.WEST);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(760, 45));
		panel_3.add(rigidArea_2, BorderLayout.SOUTH);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(63, 416));
		panel_3.add(rigidArea_3, BorderLayout.EAST);

		JTable teacherTable = new JTable(Database.getTeacherTable(), new String[]{"Teacher Name", "Gender", "Age", "Semester 1 Course", "Semester 2 Course"});;
		JScrollPane scrollPane = new JScrollPane(teacherTable);
		panel_3.add(scrollPane, BorderLayout.CENTER);

	}
}
