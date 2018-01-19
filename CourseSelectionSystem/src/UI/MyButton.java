package UI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class MyButton extends JButton{
	
	public MyButton(String s) {
		this.setText(s);
		this.setBackground(Color.WHITE);
//		this.setForeground(new Color(154, 205, 50));	
		this.setFocusPainted(false);
		this.setFont(new Font("Tahoma", Font.BOLD, 12));
	}
}
