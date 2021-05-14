package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Flag extends Fixed {
	private int sequenceNumber;

	// flags should be given a size, a location, and sequence number by the user
	public Flag(int size, float locX, float locY, int sequenceNumber) {
		super(size, locX, locY);
		this.sequenceNumber = sequenceNumber;
		this.setColor(0, 162, 255);
	}

	/**
	 * @return the sequenceNumber
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber the sequenceNumber to set
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
		
	/**
	 * Once Flag size is set through Flag constructor, it should not change.
	 * Override Grandparent GameObject setSize function to prevent changes
	 * being made.
	 */
	public void setSize(int size) {}
	
	/**
	 * Format output string to the necessary specifications mentioned
	 * in Assignment 1 PDF document.
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = "seqNum=" + this.sequenceNumber;
		return parentDesc + myDesc ;
 
	}
	
	/* a way to determine if a pointer is “in” an object
	 * pPtrRelPrnt is pointer position relative to the parent origin
	 * pCmpRelPrnt is the component position relative to the parent origin
	*/ 
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int xLoc = (int) (pCmpRelPrnt.getX() + getLocation().getX() - (getSize() / 2));// shape center location relative 
		int yLoc = (int) (pCmpRelPrnt.getY() + getLocation().getY() - (getSize() / 2));// to parent’s origin

		/* Upper left, Upper Right, and Tip locations
		 * of the triangle.
		 * */	
		int xPoints[] = {xLoc, xLoc + getSize(), xLoc + (getSize() / 2)};
		int yPoints[] = {yLoc, yLoc, yLoc + getSize()};
		
		if(isSelected()) {
			int[] color = this.getColor();
			g.setColor(ColorUtil.rgb(color[0], color[1], color[2]));
			g.drawPolygon(xPoints, yPoints, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawString(String.valueOf(this.sequenceNumber), xLoc + (this.getSize()/3), yLoc + (this.getSize()/4));
		}
		else {
			int[] color = this.getColor();
			g.setColor(ColorUtil.rgb(color[0], color[1], color[2]));
			g.fillPolygon(xPoints, yPoints, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawString(String.valueOf(this.sequenceNumber), xLoc + (this.getSize()/3), yLoc + (this.getSize()/4));
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
		int dx = (thisCenterX - otherCenterX);
		int dy = (thisCenterY - otherCenterY);
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
		
		int width  = this.getSize();
		int height = this.getSize();
		
		int px = (int) pPtrRelPrnt.getX(); // pointer location relative to
		int py = (int) pPtrRelPrnt.getY(); // parent’s origin
		int xLoc = (int) (pCmpRelPrnt.getX() + getLocation().getX() - (getSize() / 2));// shape center location relative 
		int yLoc = (int) (pCmpRelPrnt.getY() + getLocation().getY() - (getSize() / 2));// to parent’s origin
		
		
		if ( (px >= xLoc) && (px <= xLoc+width)
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
    	if(otherObject instanceof Ant) {
			gameWorld.flagCollision(this);
		}
    	
		temp.add(otherObject);			
	}
	

}
