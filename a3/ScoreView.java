/**
 * 
 */
package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;


public class ScoreView extends Container implements Observer{


	private Label clock;
	private Label livesLeft;
	private Label lastFlagReached;
	private Label antFoodLevel;
	private Label antHealthLevel;
	private Label sound;

	/* Sets the Layout of the ScoreView as FlowLayout
	 * and initializes all the labels necessary to print
	 * relevant information to the screen.
	 * */
	public ScoreView (){ 

		this.setLayout(new FlowLayout(CENTER)); 	//Arranges components left-to-right
		
		Label clockText = new Label("Time:");	
		clock = new Label("0");	
		this.add(clockText);
		this.add(clock);
		//styles
		clock.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		clockText.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));	
		
		Label lastFlagReachedText = new Label("Last Flag Reached:");
		lastFlagReached = new Label("1");
		this.add(lastFlagReachedText);
		this.add(lastFlagReached);
		//styles
		lastFlagReachedText.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		lastFlagReached.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));

		Label livesText = new Label("Lives Left:");
		livesLeft = new Label("0");
		this.add(livesText);
		this.add(livesLeft);
		//styles
		livesText.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		livesLeft.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));

	
		Label healthLevelText = new Label("Health Level:");
		antHealthLevel = new Label("0");
		this.add(healthLevelText);
		this.add(antHealthLevel);
		//styles
		healthLevelText.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		antHealthLevel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));

		Label foodLevelText = new Label("Food Level:");
		antFoodLevel = new Label("0");
		this.add(foodLevelText);
		this.add(antFoodLevel);
		//styles
		foodLevelText.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		antFoodLevel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));

	
		Label soundText = new Label("Sound:");
		sound = new Label("ON");
		this.add(soundText);
		this.add(sound);
		//styles
		soundText.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		sound.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
	}

	/* Updates the Score View with new information. */
	@Override
	public void update (Observable gameWorld, Object gc) {

		GameWorld gw =(GameWorld) gameWorld;
		Ant ant = Ant.getAnt();
		clock.setText(Integer.toString(gw.getClock()));
		livesLeft.setText(Integer.toString(gw.getLives()));
		lastFlagReached.setText(Integer.toString(ant.getLastFlagReached()));
		antFoodLevel.setText( Integer.toString(ant.getFoodLevel()));
		antHealthLevel.setText( Integer.toString(ant.getHealthLevel()));
		
		if(gw.getSound()) {
			sound.setText("ON");
			System.out.println("Sound is : ON ");
		}
		else {
			sound.setText("OFF");
			System.out.println("Sound is : OFF ");

		}
	} 

}
