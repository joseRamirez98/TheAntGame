package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.models.Point;

import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	private int size;		//object size
	private Point location; //object location
	private int color;		//object color
	
	
	/**
	 * A Game object must always be passed a size, x and y coordinates, 
	 * and a color passed from child constructors.
	 */
	public GameObject(int size, float x, float y, int color) {
		this.size = size;
		this.location = new Point(x, y);
		this.color = color;
	}
	
	/**
	 * Returns the size of the Game object.
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Set the size of the Game object through a x and y coordinates.
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * Returns the location of the Game object.
	 */
	public Point getLocation() {
		return this.location;
	}
		
	/**
	 * Set the location of the Game object through a x and y coordinates.
	 */
	public void setLocation(float x, float y) {
		this.location.setX(x);
		this.location.setY(y);
	}
	
	/**
	 * Returns the color of the Game object.
	 */
	public int getColor() {
		return this.color;
	}
	
	/**
	 * Set the color of the Game object based on rgb values.
	 */
	public void setColor(int r, int g, int b){
		this.color = ColorUtil.rgb(r, g, b);
	}
	
	/**
	 * Generate a random number between min (inclusive) and max (exclusive).
	 * To be used in child classes, not in GameObject.
	 */
	public static int getRandomInt(int min, int max) {
		Random random = new Random();
	    return min + random.nextInt(max-min);
	}
	
	/**
	 * Generate a random float number between min (inclusive) and max (exclusive).
	 * To be used in child classes, not in GameObject.
	 */
	public static float getRandomFloat(float min, float max) {
		Random random = new Random();
	    return random.nextFloat() * (max - min) + min;
	}
	
	/**
	 * Format output string to the necessary specifications mentioned
	 * in Assignment 1 PDF document.
	 */
	public String toString() {
		double locX = (Math.round(location.getX()*10.0)/10.0);
		double locY = (Math.round(location.getX()*10.0)/10.0);
		String parent = "loc=" + locX + "," + locY + " " +
						"color: " + "[" + ColorUtil.red(this.color) + "," 
										+ ColorUtil.green(this.color) + "," 
										+ ColorUtil.blue(this.color) + "] ";
		return parent;
	}
}
