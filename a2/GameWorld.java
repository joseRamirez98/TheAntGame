package com.mycompany.a2;
import java.util.Observable;
import java.util.Vector;

public class GameWorld extends Observable{

	private int clock;
	private int lives;
	private GameObjectCollection theGameObjects;
	private boolean sound;
	private double width; // the map view's width
	private double height; //  the map view's height
	
	public GameWorld() {
		this.lives = 3;
		this.clock = 0;
		this.theGameObjects = new GameObjectCollection();
		this.sound = false;
		this.setWidth(0.0);
		this.setHeight(0.0);
	}
	
	/*
	 * Instantiate a world with the following:
	 * - four flags in random locations
	 * - at least two spiders in random locations
	 * - at least 2 food stations
	 * - one Ant at the location of the first flag 
	 * */
	public void init() {		
		Flag flagOne   =  new Flag(10, (float)200.0, (float)200.0, 1);
		Flag flagTwo   =  new Flag(10, (float)200.0, (float)800.0, 2);
		Flag flagThree =  new Flag(10, (float)700.0, (float)800.0, 3);
		Flag flagFour  =  new Flag(10, (float)900.0, (float)400.0, 4);
		
		Spider spiderOne = new Spider();
		Spider spiderTwo = new Spider();
		Spider spiderThree = new Spider();
		
		FoodStation foodStationOne =  new FoodStation();
		FoodStation foodStationTwo =  new FoodStation();
		FoodStation foodStationThree =  new FoodStation();
		FoodStation foodStationFour =  new FoodStation();
		
		// set the ants location the same as flag one
		float x = flagOne.getLocation().getX();
		float y = flagOne.getLocation().getY();
		Ant ant =  Ant.getAnt();
		ant.setLocation(x, y);
		
		theGameObjects.add(ant);
		
		theGameObjects.add(spiderOne);
		theGameObjects.add(spiderTwo);
		theGameObjects.add(spiderThree);
		
		theGameObjects.add(foodStationOne);
		theGameObjects.add(foodStationTwo);
		theGameObjects.add(foodStationThree);
		theGameObjects.add(foodStationFour);
		
		theGameObjects.add(flagOne);
		theGameObjects.add(flagTwo);
		theGameObjects.add(flagThree);
		theGameObjects.add(flagFour);
		
		this.setChanged();
				
	}

	/**
	 * @return the clock
	 */
	public int getClock() {
		return clock;
	}

	/**
	 * @param clock the clock to set
	 */
	public void setClock(int clock) {
		this.clock = clock;
	}

	/**
	 * @return the lives
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * @param lives the lives to set
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

	/**
	 * @return the objects
	 */
	public GameObjectCollection getTheGameObjects() {
		return theGameObjects;
	}

	/**
	 * GameObjects Vector should not be accessible to the user outside
	 * of the Game World class.
	 */
	public void setObjects(Vector<GameObject> objects) {}
	
	/**
	 * @return the sound
	 */
	public boolean getSound() {
		return sound;
	}

	/**
	 * @param this.sound is set to sound
	 */
	public void setSound(boolean sound) {
		this.sound = sound;
		this.setChanged();
	}
	
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	

	
	/**
	 *  Accelerate the speed of the ant by a small amount. Note that the
	 *  effect of acceleration is to be limited based on health level,
	 *  food level, and maximum speed.
	 */
	public void accelerate() {
		Ant ant = Ant.getAnt();
		ant.increaseSpeed();
		System.out.println("The Ant's speed has increased.\n");
		this.setChanged();
	}
	
	/**
	 * Reduce the speed of the ant by a small amount. Note that the minimum
	 * speed for the ant is zero.
	 * */
	public void brake() {
		Ant ant = Ant.getAnt();
		ant.decreaseSpeed();
		System.out.println("The Ant's speed has decreased.\n");
		this.setChanged();
	}
	
	/**
	 * Change the heading of an Ant by by 5 degrees. 
	 * Direction parameter determines if the Ant's 
	 * heading to moves left or right.
	 */
	public void changeAntHeading(char direction) {
		Ant ant = Ant.getAnt();
		ant.changeHeading(direction);
		System.out.println("Changed ant heading.\n");
		this.setChanged();
	}
	
	/**
	 * Function returns a Boolean based on the status of the game world. 
	 * 
	 * The purpose of this function is to check if the player has died and
	 * needs the game world to be reset, or if the player has lost the game.
	 * If the player has lost the game terminate the game. 
	 * 
	 * Returns true when the Ant has died, but player has not lost the game.
	 * 
	 * Return false if the Ant has not died.
	 */
	private Boolean checkGameStatus(Ant ant) {
		if(ant.getFoodLevel() <= 0 || ant.getHealthLevel() <= 0) {
			this.lives = this.lives - 1;
			if(this.lives <= 0) {
				System.out.println("Game over, you failed!");
				System.exit(0);
			}
			theGameObjects.clear();
			ant.reset();
			init();
			return true;
		}
		
		return false;
	}
	

	/*
	 * A clock tick in the game world has the following effects:
	 * - Spiders update their heading
	 * - all movable objects are told to update their positions 
	 *   according to their current heading and speed, and 
	 * - the ant’s food level is reduced by the amount indicated by its foodConsumptionRate
	 * - the elapsed time “game clock” is incremented by one
	 * - checks status of the game
	 * */
	public void clockTick() {
		Ant ant = Ant.getAnt();
		//lower Ant food level by the food consumption rate
		ant.setFoodLevel(ant.getFoodLevel() - ant.getFoodConsumptionRate());
		// check if the Ant has died or if the player has run out of lives
		Boolean status = checkGameStatus(ant);
		// if status is true, Ant has died, player has not died. game world is reset.
		if(status == true) {
			clock = clock + 1;
			return;
		}
		ant.move(this.width, this.height);
		
		// move all spiders
		IIterator theElements = theGameObjects.getIterator();

		/*
		 * Move all the spiders in the GameObjectCollection
		 */
		
		while(theElements.hasNext()) {
			GameObject currObject = (GameObject) theElements.getNext();
			if(currObject instanceof Spider) {
				Spider spider = (Spider) currObject;
 				spider.move(this.width, this.height);
			}
		}
		
		clock = clock + 1;
		System.out.println("Clock tick occured.\n");
		this.setChanged();
	}
	
	/**
	 * Detects if an Ant has intersected a Flag. FlagNumber parameter 
	 * determines which flag has been intersected. Check to see whether 
	 * the flag number is exactly one greater than the flag indicated by 
	 * lastFlagReached field of the ant and if so, update the lastFlagReached
	 * field of the ant by increasing it by one. Function also checks if
	 * flag number parameter passed by user exists in the current game objects.
	 */
	public void flagCollision(int flagNumber) {
		// if the user inputs 1, do nothing since Ant starts at flag one
		if(flagNumber == 1) {
			return;
		}
		
        Ant ant = Ant.getAnt();
        if (flagNumber == (ant.getLastFlagReached() + 1))  {// if the flag is the next flag in the game
            ant.setLastFlagReached(flagNumber); // change LastFlagReached to the flag
            this.checkIfGameWon(ant);
            System.out.println("Collison with flag " + flagNumber + " occured.\n");
        }
		
		
		this.setChanged();
	}
	
	private void checkIfGameWon(Ant ant) {
		
		IIterator theElements = theGameObjects.getIterator();
		int highestSeqenceNum = 0; // holds the highest flag sequence number
		
		// loop through all objects to find the highest sequence number
		while(theElements.hasNext()) {
			GameObject object = (GameObject) theElements.getNext();
			if(object instanceof Flag) {
				Flag flag = (Flag) object;
				if(flag.getSequenceNumber() > highestSeqenceNum) {
					highestSeqenceNum = flag.getSequenceNumber();
				}
			}
			
		}
		
		// player has won the game if they reached the last flag
		if(ant.getLastFlagReached() == highestSeqenceNum) {
			System.out.println("Game over, you win! Total time: " + this.clock);
			System.exit(0);
		}
	}

	/**
	 * Detects if an Ant has intersected with a FoodStation. 
	 * If an Ant intersects a FoodStation, set the FoodStation color
	 * to light green, and set it's capacity to zero. Add the capacity of
	 * food from the FoodStation to the Ant's foodLevel.
	 * 
	 *  Collision currently is artificial. 
	 */
	public void foodStationCollision() {
		int capacity = 0;
		Boolean stationFound = false;
		Ant ant = Ant.getAnt();
		IIterator theElements = theGameObjects.getIterator();

		/*
		 * Find the first instance of FoodStation with non-zero capacity 
		 * in objects Vector. If an Ant collides with a food station add 
		 * its capacity to the ant's food capacity.
		 */
		
		while(theElements.hasNext() && stationFound != true) {
			GameObject currObject = (GameObject) theElements.getNext();
			if(currObject instanceof FoodStation) {
				FoodStation station = (FoodStation) currObject;
				// station capacity is non zero, and is in same location as the ant
				if(station.getCapacity() != 0) {			
					capacity = station.getCapacity();
					ant.setFoodLevel(ant.getFoodLevel() + capacity);
					station.setColor(0, 200, 0); //make station light green
					station.setCapacity(0);
					stationFound = true;
					
					System.out.println("Collison with food station occured.\n");
				}
			}
		}
		
		this.setChanged();
	}
	
	
	/**
	 * Detect if an Ant has collided with a Spider. 
	 * 
	 * If an Ant and Spider collide, check if the Ant has died and if the
	 * game needs to be reset or if the player has lost the game.
	 * 
	 * If Ant has not died and the player has not lost the game, 
	 * decrement the Ant's health level, and speed; set the color to light red. 
	 * Also, delete the Spider object that collided with Ant; create a new Spider
	 * object, and append the new Spider object to the objects vector.
	 */
	public void spiderCollision() {
		Ant ant =  Ant.getAnt();
		IIterator theElements = theGameObjects.getIterator();
		boolean collisonOccured = false;
		// Determine if Spider has collided with Ant.
		while(theElements.hasNext() && collisonOccured != true) {
			
			GameObject currObject = (GameObject) theElements.getNext();
			if(currObject instanceof Spider) {
				Spider spider = (Spider) currObject;					
				ant.setHealthLevel(ant.getHealthLevel() - 1);
				// check if the Ant has died or the game has been lost
				Boolean status = checkGameStatus(ant);
				// if status is true, the ant has died and the game has been reset
				if(status == true) {
					return;
				}
				ant.setColor(250, 0, 0);
				ant.decreaseSpeed();
				theGameObjects.remove(spider);
				Spider newSpider = new Spider();
				theGameObjects.add(newSpider);
				collisonOccured = true;
				
				System.out.println("Collison with Spider occured.\n");
			}
		}
		
		this.setChanged();
	}
		
	
	/**
	 * Return immediately if no objects exists in the game.  
	 * Otherwise, print all GameObjects in the objects Vector with its
	 * respective information.
	 */
	public void printAllObjects() {
		IIterator theElements = theGameObjects.getIterator();
		
		// Iterate through objects Vector and print necessary
		// information based on the GameObject.
		while (theElements.hasNext()) {
			
			GameObject object = (GameObject) theElements.getNext();
			
			if (object instanceof Ant) {
				System.out.println ("Ant: " + (Ant)object);
			}
			else if (object instanceof Spider) {
				System.out.println ("Spider: " + (Spider) object);
			}
			else if (object instanceof FoodStation) {
				System.out.println ("FoodStation: " + (FoodStation)object);
			}
			else {
				System.out.println ("Flag: " + (Flag)object);
			}
		}
		System.out.println();
	}

	
	
}