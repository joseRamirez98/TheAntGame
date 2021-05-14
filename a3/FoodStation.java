package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;


public class FoodStation extends Fixed {

	private int capacity;
	
	public FoodStation(int width, int height) {
		//super(size, locX, locY, color)
		super(getRandomInt(50,151), 
			  getRandomFloat((float)100.0, (float)width), 
			  getRandomFloat((float)100.0, (float)height));
		this.capacity = (int) super.getSize()/2; // 1/2 proportional to the size of the food station
		this.setColor(0, 255, 0);
	}
	

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * Once FoodStation color is set through FoodStation constructor, it should not change.
	 * Override Grandparent GameObject setColor function to prevent changes
	 * being made.
	 */
	public void setSize(int size) {}
	
	/**
	 * Format output string to the necessary specifications mentioned
	 * in Assignment 1 PDF document.
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = "capacity=" + this.capacity;
		return parentDesc + myDesc ;
 
	}
		
	/* a way to determine if a pointer is “in” an object
	 * pPtrRelPrnt is pointer position relative to the parent origin
	 * pCmpRelPrnt is the component position relative to the parent origin
	*/ 
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// shape's center location relative 
		// to parent container's origin
		int upperLeftX = (int) ((pCmpRelPrnt.getX() + getLocation().getX()) - (this.getSize() / 2)); 
		int upperLeftY = (int) ((pCmpRelPrnt.getY() + getLocation().getY()) - (this.getSize() / 2));
	
		
		// convert the food station's capacity to a string
		String capVal = Integer.toString(capacity);
		
		if(isSelected()) {
			// color of rectangle is the color of the object itself
			int[] color = this.getColor();
			g.setColor(ColorUtil.rgb(color[0], color[1], color[2]));
			// draw a rectangle that is SIZE x SIZE dimensions starting
			// starting at upper left coordinates
			g.drawRect(upperLeftX, upperLeftY, getSize(), getSize()); 
			// set the graphic color to black so
			// that the text will be black color
			g.setColor(ColorUtil.BLACK);
			// draw the string in the center of the rectangle
			g.drawString(capVal, upperLeftX + (this.getSize()/4), upperLeftY + (this.getSize()/4));
		}
		else {
			// color of rectangle is the color of the object itself
			int[] color = this.getColor();
			g.setColor(ColorUtil.rgb(color[0], color[1], color[2]));
			// draw a rectangle that is SIZE x SIZE dimensions starting
			// starting at upper left coordinates
			g.fillRect(upperLeftX, upperLeftY, getSize(), getSize()); 
			// set the graphic color to black so
			// that the text will be black color
			g.setColor(ColorUtil.BLACK);
			// draw the string in the center of the rectangle
			g.drawString(capVal, upperLeftX + (this.getSize()/4), upperLeftY + (this.getSize()/4));
		}
	}
	
	/* Use bounding circles to determine whether this object has collided with another */
	@Override
	public boolean collidesWith(GameObject otherObject) {
		boolean result = false;
		
		int thisCenterX = (int) this.getLocation().getX();
		int thisCenterY = (int) this.getLocation().getY();
		
		int otherCenterX = (int) otherObject.getLocation().getX();
		int otherCenterY = (int) otherObject.getLocation().getY();
		
		// find distance between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		
		int thisRadius  = this.getSize() / 2; 		 // R1
		int otherRadius = otherObject.getSize() /2;  // R2
		// (R1+R2)^2 = R1^2 + 2*R1*R2 + R^2
 		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius
		                                       + otherRadius*otherRadius);
 		
 		// D^2 ≤ (R1+R2)^2 -> colliding
		if (distBetweenCentersSqr <= radiiSqr) {
			result = true;
		}
		else {
			Vector<GameObject> temp = this.getCollectionVector();
			// check if the vector is not empty,
			// meaning it has collided with an object
			// before.
			if(temp.isEmpty() == false) {
				if(temp.contains(otherObject)) {
		    		temp.remove(otherObject);
		    	}
			}
		}
		
		return result;
	}

	// a way to “draw” the object that knows about drawing
	// different ways depending on “isSelected”
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		
		int width  = getSize();
		int height = getSize();
		
		int px = (int) pPtrRelPrnt.getX(); // pointer location relative to
		int py = (int) pPtrRelPrnt.getY(); // parent’s origin
		int xLoc = (int) (pCmpRelPrnt.getX() + getLocation().getX() - (getSize() / 2));// shape center location relative 
		int yLoc = (int) (pCmpRelPrnt.getY() + getLocation().getY() - (getSize() / 2));// to parent’s origin
		
		/* Check if he pointer is was clicked within the object */
		if (   (px >= xLoc) && (px <= xLoc+width)
		    && (py >= yLoc) && (py <= yLoc+height) ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// defines this object’s response to a collision with otherObject
	@Override
	public void handleCollision(GameWorld gameWorld, GameObject otherObject) {
		Vector<GameObject> temp = this.getCollectionVector();
		
		/* Check if the other object has already collided with
    	 * this object. If so ignore the collision.
    	 * */
    	if(temp.contains(otherObject)) {
    		return;
    	}

		temp.add(otherObject);
		
		if(otherObject instanceof Ant) {
			gameWorld.foodStationCollision(this);
		}
	}
	
}
