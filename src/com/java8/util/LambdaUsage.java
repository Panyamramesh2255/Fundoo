package com.java8.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaUsage {
	public static void main(String[] args)
	{
	List<ObjectCreationLambda> list1=new ArrayList<ObjectCreationLambda>();
	list1.add(new ObjectCreationLambda(20, "ram", 20000));
	list1.add(new ObjectCreationLambda(21, "raj", 21000));
	list1.add(new ObjectCreationLambda(22, "rak", 22000));
	Collections.sort(list1,(p1,p2)->{
		
		return p1.name.compareTo(p2.name);
		
	});
	for(ObjectCreationLambda ob:list1)
	{
		System.out.println(ob.id+" "+ ob.name+" "+ob.salary);
	}
	
	
	
	}

}
