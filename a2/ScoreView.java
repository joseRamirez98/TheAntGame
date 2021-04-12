/**
 * 
 */
package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;


public class ScoreView extends Container implements Observer {
	private Label timeLabel;
	private Label livesLabel;
	private Label lastFlagReachedLabel;
	private Label foodLevelLabel;
	private Label healthLevelLabel;
	private Label soundLabel;
	/**
	 * 
	 */
	public ScoreView() {

		// Initialize all the labels
		timeLabel = new Label("Time: 0");
		timeLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		livesLabel = new Label("Lives Left: 0");
		livesLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		lastFlagReachedLabel = new Label("Last Flag Reached: 0");
		lastFlagReachedLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		foodLevelLabel = new Label("Food Level: 0");
		foodLevelLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		healthLevelLabel = new Label("Health Level: 0");
		healthLevelLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		soundLabel = new Label("Sound: OFF");
		soundLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		// set the layout of the score view as flow layout
		this.setLayout(new FlowLayout(CENTER));
		
		// Add all the labels to the ScoreView container
		this.add(timeLabel);
		this.add(livesLabel);
		this.add(lastFlagReachedLabel);
		this.add(foodLevelLabel);
		this.add(healthLevelLabel);
		this.add(soundLabel);
		
	}

	@Override
	public void update(Observable gameWorld, Object gameObjectCollection) {
		// type cast the Observable object into a Game World object
		GameWorld gw = (GameWorld) gameWorld;
		// Get the Ant object
		Ant ant = Ant.getAnt();
		
		// Update all the label with the necessary information
		timeLabel.setText("Time: " + gw.getClock());
		livesLabel.setText("Lives: " + gw.getLives());
		lastFlagReachedLabel.setText("Last Flag Reached: " + ant.getLastFlagReached());
		foodLevelLabel.setText("Food Level: " + ant.getFoodLevel());
		healthLevelLabel.setText("Health Level: " + ant.getHealthLevel());
		
		// change the sound label depending on
		// status of the sound variable from Game World
		if(gw.getSound()) {
			soundLabel.setText("Sound: ON");
		}
		else {
			soundLabel.setText("Sound: OFF");
		}
	}

}
