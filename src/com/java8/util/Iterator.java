package com.java8.util;

import java.util.stream.Stream;

public class Iterator {
	
	public static void main(String[] args) {
		Stream.iterate(1, element->element+1).filter(element->element%5==0).forEach(System.out::println);
		
		
		
		
//		for(int i=0;;i++)
//		{
//			
//			if(i%5==0)
//				System.out.println(i);
//			
//			
//		}
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
