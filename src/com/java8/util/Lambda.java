package com.java8.util;

import java.awt.List;
import java.util.ArrayList;

public class Lambda {
	
	public static void main(String[] args) {
		
		//implementation of interface without lambda
//		Drawable d=new Drawable() {
//			
//			@Override
//			public void draw() {
//
//				System.out.println("drwable method...");
//				
//			}
//		};
//		d.draw();
		Lambda ll=new Lambda();
		ll.lambda();
		
		
		
	}
	public void lambda()
	{
		
//		Drawable d=()->{
//			
//				System.out.println("drawable method with lambda");
//			
//			
//			
//		};
//		Writable d=()->{
//			
//			return "i have nothing to say ";
//		};
//		System.out.println(d.write());
//		
//		Readable rd=(name)->{
//			return "Hello "+name; 
//			
//		};
//		System.out.println(rd.read("sonoo"));
		//lanbda expression with out return keyword
//		Addable ad=(a,b)->(a+b);
//		System.out.println(ad.add(10, 20));
//		
//		//lambda expression with return keyword
//		Addable ad1=(int a,int b)->{
//			
//			return (a+b);
//		};
//		System.out.println("with return keyword "+ad1.add(10, 20));
//			
	   //lambda in list iterator  		
//			
//	  ArrayList list=new ArrayList();
//	  list.add("raj");
//	  list.add("ram");
//	  list.add("rak");
//	  list.add("raw");
//		
//	list.forEach((n)->System.out.println(n));
//	
		
		
		//lambda with multiple statements
//		Readable rd=(name)->{
//			
//			String msg1="nothing to say ";
//			String msg2=msg1+name;
//			return msg2;
//			
//			
//			
//		};
//		System.out.println(rd.read("vijay"));
//		
		//implementing thread without lambda
    
		Runnable e=new Runnable() {
			
			@Override
			public void run() {

				System.out.println("thread one is running.. ");
				
			}
		};
		Thread t=new Thread(e);
		t.run();
		
		//thread creation without lambda
		Runnable re=()->{
			
			System.out.println("thread2 is running.. ");
		};
		Thread th=new Thread(re);
		th.run();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
