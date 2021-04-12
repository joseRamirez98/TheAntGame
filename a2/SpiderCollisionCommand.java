package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SpiderCollisionCommand extends Command {

	private GameWorld gameWorld;
	
	public SpiderCollisionCommand(GameWorld gameWorld) {
		super("Collide with Spider");
		// store a pointer to the GameWorld created in Game Class
		this.gameWorld = gameWorld;
		
	}
	
	public void actionPerformed(ActionEvent ev) {
		gameWorld.spiderCollision();
		// update the score view and the map view
		gameWorld.notifyObservers(gameWorld.getTheGameObjects());
	}


}
