package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class Spider extends Movable {
	
	public Spider() {
		//super(heading, speed, size, locX, locY, color)
		super(getRandomInt(0, 360), 
			  getRandomInt(5, 11), 
			  20,
			  getRandomFloat((float)0.0, (float)1001.0),
			  getRandomFloat((float)0.0, (float)1001.0),
			  ColorUtil.rgb(0, 0, 0));
	}
	
	// only use this constructor if you want to instantiate a spider
	// in a specific location.
	public Spider(float locX, float locY) {
		//super(heading, speed, size, locX, locY, color)
		super(getRandomInt(0, 360),
			  getRandomInt(5, 11),
			  20,
			  locX,
			  locY, 
			  ColorUtil.rgb(0, 0, 0));
	}
	
	/**
	 * Once Spider color is set through Spider constructor, it should not change.
	 * Override Grandparent GameObject setColor function to prevent changes
	 * being made.
	 */
	public void setColor(int r, int g, int b){}
	
	/**
	 * Once Spider Size is set through Spider constructor, it should not change.
	 * Override Grandparent GameObject setSize function to prevent changes
	 * being made.
	 */
	public void setSize(int size) {}
	
	/*
	 * Moving objects need to determine their new location when their move() method
	 * is invoked, at each clock tick. The new location can be computed as follows:
	 * newLocation(x,y) = (deltaX, deltaY) + oldLocation(x,y), where deltaX = cos(θ)*speed,
	 * deltaY = sin(θ)*speed,
	 * 
	 * If the spider's new location is out of bounds, increase or decrease the heading of
	 * the spider by a small amount but do not move it.
	 * 
	 * PARAMETERS
	 * - maxWidth  : the map view's max width
	 * - maxHeight : the map view's max height
	 * */
	public void move(double maxWidth, double maxHeight) {
		double minHeight = 0.0;
		double minWidth = 0.0;
		double radianAngle = Math.toRadians(90 - super.getHeading());
		
		//newLocation(x,y) = (deltaX, deltaY) + oldLocation(x,y)
		double deltaX = (Math.cos(radianAngle) * (double) super.getSpeed()) + super.getLocation().getX();
		double deltaY = (Math.sin(radianAngle) * (double) super.getSpeed()) + super.getLocation().getY();
		
		//check to see if new location is within game bounds (0.0 - maxWidth, 0.0 - maxHeight)
		if(deltaX < minWidth || deltaX > maxWidth || deltaY < minHeight || deltaY > maxHeight) {
			super.setHeading(super.getHeading() + getRandomInt(-5, 6));
			return;
		}
		
		super.setLocation((float) deltaX, (float) deltaY);
		//add (or subtract) small random values (e.g., 5 degrees) to spider heading
		super.setHeading(super.getHeading() + getRandomInt(-5,6));  
	}
		
	/**
	 * Format output string to the necessary specifications mentioned
	 * in Assignment 1 PDF document.
	 */
	public String toString() {
		return super.toString();
	}
}
