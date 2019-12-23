package com.java8.util;

public class AbImplementation extends AbstractClass {

	@Override
	void add(int a, int b) {
		System.out.println("sum is " + (a + b));

	}

	public static void main(String[] args) {

		AbImplementation ai = new AbImplementation();
		ai.add(20, 30);
		int resu = ai.sub(30, 20);
		int mulre = ai.multiplication(20, 2);
		System.out.println("subtration " + resu);
		System.out.println("multiplication  " + mulre);

	}
}
