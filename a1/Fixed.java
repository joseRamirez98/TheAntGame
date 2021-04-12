package com.mycompany.a1;

public abstract class Fixed extends GameObject {
	/**
	 * A Fixed object must always be passed a size, x and y coordinates, 
	 * and a color passed from child constructors.
	 */
	public Fixed(int size, float locX, float locY, int color) {
		super(size, locX, locY, color);
	}
	
	/**
	 * Format output string to the necessary specifications mentioned
	 * in Assignment 1 PDF document.
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = "size=" + super.getSize() + " ";
		return parentDesc + myDesc ;
	}
}
