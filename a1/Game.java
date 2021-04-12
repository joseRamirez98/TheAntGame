package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent; 
import java.lang.String;

public class Game extends Form {
	private GameWorld gw;
	private Boolean exitFlag;
	
	public Game(){ 
		this.gw = new GameWorld();
		this.exitFlag = false;
		gw.init();
		play();		
	}
	

	private void play() {
		Label myLabel= new Label("Enter a Command: ");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();
		
		
		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				if(sCommand.length() != 0) {
					switch(sCommand.charAt(0)) {
					case 'a': //tell the game world to accelerate (increase the speed of) the ant.
						gw.accelerate();
						break;
					case 'b': //tell the game world to brake (reduce the speed of) the ant.
						gw.brake();
						break;
					case 'l': //tell the game world to change the heading of the ant by 5 degrees to the left.
						gw.changeAntHeading(sCommand.charAt(0));
						break;
					case 'r': //tell the game world to change the heading of the ant by 5 degrees to the right.
						gw.changeAntHeading(sCommand.charAt(0));
						break;
					case 'f': //Pretend that the ant has collided with a food station.
						gw.foodStationCollision();
						break;
					case 'g': //Pretend that a spider has gotten to the same location and collided with the ant.
						gw.spiderCollision();
						break;
					case 't': // Tell the game world that the â€œgame clockâ€� has ticked.
						gw.clockTick();
						break;
					case 'd': //generate a display of text on the console describing the current game/ant state values.
						gw.displayGameStatus();
						break;
					case 'm': //tell the game world to output a map a showing the current world (refer to PDF document).
						gw.printAllObjects();
						break;
					case 'x': //set flag to true, user wants to quit the game.
						exitFlag = true;
						break;
					case 'y': //if the flag is set to true, use the exit system call, otherwise ignore input.
						if(exitFlag)
							System.exit(0);
						break;
					case 'n': //if the flag is set to true, user does not want to quit the game, so reset flag. Otherwise ignore input.
						if(exitFlag)
							exitFlag = false;
						break;	
					default: //Pretend that the ant has collided with (walked over) the flag number x
						// Use the ASCII value of the input to determine if input is the 
						// ASCII number 1 - ASCII value 9. (1 and 9 inclusive).
						if( 49 <= sCommand.charAt(0) && sCommand.charAt(0) <=57) { 
							gw.flagCollision(Integer.parseInt(sCommand));
						}
						break;
					}
			}
		} //new ActionListener()
		}); //addActionListener
	} //end of play

	/**
	 * @return the gw
	 */
	public GameWorld getGw() {
		return gw;
	}

	/**
	 * @param gw set to gw data member
	 */
	public void setGw(GameWorld gw) {
		this.gw = gw;
	}
	
	/**
	 * @return exitFlag 
	 */
	public Boolean getExitFlag() {
		return exitFlag;
	}

	/**
	 * @param exitFlag set to gwexitFlag data member
	 */
	public void setExitFlag(Boolean exitFlag) {}	
 }
