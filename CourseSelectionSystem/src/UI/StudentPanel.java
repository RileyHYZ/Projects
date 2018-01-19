package UI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Component;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.border.MatteBorder;

import UI.addFunction.AddClassToStudentPanel;
import data.Database;
import data.Student;
import data.Class; 

import javax.swing.border.LineBorder;
import javax.swing.JTable;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class StudentPanel extends JPanel {

	private JPanel upperPanel;
	private JPanel leftPanel;
	private JPanel centerPanel;	
	private JPanel semOnePane;
	private JPanel semTwoPane;	
	private MyButton btnChangeCourse;
	private MyButton btnChangeCourse2;
	private MyButton btnRemoveSem2;
	private MyButton btnRemoveSem1;
	private MyButtonSolid btnBack;
	private JTable semOneCourse;
	private JTable semTwoCourse;
	private Student currentStudent;
	private int sem;	//Display sem's courses
	private JPanel coursePane;
	private CardLayout CL;
	private MyButtonSolid btnSem1;
	private MyButtonSolid btnSem2;
	private StudentInfoPanel studentInfoPane;


	/**
	 * Create the panel.
	 * @param currentStudent 
	 */
	
	public StudentPanel(Student currentStudent, int show) {	
		this.currentStudent = currentStudent;
		this.sem = show;
		
		initialize();
		addListener();
	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		getUpperPanel();
		getLeftPanel();
		getCenterPanel();
	}

	private void getUpperPanel(){
		upperPanel = new JPanel();
		upperPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(154, 205, 50)));
		upperPanel.setBackground(Color.WHITE);
		upperPanel.setPreferredSize(new Dimension(10, 40));
		upperPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		upperPanel.add(panel_2, BorderLayout.WEST);

		JLabel lblStudentAndCourses = new JLabel("Student and Courses");
		lblStudentAndCourses.setForeground(new Color(154, 205, 50));
		lblStudentAndCourses.setFont(new Font("Times New Roman", Font.BOLD, 25));
		panel_2.add(lblStudentAndCourses);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		upperPanel.add(panel_3, BorderLayout.EAST);

		btnBack = new MyButtonSolid("Back");
		panel_3.add(btnBack);	

		add(upperPanel, BorderLayout.NORTH);
	}

	private void getLeftPanel(){
		leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setPreferredSize(new Dimension(350, 10));
		leftPanel.setLayout(new BorderLayout(0, 0));

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		leftPanel.add(rigidArea_1, BorderLayout.WEST);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		leftPanel.add(rigidArea_2, BorderLayout.SOUTH);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		leftPanel.add(rigidArea_3, BorderLayout.EAST);

//		JPanel studentInfoPane = new JPanel();
//		studentInfoPane.setBorder(new LineBorder(new Color(154, 205, 50)));
//		studentInfoPane.setBackground(Color.WHITE);
		studentInfoPane = new StudentInfoPanel(currentStudent);
		leftPanel.add(studentInfoPane, BorderLayout.CENTER);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setPreferredSize(new Dimension(10, 30));
		leftPanel.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblStudentInfo = new JLabel("Student Info");
		lblStudentInfo.setForeground(Color.BLACK);
		lblStudentInfo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_5.add(lblStudentInfo);

		add(leftPanel, BorderLayout.WEST);
	}

	private void getCenterPanel(){
		//center panel
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(new BorderLayout());

		//title "Courses"
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_6.setPreferredSize(new Dimension(10, 30));
		centerPanel.add(panel_6, BorderLayout.NORTH);

		JLabel lblCourses = new JLabel("Courses");
		lblCourses.setForeground(Color.BLACK);
		lblCourses.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_6.add(lblCourses);

		//filler
		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		centerPanel.add(rigidArea_5, BorderLayout.SOUTH);

		Component rigidArea_6 = Box.createRigidArea(new Dimension(36, 432));
		centerPanel.add(rigidArea_6, BorderLayout.EAST);


		//Sem selection bar panel
		JPanel semBarPane = new JPanel();
		semBarPane.setBackground(Color.LIGHT_GRAY);
		centerPanel.add(semBarPane, BorderLayout.WEST);
		semBarPane.setLayout(new BoxLayout(semBarPane, BoxLayout.Y_AXIS));

		btnSem1 = new MyButtonSolid("Sem 1");
		semBarPane.add(btnSem1);

		btnSem2 = new MyButtonSolid("Sem 2");
		semBarPane.add(btnSem2);

		//Course Panel
		coursePane = new JPanel();
		coursePane.setBorder(new MatteBorder(1, 0, 1, 1, (Color) new Color(154, 205, 50)));
		coursePane.setBackground(Color.WHITE);
		centerPanel.add(coursePane, BorderLayout.CENTER);
		CL = new CardLayout();
		coursePane.setLayout(CL);


		//Sem one Panel
		semOnePane = new JPanel();
		semOnePane.setBackground(Color.WHITE);
		semOnePane.setLayout(new BorderLayout());
		coursePane.add(semOnePane, "semOnePane");

		//Sem one course JTable	
		JPanel semOneContainer = new JPanel();
		semOneContainer.setBackground(new Color(255, 250, 250));
		semOneCourse = new JTable(currentStudent.generateCourseTableData(1), new String[]{"Course Code", "Teacher", "Students"});
		semOneCourse.setRowHeight(40);
		semOneContainer.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane = new JScrollPane(semOneCourse);
		semOneContainer.add(scrollPane);
		semOnePane.add(semOneContainer, BorderLayout.CENTER);

		//buttons
		JPanel semOneButtons = new JPanel();
		semOneButtons.setBackground(Color.WHITE);
		semOnePane.add(semOneButtons, BorderLayout.SOUTH);

		btnChangeCourse = new MyButton("Change Course");
		btnChangeCourse.setForeground(new Color(154, 205, 50));
		semOneButtons.add(btnChangeCourse);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		semOneButtons.add(horizontalStrut);

		btnRemoveSem1 = new MyButton("Remove Course");
		btnRemoveSem1.setForeground(new Color(154, 205, 50));
		semOneButtons.add(btnRemoveSem1);

		//fillier
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		semOnePane.add(rigidArea, BorderLayout.WEST);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		semOnePane.add(rigidArea_1, BorderLayout.NORTH);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		semOnePane.add(rigidArea_2, BorderLayout.EAST);


		//Sem two panel
		semTwoPane = new JPanel();
		semTwoPane.setBackground(Color.WHITE);
		semTwoPane.setLayout(new BorderLayout());
		coursePane.add(semTwoPane, "semTwoPane");

		//Sem two course JTable	
		JPanel semTwoContainer = new JPanel();
		semTwoContainer.setBackground(new Color(255, 250, 250));
		semTwoCourse = new JTable(currentStudent.generateCourseTableData(2), new String[]{"Course Code", "Teacher", "Students"});
		semTwoCourse.setRowHeight(40);
		semTwoContainer.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane_1 = new JScrollPane(semTwoCourse);	
		semTwoContainer.add(scrollPane_1);
		semTwoPane.add(semTwoContainer, BorderLayout.CENTER);

		//buttons
		JPanel semTwoButtons = new JPanel();
		semTwoPane.add(semTwoButtons, BorderLayout.SOUTH);
		semTwoButtons.setBackground(Color.WHITE);

		btnChangeCourse2 = new MyButton("Change Course");
		btnChangeCourse2.setForeground(new Color(154, 205, 50));
		semTwoButtons.add(btnChangeCourse2);

		Component horizontalStrut2 = Box.createHorizontalStrut(20);
		semTwoButtons.add(horizontalStrut2);

		btnRemoveSem2 = new MyButton("Remove Course");
		btnRemoveSem2.setForeground(new Color(154, 205, 50));
		semTwoButtons.add(btnRemoveSem2);

		//filler
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		semTwoPane.add(rigidArea_3, BorderLayout.NORTH);

		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		semTwoPane.add(rigidArea_4, BorderLayout.WEST);

		Component rigidArea_7 = Box.createRigidArea(new Dimension(20, 20));
		semTwoPane.add(rigidArea_7, BorderLayout.EAST);

		
		//Display different semester course
		if(sem == 1){
			btnSem1.setEnabled(true);
			btnSem2.setEnabled(false);
			CL.show(coursePane, "semOnePane");
		}
		else{
			btnSem1.setEnabled(false);
			btnSem2.setEnabled(true);
			CL.show(coursePane, "semTwoPane");
		}
		

		add(centerPanel, BorderLayout.CENTER);
	}

	private void addListener(){
		btnSem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSem1.setEnabled(true);
				btnSem2.setEnabled(false);
				CL.show(coursePane, "semOnePane");
			}
		});

		btnSem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSem1.setEnabled(false);
				btnSem2.setEnabled(true);
				CL.show(coursePane, "semTwoPane");
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchStudentPanel searchPanel = new SearchStudentPanel();
				AppMainWindow.contentPane.removeAll();
				AppMainWindow.contentPane.add(searchPanel);
				AppMainWindow.contentPane.updateUI();
			}
		});

		btnChangeCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choose = semOneCourse.getSelectedRow();

				if(choose == -1){
					JOptionPane.showMessageDialog (new JPanel(), "Please choose a period first!",
							"SORRY", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					AddClassToStudentPanel acsp = new AddClassToStudentPanel(currentStudent, 1, choose);
				}				
			}
		});

		btnRemoveSem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choose = semOneCourse.getSelectedRow();
				currentStudent.removeCourse(1, choose + 1);	
				
				StudentPanel studentPanel = new StudentPanel(currentStudent, 1);
				AppMainWindow.contentPane.removeAll();
				AppMainWindow.contentPane.add(studentPanel);
				AppMainWindow.contentPane.updateUI();
			}
		});

		btnChangeCourse2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choose = semTwoCourse.getSelectedRow();
				if(choose == -1){
					JOptionPane.showMessageDialog (new JPanel(), "Please choose a period first!",
							"SORRY", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					AddClassToStudentPanel acsp = new AddClassToStudentPanel(currentStudent, 2, choose);
				}			
			}
		});

		btnRemoveSem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choose = semTwoCourse.getSelectedRow();
				currentStudent.removeCourse(2, choose + 1);
				
				StudentPanel studentPanel = new StudentPanel(currentStudent, 2);
				AppMainWindow.contentPane.removeAll();
				AppMainWindow.contentPane.add(studentPanel);
				AppMainWindow.contentPane.updateUI();
			}
		});
		
		StudentInfoPanel.btnChangePhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileDialog openDialog = new FileDialog (new JFrame(), "Open a new file", FileDialog.LOAD);
	            openDialog.setVisible (true);
	            String fileName = openDialog.getFile ();
	            String dir = openDialog.getDirectory ();
				currentStudent.setPhoto(dir + fileName);		
				
				StudentInfoPanel newInfoPane=  new StudentInfoPanel(currentStudent);
				leftPanel.remove(studentInfoPane);
				leftPanel.add(newInfoPane, BorderLayout.CENTER);
				leftPanel.updateUI();
			}
		});
	}
}
