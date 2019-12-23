package com.java8.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LambdaExample {
	public static void main(String[] args) {
		
	
	List<Product> list=new ArrayList<Product>();
	list.add(new  Product(1, "sonata", 41000));
	list.add(new  Product(2, "fastrack", 12000));
	list.add(new  Product(3, "titan", 19000));
	list.add(new  Product(4, "chile", 10000));
	list.add(new  Product(5, "roelx", 71000));
	list.add(new  Product(6, "saso", 5000));
	list.add(new  Product(7, "rado", 50000));
	list.add(new  Product(8, "rools", 15000));
	list.add(new  Product(9, "ferrari", 30000));
	list.add(new  Product(10, "lamb", 25000));
	
	Stream<Product> filtered_data=list.stream().filter(p->p.price>20000);
    filtered_data.forEach(product->System.out.println(product.id+" "+product.name+" "+product.price));	
	
	
	
	
	
	
	
	
	
	
	
	
	}
}
