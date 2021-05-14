package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Ant extends Movable implements ISteerable{
	private static Ant ant;
	private int maximumSpeed;
	private int foodConsumption;
	private int healthLevel;
	private int foodLevel;
	private int lastFlagReached;


	/**
	 *  When the an Ant is created, it should always spawn in the same 
	 *  location as the first flag.
	 *  
	 *  Therefore when instantiating an Ant, it should be passed an x
	 *  and y coordinate to set the Ant's location.
	 */
	private Ant() {
		// super(heading, speed, size, locX, locY, color)
		super(0,
			  getRandomInt(80,100),
			  100,
			  getRandomFloat((float)0.0, (float)800.0),
			  getRandomFloat((float)0.0, (float)800.0));
		this.setColor(255, 0, 0);
		this.lastFlagReached = 1;
		this.maximumSpeed = getRandomInt(50,100);
		this.foodLevel= 1000;
		this.foodConsumption = 1;
		this.healthLevel = 10;
		
		// if random generated speed is greater than random generated max speed,
		// set the speed equal to the max speed.
		if(super.getSpeed() > maximumSpeed) {
			super.setSpeed(maximumSpeed);
		}
	}
	
	public static Ant getAnt() {
		if(ant == null)
			ant= new Ant();
		return ant;
	}
	
	
	/**
	 * @return the maxSpeed
	 */
	public int getMaximumSpeed() {
		return maximumSpeed;
	}

	/**
	 * Once max speed is set in constructor, it should not be changed.
	 */
	public void setMaximumSpeed(int maximumSpeed) {}

	/**
	 * @return the foodConsumption
	 */
	public int getFoodConsumptionRate() {
		return foodConsumption;
	}

	/**
	 * Once food consumption rate is set in constructor, it should not be changed.
	 */
	public void setFoodConsumptionRate(int foodConsumptionRate) {}

	/**
	 * @return the healthLevel
	 */
	public int getHealthLevel() {
		return healthLevel;
	}

	/**
	 * @param healthLevel the healthLevel to set
	 */
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}

	/**
	 * @return the foodLevel
	 */
	public int getFoodLevel() {
		return foodLevel;
	}

	/**
	 * @param foodLevel the foodLevel to set
	 * Food level should never be less than zero.
	 */
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}

	/**
	 * @return the lastFlagReached
	 */
	public int getLastFlagReached() {
		return lastFlagReached;
	}

	/**
	 * @param lastFlagReached the lastFlagReached to set
	 */
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	
	/**
	 * Once Ant size is set through Ant constructor, it should not change.
	 * Override Grandparent GameObject setSize function to prevent changes
	 * being made.
	 */
	public void setSize(int size) {}
	
	/*
	 * Determine whether to change the heading left or right based
	 * on the direction parameter. Going right increases the heading
	 * by 5. Going left decreases the heading by 5.
	 * */
	public void changeHeading(char direction) {
		if(direction == 'r') {
			super.setHeading(super.getHeading() + 5);
		} 
		else if(direction == 'l') {
			super.setHeading(super.getHeading() - 5);
		}
	}
	
	/*
	 * This function increases an Ant's speed. A Ant's speed
	 * can only be increased if the speed is less than the max
	 * speed or if the speed is less than the max speed to health
	 * level ratio.
	 * 
	 * example: if the ant's health value is 5, it can only achieve
	 * 50% of its maximum speed.
	 * */
	public void increaseSpeed() {
		double healthPercent = healthLevel * 0.1;
    	int speedLimit = (int) (this.maximumSpeed * healthPercent);
		
		if(super.getSpeed() == speedLimit) {
			return;
		}
		else if(super.getSpeed() > speedLimit) {
			super.setSpeed(speedLimit);
		}
		else {
			super.setSpeed(super.getSpeed() + 1);
		}
	}
	
	/*
	 * This function decreases an Ant's speed. A Ant's speed
	 * can only be decreased if the speed is greater than zero.
	 * 
	 * The function also enforces the speed of an Ant not be
	 * greater than the max speed to health level ratio.
	 * 
	 * example: if the ant's health value is 5, it can only achieve
	 * 50% of its maximum speed.
	 * */
	public void decreaseSpeed() {
		// don't decrease speed if the speed is already zero
		if(super.getSpeed() > 0) {
			double healthPercent = healthLevel * 0.1;
        	int speedLimit = (int) (this.maximumSpeed * healthPercent);
			
			if(super.getSpeed() > speedLimit) {
				super.setSpeed(speedLimit);
			}
			else {
				super.setSpeed(super.getSpeed() - 1);
			}
		}		
	}
	
	/*
	 * Reset the private  Ant object to null in order to
	 * allow user to create a new Ant object.
	 */
	public void reset() {
		ant = null;
	}

	
	/*
	 * Moving objects need to determine their new location when their move() method
	 * is invoked, at each clock tick. The new location can be computed as follows:
	 * newLocation(x,y) = (deltaX, deltaY) + oldLocation(x,y), 
	 * where deltaX = cos(θ)*speed and deltaY = sin(θ)*speed.
	 * Don't move the Ant if the food level, health level, or speed is zero.
	 * 
	 * PARAMETERS
	 * - maxWidth  : the map view's max width
	 * - maxHeight : the map view's max height
	 * */
	
	public void move(int maxW, int maxH, int elapsedTime) {
		// If Ant's food level, health level, or speed is zero, then it shouldn't move
		if(foodLevel == 0 | healthLevel == 0 | super.getSpeed() == 0) {
			return;
		}

		double minHeight = 0.0;
		double minWidth  = 0.0;
		double maxHeight = (double) (maxH);
		double maxWidth = (double) (maxW); 
		double radianAngle = Math.toRadians(90 - super.getHeading());
		double dist = (super.getSpeed() * elapsedTime)/1000;
		
		//newLocation(x,y) = (deltaX, deltaY) + oldLocation(x,y)
		double deltaX = Math.cos(radianAngle) * dist;
		double deltaY = Math.sin(radianAngle) * dist;
		
		deltaX = deltaX + super.getLocation().getX();
		deltaY = deltaY + super.getLocation().getY();
		super.setLocation((float) deltaX, (float) deltaY);
		
		//check to see if new location is within game bounds
		if( (deltaX <= minWidth) || (deltaX+super.getSize() >= maxWidth)) {
			super.setHeading(super.getHeading() + 180);
		}
		if( (deltaY <= minHeight) || (deltaY+super.getSize() >= maxHeight)){
			super.setHeading(super.getHeading() + 180);
		}
		
	}
	
	/* Draw the Ant based on its location relative to the
	 * MapViews's origin. Ant's shape is a filled circle.
	*/ 
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int radius = super.getSize()/ 2;
		int xLoc = (int) (pCmpRelPrnt.getX() + getLocation().getX());// shape center location relative 
		int yLoc = (int) (pCmpRelPrnt.getY() + getLocation().getY());// to parent’s origin
		int[] color = this.getColor();
		g.setColor(ColorUtil.rgb(color[0], color[1], color[2]));
		g.fillArc(xLoc, yLoc, 2*radius, 2*radius, 0, 360);
	}
	
	/* Use bounding circles to determine whether this object has collided with another */
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
 		//System.out.println("\ndistBetweenCentersSqr: " + distBetweenCentersSqr);
 		//System.out.println("\nradiiSqr: " + radiiSqr);
 		
		/* Check if two objects are colliding.
		 * 
		 * D^2 ≤ (R1+R2)^2 -> colliding */
		if (distBetweenCentersSqr <= radiiSqr) {
			result = true;
		}
		else {
			Vector<GameObject> temp = this.getCollectionVector();
			
			/* Check if an object is no longer colliding with
			 * this current object. Remove the object if true.*/
			if(temp.isEmpty() == false) {
				if(temp.contains(otherObject)) {
		    		temp.remove(otherObject);
		    	}
			}
		}
		
		return result;
	}
    
	/* Defines this object’s response to a collision with otherObject.
	 * If an another object collides with this object, add it to it's
	 * collision vector and ignore future collisions until they stop
	 * colliding.
	 * */
	public void handleCollision(GameWorld gameWorld, GameObject otherObject) {
    	Vector<GameObject> temp = this.getCollectionVector();
    	
    	/* Check if the other object has already collided with
    	 * this object. If so ignore the collision.
    	 * */
    	if(temp.contains(otherObject)) {
    		return;
    	}
		
		temp.add(otherObject);
    }

	
	/**
	 * Format output string to the necessary specifications mentioned
	 * in Assignment 1 PDF document.
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = "maxSpeed=" + this.maximumSpeed + " " +
						"foodConsumptionRate=" + this.foodConsumption + " ";
		return parentDesc + myDesc ;
 
	}
}
