package data;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Teacher {

	private String name;
	private int age;
	private String gender;
	private Class[] semOneCourse;
	private Class[] semTwoCourse;
	private Image photo;
	
	public Teacher(String name, int age, String gender){
		this.name = name;
		this.age = age;
		this.gender = gender;
		semOneCourse = new Class[]{new Class(1), new Class(2), new Class(3), new Class(4)};
		semTwoCourse = new Class[]{new Class(1), new Class(2), new Class(3), new Class(4)};
		this.setPhoto(System.getProperty("user.dir") + File.separator + 
					"icon" + File.separator + "defaultPhoto.jpg");
	}
	
	public Teacher(){
		this.name = "None"; 
	}
	
	public String getName(){
		return name;
	}
	
	public String getGender() {
		return gender;
	}
	
	public int getAge() {
		return age;
	}
	
	public Class[] getSemOneCourse() {
		return semOneCourse;
	}
	
	public Class[] getSemTwoCourse() {
		return semTwoCourse;
	}
	
	public Image getPhoto(){
		return photo;
	}
	
	public String toString(){
		return name + " " + gender;
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
	
	public void addCourse(Class c){
		if(c.getSemester() == 1){	//semester one
			
			//if user is replacing class with itself, show the warning window
			if(semOneCourse[c.getPeriod() - 1].getCourseCode().equals(c.getCourseCode())){			
				JOptionPane.showMessageDialog (new JPanel(), "You cannot replace a class with itself!",
						"SORRY", JOptionPane.INFORMATION_MESSAGE);
			}		
			//if student has a class for this period, ask user for confirmation to replace current class
			else if(!semOneCourse[c.getPeriod() - 1].getCourseCode().equals("spare")){	//if course
				int choose = JOptionPane.showConfirmDialog(new JPanel(), "Do you want to change " + name + "'s semester " + c.getSemester() + 
						" period " + c.getPeriod() + " course " + semOneCourse[c.getPeriod() - 1].getCourseCode() + " to " + c.getCourseCode() + "?"
						, "Replacing existing course?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(choose == 0){
					semOneCourse[c.getPeriod() - 1].getStudentList().remove(this);
					semOneCourse[c.getPeriod() - 1] = c;
					c.setTeacher(this);
				}
			}			
			//if chosen period is a spare, replace it
			else{
				c.setTeacher(this);
				semOneCourse[c.getPeriod() - 1] = c;
			}			
		}	
		
		else{	//same as semester one
			if(semTwoCourse[c.getPeriod() - 1].getCourseCode().equals(c.getCourseCode())){			
				JOptionPane.showMessageDialog (new JPanel(), "You cannot replace a class with itself!",
						"SORRY", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(!semTwoCourse[c.getPeriod() - 1].getCourseCode().equals("spare")){	//semeter two
				int choose = JOptionPane.showConfirmDialog(new JPanel(), "Do you want to change " + name + "'s semester " + c.getSemester() + 
						" period " + c.getPeriod() + " course " + semTwoCourse[c.getPeriod() - 1].getCourseCode() + " to " + c.getCourseCode() + "?"
						, "Replacing existing course?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(choose == 0){
					semTwoCourse[c.getPeriod() - 1].getStudentList().remove(this);
					semTwoCourse[c.getPeriod() - 1] = c;
					c.setTeacher(this);
				}				
			}
			else{
				c.setTeacher(this);
				semTwoCourse[c.getPeriod() - 1] = c;
			}		
		}		
	}
	
	public String[][] generateCourseTableData(int sem){
		if(sem == 1){
			String[][] semOneData = new String[4][2];
			for(int i = 0; i < 4; i++){
				semOneData[i][0] = semOneCourse[i].getCourseCode();
				semOneData[i][1] = semOneCourse[i].getNumStudent() + "/" + semOneCourse[i].studentLimit;
			}

			return semOneData;
		}
		else{
			String[][] semTwoData = new String[4][2];
			for(int i = 0; i < 4; i++){
				semTwoData[i][0] = semTwoCourse[i].getCourseCode();
				semTwoData[i][1] = semTwoCourse[i].getNumStudent() + "/" + semTwoCourse[i].studentLimit;
			}

			return semTwoData;
		}
	}
	
	public static void readFile(String fileName) throws IOException{
		try{
			Scanner in = new Scanner(new File(fileName));

			while(in.hasNextLine()){
				String teacherName = in.nextLine().trim();
				int age = Integer.parseInt(in.nextLine().trim()); 
				String gender = in.nextLine().trim();
				Teacher t = new Teacher(teacherName, age ,gender);  
				Database.addTeacher(t);
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
