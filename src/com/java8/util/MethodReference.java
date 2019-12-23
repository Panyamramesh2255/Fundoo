package com.java8.util;

public class MethodReference {

public static void main(String[] args) {

	Sayable sayable=MethodReference::saySomething;
	sayable.say();
		
	
	
}
public static void saySomething()
{

	System.out.println("nothing to say...");	

}
















}
