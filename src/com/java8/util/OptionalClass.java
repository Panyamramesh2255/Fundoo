package com.java8.util;

import java.util.Optional;

public class OptionalClass {
	public static void main(String[] args) {
		
	
	String[] data=new String[10];

Optional<String> checkNull=Optional.ofNullable(data[5]);
if(checkNull.isPresent())
{
	String location=data[5].toLowerCase();
System.out.println("fifth location is "+location);
	}
else
{
	System.out.println("the location which you are looking is null ");}
}
}