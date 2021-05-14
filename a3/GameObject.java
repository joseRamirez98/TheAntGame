package com.mycompany.a3;

import java.util.Random;
import java.util.Vector;

import com.codename1.charts.models.Point;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public abstract class GameObject implements IDrawable, ICollider{
	private int size;		//object size
	private Point location; //object location
	private int[] color;		//object color
	private Vector<GameObject> collectionVector;
	
	
	/**
	 * A Game object must always be passed a size, x and y coordinates, 
	 * and a color passed from child constructors.
	 */
	public GameObject(int size, float x, float y) {
		this.size = size;
		this.location = new Point(x, y);
		this.color = new int[3];
		this.collectionVector = new Vector<GameObject>();
	}
	
	/**
	 * Returns the size of the Game object.
	 */
	public int getSize() {
		return this.size;
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
		Point newPoint = new Point(x, y);
		this.location = newPoint;
	}
	
	/**
	 * Returns the color of the Game object.
	 */
	public int[] getColor() {
		return color;
	}
	
	/**
	 * Set the color of the Game object based on rgb values.
	 */
	public void setColor(int r, int g, int b){
		this.color[0] = r;
		this.color[1] = g;
		this.color[2] = b;
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
	
	/* 	Return the Vector of collided objects.  */
	public Vector<GameObject> getCollectionVector() {
		return collectionVector;
	}
	
	/**
	 * Format output string to the necessary specifications mentioned
	 * in Assignment 1 PDF document.
	 */
	public String toString() {
		double locX = (Math.round(location.getX()*10.0)/10.0);
		double locY = (Math.round(location.getX()*10.0)/10.0);
		String parent = "loc=" + locX + "," + locY + " " +
						"color: " + "[" + color[0] + "," 
										+ color[1] + "," 
										+ color[2] + "] ";
		return parent;
	}
	
	abstract public void draw(Graphics g, Point pCmpRelPrnt);
	abstract public boolean collidesWith(GameObject otherObject);
    abstract public void handleCollision(GameWorld gameWorld, GameObject otherObject);

}