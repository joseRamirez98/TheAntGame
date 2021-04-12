package com.mycompany.a1;
import java.util.Vector;

public class GameWorld {

	private int clock;
	private int lives;
	private Vector<GameObject> objects;
	
	public GameWorld() {
		this.lives = 3;
		this.clock = 0;
		this.objects = new Vector<GameObject>();
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
		
		float x = flagOne.getLocation().getX();
		float y = flagOne.getLocation().getY();
		Ant ant =  new Ant(x,y);
		
		addObject(ant);
		addObject(flagOne);
		addObject(flagTwo);
		addObject(flagThree);
		addObject(flagFour);
		addObject(spiderOne);
		addObject(spiderTwo);
		addObject(spiderThree);
		addObject(foodStationOne);
		addObject(foodStationTwo);
		addObject(foodStationThree);
		addObject(foodStationFour);
				
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
	public Vector<GameObject> getObjects() {
		return objects;
	}

	/**
	 * GameObjects Vector should not be accessible to the user outside
	 * of the Game World class.
	 */
	public void setObjects(Vector<GameObject> objects) {}
	
	/**
	 * @param object is added to the Vector of GameObjects.
	 * Only one Ant can exists in the game, therefore before
	 * appending the GameObject, check if the it's an Ant. If
	 * the parameter is an instance of an Ant, check if an Ant
	 * already exists in the game; return if true otherwise
	 * append the new Ant. 
	 */
	private void addObject(GameObject object) {
		//check if parameter passed is an instance of an Ant.
		if(object instanceof Ant) {
			
			// if an Ant exists, don't add it to the vector
			// and return immediately.
			for(int i = 0; i < objects.size(); i++) {
				if(objects.elementAt(i) instanceof Ant) {
					return;
				}
			}
		}
		this.objects.addElement(object);
	}
	
	
	/**
	 *  Accelerate the speed of the ant by a small amount. Note that the
	 *  effect of acceleration is to be limited based on health level,
	 *  food level, and maximum speed.
	 */
	public void accelerate() {
		Ant ant = getAnt();
		ant.increaseSpeed();
	}
	
	/**
	 * Reduce the speed of the ant by a small amount. Note that the minimum
	 * speed for the ant is zero.
	 * */
	public void brake() {
		Ant ant = getAnt();
		ant.decreaseSpeed();
	}
	
	/**
	 * Change the heading of an Ant by by 5 degrees. 
	 * Direction parameter determines if the Ant's 
	 * heading to moves left or right.
	 */
	public void changeAntHeading(char direction) {
		Ant ant = getAnt();
		ant.changeHeading(direction);
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
			objects.clear();
			init();
			return true;
		}
		
		return false;
	}
	
	/**
	 * Function determines if the player has reached the last flag 
	 * and has won the game.
	 * 
	 * If the Ant has reached the last flag, notify the player they 
	 * have won the game.
	 * 
	 * If the Ant has not reached the last flag, then have the player
	 * continue playing the game.
	 */
	private void reachedLastFlag(Ant ant) {
		Flag lastFlag = getFlags().lastElement();
		
		if(ant.getLastFlagReached() == lastFlag.getSequenceNumber()) {
			System.out.println("Game over, you win! Total time: " + this.clock);
			System.exit(0);
		}
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
		Ant ant = getAnt();
		//lower Ant food level by the food consumption rate
		ant.setFoodLevel(ant.getFoodLevel() - ant.getFoodConsumptionRate());
		// check if the Ant has died or if the player has run out of lives
		Boolean status = checkGameStatus(ant);
		// if status is true, Ant has died, player has not died. game world is reset.
		if(status == true) {
			clock = clock + 1;
			return;
		}
		ant.move();
		
		// move all spiders
		Vector<Spider> spiders = getSpiders();
		for(int i = 0; i < spiders.size(); i++) {
			spiders.elementAt(i).move();
		}
		
		clock = clock + 1;
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
		Vector<Flag> flags = getFlags();
		
		// no flags exist in game world. do nothing and return.
		if(flags.isEmpty())
			return;
		
		Boolean flagExists = false;
		// find if the given flag number exists in the objects vector.
		for(int i = 0; i < flags.size() && flagExists != true; i++) {
			if(flagNumber  == flags.elementAt(i).getSequenceNumber()) {
				flagExists = true; // flag number has been found
			}
		}
		
		// no flag found with flagNumber parameter, return immediately.
		if(flagExists == false)
			return;
		
		Ant ant = getAnt();

		if(flagNumber == (ant.getLastFlagReached() + 1) ) {
			ant.setLastFlagReached(flagNumber);
			reachedLastFlag(ant);
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
		Ant ant = getAnt();
		if(ant == null) { // no ant was found, return
			return;
		}
		
		/*
		 * Find the first instance of FoodStation with non-zero capacity 
		 * in objects Vector. If an Ant collides with a food station add 
		 * its capacity to the ant's food capacity.
		 */
		for(int i = 0; i<objects.size() && stationFound != true; i++) {
			if(objects.elementAt(i) instanceof FoodStation) {
				FoodStation station = (FoodStation)objects.elementAt(i);
				// station capacity is non zero, and is in same location as the ant
				if((station.getCapacity() != 0) &&
				   (ant.getLocation().getX() == station.getLocation().getX()) &&
				   (ant.getLocation().getY() == station.getLocation().getY())) {
					
					capacity = station.getCapacity();
					ant.setFoodLevel(ant.getFoodLevel() + capacity);
					station.setColor(0, 200, 0); //make station light green
					station.setCapacity(0);
					stationFound = true;
				}
			}
		}
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
		Ant ant =  getAnt();
		Vector<Spider> spiders = getSpiders();
		
		//no collision occurs if no ants or spiders exist in the game world
		if(ant == null || spiders.isEmpty())
			return;
		
		// Determine if Spider has collided with Ant.
		for(int i = 0; i<spiders.size(); i++) {
			Spider spider = spiders.elementAt(i);
			// if a spider has the same location as the ant, collision has occurred
			if((spider.getLocation().getX() == ant.getLocation().getX()) &&
			   (spider.getLocation().getY() == ant.getLocation().getY())) {
				
				ant.setHealthLevel(ant.getHealthLevel() - 1);
				// check if the Ant has died or the game has been lost
				Boolean status = checkGameStatus(ant);
				// if status is true, the ant has died and the game has been reset
				if(status == true) {
					return;
				}
				ant.setColor(250, 0, 0);
				ant.decreaseSpeed();
				objects.removeElement(spider);
				Spider newSpider = new Spider();
				addObject(newSpider);
			}
		}
	}
	
	
	/**
	 * A function that returns the Ant object contained within
	 * the objects Vector. If no Ant object exists, the function
	 * will return an Ant object that points to nothing.
	 */
	private Ant getAnt() {
		Ant ant = null;
		Boolean found = false;
		
		//look for Ant in objects vector, exit loop once ant is found
		for (int i=0; i<objects.size() && found != true; i++) {
			if (objects.elementAt(i) instanceof Ant) {
				ant = (Ant)objects.elementAt(i);
				found = true;
			}
		}
		
		return ant;
	}
	
	
	/**
	 * A function that returns a Vector of Spider objects.
	 * If a Spider exists in the objects Vector, then append
	 * the Spider object to the Spiders Vector.
	 */
	private Vector<Spider> getSpiders() {
		Vector<Spider> spiders = new Vector<Spider>();
		for (int i=0; i<objects.size(); i++) {
			if (objects.elementAt(i) instanceof Spider)
				spiders.addElement((Spider) objects.elementAt(i));
		}
		return spiders;
	}
	
	/**
	 * A function that returns a Vector of Flag objects.
	 * This function assumes the flags contained in the objects
	 * Vector have been placed in the correct numerical order.
	 * For example, a flag with sequence number 2 does not
	 * come before a flag with sequence number 1 in the objects Vector.
	 */
	private Vector<Flag> getFlags() {
		Vector<Flag> flags = new Vector<Flag>();
		for (int i=0; i<objects.size(); i++) {
			if (objects.elementAt(i) instanceof Flag)
				flags.addElement((Flag) objects.elementAt(i));
		}
		return flags;
	}
	
	/**
	 * Generate a display outputting lines of text on the console
	 * describing the current game/ant state values. Values include:
	 * player lives, clock, Ant health, Ant food level, Ant's last
	 * flag reached.
	 */
	public void displayGameStatus() {
		Ant ant = getAnt();
		System.out.println("Lives: " + this.lives);
		System.out.println("Elapsed Time: " + this.clock);
		System.out.println("Last Flag Reached: " + ant.getLastFlagReached());
		System.out.println("Food Level: " + ant.getFoodLevel());
		System.out.println("Health Level: " + ant.getHealthLevel());
	}
	
	
	/**
	 * Return immediately if no objects exists in the game.  
	 * Otherwise, print all GameObjects in the objects Vector with its
	 * respective information.
	 */
	public void printAllObjects() {
		if(objects.isEmpty()) {
			System.out.println("No objects exist currently in the game.");
			return;
		}
		
		// Iterate through objects Vector and print necessary
		// information based on the GameObject.
		for (int i=0; i<objects.size(); i++) {
			if (objects.elementAt(i) instanceof Ant) {
				System.out.println ("Ant: " + objects.elementAt(i));
			}
			else if (objects.elementAt(i) instanceof Spider) {
				System.out.println ("Spider: " + objects.elementAt(i));
			}
			else if (objects.elementAt(i) instanceof FoodStation) {
				System.out.println ("FoodStation: " + objects.elementAt(i));
			}
			else {
				System.out.println ("Flag: " + objects.elementAt(i));
			}
		}
		System.out.println();
	}
	
}