package data;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import UI.AppMainWindow;
import UI.ClassPanel;
import UI.StudentPanel;

public class Database {
	public static HashSet<Class> classes = new HashSet();
	public static HashSet<Student> students = new HashSet();
	public static HashSet<Teacher> teachers = new HashSet();

	public static void addClass(Class c){
		classes.add(c);
	}

	public static void addStudent(Student s){
		students.add(s);
	}

	public static void addTeacher(Teacher t){
		teachers.add(t);
	}

	public static String[][] getStudentTable(){
		Iterator<Student> iter = students.iterator();
		String[][] students = new String[Database.students.size()][4];
		for(int i = 0; i < Database.students.size(); i++){
			Student s = (Student) iter.next();
			students[i][0] = s.getName();
			students[i][1] = s.getID();
			students[i][2] = "1. " + s.getSemOneCourse()[0] + " 2. " + s.getSemOneCourse()[1] +
					" 3. " + s.getSemOneCourse()[2] + " 4. " + s.getSemOneCourse()[3];
			students[i][3] = "1. " + s.getSemTwoCourse()[0] + " 2. " + s.getSemTwoCourse()[1] +
					" 3. " + s.getSemTwoCourse()[2] + " 4. " + s.getSemTwoCourse()[3];
		}
		return students;
	}
	
	public static String[][] getClassTable(){
		Iterator<Class> iter = classes.iterator();
		String[][] classes= new String[Database.classes.size()][5];
		for(int i = 0; i < Database.classes.size(); i++){
			Class c = (Class) iter.next();
			classes[i][0] = c.getCourseCode();
			classes[i][1] = c.getSemester() + "";
			classes[i][2] = c.getPeriod() + "";
			classes[i][3] = c.getTeacher().getName();
			classes[i][4] = c.getNumStudent() + "/" + c.studentLimit;
		}
		return classes;
	}
	
	public static String[][] getTeacherTable(){
		Iterator<Teacher> iter = teachers.iterator();
		String[][] teachers = new String[Database.teachers.size()][5];
		for(int i = 0; i < Database.teachers.size(); i++){
			Teacher t = (Teacher) iter.next();
			teachers[i][0] = t.getName();
			teachers[i][1] = t.getGender();
			teachers[i][2] = t.getAge() + "";
			teachers[i][3] = "1. " + t.getSemOneCourse()[0] + " 2. " + t.getSemOneCourse()[1] +
					" 3. " + t.getSemOneCourse()[2] + " 4. " + t.getSemOneCourse()[3];
			teachers[i][4] = "1. " + t.getSemTwoCourse()[0] + " 2. " + t.getSemTwoCourse()[1] +
					" 3. " + t.getSemTwoCourse()[2] + " 4. " + t.getSemTwoCourse()[3];
		}
		return teachers;
	}
	
	public static JList generateTeacherJList (){
		 
        Iterator<Teacher> iter = Database.teachers.iterator();
        Teacher[] teachers = new Teacher[Database.teachers.size()];
        for(int i = 0; i < Database.teachers.size(); i++){
            teachers[i] = (Teacher) iter.next();
        }
        JList teacherList = new JList(teachers);
        teacherList.setBackground(new Color(255, 255, 250));
        return teacherList;
 
    }

	public static JList generateStudentJList (){

		Iterator<Student> iter = students.iterator();
		Student[] students = new Student[Database.students.size()];
		for(int i = 0; i < Database.students.size(); i++){
			students[i] = (Student) iter.next();
		}
		JList studentList = new JList(students);
		studentList.setBackground(new Color(255, 255, 250));
		return studentList;

	}
	
	
	//generate a JList for all classes for certain semester and period
	public static JList<Class> generateClassJList(int sem, int period){
		Iterator<Class> iter = classes.iterator();
		ArrayList<Class> tempClasses = new ArrayList<Class>();
		while (iter.hasNext()){
			Class c = iter.next();
			if(c.getPeriod() == period && c.getSemester() == sem)
				tempClasses.add(c);	
		}
		
		Class[] classes = new Class[tempClasses.size()];
		for(int i = 0; i < tempClasses.size(); i++){
			classes[i] = tempClasses.get(i);
		}
		
		JList<Class> classList = new JList<Class>(classes);
		classList.setBackground(new Color(255, 255, 250));
		classList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		classList.setVisibleRowCount(-1);
		return classList;		
	}

	public static JList<Student> searchStudent(String input, String type, String ifEnter){		
		Iterator<Student> iter = students.iterator();
		ArrayList<Student> tempArray = new ArrayList();
		Student[] studentList;

		//search by name
		if(type.equals("by name")){
			while(iter.hasNext()){
				Student tempS = iter.next();
				if (tempS.getName().toLowerCase().contains(input.toLowerCase()))
					tempArray.add(tempS);
			}
		}
		//search by ID
		else{
			while(iter.hasNext()){
				Student tempS = iter.next();
				if(tempS.getID().equals(input))
					tempArray.add(tempS);
			}
		}

		//return the result as a JList
		if(tempArray.size() == 0){	//No result were found
			studentList = new Student[0];
		}
		else{
			studentList = new Student[tempArray.size()];
			for(int i = 0; i < tempArray.size(); i++){
				studentList[i] = tempArray.get(i);
			}
		}

		JList<Student> foundStudent = new JList(studentList);
		foundStudent.setFont(new Font("Tahoma", Font.PLAIN, 14));

		if(ifEnter.equals("enter a student panel") ){
			foundStudent.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						Student selectedStudent = (Student) foundStudent.getSelectedValue();
						StudentPanel studentPanel = new StudentPanel(selectedStudent, 1);
						
						AppMainWindow.contentPane.removeAll();
						AppMainWindow.contentPane.add(studentPanel);
						AppMainWindow.contentPane.updateUI();
					}
				}
			});
		}
		return foundStudent;
	}
}
