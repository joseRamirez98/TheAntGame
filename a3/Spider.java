package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Spider extends Movable {
	public Spider() {
		//super(heading, speed, size, locX, locY, color)
		super(getRandomInt(1, 360), 
			  getRandomInt(100, 200), 
			  getRandomInt(100, 150),
			  getRandomFloat((float)100.0, (float)800.0),
			  getRandomFloat((float)100.0, (float)800.0));
		this.setColor(0, 0, 0);
	}
	
	// only use this constructor if you want to instantiate a spider
	// in a specific location.
	public Spider(int width, int height) {
		//super(heading, speed, size, locX, locY, color)
		super(getRandomInt(1, 360), 
			  getRandomInt(100, 200),
			  getRandomInt(100, 150),
			  getRandomFloat((float)100.0, (float)width),
			  getRandomFloat((float)100.0, (float)height));
		this.setColor(0, 0, 0);
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
	public void move(int maxW, int maxH, int elapsedTime) {
		double minHeight   = 0.0;
		double minWidth    = 0.0;
		double maxHeight   = (double) (maxH);
		double maxWidth    = (double) (maxW); 
		double radianAngle = Math.toRadians(90 - super.getHeading());
		double dist        = (super.getSpeed() * elapsedTime)/1000;
		
		//newLocation(x,y) = (deltaX, deltaY) + oldLocation(x,y)
		double deltaX = Math.cos(radianAngle) * dist;
		double deltaY = Math.sin(radianAngle) * dist;
		
		deltaX = deltaX + super.getLocation().getX();
		deltaY = deltaY + super.getLocation().getY();
		super.setLocation((float) deltaX, (float) deltaY);
		
		//add (or subtract) small random values (e.g., 5 degrees) to spider heading
		super.setHeading(super.getHeading() + getRandomInt(-5,6)); 
		
		//check to see if new location is within game bounds
		if((deltaX <= minWidth) || (deltaX >= maxWidth)) {
			super.setHeading(super.getHeading() + 180);
		}
		if((deltaY <= minHeight) || (deltaY >= maxHeight)){
			super.setHeading(super.getHeading() + 180);
		} 
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int xLoc = (int) (pCmpRelPrnt.getX()+ getLocation().getX() - (getSize() / 2));// shape center location relative 
		int yLoc = (int) (pCmpRelPrnt.getY()+ getLocation().getY() - (getSize() / 2));// to parent’s origin
		
		/*
		 * (xLoc, yLoc) : THE UPPER LEFT CORNER COORDINATE OF THE TRIANGLE
		 * (xLoc + getSize(), yLoc) : THE UPPER RIGHT CORNER COORDINATE OF THE TRIANGLE
		 * 
		 * THESE TWO COORDINATES ABOVE BUILD THE LINE SEGAMENT FOR THE BASE OF THE
		 * TRIANGLE.
		 * 
		 * THE TIP OF THE TRIANGLE HAS A HEIGHT OF SIZE. IT'S X COORDINATE IS HALF
		 * OF THE BASE (SIZE) FROM THE UPPER LEFT, AND ITS Y COORDINATE IS SIZE
		 * PIXELS DOWN FROM THE UPPER LEFT.
		 * 
		 * (xLoc + (getSize() / 2), yLoc + getSize()) : TH TIP OF THE TRIANGLE
		 * */
		int xPoints[] = {xLoc, xLoc + getSize(), xLoc + (getSize() / 2)};
		int yPoints[] = {yLoc, yLoc, yLoc + getSize()};
		
		
		int[] color = this.getColor();
		g.setColor(ColorUtil.rgb(color[0], color[1], color[2]));
		g.drawPolygon(xPoints, yPoints, 3);
	}
		
	/**
	 * Format output string to the necessary specifications mentioned
	 * in Assignment 1 PDF document.
	 */
	public String toString() {
		return super.toString();
	}

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
			if(temp.isEmpty() == false) {
				if(temp.contains(otherObject)) {
		    		temp.remove(otherObject);
		    	}
			}
		}
		
		return result;
	}

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
			gameWorld.spiderCollision(this);
		}
	}
}
