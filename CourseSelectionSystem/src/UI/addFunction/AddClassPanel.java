package UI.addFunction;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import UI.MyButtonSolid;
import data.Class;
import data.Database;

import java.awt.Color;
import java.awt.Font;

public class AddClassPanel implements ActionListener{

	private JFrame frame;
	private JPanel contentPane;
	JLabel label,labelTitle1,labelTitle2,labelA,labelB;
	JTextField textField,textA, textB;
	MyButtonSolid open;
	String fileName;
    String dir;
    private JTextField textC;
    private JTextField textD;


	
	public AddClassPanel(){
		frame = new JFrame();
		frame.setTitle("Add a class");
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
		open.setBounds(321, 77, 89, 29);
		open.setActionCommand ("openAFile");
		open.addActionListener(this);
		panel.add(open);
		
		textField = new JTextField();
		textField.setBounds(22, 77, 290, 29);
		textField.setEditable(false);
		panel.add(textField);
		textField.setColumns(10);
		

		MyButtonSolid btnYes = new MyButtonSolid("YES");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.readFile(textField.getText());
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
		btnNo.setBounds(221, 149, 117, 29);
		panel.add(btnNo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Entre Manually", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblCourseCode = new JLabel("Course Code");
		lblCourseCode.setBounds(50, 32, 90, 16);
		panel_1.add(lblCourseCode);
		
		textA = new JTextField();
		textA.setBounds(181, 27, 130, 26);
		panel_1.add(textA);
		textA.setColumns(10);
		
		JLabel lblClassLimit = new JLabel("Class Limit");
		lblClassLimit.setBounds(50, 70, 90, 16);
		panel_1.add(lblClassLimit);
		
		textB = new JTextField();
		textB.setColumns(10);
		textB.setBounds(181, 65, 130, 26);
		panel_1.add(textB);
		
		MyButtonSolid btnConfirm = new MyButtonSolid("Confirm");
		btnConfirm.setBounds(50, 177, 117, 29);
		btnConfirm.setActionCommand ("confirm");
		btnConfirm.addActionListener(this);
		panel_1.add(btnConfirm);
		
		MyButtonSolid btnCancel = new MyButtonSolid("Cancel");
		btnCancel.setBounds(193, 177, 117, 29);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);           
				frame.dispose();
			}
		});
		panel_1.add(btnCancel);
		
		textC = new JTextField();
		textC.setColumns(10);
		textC.setBounds(181, 102, 130, 26);
		panel_1.add(textC);
		
		JLabel lblPeriod = new JLabel("Period");
		lblPeriod.setBounds(50, 107, 90, 16);
		panel_1.add(lblPeriod);
		
		JLabel lblbetween = new JLabel("(Between 1 - 4)");
		lblbetween.setFont(new Font("Dialog", Font.BOLD, 10));
		lblbetween.setBounds(329, 106, 92, 22);
		panel_1.add(lblbetween);
		
		textD = new JTextField();
		textD.setColumns(10);
		textD.setBounds(181, 139, 130, 26);
		panel_1.add(textD);
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setBounds(50, 144, 90, 16);
		panel_1.add(lblSemester);
		
		JLabel lblOr = new JLabel("(1 or 2)");
		lblOr.setFont(new Font("Dialog", Font.BOLD, 10));
		lblOr.setBounds(329, 143, 92, 22);
		panel_1.add(lblOr);
		
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
            dir = openDialog.getDirectory ();
            textField.setText(dir + fileName);
        }
        if (eventName.equals ("confirm"))
        {
            String className = textA.getText()+".txt";
            int limit = Integer.parseInt(textB.getText());
            int period = Integer.parseInt(textC.getText());       
            int semester = Integer.parseInt(textD.getText());
            //check if exist
            try {
            	 if(limit < 1 || period < 1 || period > 4 || (semester != 1 && semester != 2))
                 	throw new Exception();
                Class c = new Class(textA.getText(),limit, period, semester);
                Database.addClass(c);
                frame.dispose();
                JOptionPane.showMessageDialog (contentPane, "SUCCESSFULLY ADDED",
                        "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog (contentPane, "Wrong Format!",
                        "ERROR", JOptionPane.INFORMATION_MESSAGE);
                textA.setText("");
                textB.setText("");
                textC.setText("");
                textD.setText("");
            }
            
        }
        
    }
}
 
 

	
