package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Toolbar;

public class Game extends Form {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
	public Game(){ 
		this.gw = new GameWorld();
		this.mv = new MapView();
		this.sv = new ScoreView();
		
		gw.addObserver(mv);
		gw.addObserver(sv);
		// set the layout of the Form as border layout
		this.setLayout(new BorderLayout());
		// initialize Game World
		gw.init();
	
	// BEGINING OF WEST CONTAINER CREATION
		Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		Button accelerateButton = new Button("Accelerate");
		Button changeHeadLeftButton =  new Button("Left");
		
		AccelerateCommand accelerate = new AccelerateCommand(this.gw);
		ChangeHeadingLeftCommand changeHeadLeft = new ChangeHeadingLeftCommand(this.gw);
		
		// STYLE THE WEST CONTAINER
		westContainer.getAllStyles().setPadding(Component.TOP, 100);
		westContainer.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		
		// STYLE THE ACCELERATE BUTTON
		accelerateButton.getAllStyles().setPadding(TOP, 5);
		accelerateButton.getAllStyles().setPadding(BOTTOM, 5);
		accelerateButton.getAllStyles().setBgTransparency(255);
		accelerateButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		accelerateButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		accelerateButton.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));
		
		// STYLE THE CHANGE HEADING BUTTON
		changeHeadLeftButton.getAllStyles().setPadding(TOP, 5);
		changeHeadLeftButton.getAllStyles().setPadding(BOTTOM, 5);
		changeHeadLeftButton.getAllStyles().setBgTransparency(255);
		changeHeadLeftButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		changeHeadLeftButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		changeHeadLeftButton.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));
		
		// CONNECT BUTTONS TO COMMANDS
		accelerateButton.setCommand(accelerate);
		changeHeadLeftButton.setCommand(changeHeadLeft);
		
		// KEY BIND LISTNERS TO COMMAND
		addKeyListener('a', accelerate);
		addKeyListener('l', changeHeadLeft);
		
		// ADD BUTTONS TO CONTAINER
		westContainer.add(accelerateButton);
		westContainer.add(changeHeadLeftButton);		
	// END OF WEST CONTAINER CREATION

	// BEGINING OF EAST CONTAINER CREATION	
		Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		Button brakeButton =  new Button("Brake");
		Button changeHeadRightButton =  new Button("Right");	
		
		ChangeHeadingRightCommand changeHeadRight = new ChangeHeadingRightCommand(this.gw);
		BrakeCommand brake = new BrakeCommand(this.gw);
		
		// STYLE THE EAST CONTAINER
		eastContainer.getAllStyles().setPadding(Component.TOP, 100);
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		
		// STYLE THE CHANGE HEADING BUTTON
		changeHeadRightButton.getAllStyles().setPadding(TOP, 5);
		changeHeadRightButton.getAllStyles().setPadding(BOTTOM, 5);
		changeHeadRightButton.getAllStyles().setBgTransparency(255);
		changeHeadRightButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		changeHeadRightButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		changeHeadRightButton.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));
		
		// STYLE THE BRAKE BUTTON
		brakeButton.getAllStyles().setPadding(TOP, 5);
		brakeButton.getAllStyles().setPadding(BOTTOM, 5);
		brakeButton.getAllStyles().setBgTransparency(255);
		brakeButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		brakeButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		brakeButton.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));
	
		// CONNECT COMMANDS TO BUTTONS
		changeHeadRightButton.setCommand(changeHeadRight);
		brakeButton.setCommand(brake);
		
		// KEY BIND LISTNERS TO COMMANDS
		addKeyListener('r', changeHeadRight);
		addKeyListener('b', brake);
		 
		// ADD BUTTONS TO CONTAINER
		eastContainer.add(brakeButton);
		eastContainer.add(changeHeadRightButton);
	// END OF EAST CONTAINER CREATION
		
	// BEGINING OF BOTTOM CONTAINER CREATION
		Container bottomContainer = new Container(new FlowLayout(CENTER));
		
		Button flagCollisionButton = new Button("Collide With Flag");
		Button spiderCollisionButton = new Button("Collide With Spider");
		Button foodStationCollisionButton = new Button("Collide With Spider");
		Button tickButton = new Button("Tick");
		
		FlagCollisionCommand flagCollision = new FlagCollisionCommand(this.gw);
		SpiderCollisionCommand spiderCollision = new SpiderCollisionCommand(this.gw);
		FoodStationCollisionCommand foodStationCollision = new FoodStationCollisionCommand(this.gw);
		TickCommand tick = new TickCommand(this.gw);
		
		// STYLE THE FLOW LAYOUT CONTAINER
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
			
		// STYLE THE FLAG COLLISION BUTTON
		flagCollisionButton.getAllStyles().setPadding(TOP, 5);
		flagCollisionButton.getAllStyles().setPadding(BOTTOM, 5);
		flagCollisionButton.getAllStyles().setBgTransparency(255);
		flagCollisionButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		flagCollisionButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		flagCollisionButton.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));

		
		// STYLE THE SPIDER COLLISION BUTTON
		spiderCollisionButton.getAllStyles().setPadding(TOP, 5);
		spiderCollisionButton.getAllStyles().setPadding(BOTTOM, 5);
		spiderCollisionButton.getAllStyles().setBgTransparency(255);
		spiderCollisionButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		spiderCollisionButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		spiderCollisionButton.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));

		
		// STYLE THE FOOD COLLISION BUTTON
		foodStationCollisionButton.getAllStyles().setPadding(TOP, 5);
		foodStationCollisionButton.getAllStyles().setPadding(BOTTOM, 5);
		foodStationCollisionButton.getAllStyles().setBgTransparency(255);
		foodStationCollisionButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		foodStationCollisionButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		foodStationCollisionButton.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));
		
		
		// STYLE THE TICK BUTTON
		tickButton.getAllStyles().setPadding(TOP, 5);
		tickButton.getAllStyles().setPadding(BOTTOM, 5);
		tickButton.getAllStyles().setBgTransparency(255);
		tickButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		tickButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		tickButton.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));
		
		// CONNECT THE COMMAND WITH CORRESPONDING BUTTON
		flagCollisionButton.setCommand(flagCollision);
		foodStationCollisionButton.setCommand(foodStationCollision);
		spiderCollisionButton.setCommand(spiderCollision);
		tickButton.setCommand(tick);
		
		// KEY BIND LISTNERS TO COMMANDS
		addKeyListener('g', spiderCollision);
		addKeyListener('f', foodStationCollision);
		addKeyListener('t', tick);
		
		// ADD THE BUTTONS TO THE CONTAINER
		bottomContainer.add(flagCollisionButton);
		bottomContainer.add(spiderCollisionButton);
		bottomContainer.add(foodStationCollisionButton);
		bottomContainer.add(tickButton);
	// END OF BOTTOM CONTAINER CREATION
		
	// BEGINING OF TOOL BAR CREATION
		Toolbar myToolbar = new Toolbar();
		SoundCommand sound = new SoundCommand(this.gw);
		AboutCommand about = new AboutCommand();
		HelpCommand help = new HelpCommand();
		ExitGameCommand exit = new ExitGameCommand();
		CheckBox soundComponent = new CheckBox("Sound");
		
		// INIT TOOL BAR
		this.setToolbar(myToolbar);
		myToolbar.setTitle("The Path Game");
		
		// STYLE THE SOUND CHECK BOX
		soundComponent.getAllStyles().setBgTransparency(255);
		soundComponent.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		soundComponent.getAllStyles().setFgColor(ColorUtil.WHITE);
		soundComponent.setCommand(sound);

		// the accelerate command used here is the same one that was
		// created in the west container section above.
		myToolbar.addCommandToSideMenu(accelerate); 
		myToolbar.addComponentToSideMenu(soundComponent);
		myToolbar.addCommandToSideMenu(about);
		myToolbar.addCommandToSideMenu(exit);
		myToolbar.addCommandToRightBar(help);
	// END OF TOOL BAR CREATION

		// ALL CONTAINERS TO THE FORM
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.WEST, westContainer);
		this.add(BorderLayout.EAST, eastContainer);
		this.add(BorderLayout.SOUTH, bottomContainer);
		this.add(BorderLayout.CENTER, mv);
		
		// update the mapView and ScoreView with current information
		gw.notifyObservers();
		this.show();
		
		// query MapView’s width and height and set them as world’s
		// width and height
		gw.setWidth(mv.getWidth());
		gw.setHeight(mv.getHeight());
	}
		
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
		
 }
