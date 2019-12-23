package com.java8.util;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class SwingLambda {
	public static void main(String[] args)
	{
		JTextField jf=new JTextField();
		jf.setBounds(50, 50, 150, 20);
		JButton b=new JButton("click");
		b.setBounds(80, 100, 70, 30);
		b.addActionListener(e->{jf.setText("hello swing");});
		JFrame f=new JFrame();
		f.add(jf);
		f.add(b);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);
		f.setSize(200,300);
		f.setVisible(true);
		
		
		
		
		
		
	}

}
