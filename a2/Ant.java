package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

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
		super(0, getRandomInt(2,5), 20, 
			  getRandomFloat((float)0.0, (float)1000.0), 
			  getRandomFloat((float)0.0, (float)1000.0),
			  ColorUtil.rgb(255, 0, 0));
		
		this.lastFlagReached = 1;
		this.maximumSpeed = getRandomInt(4,9);
		this.foodLevel= 5;
		this.foodConsumption = 2;
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
	
	public void move(double maxWidth, double maxHeight) {
		// If Ant's food level, health level, or speed is zero, then it shouldn't move
		if(foodLevel == 0 | healthLevel == 0 | super.getSpeed() == 0) {
			return;
		}

		double minWidth = 0.0;
		double minHeight = 0.0;
		double radianAngle = Math.toRadians(90 - super.getHeading());
		
		//newLocation(x,y) = (deltaX, deltaY) + oldLocation(x,y) 
		double deltaX = (Math.cos(radianAngle) * (double) super.getSpeed()) + super.getLocation().getX();
		double deltaY = (Math.sin(radianAngle) * (double) super.getSpeed()) + super.getLocation().getY();
		
		// don't move if Ant's new location is out of bounds
		if(deltaX < minWidth || deltaX > maxWidth || deltaY < minHeight || deltaY > maxHeight) {
			return;
		}
		
		super.setLocation((float) deltaX, (float) deltaY);
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
