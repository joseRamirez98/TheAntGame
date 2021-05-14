package com.mycompany.a3;
import java.util.Observable;
import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Dimension;

public class GameWorld extends Observable{

	private int clock;
	private int lives;
	private GameObjectCollection theGameObjects;
	
	private int width;
	private int height;
	
	private boolean sound;
	private BGSound bgSound;
	private Sound   flagCollisionSound;
    private Sound   spiderCollisionSound;
    private Sound   fsCollisionSound;
	
	public GameWorld() {
		this.lives = 3;
		this.clock = 0;
		this.theGameObjects = new GameObjectCollection();
		this.sound = true;
		this.width = 0;
		this.height = 0;
		//this.setChanged();
	}
	
	/*
	 * Instantiate a world with the following:
	 * - four flags in random locations
	 * - at least two spiders in random locations
	 * - at least 2 food stations
	 * - one Ant at the location of the first flag 
	 * */
	public void init(int width, int height) {
		/* Subtract 50 from the width and height so the objects
		 * that are randomly generated aren't generated near the
		 * edge of the map view.
		 * */
		this.width = width - 50;
		this.height = height - 50;
		
		Flag flagOne   =  new Flag(100, (float)200.0, (float)200.0, 1);
		Flag flagTwo   =  new Flag(100, (float)200.0, (float)800.0, 2);
		Flag flagThree =  new Flag(100, (float)700.0, (float)800.0, 3);
		Flag flagFour  =  new Flag(100, (float)900.0, (float)400.0, 4);
		
		Spider spiderOne = new Spider(this.width, this.height);
		Spider spiderTwo = new Spider(this.width, this.height);
		Spider spiderThree = new Spider(this.width, this.height);
		
		FoodStation foodStationOne =  new FoodStation(this.width, this.height);
		FoodStation foodStationTwo =  new FoodStation(this.width, this.height);
		FoodStation foodStationThree =  new FoodStation(this.width, this.height);
		FoodStation foodStationFour =  new FoodStation(this.width, this.height);
		
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
	
	/* ONLY USE THIS INIT FUNCTION TO RE-INITIALIZE 
	 * THE GAME WORLD.
	 * 
	 * Instantiate a world with the following:
	 * - four flags in random locations
	 * - at least two spiders in random locations
	 * - at least 2 food stations
	 * - one Ant at the location of the first flag 
	 * */
	private void init() {
		
		Flag flagOne   =  new Flag(100, (float)200.0, (float)200.0, 1);
		Flag flagTwo   =  new Flag(100, (float)200.0, (float)800.0, 2);
		Flag flagThree =  new Flag(100, (float)700.0, (float)800.0, 3);
		Flag flagFour  =  new Flag(100, (float)900.0, (float)400.0, 4);
		
		Spider spiderOne = new Spider(this.width, this.height);
		Spider spiderTwo = new Spider(this.width, this.height);
		Spider spiderThree = new Spider(this.width, this.height);
		
		FoodStation foodStationOne =  new FoodStation(this.width, this.height);
		FoodStation foodStationTwo =  new FoodStation(this.width, this.height);
		FoodStation foodStationThree =  new FoodStation(this.width, this.height);
		FoodStation foodStationFour =  new FoodStation(this.width, this.height);
		
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
	
	/*_________________________________________________________________________*/
	
	/* BEGININNG OF GETTERS AND SETTERS */

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
		
		if(this.sound == true) {
			bgSound.run();
		}
		else {
			bgSound.pause();
		}
		
		this.setChanged();
	}

	/* END OF GETTERS AND SETTERS */
	/*_________________________________________________________________________*/
	
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
	
	/* Initialize all the necessary sounds. */
	public void createSounds() {
		bgSound              = new BGSound("background.wav");
        flagCollisionSound   = new Sound("flag.wav");
        spiderCollisionSound = new Sound("spider.wav");
        fsCollisionSound     = new Sound("foodstation.wav");
        
        bgSound.run();
	}

	/*
	 * A clock tick in the game world has the following effects:
	 * - all movable objects are told to update their positions 
	 *   according to their current heading and speed, and 
	 * - the ant’s food level is reduced by the amount indicated by its foodConsumptionRate
	 * - the elapsed time “game clock” is incremented by one
	 * - checks for any collisions
	 * - checks status of the game
	 * */
	public void clockTick(Dimension dCmpSize, int elapsedTime) {
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
		
		ant.move(dCmpSize.getWidth(), dCmpSize.getHeight(), elapsedTime);
		
		// Get the Iterator.
		IIterator theElements = theGameObjects.getIterator();

		/*
		 * Move all the spiders in the GameObjectCollection
		 */	
		while(theElements.hasNext()) {
			GameObject currObject = (GameObject) theElements.getNext();
			if(currObject instanceof Spider) {
				Spider spider = (Spider) currObject;
 				spider.move(dCmpSize.getWidth(), dCmpSize.getHeight(), elapsedTime);
			}
		}
		
		// check if moving caused any collisions
        IIterator iter = theGameObjects.getIterator(); 

        while(iter.hasNext()){
        	// get a collidable object // check if this object collides with any OTHER object
            ICollider curObj = (ICollider)iter.getNext(); 
            
            IIterator iter2 = theGameObjects.getIterator(); 
            while(iter2.hasNext()){
                    ICollider otherObj = (ICollider) iter2.getNext(); // get a collidable object
                    // check for collision
                    if(otherObj!=curObj){ // make sure it's not the SAME object
	                    if(curObj.collidesWith((GameObject) otherObj)){
	                        curObj.handleCollision(this, (GameObject) otherObj);
	                    }
                    }
            }
        }
		
		clock = clock + 1;
		System.out.println("Clock tick occured. Clock: " + this.clock + "\n");
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
	public void flagCollision(Flag flag) {
		// if the user inputs 1, do nothing since Ant starts at flag one
		if(flag.getSequenceNumber() == 1) {
			return;
		}
		
		
        Ant ant = Ant.getAnt();
        
        /* If the Ant collides with a flag that has the same
         * flag number as the ant, ignore the collision. */
        if (flag.getSequenceNumber() == ant.getLastFlagReached())  {
			return;
		}
        
     /* if the flag is the next flag in the sequence update the Ant's
      * current flag.
      */
        if (flag.getSequenceNumber() == (ant.getLastFlagReached() + 1))  {
            ant.setLastFlagReached(flag.getSequenceNumber()); // change LastFlagReached to the flag
            this.checkIfGameWon(ant);
            
            /* play the flag collision sound
             * if the sound variable is true
             */
            if(this.sound == true) {
            	flagCollisionSound.play();
            }
            
            System.out.println("Collison with flag " + flag.getSequenceNumber() + " occured.\n");
        }
		
		this.setChanged();
	}
	
	/* Check if the Ant has won the game. 
	 * Exit the game if Ant won the game.*/
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
	public void foodStationCollision(FoodStation station) {
		if(station.getCapacity() == 0) {
			return;
		}
		
		int capacity = 0;
		Ant ant = Ant.getAnt();

		// station capacity is non zero, and is in same location as the ant
		if(station.getCapacity() != 0) {			
			capacity = station.getCapacity();
			ant.setFoodLevel(ant.getFoodLevel() + capacity);
			station.setColor(0, 200, 0); //make station light green
			station.setCapacity(0);
			/* play the food station collision sound
	         * if the sound variable is true
	         */
	        if(this.sound == true) {
	        	fsCollisionSound.play();
	        }
		}
					
		System.out.println("Collison with food station occured.\n");

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
	public void spiderCollision(Spider spider) {
		Ant ant =  Ant.getAnt();
							
		ant.setHealthLevel(ant.getHealthLevel() - 1);
		// check if the Ant has died or the game has been lost
		Boolean status = checkGameStatus(ant);
		// if status is true, the ant has died and the game has been reset
		if(status == true) {
			return;
		}
		
		int[] color = ant.getColor();
		/* Make the Ant's red hue color a lighter red every
		 * time a collision happens.
		 * */
		ant.setColor(color[0]-15, color[1], color[2]);
		ant.decreaseSpeed();
		theGameObjects.remove(spider);
		Spider newSpider = new Spider();
		theGameObjects.add(newSpider);
		
		/* play the spider collision sound
         * if the sound variable is true
         */
        if(this.sound == true) {
        	spiderCollisionSound.play();
        }
		
		System.out.println("Collison with Spider occured.\n");
		
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