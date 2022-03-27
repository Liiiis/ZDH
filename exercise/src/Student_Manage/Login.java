package Student_Manage;


import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import Student_Manage.ManagerLogin;

public class Login extends JFrame{    
	 TextField name=new TextField(20);
	JPasswordField mima=new JPasswordField(15);
	JFrame f=new JFrame("运动会成绩管理系统");
	
	public Login(){
	
		f.setBounds(700,300,400,300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false); 
	    f.setLayout(null);
		JLabel label1=new JLabel("管理员登录：");  
		JLabel label2=new JLabel("管理员：");  
		JLabel label3=new JLabel(" 密   码：");
		label1.setFont(new Font("Serif",Font.BOLD,20));
	    label1.setBounds(0,0,300,50);  
		label2.setFont(new Font("Serif",Font.BOLD,15));
		label3.setFont(new Font("Serif",Font.BOLD,15));
       JButton button1=new JButton("登录");
		JButton button2=new JButton("取消");

		
		BHandler h=new BHandler();
		button1.addActionListener((ActionListener) h);
		button2.addActionListener(h);
		JPanel p1=new JPanel();  
		JPanel p2=new JPanel(); 
		JPanel p3=new JPanel(); 
	    f.add(label1);
	    p1.setBounds(0,60,380,30); 
       p1.add(label2);
		p1.add(name);
		p2.setBounds(0,90,380,30);
		p2.add(label3);
		p2.add(mima);
		p3.setBounds(0,150,420,40);
		p3.add(button1);
		p3.add(button2);
	
		f.add(p1);
		f.add(p2);
		f.add(p3);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
	Login a=new Login();

	}
	public class BHandler implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			if(event.getActionCommand()=="登录")
			{
				if(name.getText().equals("lyc")&&mima.getText().equals("123"))
				{
					ManagerLogin zhuce=new ManagerLogin();
				}
				else
					   JOptionPane.showMessageDialog(Login.this,"卡号或者密码不正确！");
			}
			else 
			{
		
				f.dispose();
			}
		}
		}

}