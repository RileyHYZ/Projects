package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import UI.addFunction.AddClassPanel;
import UI.addFunction.AddStudentPanel;
import UI.addFunction.AddTeacherPanel;
import data.Class;
import data.Database;
import data.Student;
import data.Teacher;

import java.awt.GridLayout;
import java.awt.ScrollPane;


public class MainMenuPanel extends JPanel {

	private MyButton btnAccessClass;
	private MyButton btnAddClass;
	private MyButton btnDisplayClass;
	private MyButton btnAccessStudent;
	private MyButton btnAddStudent;
	private MyButton btnDisplayStudent;
	private MyButton btnAddTeacher;
	private MyButton btnDisplayTeacher;
	
	/**
	 * Create the panel.
	 */
	public MainMenuPanel() {
		initialize();
		addListener();
	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		add(getUpperPanel(), BorderLayout.NORTH);
		add(getLeftPanel(), BorderLayout.WEST);
		add(getCenterPanel(), BorderLayout.CENTER);
	}

	private JPanel getUpperPanel(){
		JPanel upperPanel = new JPanel();
		upperPanel.setBackground(Color.WHITE);
		upperPanel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(154, 205, 50)));
		upperPanel.setPreferredSize(new Dimension(10, 180));		
		upperPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblLangstaffCourseSelection = new JLabel("Langstaff Course Selection System");
		lblLangstaffCourseSelection.setHorizontalAlignment(SwingConstants.CENTER);
		lblLangstaffCourseSelection.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		lblLangstaffCourseSelection.setForeground(new Color(154, 205, 50));
		lblLangstaffCourseSelection.setAlignmentX(Component.CENTER_ALIGNMENT);
		upperPanel.add(lblLangstaffCourseSelection);

		return upperPanel;
	}

	private JPanel getLeftPanel(){
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(154, 205, 50));
		leftPanel.setPreferredSize(new Dimension(150, 10));
		leftPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblManageClass = new JLabel("Manage Class");
		lblManageClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageClass.setFont(new Font("Arial", Font.BOLD, 15));
		lblManageClass.setForeground(Color.WHITE);
		leftPanel.add(lblManageClass);

		JLabel lblManageStudent = new JLabel("Manage Student");
		lblManageStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageStudent.setForeground(Color.WHITE);
		lblManageStudent.setFont(new Font("Arial", Font.BOLD, 15));
		leftPanel.add(lblManageStudent);

		JLabel lblManageTeacher = new JLabel("Manage Teacher");
		lblManageTeacher.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageTeacher.setForeground(Color.WHITE);
		lblManageTeacher.setFont(new Font("Arial", Font.BOLD, 15));
		leftPanel.add(lblManageTeacher);

		return leftPanel;
	}

	private JPanel getCenterPanel(){
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));

		//top panel
		JPanel manageClassPane = new JPanel();
		manageClassPane.setBackground(Color.WHITE);
		manageClassPane.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(154, 205, 50)));
		manageClassPane.setPreferredSize(new Dimension(10, 108));
		manageClassPane.setLayout(new BoxLayout(manageClassPane, BoxLayout.X_AXIS));

		Component glue = Box.createGlue();
		manageClassPane.add(glue);

		btnAccessClass = new MyButton("Access a Class");
		btnAccessClass.setFont(new Font("Calibri", Font.PLAIN, 17));
		manageClassPane.add(btnAccessClass);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		manageClassPane.add(horizontalStrut);

		btnAddClass = new MyButton("Add Class");
		btnAddClass.setFont(new Font("Calibri", Font.PLAIN, 17));
		manageClassPane.add(btnAddClass);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		manageClassPane.add(horizontalStrut_1);

		btnDisplayClass = new MyButton("Display All Class");
		btnDisplayClass.setFont(new Font("Calibri", Font.PLAIN, 17));
		manageClassPane.add(btnDisplayClass);

		Component glue_1 = Box.createGlue();
		manageClassPane.add(glue_1);

		centerPanel.add(manageClassPane);

		//middle panel
		JPanel manageStudentPane = new JPanel();
		manageStudentPane.setBackground(Color.WHITE);
		manageStudentPane.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(154, 205, 50)));
		manageStudentPane.setLayout(new BoxLayout(manageStudentPane, BoxLayout.X_AXIS));

		Component glue_2 = Box.createGlue();
		manageStudentPane.add(glue_2);

		btnAccessStudent = new MyButton("Access a Student");
		btnAccessStudent.setFont(new Font("Calibri", Font.PLAIN, 17));
		manageStudentPane.add(btnAccessStudent);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		manageStudentPane.add(horizontalStrut_2);

		btnAddStudent = new MyButton("Add Student");
		btnAddStudent.setFont(new Font("Calibri", Font.PLAIN, 17));
		manageStudentPane.add(btnAddStudent);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		manageStudentPane.add(horizontalStrut_3);

		btnDisplayStudent = new MyButton("Display All Student");
		btnDisplayStudent.setFont(new Font("Calibri", Font.PLAIN, 17));
		btnDisplayStudent.setAlignmentX(Component.CENTER_ALIGNMENT);
		manageStudentPane.add(btnDisplayStudent);

		Component glue_3 = Box.createGlue();
		manageStudentPane.add(glue_3);

		centerPanel.add(manageStudentPane);


		//bottom panel
		JPanel manageTeacherPane = new JPanel();
		manageTeacherPane.setBackground(Color.WHITE);
		manageTeacherPane.setPreferredSize(new Dimension(10, 108));
		manageTeacherPane.setLayout(new BoxLayout(manageTeacherPane, BoxLayout.X_AXIS));

		Component glue_5 = Box.createGlue();
		manageTeacherPane.add(glue_5);

		btnAddTeacher = new MyButton("Add Teacher");
		btnAddTeacher.setFont(new Font("Calibri", Font.PLAIN, 17));
		manageTeacherPane.add(btnAddTeacher);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		manageTeacherPane.add(horizontalStrut_4);

		btnDisplayTeacher = new MyButton("Display All Teacher");
		btnDisplayTeacher.setFont(new Font("Calibri", Font.PLAIN, 17));
		manageTeacherPane.add(btnDisplayTeacher);

		Component glue_4 = Box.createGlue();
		manageTeacherPane.add(glue_4);

		centerPanel.add(manageTeacherPane);

		return centerPanel;
	}

	private void addListener(){
		
		btnAccessClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Class.chooseClass();			
			}
		});
		
		btnAddClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddClassPanel ac = new AddClassPanel();
			}
		});
		
		btnDisplayClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayPanel classPane = new DisplayPanel(new Class(1));
				AppMainWindow.contentPane.removeAll();
				AppMainWindow.contentPane.add(classPane);
				AppMainWindow.contentPane.updateUI();;
			}
		});
		
		btnAccessStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchStudentPanel searchPanel = new SearchStudentPanel();
				AppMainWindow.contentPane.removeAll();
				AppMainWindow.contentPane.add(searchPanel);
				AppMainWindow.contentPane.updateUI();;
			}
		});
		
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentPanel as = new AddStudentPanel();
			}
		});
		
		btnDisplayStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayPanel studentPane = new DisplayPanel(new Student(null, null));
				AppMainWindow.contentPane.removeAll();
				AppMainWindow.contentPane.add(studentPane);
				AppMainWindow.contentPane.updateUI();;
			}
		});
		
		btnAddTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTeacherPanel at = new AddTeacherPanel();
			}
		});
		
		btnDisplayTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayPanel teacherPane = new DisplayPanel(new Teacher());
				AppMainWindow.contentPane.removeAll();
				AppMainWindow.contentPane.add(teacherPane);
				AppMainWindow.contentPane.updateUI();;
			}
		});
	}
}
