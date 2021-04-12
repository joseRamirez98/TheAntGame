package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class BrakeCommand extends Command {
	private GameWorld gameWorld;
	
	public BrakeCommand(GameWorld gameWorld) {
		super("Brake");
		// store a pointer to the GameWorld created in Game Class
		this.gameWorld = gameWorld;
	}
	
	public void actionPerformed(ActionEvent ev) {
		gameWorld.brake();
		// update the score view and map view
		gameWorld.notifyObservers();
	}


}
