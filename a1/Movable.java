package com.mycompany.a1;

public abstract class Movable extends GameObject {
	private int heading;
	private int speed;
	
	/**
	 * A Movable object must always be passed a size, x and y coordinates, 
	 * and a color passed from child constructors.
	 */
	public Movable(int heading, int speed, int size, float locX, float locY, int color) {
		super(size, locX, locY, color);
		this.heading = heading;
		this.speed = speed;
	}
	/**
	 * @return the heading
	 */
	public int getHeading() {
		return heading;
	}
	/**
	 * @param heading the heading to set
	 */
	public void setHeading(int heading) {
		this.heading = heading;
	}
	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * Move function should be implemented by classes who 
	 * extend the Movable class.
	 */
	public abstract void move();
	
	/**
	 * Format output string to the necessary specifications mentioned
	 * in Assignment 1 PDF document.
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = "heading=" + this.heading + " " +
						"speed=" + this.speed + " " +
						"size=" + super.getSize() + " ";
		return parentDesc + myDesc;
	}	
}
