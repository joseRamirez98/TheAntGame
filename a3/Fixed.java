package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public abstract class Fixed extends GameObject implements ISelectable{
	private boolean isSelected;
	
	/**
	 * A Fixed object must always be passed a size, x and y coordinates, 
	 * and a color passed from child constructors.
	 */
	public Fixed(int size, float locX, float locY) {
		super(size, locX, locY);
	}
		
	public void setSelected(boolean b) { isSelected = b; }
	
	public boolean isSelected() { return isSelected; }
	
	// a way to determine if a pointer is “in” an object
	// pPtrRelPrnt is pointer position relative to the parent origin
	// pCmpRelPrnt is the component position relative to the parent origin
	abstract public void draw(Graphics g, Point pCmpRelPrnt);
	
	// a way to “draw” the object that knows about drawing
	// different ways depending on “isSelected”
	abstract public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	
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
		String myDesc = "size=" + super.getSize() + " ";
		return parentDesc + myDesc ;
	}
}
