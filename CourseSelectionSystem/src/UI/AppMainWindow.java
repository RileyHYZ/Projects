package UI;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import data.Student;
import data.Teacher;
import data.Class;

import java.awt.BorderLayout;


public class AppMainWindow {

	private JFrame frame;
	public static JPanel contentPane;
	public static MainMenuPanel mainPanel;
	public static StudentPanel studentPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMainWindow window = new AppMainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public AppMainWindow() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		Student.readFile(System.getProperty("user.dir") + File.separator + "student.txt");
		Class.readFile(System.getProperty("user.dir") + File.separator + "class.txt");
		Teacher.readFile(System.getProperty("user.dir") + File.separator + "teacher.txt");
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 780, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Langstaff Course Selection System");
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		mainPanel = new MainMenuPanel();
		
		contentPane.add(mainPanel);
		frame.getContentPane().add(contentPane);
		
	}
	
	private void addListener(){
		
	}
}
