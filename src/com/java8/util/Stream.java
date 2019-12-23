package com.java8.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Stream {
	public static void main(String[] args) {
		
	
	List<Product> productList=new ArrayList<Product>();
	productList.add(new Product(1, "sonata", 2000));
	productList.add(new Product(2, "ludo", 3000));
	productList.add(new Product(3, "china", 1000));
	productList.add(new Product(4, "japan", 7000));
	productList.add(new Product(5, "fastrack", 6000));
	productList.add(new Product(6, "rado", 5000));
	
//	List<Float>productPrize=productList.stream().filter(p->p.price>3000).map(p->p.price).collect(Collectors.toList());
//	System.out.println(productPrize);

	
	/* filtering data from the list using stream  */
	
	
	//	System.out.println("products list.. "+productList);
	//productList.stream().filter(product->product.prize==3000).forEach(product->System.out.println(product.name));
	
     	
	
	/* using reduce method to get sum of prizes of product */
	
//	float total_prize=productList.stream().map(product->product.prize).reduce(0.0f,Float::sum);
//    System.out.println("total prize is.. "+total_prize);
	
   /* getting maximum prize of a product using stream  */

	
//	Product product=productList.stream().max((product1,product2)->product1.prize>product2.prize?1:-1).get();
//    System.out.println("maximum prize product.. "+product.prize);
    
    
    /* minimum prize of a product using stream  */
    
//	Product prodct=productList.stream().min((product1,product2)->product1.prize>product2.prize?1:-1).get();
//    System.out.println("minimum prize of a product.. "+prodct.prize);
//    
    

	/* counting the number of items using count() method in stream */
	
//	long count=productList.stream().filter(product->product.prize>2000).count();
//	System.out.println("count is.. "+count);
	
	/* inserting list of elements into set   */
	
 	
//	Set<Float> set=productList.stream().filter(product->product.price>2000).map(product->product.price).collect(Collectors.toSet());      
//    
//    System.out.println("set data is.. "+set);
	
	
	/* inserting list of elements in to map */
	
//	Map<Integer, String> mapp=productList.stream().collect(Collectors.toMap(p->p.id, p->p.name));
//	System.out.println("map data.. "+mapp);
	
	
	/*  Method reference in stream  */
	List<Float> productprizelist=productList.stream().filter(p->p.price>2000).map(Product::getPrice).collect(Collectors.toList());
	System.out.println("by method reference list.. "+productprizelist);
	
	
	}
}