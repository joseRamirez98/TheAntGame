package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command {

	private GameWorld gameWorld;
	
	public SoundCommand(GameWorld gameWorld) {
		super("Sound");
		// store a pointer to the GameWorld created in Game Class
		this.gameWorld = gameWorld;	
	}
	
	public void actionPerformed(ActionEvent ev) {
		// If the sound is OFF, change the sound to ON
		// and vise versa. 
		if(gameWorld.getSound() == false) {
			System.out.println("Sound was changed to ON.\n");
			gameWorld.setSound(true);
		}
		else {
			System.out.println("Sound was changed to OFF.\n");
			gameWorld.setSound(false);
		}
		// update the score view and map view
		gameWorld.notifyObservers();
	}

}
