package data;


import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import UI.AppMainWindow;
import UI.ClassPanel;

public class Class {

	public static int numOfClass = 0;
	public int studentLimit;
	private int numOfStudent = 0;
	private String courseCode;
	private ArrayList<Student> studentList;
	private Teacher teacher = new Teacher();
	private int period;
	private int semester;

	private static Class selectedClass;

	public Class(String courseCode, int limit, int period, int semester) {
		this.courseCode = courseCode;
		this.studentLimit = limit;
		this.period = period;
		this.semester = semester;
		studentList = new ArrayList<Student>();
		
		numOfClass++;
	}
	
	public Class(int period){
		courseCode = "spare";
		studentLimit = 1;
		numOfStudent = 1;
		this.period = period;
	}
	
	public void addClass(){
		numOfClass++;
	}

	public String getCourseCode(){
		return courseCode;
	}

	public int getNumStudent(){
		return numOfStudent;
	}

	public void setTeacher(Teacher t){
		this.teacher = t;
	}

	public Teacher getTeacher(){
		return this.teacher;
	}

	public void addStudent(Student s){
		if(studentList.contains(s)){
			JOptionPane.showMessageDialog (new JPanel(), "Student is already in the class!",
					"SORRY", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			studentList.add(s);
			numOfStudent ++;
		}			
	}
	
	public int getPeriod() {
		return period;
	}

	public int getSemester() {
		return semester;
	}

	public ArrayList<Student> getStudentList(){
		return studentList;
	}
	
	public boolean equals(Class c){
		return this.courseCode.equals(c.courseCode);
	}

	public String toString(){
		return courseCode;
	}
	public String[][] generateTableData(){
		String[][] tableData; 

		Iterator iter = studentList.iterator();
		tableData = new String[studentLimit][2];

		for(int i = 0; i < studentList.size(); i ++){
			Student s = (Student) iter.next();
			tableData[i][0] = s.getName();
			tableData[i][1] = s.getID();
			
		}

		return tableData;
	}

	public static void chooseClass ()
	{
		if(Database.classes.size()<1){
			JOptionPane.showMessageDialog (new JPanel(), "Please add classes first!",
					"Error", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			Iterator iter = Database.classes.iterator();
			Class[] classes = new Class[Database.classes.size()];
			for(int i = 0; i < Database.classes.size(); i++){
				classes[i] = (Class) iter.next();
			}
			JList classList = new JList(classes);


			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1, 0, 0, 0));
			ScrollPane sp = new ScrollPane();				
			sp.add(classList);
			panel.add(sp);

			int choice = JOptionPane.showConfirmDialog (new JPanel(), panel,
					"Choose Class", JOptionPane.INFORMATION_MESSAGE);

			if(choice == 0){
				try{
					selectedClass = (Class) classList.getSelectedValue();
					ClassPanel classPanel = new ClassPanel(selectedClass, 1);
					AppMainWindow.contentPane.removeAll();
					AppMainWindow.contentPane.add(classPanel);
					AppMainWindow.contentPane.updateUI();
				}
				catch(NullPointerException e){
					JOptionPane.showMessageDialog (new JPanel(), "Select a class first!",
							"ERROR", JOptionPane.INFORMATION_MESSAGE);
					e.printStackTrace();
				}
			}
		}
	}

	public static void readFile(String fileName) throws IOException{
		try{
			Scanner in = new Scanner(new File(fileName));

			while(in.hasNextLine()){
				String classCode = in.nextLine().trim();
				int classLimit = Integer.parseInt(in.nextLine().trim());
				int period = Integer.parseInt(in.nextLine().trim());
				int semester = Integer.parseInt(in.nextLine().trim());
				if(classLimit < 1 || period < 1 || period > 4 || semester < 1 || semester > 2)
                 	throw new NumberFormatException();
				
				Class c = new Class(classCode, classLimit, period, semester);  				
				Database.classes.add(c);
			}
			JOptionPane.showMessageDialog (new JPanel(), "SUCCESSFULLY ADDED",
					"SUCCESS", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(FileNotFoundException e){
			JOptionPane.showMessageDialog (new JPanel(), "FILE NOT FOUND!!!!!!",
					"!!!", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog (new JPanel(), "FILE DOES NOT HAVE CORRECT FORMAT!",
					"!!!", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
}