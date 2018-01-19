package UI.addFunction;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Database;
import data.Student;
import data.Class;

import java.awt.GridLayout;

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
import UI.MyButton;
import UI.StudentPanel;

public class AddClassToStudentPanel{

	private JPanel contentPanel = new JPanel();
	private JFrame frame;
	private MyButton btnCancel;
	private MyButton btnConfirm;
	private JList availClass;
	private int period;
	private int semester;
	private Student currentStudent;

	/**
	 * Create the dialog.
	 */
	public AddClassToStudentPanel(Student selectedStudent, int semester, int period) {
		this.period = period + 1;
		this.semester = semester;
		this.currentStudent = selectedStudent;

		initialize();
		addListener();
	}

	private void initialize(){
		frame = new JFrame();
		frame.setTitle("Change Course for Student");
		frame.setPreferredSize(new Dimension(600, 450));
		frame.setLocation(200, 200);
		frame.setBounds(100, 100, 600, 450);

		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));

		frame.getContentPane().add(contentPanel);
		JPanel fromDatabasePane = new JPanel();
		contentPanel.add(fromDatabasePane);
		fromDatabasePane.setBackground(Color.WHITE);
		fromDatabasePane.setLayout(null);

		JLabel lblChooseFromDatabase = new JLabel(" Choose From Database");
		lblChooseFromDatabase.setForeground(new Color(154, 205, 50));
		lblChooseFromDatabase.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblChooseFromDatabase.setBounds(30, 24, 232, 29);
		fromDatabasePane.add(lblChooseFromDatabase);

		JPanel panel = new JPanel();
		panel.setBounds(69, 63, 453, 260);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		availClass = Database.generateClassJList(semester, period);
		JScrollPane sp = new JScrollPane(availClass);	
		panel.add(sp);
		fromDatabasePane.add(panel);

		btnConfirm = new MyButton("Confirm");
		btnConfirm.setForeground(new Color(0, 128, 0));
		btnConfirm.setBounds(322, 341, 93, 23);
		fromDatabasePane.add(btnConfirm);

		btnCancel = new MyButton("Cancel");
		btnCancel.setForeground(new Color(0, 128, 0));
		btnCancel.setBounds(440, 341, 93, 23);
		fromDatabasePane.add(btnCancel);
		frame.pack();
		frame.setVisible(true);
	}


	private void addListener(){

		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Class c = (Class) availClass.getSelectedValue();
				currentStudent.addCourse(c);
				frame.dispose();

				StudentPanel studentPanel = new StudentPanel(currentStudent, semester);			
				AppMainWindow.contentPane.removeAll();
				AppMainWindow.contentPane.add(studentPanel);
				AppMainWindow.contentPane.updateUI();
			}	
		});


		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		availClass.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {			
					Class c = (Class) availClass.getSelectedValue();
					currentStudent.addCourse(c);
					frame.dispose();

					StudentPanel studentPanel = new StudentPanel(currentStudent, semester);
					AppMainWindow.contentPane.removeAll();
					AppMainWindow.contentPane.add(studentPanel);
					AppMainWindow.contentPane.updateUI();
				}
			}
		});
	}
}
