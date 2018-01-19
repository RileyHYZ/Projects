package UI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.FileDialog;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.border.MatteBorder;
import UI.addFunction.AddStudentToClassPanel;
import UI.addFunction.AddTeacherToClassPanel;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import data.Class;
import data.Database;
import data.Student;
import data.Teacher;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ClassPanel extends JPanel {

	private JPanel centerPanel;
	private JPanel upperPanel;
	private JPanel leftPanel;
	private JPanel studentPane;
	private JPanel teacherPane;
	private JPanel insaideStudentInfoPane;
	private MyButtonSolid btnStudent;
	private MyButtonSolid btnTeacher;
	private MyButtonSolid btnBack;
	private MyButton btnAddStudent;
	private MyButton btnRemoveStudent;
	private MyButtonSolid btnChangeTeacher;
	private CardLayout CL;
	private String[][] tableData;
	private JTable studentTable;
	private Teacher teacher;
	private int selectedStudent;

	private Class currentClass;
	private JPanel studentInfoContainer;
	private JPanel teacherInfoContainer;
	private int show;
	/**
	 * Create the panel.
	 */
	public ClassPanel(Class currentClass, int show) {
		this.currentClass = currentClass;
		this.tableData = currentClass.generateTableData();
		this.teacher = currentClass.getTeacher();
		this.show = show;

		setLayout(new BorderLayout(0, 0));
		initialize();	
		addListener();
	}

	private void initialize() {
		getUpperPanel();
		getLeftPanel();
		getCenterPanel();
	}


	private void getUpperPanel(){
		upperPanel = new JPanel();
		upperPanel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(154, 205, 50)));
		upperPanel.setBackground(Color.WHITE);
		upperPanel.setPreferredSize(new Dimension(10, 40));
		upperPanel.setLayout(new BorderLayout(0, 0));

		JPanel classCodePane = new JPanel();
		classCodePane.setBackground(Color.WHITE);
		upperPanel.add(classCodePane, BorderLayout.WEST);
		classCodePane.setLayout(new BoxLayout(classCodePane, BoxLayout.X_AXIS));

		JLabel classCode = new JLabel(currentClass.getCourseCode());
		classCode.setForeground(new Color(154, 205, 50));
		classCode.setFont(new Font("Times New Roman", Font.BOLD, 25));
		classCodePane.add(classCode);

		JPanel backPane = new JPanel();
		backPane.setBackground(Color.WHITE);
		upperPanel.add(backPane, BorderLayout.EAST);
		backPane.setLayout(new BoxLayout(backPane, BoxLayout.X_AXIS));

		btnBack = new MyButtonSolid("Back");		
		backPane.add(btnBack);

		add(upperPanel, BorderLayout.NORTH);
	}

	private void getLeftPanel() {
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(100, 10));
		leftPanel.setLayout(new GridLayout(0, 1, 0, 0));

		btnStudent = new MyButtonSolid("Student");
		btnStudent.setBackground(new Color(154, 205, 50));
		leftPanel.add(btnStudent);

		btnTeacher = new MyButtonSolid("Teacher");
		btnTeacher.setBackground(new Color(154, 205, 50));
		leftPanel.add(btnTeacher);

		add(leftPanel, BorderLayout.WEST);
	}

	private void getCenterPanel() {
		centerPanel = new JPanel();
		CL = new CardLayout();
		centerPanel.setLayout(CL);

		//Student panel
		studentPane = new JPanel();
		studentPane.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel studentListPane = new JPanel();
		studentListPane.setBackground(Color.WHITE);
		studentPane.add(studentListPane);
		studentListPane.setLayout(new BorderLayout(0, 0));

		JLabel studentList = new JLabel("   Student List");
		studentList.setPreferredSize(new Dimension(68, 35));
		studentList.setForeground(new Color(154, 205, 50));
		studentList.setFont(new Font("Calibri", Font.BOLD, 20));
		studentListPane.add(studentList, BorderLayout.NORTH);

		//table
		studentTable = new JTable(tableData, new String[]{"Student Name", "Student ID"});
		studentTable.getColumnModel().getColumn(0).setPreferredWidth(130);
		studentTable.getTableHeader().setBackground(new Color(200,200,200));		
		studentTable.setRowHeight(20);
		studentTable.setSelectionBackground(new Color(102, 204, 204));
		JScrollPane scrollPane = new JScrollPane(studentTable);
		scrollPane.setBackground(Color.WHITE);
		studentListPane.add(scrollPane, BorderLayout.CENTER);

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		studentListPane.add(rigidArea, BorderLayout.WEST);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		studentListPane.add(rigidArea_2, BorderLayout.EAST);

		JPanel buttonBar = new JPanel();
		buttonBar.setPreferredSize(new Dimension(10, 40));
		buttonBar.setBackground(Color.WHITE);
		studentListPane.add(buttonBar, BorderLayout.SOUTH);

		btnAddStudent = new MyButton("Add Student");
		btnAddStudent.setForeground(new Color(154, 205, 50));
		buttonBar.add(btnAddStudent);

		btnRemoveStudent = new MyButton("Remove Student");
		btnRemoveStudent.setForeground(new Color(154, 205, 50));
		buttonBar.add(btnRemoveStudent);

		JPanel studentInfoPane = new JPanel();
		studentInfoPane.setBackground(Color.WHITE);
		studentPane.add(studentInfoPane);
		studentInfoPane.setLayout(new BorderLayout(0, 0));

		JLabel studentInfo = new JLabel("  Student Info");
		studentInfo.setPreferredSize(new Dimension(68, 35));
		studentInfo.setForeground(new Color(154, 205, 50));
		studentInfo.setFont(new Font("Calibri", Font.BOLD, 20));
		studentInfoPane.add(studentInfo, BorderLayout.NORTH);

		insaideStudentInfoPane = new JPanel();
		insaideStudentInfoPane.setBackground(Color.WHITE);
		studentInfoPane.add(insaideStudentInfoPane, BorderLayout.CENTER);
		insaideStudentInfoPane.setLayout(new BorderLayout(0, 0));
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(330, 38));
		insaideStudentInfoPane.add(rigidArea_1, BorderLayout.SOUTH);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		insaideStudentInfoPane.add(rigidArea_3, BorderLayout.EAST);
		
		studentInfoContainer = new JPanel();
		studentInfoContainer.setBackground(new Color(255, 250, 250));
		insaideStudentInfoPane.add(studentInfoContainer, BorderLayout.CENTER);
		studentInfoContainer.setLayout(new GridLayout(1, 0, 0, 0));


		//Teacher Panel
		teacherPane = new JPanel();
		teacherPane.setBackground(new Color(255, 250, 250));	
		teacherPane.setLayout(new BorderLayout(0, 0));

		JLabel teacherInfo = new JLabel("  Teacher Info");
		teacherInfo.setPreferredSize(new Dimension(68, 60));
		teacherInfo.setForeground(new Color(154, 205, 50));
		teacherInfo.setFont(new Font("Calibri", Font.BOLD, 20));
		teacherPane.add(teacherInfo, BorderLayout.NORTH);

		JPanel teacherInfoPane = new JPanel();
		teacherInfoPane.setBackground(new Color(255, 250, 250));
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(58, 380));
		teacherPane.add(rigidArea_4, BorderLayout.WEST);
		
		Component rigidArea_5 = Box.createRigidArea(new Dimension(49, 360));
		teacherPane.add(rigidArea_5, BorderLayout.EAST);
		
		JPanel menuBarPanel = new JPanel();
		menuBarPanel.setBackground(new Color(255, 250, 250));
		menuBarPanel.setPreferredSize(new Dimension(10, 60));
		teacherPane.add(menuBarPanel, BorderLayout.SOUTH);
		menuBarPanel.setLayout(null);
		
		btnChangeTeacher = new MyButtonSolid("Back");
		btnChangeTeacher.setBounds(482, 12, 131, 25);
		btnChangeTeacher.setText("Change Teacher");
		menuBarPanel.add(btnChangeTeacher);
		
		teacherInfoContainer = new JPanel();
		teacherInfoContainer.setBorder(new LineBorder(new Color(154, 205, 50)));
		teacherInfoContainer.setBackground(new Color(255, 250, 250));		
		teacherInfoContainer.setLayout(new GridLayout(1, 0, 0, 0));
		
		//If current class has no teacher, remind the user to set a teacher
		//If current class has a teacher, display the information of that teacher
		if(teacher.getName().equals("None")){
			JLabel warning = new JLabel("Get a teache for this class!");
			warning.setFont(new Font("Dialog", Font.PLAIN, 18));
			warning.setHorizontalAlignment(SwingConstants.CENTER);
			teacherInfoContainer.add(warning);
		}
		else{
			//create a teacher info panel based on current class' teacher
			TeacherInfoPanel teacherInfoPanel = new TeacherInfoPanel(teacher);
			teacherInfoContainer.add(teacherInfoPanel);
			
			//Make change photo workable for teacher
			TeacherInfoPanel.btnChangeTeacherPhoto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FileDialog openDialog = new FileDialog (new JFrame(), "Open a new file", FileDialog.LOAD);
		            openDialog.setVisible (true);
		            String fileName = openDialog.getFile ();
		            String dir = openDialog.getDirectory ();
					teacher.setPhoto(dir + fileName);
					
					TeacherInfoPanel newTeacherInfo = new TeacherInfoPanel(teacher);
					teacherInfoContainer.removeAll();
					teacherInfoContainer.add(newTeacherInfo);
					teacherInfoContainer.updateUI();
				}
			});
		}		
		teacherPane.add(teacherInfoContainer, BorderLayout.CENTER);
		
		centerPanel.add(studentPane, "studentPane");
		centerPanel.add(teacherPane, "teacherPane");
		
		add(centerPanel, BorderLayout.CENTER);
		
		
		//Showing student info or teacher info
				if(show == 1){	//show student info
					btnStudent.setEnabled(true);
					btnTeacher.setEnabled(false);
					CL.show(centerPanel, "studentPane");
				}
				else{	//show teacher info
					btnStudent.setEnabled(false);
					btnTeacher.setEnabled(true);
					CL.show(centerPanel, "teacherPane");
				}
	}

	private void addListener() {
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnStudent.setEnabled(true);
				btnTeacher.setEnabled(false);
				CL.show(centerPanel, "studentPane");
			}
		});

		btnTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStudent.setEnabled(false);
				btnTeacher.setEnabled(true);
				CL.show(centerPanel, "teacherPane");
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppMainWindow.contentPane.removeAll();
				AppMainWindow.contentPane.add(AppMainWindow.mainPanel);
				AppMainWindow.contentPane.updateUI();
			}
		});

		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Database.students.size() < 1){
					JOptionPane.showMessageDialog (new JPanel(), "Database of student is empty! Please add student to database first!",
							"Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					AddStudentToClassPanel addStudent = new AddStudentToClassPanel(currentClass);
				}				
			}
		});

		btnRemoveStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choose = studentTable.getSelectedRow();
				currentClass.getStudentList().get(choose).removeCourse(currentClass);

				ClassPanel classPanel = new ClassPanel(currentClass, 1);
				AppMainWindow.contentPane.removeAll();
				AppMainWindow.contentPane.add(classPanel);
				AppMainWindow.contentPane.updateUI();

			}
		});

		studentTable.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					try{
						int choose = studentTable.getSelectedRow();
						Student s = currentClass.getStudentList().get(choose);
						StudentInfoPanel studentPane = new StudentInfoPanel(s);

						studentInfoContainer.removeAll();
						studentInfoContainer.add(studentPane);
						studentInfoContainer.updateUI();
						
						StudentInfoPanel.btnChangePhoto.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								int choose = studentTable.getSelectedRow();
								Student s = currentClass.getStudentList().get(choose);
								
								FileDialog openDialog = new FileDialog (new JFrame(), "Open a new file", FileDialog.LOAD);
					            openDialog.setVisible (true);
					            String fileName = openDialog.getFile ();
					            String dir = openDialog.getDirectory ();
								s.setPhoto(dir + fileName);		
								
								StudentInfoPanel newStudentInfoe=  new StudentInfoPanel(s);
								studentInfoContainer.removeAll();
								studentInfoContainer.add(newStudentInfoe);
								studentInfoContainer.updateUI();
								
							}
						});
					}
					catch(IndexOutOfBoundsException ex){
						System.out.println("IndexOutOfBoundsException");
					}
				}
			}
		});	
		
		btnChangeTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddTeacherToClassPanel attc = new AddTeacherToClassPanel(currentClass);
			}
		});
	}
}
