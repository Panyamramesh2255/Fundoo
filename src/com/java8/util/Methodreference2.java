package com.java8.util;

public class Methodreference2 {
	public static void threadStatus()
	{
		System.out.println("thread is running..");
	}
	public static void main(String[] args) {
		
		Thread t2= new Thread(Methodreference2::threadStatus);
		t2.start();
	}

}
