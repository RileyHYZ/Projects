package UI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.border.MatteBorder;

import data.Database;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchStudentPanel extends JPanel {
	public static JPanel centerPanel;
	private JPanel upperPanel;
	private JPanel leftPanel;
	private JPanel namePane;
	private JPanel studentIDPane;
	private JPanel infoPanel;
	private JPanel infoPanel_2;
	private JScrollPane scrollPanel;
	public static MyButtonSolid btnByName;
	public static MyButtonSolid btnByID;
	private MyButtonSolid btnBack;
	private MyButtonSolid btnFind;
	private MyButtonSolid btnFind_2;
	private JTextField textField;
	private JTextField textField_2;
	public static CardLayout CL;
	private JList foundStudent;

	/**
	 * Create the panel.
	 */
	public SearchStudentPanel() {		
		initialize();	
		addListener();

	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		getUpperPanel();
		getLeftPanel();
		getCenterPanel();
	}

	private void getUpperPanel() {
		upperPanel = new JPanel();
		upperPanel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(154, 205, 50)));
		upperPanel.setBackground(Color.WHITE);
		upperPanel.setPreferredSize(new Dimension(10, 40));
		upperPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		upperPanel.add(panel, BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JLabel lblSearchStudent = new JLabel(" Search Student");
		lblSearchStudent.setForeground(new Color(154, 205, 50));
		lblSearchStudent.setFont(new Font("Times New Roman", Font.BOLD, 25));
		panel.add(lblSearchStudent);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		upperPanel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		btnBack = new MyButtonSolid("Back");
		panel_1.add(btnBack);

		add(upperPanel, BorderLayout.NORTH);		
	}

	private void getLeftPanel() {
		leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(0, 1, 0, 0));

		btnByName = new MyButtonSolid("Student");
		btnByName.setText("By Name");
		btnByName.setBackground(new Color(154, 205, 50));
		btnByName.setEnabled(true);
		leftPanel.add(btnByName);

		btnByID = new MyButtonSolid("Teacher");
		btnByID.setText("By Student ID");
		btnByID.setBackground(new Color(154, 205, 50));
		leftPanel.add(btnByID);

		add(leftPanel, BorderLayout.WEST);	
	}

	private void getCenterPanel() {
		centerPanel = new JPanel();	
		centerPanel.setBackground(Color.WHITE);
		CL = new CardLayout();
		centerPanel.setLayout(CL);

		namePane = new JPanel();
		namePane.setBackground(Color.WHITE);
		namePane.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setPreferredSize(new Dimension(10, 110));
		namePane.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_2.add(panel_3, BorderLayout.CENTER);

		JLabel lbByName = new JLabel(" Search Student By Name");
		lbByName.setForeground(Color.BLACK);
		lbByName.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel_3.add(lbByName);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setPreferredSize(new Dimension(10, 50));
		panel_2.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		textField = new JTextField();
		panel_4.add(textField);
		textField.setColumns(30);

		btnFind = new MyButtonSolid("Back");
		btnFind.setText("Find");
		panel_4.add(btnFind);

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		panel_2.add(rigidArea, BorderLayout.NORTH);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(113, 380));
		namePane.add(rigidArea_2, BorderLayout.WEST);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(99, 380));
		namePane.add(rigidArea_3, BorderLayout.EAST);

		studentIDPane = new JPanel();
		studentIDPane.setBackground(Color.WHITE);
		studentIDPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(10, 110));
		panel_5.setBackground(Color.WHITE);
		studentIDPane.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		panel_5.add(rigidArea_1, BorderLayout.NORTH);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_5.add(panel_6, BorderLayout.CENTER);
		
		infoPanel = new JPanel();
		infoPanel.setBackground(Color.WHITE);
		namePane.add(infoPanel, BorderLayout.CENTER);
		infoPanel.setLayout(new GridLayout(1, 0, 0, 0));
	

		JLabel lblByID = new JLabel(" Search Student By Student ID Number");
		lblByID.setForeground(Color.BLACK);
		lblByID.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel_6.add(lblByID);

		JPanel panel_7 = new JPanel();
		panel_7.setPreferredSize(new Dimension(10, 50));
		panel_7.setBackground(Color.WHITE);
		panel_5.add(panel_7, BorderLayout.SOUTH);

		textField_2 = new JTextField();
		textField_2.setColumns(30);
		panel_7.add(textField_2);

		btnFind_2 = new MyButtonSolid("Find");
		panel_7.add(btnFind_2);
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(113, 370));
		studentIDPane.add(rigidArea_4, BorderLayout.WEST);

		Component rigidArea_5 = Box.createRigidArea(new Dimension(111, 380));
		studentIDPane.add(rigidArea_5, BorderLayout.EAST);
		
		infoPanel_2 = new JPanel();
		infoPanel_2.setBackground(Color.WHITE);
		studentIDPane.add(infoPanel_2, BorderLayout.CENTER);
		infoPanel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		centerPanel.add(namePane, "namePane");	
		centerPanel.add(studentIDPane, "studentIDPane");
		
		add(centerPanel, BorderLayout.CENTER);
	}

	private void addListener(){
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_2.setText("");
				infoPanel.removeAll();
				infoPanel_2.removeAll();
				AppMainWindow.contentPane.removeAll();
				AppMainWindow.contentPane.add(AppMainWindow.mainPanel);
				AppMainWindow.contentPane.updateUI();
			}
		});

		btnByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnByName.setEnabled(true);
				btnByID.setEnabled(false);
				CL.show(centerPanel, "namePane");
				textField_2.setText("");
				infoPanel_2.removeAll();
			}
		});

		btnByID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnByName.setEnabled(false);
				btnByID.setEnabled(true);
				textField.setText("");
				infoPanel.removeAll();
				CL.show(centerPanel, "studentIDPane");
			}
		});

		btnFind.addActionListener(new ActionListener() {	//Find by name
			public void actionPerformed(ActionEvent e) {
				scrollPanel = new JScrollPane(Database.searchStudent(textField.getText(), "by name", "enter a student panel"));	
				scrollPanel.setBorder(null);
				infoPanel.removeAll();
				infoPanel.add(scrollPanel);
				textField.setText("");
				namePane.updateUI();
			}
		});
		
		btnFind_2.addActionListener(new ActionListener() {	//Find by ID
			public void actionPerformed(ActionEvent e) {
				scrollPanel = new JScrollPane(Database.searchStudent(textField_2.getText(), "by ID", "enter a student panel"));	
				scrollPanel.setBorder(null);
				infoPanel_2.removeAll();
				infoPanel_2.add(scrollPanel);
				textField_2.setText("");
				studentIDPane.updateUI();
				
			}
		});
	}
}
