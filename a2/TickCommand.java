package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TickCommand extends Command {

	private GameWorld gameWorld;
	
	public TickCommand(GameWorld gameWorld) {
		super("Tick");
		// store a pointer to the GameWorld created in Game Class
		this.gameWorld = gameWorld;
	}
	
	public void actionPerformed(ActionEvent ev) {
		gameWorld.clockTick();
		// update the score view and map view
		gameWorld.notifyObservers();
	}

}
