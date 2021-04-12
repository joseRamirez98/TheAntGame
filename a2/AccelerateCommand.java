package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AccelerateCommand extends Command {
	private GameWorld gameWorld;
	
	public AccelerateCommand(GameWorld gameWorld) {
		super("Accelerate");
		// store a pointer to the GameWorld created in Game Class
		this.gameWorld = gameWorld;
		
	}
	
	public void actionPerformed(ActionEvent ev) {
		gameWorld.accelerate();
		// update the score view and map view
		gameWorld.notifyObservers(gameWorld.getTheGameObjects());
	}

}
