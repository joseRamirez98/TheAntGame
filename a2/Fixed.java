package com.mycompany.a2;

public abstract class Fixed extends GameObject {
	/**
	 * A Fixed object must always be passed a size, x and y coordinates, 
	 * and a color passed from child constructors.
	 */
	public Fixed(int size, float locX, float locY, int color) {
		super(size, locX, locY, color);
	}
	
	/**
	 * Once fixed object location is set through constructor, it should not change.
	 * Override parent GameObject setLocation function to prevent changes
	 * being made.
	 */
	public void setLocation(float x, float y) {}
	
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
