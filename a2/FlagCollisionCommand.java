package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class FlagCollisionCommand extends Command {
	private GameWorld gameWorld;
	
	public FlagCollisionCommand(GameWorld gameWorld) {
		super("Collide with Flag");
		// store a pointer to the GameWorld created in Game Class
		this.gameWorld = gameWorld;
	}

	public void actionPerformed(ActionEvent ev) {
		Command enter = new Command("Enter");
		Command close = new Command("Close");

        TextField myTF = new TextField();
        Dialog.show(" Enter a number between 1 and 9 ", myTF,enter);
        
        /* Use a try and catch block to make sure the user
         * input is a integer value and not anything else.
         * Display an error to the game screen if the user
         * input was not a integer value.
         * */
        try {
        	// store user input in a variable
        	int userInput = Integer.parseInt(myTF.getText());
            
        	// check if the user input is a number between 1 and 9.
        	// If not, display an error to the game screen.
            if (userInput <= 9 && userInput >= 1) {
            	Ant ant = Ant.getAnt();
            	
            	// check if the user input is the ant's current flag
            	// plus one. User must capture flags in sequential order.
            	// If not, display an error to the game screen.
                if (userInput == (ant.getLastFlagReached() + 1)) {
    	        	gameWorld.flagCollision(Integer.parseInt(myTF.getText()));
    	        	gameWorld.notifyObservers(gameWorld.getTheGameObjects());
                }
                else {
                	Dialog.show("ERROR", "Must capture flags in sequential order.", close);
                }
            }
            else { 
            	Dialog.show("ERROR", "Input is not within range (1 - 9).", close);
            }
        } 
        catch (NumberFormatException error) {
        	Dialog.show("ERROR", "Input was not a number.", close);
        }
        
        
    
	}
	
}
