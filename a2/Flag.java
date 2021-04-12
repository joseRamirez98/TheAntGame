package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class Flag extends Fixed {
	private int sequenceNumber;

	// flags should be given a size, a location, and sequence number by the user
	public Flag(int size, float locX, float locY, int sequenceNumber) {
		super(size, locX, locY, ColorUtil.rgb(0,0,255));
		this.sequenceNumber = sequenceNumber;
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
	 * Once Flag location is set through Flag constructor, it should not change.
	 * Override Grandparent GameObject setLocation function to prevent changes
	 * being made.
	 */
	public void setLocation(float x, float y) {}
	
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
	

}
