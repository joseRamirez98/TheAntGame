package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;


public class FoodStation extends Fixed {

	private int capacity;
	
	public FoodStation() {
		//super(size, locX, locY, color)
		super(getRandomInt(10,21), 
			  getRandomFloat((float)0.0, (float)1001.0), 
			  getRandomFloat((float)0.0, (float)1001.0),
			  ColorUtil.rgb(0, 255, 0));
		this.capacity = (int) super.getSize()/3; //1/3 proportional to the size of the food station
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
	 * Once FoodStation location is set through FoodStation constructor, it should not change.
	 * Override Grandparent GameObject setLocation function to prevent changes
	 * being made.
	 */
	public void setLocation(float x, float y) {}
	
	/**
	 * Format output string to the necessary specifications mentioned
	 * in Assignment 1 PDF document.
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = "capacity=" + this.capacity;
		return parentDesc + myDesc ;
 
	}
	
}
