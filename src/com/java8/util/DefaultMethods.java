package com.java8.util;

public class DefaultMethods implements Sayablee {


	/* implementing default methods */
	@Override
	public void saymore(String msg) {
		System.out.println("unimplmented method :: "+msg);
		
	}
	public static void main(String[] args)
	{
		DefaultMethods dm=new DefaultMethods();
		dm.saymore("no more to say ");
		dm.say();
		Sayablee.sayLouder("loudley saying");
		
		
		
		
		
		
	}
	
	

	
	
	
	

}
