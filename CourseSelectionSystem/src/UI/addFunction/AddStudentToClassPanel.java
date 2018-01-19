package UI.addFunction;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Database;
import data.Student;
import data.Class;

import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import UI.AppMainWindow;
import UI.ClassPanel;
import UI.MyButton;

import javax.swing.JButton;

public class AddStudentToClassPanel{

	private JPanel contentPanel = new JPanel();
	private JFrame frame;
	private JTextField textFieldName;
	private JTextField textFieldID;
	private JScrollPane sp1;
	private JScrollPane sp2;
	private JPanel infoNamePane;
	private JPanel infoIDPane;
	private MyButton btnFindName;
	private MyButton btnFindID;
	private MyButton btnCancel;
	private MyButton btnConfirm;
	private JList studentList;
	private JList foundStudent;
	private Class currentClass;

	
	/**
	 * Create the dialog.
	 */
	public AddStudentToClassPanel(Class currentClass) {
		this.currentClass = currentClass;
		initialize();
		addListener();
	}

	private void initialize(){
		frame = new JFrame();
		frame.setTitle("Add student to class");
		frame.setPreferredSize(new Dimension(600, 450));
		frame.setLocation(200, 200);
		frame.setBounds(100, 100, 600, 450);

		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));

		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(tabbedPane);
			{
				JPanel fromDatabasePane = new JPanel();
				fromDatabasePane.setBackground(Color.WHITE);
				tabbedPane.addTab("Choose from database", null, fromDatabasePane, null);
				fromDatabasePane.setLayout(null);

				JLabel lblChooseFromDatabase = new JLabel(" Choose From Database");
				lblChooseFromDatabase.setForeground(new Color(154, 205, 50));
				lblChooseFromDatabase.setFont(new Font("Times New Roman", Font.BOLD, 22));
				lblChooseFromDatabase.setBounds(10, 10, 232, 29);
				fromDatabasePane.add(lblChooseFromDatabase);

				JPanel panel = new JPanel();
				panel.setBounds(49, 49, 453, 260);
				panel.setLayout(new GridLayout(1, 0, 0, 0));
				ScrollPane sp = new ScrollPane();	
				studentList = Database.generateStudentJList();
				studentList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
				studentList.setVisibleRowCount(-1);
				sp.add(studentList);
				panel.add(sp);
				fromDatabasePane.add(panel);

				btnConfirm = new MyButton("Confirm");
				btnConfirm.setForeground(new Color(46, 139, 87));
				btnConfirm.setBounds(302, 327, 93, 23);
				fromDatabasePane.add(btnConfirm);

				btnCancel = new MyButton("Cancel");
				btnCancel.setForeground(new Color(46, 139, 87));
				btnCancel.setBounds(420, 327, 93, 23);
				fromDatabasePane.add(btnCancel);


			}

			{
				JPanel searchNamePane = new JPanel();
				searchNamePane.setBackground(Color.WHITE);
				tabbedPane.addTab("Search By Name", null, searchNamePane, null);
				searchNamePane.setLayout(null);
				{
					JLabel lblSearchStudentBy = new JLabel(" Search Student By");
					lblSearchStudentBy.setBounds(10, 10, 192, 29);
					lblSearchStudentBy.setForeground(new Color(154, 205, 50));
					lblSearchStudentBy.setFont(new Font("Times New Roman", Font.BOLD, 22));
					searchNamePane.add(lblSearchStudentBy);
				}

				textFieldName = new JTextField();
				textFieldName.setBounds(55, 60, 357, 21);
				searchNamePane.add(textFieldName);
				textFieldName.setColumns(10);

				btnFindName = new MyButton("Find");
				btnFindName.setForeground(new Color(46, 139, 87));
				btnFindName.setBounds(433, 59, 82, 23);
				searchNamePane.add(btnFindName);

				JLabel lblName = new JLabel("Name");
				lblName.setForeground(Color.BLACK);
				lblName.setFont(new Font("Times New Roman", Font.BOLD, 22));
				lblName.setBounds(195, 10, 67, 29);
				searchNamePane.add(lblName);

				infoNamePane = new JPanel();
				infoNamePane.setBackground(new Color(255, 250, 250));
				infoNamePane.setBounds(55, 101, 428, 218);
				searchNamePane.add(infoNamePane);
				infoNamePane.setLayout(new GridLayout(1, 0, 0, 0));
				
				JButton btnConfirmName = new JButton("Confirm");
				btnConfirmName.setBounds(459, 331, 98, 28);
				searchNamePane.add(btnConfirmName);
			}

			{
				JPanel searchIDPane = new JPanel();
				searchIDPane.setForeground(new Color(46, 139, 87));
				searchIDPane.setBackground(Color.WHITE);
				tabbedPane.addTab("Search By ID", null, searchIDPane, null);
				searchIDPane.setLayout(null);

				textFieldID = new JTextField();
				textFieldID.setColumns(10);
				textFieldID.setBounds(55, 60, 357, 21);
				searchIDPane.add(textFieldID);

				btnFindID = new MyButton("Find");
				btnFindID.setForeground(new Color(46, 139, 87));
				btnFindID.setBounds(433, 59, 82, 23);
				searchIDPane.add(btnFindID);

				JLabel label = new JLabel(" Search Student By");
				label.setForeground(new Color(154, 205, 50));
				label.setFont(new Font("Times New Roman", Font.BOLD, 22));
				label.setBounds(10, 10, 192, 29);
				searchIDPane.add(label);

				JLabel lblStudentId = new JLabel("Student ID");
				lblStudentId.setForeground(Color.BLACK);
				lblStudentId.setFont(new Font("Times New Roman", Font.BOLD, 22));
				lblStudentId.setBounds(195, 10, 115, 29);
				searchIDPane.add(lblStudentId);

				infoIDPane = new JPanel();
				infoIDPane.setBackground(new Color(255, 250, 250));
				infoIDPane.setBounds(55, 100, 428, 219);
				searchIDPane.add(infoIDPane);
				infoIDPane.setLayout(new GridLayout(1, 0, 0, 0));
				
				JButton btnConfirmID = new JButton("Confirm");
				btnConfirmID.setBounds(459, 331, 98, 28);
				searchIDPane.add(btnConfirmID);
			}
		}

		frame.getContentPane().add(contentPanel);
		frame.pack();
		frame.setVisible(true);
	}


	private void addListener(){

		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student selectedStudent = (Student) studentList.getSelectedValue();
				selectedStudent.addCourse(currentClass);
				frame.dispose();
				
				ClassPanel classPanel = new ClassPanel(currentClass, 1);
				AppMainWindow.contentPane.removeAll();
				AppMainWindow.contentPane.add(classPanel);
				AppMainWindow.contentPane.updateUI();
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		btnFindName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foundStudent = Database.searchStudent(textFieldName.getText(), "by name", "");
				sp1 = new JScrollPane(foundStudent);	
				sp1.setBorder(null);
				infoNamePane.removeAll();
				infoNamePane.add(sp1);
				textFieldName.setText("");
				infoNamePane.updateUI();
				
				foundStudent.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {
							Student selectedStudent = (Student) foundStudent.getSelectedValue();
							selectedStudent.addCourse(currentClass);
							frame.dispose();
							ClassPanel classPanel = new ClassPanel(currentClass, 1);
							AppMainWindow.contentPane.removeAll();
							AppMainWindow.contentPane.add(classPanel);
							AppMainWindow.contentPane.updateUI();
						}
					}
				});
			}
		});
		
		btnFindID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foundStudent = Database.searchStudent(textFieldID.getText(), "by ID", "");
				sp2 = new JScrollPane(foundStudent);	
				sp2.setBorder(null);
				infoIDPane.removeAll();
				infoIDPane.add(sp2);
				textFieldID.setText("");
				infoIDPane.updateUI();
				
				foundStudent.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {
							Student selectedStudent = (Student) foundStudent.getSelectedValue();
							selectedStudent.addCourse(currentClass);
							frame.dispose();
							ClassPanel classPanel = new ClassPanel(currentClass, 1);
							AppMainWindow.contentPane.removeAll();
							AppMainWindow.contentPane.add(classPanel);
							AppMainWindow.contentPane.updateUI();
						}
					}
				});			
			}
		});		

		studentList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					
					Student selectedStudent = (Student) studentList.getSelectedValue();
					selectedStudent.addCourse(currentClass);
					frame.dispose();
					
					ClassPanel classPanel = new ClassPanel(currentClass, 1);
					AppMainWindow.contentPane.removeAll();
					AppMainWindow.contentPane.add(classPanel);
					AppMainWindow.contentPane.updateUI();
				}
			}
		});
	}
}
