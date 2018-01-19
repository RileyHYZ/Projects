package data;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import UI.AppMainWindow;
import UI.ClassPanel;

public class Student{
	private String name;
	private String studentID;
	private static int studentNum;
	private Class[] semOneCourse = {new Class(1), new Class(2), new Class(3), new Class(4)};
	private Class[] semTwoCourse = {new Class(1), new Class(2), new Class(3), new Class(4)};
	private Image photo;

	public Student(String name, String ID){
		this.name = name;
		studentID = ID;
		
		this.setPhoto(System.getProperty("user.dir") + File.separator + 
					"icon" + File.separator + "defaultPhoto.jpg");

		setStudentNum(getStudentNum() + 1);
	}

	public String getName(){
		return this.name;
	}

	public String getID(){
		return this.studentID;
	}
	public Class[] getSemOneCourse(){
		return semOneCourse;
	}

	public Class[] getSemTwoCourse(){
		return semTwoCourse;
	}

	public static int getStudentNum() {
		return studentNum;
	}

	public static void setStudentNum(int studentNum) {
		Student.studentNum = studentNum;
	}

	public String toString(){
		return name;
	}

	public boolean equals(Student s){
		return this.studentID.equals(s.studentID);	
	}

	public Image getPhoto(){
		return photo;
	}
	
	public void setPhoto(String s){
		try {
			BufferedImage b = ImageIO.read(new File(s));
			photo = b.getScaledInstance(114, 102,
			        BufferedImage.SCALE_SMOOTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Add course to a student's course list and update class's student list at the same time
	public void addCourse(Class c){
		if(c.getSemester() == 1){	//semester one

			if(semOneCourse[c.getPeriod() - 1].getCourseCode().equals(c.getCourseCode())){			
				JOptionPane.showMessageDialog (new JPanel(), "You cannot add one item twice!",
						"SORRY", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(!semOneCourse[c.getPeriod() - 1].getCourseCode().equals("spare")){	//if course
				int choose = JOptionPane.showConfirmDialog(new JPanel(), "Do you want to change " + name + "'s semester " + c.getSemester() + 
						" period " + c.getPeriod() + " course " + semOneCourse[c.getPeriod() - 1].getCourseCode() + " to " + c.getCourseCode() + "?"
						, "Replacing existing course?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if(choose == 0){
					semOneCourse[c.getPeriod() - 1].getStudentList().remove(this);
					semOneCourse[c.getPeriod() - 1] = c;
				}
			}

			else{
				c.addStudent(this);
				semOneCourse[c.getPeriod() - 1] = c;
			}			
		}	

		else{
			if(semTwoCourse[c.getPeriod() - 1].getCourseCode().equals(c.getCourseCode())){			
				JOptionPane.showMessageDialog (new JPanel(), "You cannot add one item twice!",
						"SORRY", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(!semTwoCourse[c.getPeriod() - 1].getCourseCode().equals("spare")){	//semeter two
				int choose = JOptionPane.showConfirmDialog(new JPanel(), "Do you want to change " + name + "'s semester " + c.getSemester() + 
						" period " + c.getPeriod() + " course " + semTwoCourse[c.getPeriod() - 1].getCourseCode() + " to " + c.getCourseCode() + "?"
						, "Replacing existing course?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if(choose == 0){
					semTwoCourse[c.getPeriod() - 1].getStudentList().remove(this);
					semTwoCourse[c.getPeriod() - 1] = c;
					c.addStudent(this);
				}				
			}
			else{
				c.addStudent(this);
				semTwoCourse[c.getPeriod() - 1] = c;
			}		
		}		
	}

	public void removeCourse(Class c){
		if(c.getSemester() == 1){
			c.getStudentList().remove(this);
			semOneCourse[c.getPeriod() - 1] = new Class(c.getPeriod() - 1);
		}			
		else{
			c.getStudentList().remove(this);
			semTwoCourse[c.getPeriod() - 1] = new Class(c.getPeriod() - 1);
		}
	}

	public void removeCourse(int sem, int period){
		if(period == 0){
			JOptionPane.showMessageDialog (new JPanel(), "Please choose a class first!",
					"SORRY", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			try{
				if(sem == 1){
					semOneCourse[period - 1].getStudentList().remove(this);
					semOneCourse[period - 1] = new Class(period - 1);
				}
				else{
					semTwoCourse[period - 1].getStudentList().remove(this);
					semTwoCourse[period - 1] = new Class(period - 1);
				}
			}
			catch(NullPointerException e){
				JOptionPane.showMessageDialog (new JPanel(), "Unable to remove spare!",
						"SORRY", JOptionPane.INFORMATION_MESSAGE);
			}
		}


	}

	public String[][] generateCourseTableData(int sem){
		if(sem == 1){
			String[][] semOneData = new String[4][3];
			for(int i = 0; i < 4; i++){
				semOneData[i][0] = semOneCourse[i].getCourseCode();
				semOneData[i][1] = semOneCourse[i].getTeacher().getName();
				semOneData[i][2] = semOneCourse[i].getNumStudent() + "/" + semOneCourse[i].studentLimit;
			}

			return semOneData;
		}
		else{
			String[][] semTwoData = new String[4][3];
			for(int i = 0; i < 4; i++){
				semTwoData[i][0] = semTwoCourse[i].getCourseCode();
				semTwoData[i][1] = semTwoCourse[i].getTeacher().getName();
				semTwoData[i][2] = semTwoCourse[i].getNumStudent() + "/" + semTwoCourse[i].studentLimit;
			}

			return semTwoData;
		}
	}
	
	public String[][] generateSimplyCourseTable(int sem){
		if(sem == 1){
			String[][] semOneData = new String[1][4];
			
			for(int i = 0; i < 4; i++){
				semOneData[1][i] = semOneCourse[i].getCourseCode();
				
			}
				
			
			return semOneData;
		}
		else{
			String[][] semTwoData = new String[1][4];
			
			for(int i = 0; i < 4; i++)
				semTwoData[1][i] = semTwoCourse[i].getCourseCode();
			
			return semTwoData;
		}
	}

	public static void readFile(String fileName) throws IOException{
		try{
			Scanner in = new Scanner(new File(fileName));

			while(in.hasNextLine()){
				String studentName = in.nextLine().trim();
				String studentID = in.nextLine().trim();                
				Student s = new Student(studentName, studentID);  
				Database.addStudent(s);
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
		}
	}
}
