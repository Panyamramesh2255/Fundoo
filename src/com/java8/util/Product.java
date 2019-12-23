package com.java8.util;

public class Product {
	int id;
	String name;
	float price;
	Product(int id, String name, float price)
	{
		this.id=id;
		this.name=name;
		this.price=price;
		
	}
	public int getid()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public float getPrice()
	{
		return price;
	}

}
