package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class FoodStationCollisionCommand extends Command {

	private GameWorld gameWorld;
	
	public FoodStationCollisionCommand(GameWorld gameWorld) {
		super("Collide with Food Station");
		// store a pointer to the GameWorld created in Game Class
		this.gameWorld = gameWorld;
	}
	
	public void actionPerformed(ActionEvent ev) {
		gameWorld.foodStationCollision();
		// update the score view and map view
		gameWorld.notifyObservers();
	}

}
