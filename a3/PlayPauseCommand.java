package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PlayPauseCommand extends Command {
	
	private Game game;
	private boolean wasSoundOn;

	public PlayPauseCommand(Game game) {
		super("Pause");
		this.game = game;
		this.wasSoundOn = false;
	}
	
	/* If the game is paused, and is being changed to the play
	 * state, then turn on the sound if it was previously on,
	 * unselect any objects currently selected, and set the 
	 * play/pause flag to false. Otherwise turn off the sound
	 * and change game to a paused state if the pause button
	 * was pressed.
	 * */
	public void actionPerformed(ActionEvent evt) {
		
		if(this.game.getGameIsPaused()) {
			/* Only play the background music again
			 * if the sound was on before pausing 
			 * the game.
			 * */
			if(this.wasSoundOn == true) {
				this.game.getGw().setSound(true);   // play sounds if sounds were turned on
			}
			
			this.game.getMV().unSelectObject(); // deselect any objects that were selected
			
			this.game.setGameIsPaused(false);   // un-pause the game
		}
		else {
			
			this.wasSoundOn = this.game.getGw().getSound(); // store the current sound state
			
			this.game.getGw().setSound(false); // turn off all sounds temporarily
			
			this.game.setGameIsPaused(true); // game has been paused.
		}		
	}
}
