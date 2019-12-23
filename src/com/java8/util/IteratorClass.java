package com.java8.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class IteratorClass {
	public static void main(String[] args) {
		
	IteratorClass ic=new IteratorClass();
	ic.iteratormethod();
	Vector v=new Vector();
	for(int i=0;i<=10;i++)
	{
		//v.addElement(i);
		
	}
	System.out.println(v);
	Enumeration e=v.elements();
	while(e.hasMoreElements())
	{
		int i=(Integer)e.nextElement();
		//System.out.print(i+" ");
	}

}
	public void iteratormethod()
	{
		ArrayList list=new ArrayList();
		for(int i=0;i<10;i++)
		{
			list.add(i);
		}
		
		System.out.println("Arraylist is "+list);
		Iterator itr=list.iterator();
		System.out.println("iteraor list");
		while(itr.hasNext())
		{
			int i=(Integer)itr.next();
			System.out.print(i+" ");
		}
		
		
	}
}