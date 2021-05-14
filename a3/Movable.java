package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public abstract class Movable extends GameObject {
	private int heading;
	private int speed;
	
	/**
	 * A Movable object must always be passed a size, x and y coordinates, 
	 * and a color passed from child constructors.
	 */
	public Movable(int heading, int speed, int size, float locX, float locY) {
		super(size, locX, locY);
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
	public abstract void move(int maxW, int maxH, int elapsedTime);
	
	// a way to determine if a pointer is “in” an object
	// pPtrRelPrnt is pointer position relative to the parent origin
	// pCmpRelPrnt is the component position relative to the parent origin
	abstract public void draw(Graphics g, Point pCmpRelPrnt);
	
	// Use bounding circles to determine whether this object has collided with another
	abstract public boolean collidesWith(GameObject otherObject);
	
	// defines this object’s response to a collision with otherObject
	abstract public void handleCollision(GameWorld gameWorld, GameObject otherObject);
	
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
