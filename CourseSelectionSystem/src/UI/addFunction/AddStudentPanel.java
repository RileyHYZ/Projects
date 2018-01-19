
package UI.addFunction;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import UI.MyButtonSolid;
import data.Class;
import data.Database;
import data.Student;

import java.awt.Color;

public class AddStudentPanel implements ActionListener{

	private JFrame frame;
	private JPanel contentPane;
	JLabel label,labelTitle1,labelTitle2,labelA,labelB;
	JTextField textField,textA, textB;
	MyButtonSolid open;
	String fileName;
	String dir;


	public AddStudentPanel(){
		frame = new JFrame();
		frame.setTitle("Add a student");
		frame.setPreferredSize(new Dimension(450, 300));
		frame.setLocation(200, 200);

		//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 438, 266);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Open a File", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Open a File");
		lblNewLabel.setBounds(159, 37, 111, 16);
		panel.add(lblNewLabel);

		open = new MyButtonSolid("OPEN");
		open.setBounds(324, 76, 90, 29);
		open.setActionCommand ("openAFile");
		open.addActionListener(this);
		panel.add(open);

		textField = new JTextField();
		textField.setBounds(22, 76, 290, 30);
		textField.setEditable(false);
		panel.add(textField);
		textField.setColumns(10);


		MyButtonSolid btnYes = new MyButtonSolid("YES");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Student.readFile(textField.getText());
					frame.dispose();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnYes.setBounds(70, 149, 117, 29);
		panel.add(btnYes);

		MyButtonSolid btnNo = new MyButtonSolid("NO");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNo.setBounds(230, 149, 117, 29);
		panel.add(btnNo);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Enter Manually", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblCourseCode = new JLabel("Student Name");
		lblCourseCode.setBounds(77, 61, 90, 16);
		panel_1.add(lblCourseCode);

		textA = new JTextField();
		textA.setBounds(208, 51, 130, 26);
		panel_1.add(textA);
		textA.setColumns(10);

		JLabel lblClassLimit = new JLabel("Student ID");
		lblClassLimit.setBounds(77, 89, 90, 16);
		panel_1.add(lblClassLimit);

		textB = new JTextField();
		textB.setColumns(10);
		textB.setBounds(208, 84, 130, 26);
		panel_1.add(textB);

		MyButtonSolid btnConfirm = new MyButtonSolid("Confirm");
		btnConfirm.setBounds(72, 152, 117, 29);
		btnConfirm.setActionCommand ("confirm");
		btnConfirm.addActionListener(this);
		panel_1.add(btnConfirm);

		MyButtonSolid btnCancel = new MyButtonSolid("Cancel");
		btnCancel.setBounds(221, 152, 117, 29);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//				frame.setVisible(false);           
				frame.dispose();
			}
		});
		panel_1.add(btnCancel);

		frame.getContentPane().add (contentPane);
		frame.pack ();
		frame.setVisible (true);
	}
	public void actionPerformed (ActionEvent event) {
		String eventName = event.getActionCommand();
		if (eventName.equals ("openAFile"))
		{
			FileDialog openDialog = new FileDialog (frame, "Open a new file", FileDialog.LOAD);
			openDialog.setVisible (true);
			fileName = openDialog.getFile ();
			String dir = openDialog.getDirectory ();
			textField.setText(dir + fileName);            
		}
		if (eventName.equals ("confirm"))
		{
			String studentName = textA.getText();
			String studentID = textB.getText();
			//check if exist
			try {
				int test = Integer.parseInt(studentID);
				Student s = new Student(studentName,studentID);
				Database.addStudent(s);
				frame.dispose();
				JOptionPane.showMessageDialog (contentPane, "SUCCESSFULLY ADDED",
						"SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog (contentPane, "Wrong Format!",
						"ERROR", JOptionPane.INFORMATION_MESSAGE);
				textA.setText("");
				textB.setText("");
			}
		}
	}
}
