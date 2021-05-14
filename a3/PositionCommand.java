package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PositionCommand extends Command {
	private MapView mapView;
	private GameWorld gameWorld;
	private Game game;

	/* Initialize private data members. */
	public PositionCommand(Game game, GameWorld gw, MapView mv) {
		super("Position");
		this.mapView = mv;
		this.gameWorld = gw;
		this.game = game;
	}
	
	/* Sets the flag to re draw an object in the map view
	 * to true only if the game is paused and an object
	 * has been already selected.
	 * */
	public void actionPerformed(ActionEvent ev) {
		if(game.getGameIsPaused()) {
			// Get the Iterator
			IIterator theElements = this.gameWorld.getTheGameObjects().getIterator();
			
			
			/* Find all ISelectable Objects in the Collection */
			while(theElements.hasNext()) {
				GameObject currObject = (GameObject) theElements.getNext();
				
				if(currObject instanceof Flag) {
					Flag flag = (Flag) currObject;
					
					/* Check if a flag has already been selected. */
					if(flag.isSelected()) {
						System.out.println("\nFlag Is Selected");
						mapView.setReDrawObj(true);
					}
				}
				else if(currObject instanceof FoodStation) {
					FoodStation station = (FoodStation) currObject;
					
					/* Check if a food station has already been selected. */
					if(station.isSelected()) {
						System.out.println("\nStation is Selected");
						mapView.setReDrawObj(true);
					}
				}
			}
			
			/* Tell the user the position button has been pressed. */
			System.out.println("\nPosition button pressed");
		}
		
	}

}
