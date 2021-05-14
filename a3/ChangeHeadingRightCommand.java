package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ChangeHeadingRightCommand extends Command {

	private GameWorld gameWorld;
	
	public ChangeHeadingRightCommand(GameWorld gameWorld) {
		super("Right");
		// store a pointer to the GameWorld created in Game Class
		this.gameWorld = gameWorld;
	}
	
	public void actionPerformed(ActionEvent ev) {
		gameWorld.changeAntHeading('r');
		// update the score view and map view
		gameWorld.notifyObservers(gameWorld.getTheGameObjects());
	}

}
