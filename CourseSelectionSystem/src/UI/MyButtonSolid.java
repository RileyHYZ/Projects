package UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyButtonSolid extends JButton{

	public MyButtonSolid(String s) {
		this.setText(s);
		this.setBackground(new Color(154, 205, 50));
		this.setForeground(Color.WHITE);
		this.setFocusPainted(false);
		this.setFont(new Font("Tahoma", Font.BOLD, 12));
	}

	public MyButtonSolid(String s, ImageIcon iconEnable){
		this.setText(s);
		this.setBackground(new Color(154, 205, 50));
		this.setForeground(Color.WHITE);
		this.setFocusPainted(false);
		this.setFont(new Font("Tahoma", Font.BOLD, 12));

		this.setPressedIcon(iconEnable);
	}

	public void setEnabled(boolean b){
		if(b){
			this.setBackground(new Color(154, 225, 90));
		}
		else{
			this.setBackground(new Color(154, 205, 50));
			this.setForeground(Color.WHITE);
		}
	}
}
