package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private GameWorld gameWorld;
	private Game game;
	private boolean reDrawObj;
	
	public MapView(GameWorld gameWorld, Game game) {
		this.gameWorld = gameWorld;
		this.game = game;
		this.reDrawObj = false;
		
		// create a red border line around the map view
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.rgb(255,0,0)));
	}

	@Override
	public void update(Observable gameWorld, Object gameObjectsCollection) {
        // type cast the Observable object into a Game World object
		this.gameWorld.printAllObjects();
		repaint();
	}
	
	/* Draw all Game Objects on the Map View. */
	public void paint(Graphics g) {
		super.paint(g);
		
		
		// store the coordinates of the container's origin
		Point pCmpRelPrnt = new Point(getX(), getY());
		
		// get the iterator to iterate through the
		// game objects vector
		IIterator theElements = gameWorld.getTheGameObjects().getIterator();
		
		while(theElements.hasNext()) {
			GameObject currObject = (GameObject) theElements.getNext();
			
			if(currObject instanceof Ant) {
				Ant ant = (Ant)currObject;
				ant.draw(g, pCmpRelPrnt);
			}
			else if(currObject instanceof Spider) {
				Spider spider = (Spider)currObject;
				spider.draw(g, pCmpRelPrnt);
			}
			else if(currObject instanceof FoodStation) {
				FoodStation foodStation = (FoodStation)currObject;
				foodStation.draw(g, pCmpRelPrnt);
			}
			else if(currObject instanceof Flag) {
				Flag flag = (Flag)currObject;
				flag.draw(g, pCmpRelPrnt);
			}
		} 
		
	}
	
	/* This function selects, deselects, and moves objects based on
	 * whether an object has been clicked on. The only way a object
	 * can move is if it has been selected and the reDrawObj flag
	 * has been set by the Position command. Objects can only be
	 * selected if the game is currently paused. Repaint the map view
	 * to display the selected/deselected object in the map view.
	 * 
	 * */
	@Override
	public void pointerPressed(int x,int y) {
		//make pointer location relative to parent’s origin
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());
		 
		/* Only allow Pointer Selection if the Game
		 * is paused.
		 * */
		if(game.getGameIsPaused()) {
			
			// Get the Iterator
			IIterator theElements = gameWorld.getTheGameObjects().getIterator();
			
			
			// Find all Selectable Objects in the Collection
			while(theElements.hasNext()) {
				GameObject currObject = (GameObject) theElements.getNext();
				
				if(currObject instanceof Flag) {
					Flag flag = (Flag) currObject;
					
					/* check if the pointer is in the same location of a flag */
					if(flag.contains(pPtrRelPrnt, pCmpRelPrnt)) {
						flag.setSelected(true);
					}
					else {
						/* Pointer is at location where there's no object.
						 * The only way to re draw a Selectable object to 
						 * a new location is if its been selected and the
						 * Position button has been pressed. */
						if(flag.isSelected() && this.shouldReDrawObj()) {
							float xLoc = pPtrRelPrnt.getX() - pCmpRelPrnt.getX();
							float yLoc = pPtrRelPrnt.getY() - pCmpRelPrnt.getY();
							
							flag.setLocation(xLoc, yLoc);
							this.reDrawObj = false; // reset variable
						}
						
						/* de-select a flag */
						flag.setSelected(false);
					}
	 				
				}
				else if(currObject instanceof FoodStation) {
					FoodStation station = (FoodStation) currObject;
	 				
					/* check if the pointer is in the same location of a food station */
					if(station.contains(pPtrRelPrnt, pCmpRelPrnt)) {
						station.setSelected(true);
					}
					else {
						/* The only way to re draw an Selectable object to 
						 * a new location is if its been selected and the
						 * Position button has been pressed. */
						if(station.isSelected() && this.shouldReDrawObj()) {
							float xLoc = pPtrRelPrnt.getX() - pCmpRelPrnt.getX();
							float yLoc = pPtrRelPrnt.getY() - pCmpRelPrnt.getY();
							
							station.setLocation(xLoc, yLoc);
							this.reDrawObj = false; // reset variable
						}
						
						/* De-select the food station */
						station.setSelected(false);
					}
				} // END OF ELSE IF
			} // END OF WHILE LOOP
			
			repaint();
		} // END OF IF STATEMENT
	}
	
	/* This function deselctes all objects that are currently selected.
	 * Selection is only allowed in pause mode, and switching to play 
	 * mode automatically “unselects” the object.
	 * */
	public void unSelectObject() {
		// Get the Iterator
		IIterator theElements = gameWorld.getTheGameObjects().getIterator();
		
		
		// Find all ISelectable Objects in the Collection
		while(theElements.hasNext()) {
			GameObject currObject = (GameObject) theElements.getNext();
			
			if(currObject instanceof Flag) {
				Flag flag = (Flag) currObject;
				
				if(flag.isSelected()) {
					flag.setSelected(false);
				}
 				
			}
			else if(currObject instanceof FoodStation) {
				FoodStation station = (FoodStation) currObject;
 				
				if(station.isSelected()) {
					station.setSelected(false);
				}
			}
		}
		
		repaint();
	}
	
	/* Return the reDrawObj value */
	public boolean shouldReDrawObj() {
		return this.reDrawObj;
	}
	/* Set the reDrawObj value to the parameter's value. */
	public void setReDrawObj(boolean objectSelected) {
		this.reDrawObj = objectSelected;
	}


}
