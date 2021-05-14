package com.mycompany.a3;

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
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;

public class Game extends Form implements Runnable{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private Boolean gameIsPaused;
	
	private Button accelerateButton;
	private Button changeHeadLeftButton;
	private Button brakeButton;
	private Button changeHeadRightButton;
	private Button playPauseButton;
	private Button positionButton;
	
	private Container westContainer;
	private Container eastContainer;
	private Container bottomContainer;
	private Toolbar myToolbar;
	private CheckBox soundComponent;
	
	private AccelerateCommand accelerate;
	private ChangeHeadingLeftCommand changeHeadLeft;
	private ChangeHeadingRightCommand changeHeadRight;
	private BrakeCommand brake;
	private PositionCommand positionCommand;
	private PlayPauseCommand playPauseCommand;
	private SoundCommand sound;
	private AboutCommand about;
	private HelpCommand help;
	private ExitGameCommand exit;
	
	/* END OF PRIVATE DATA MEMBER DEFINTIONS */

	public Game(){
		this.gameIsPaused = false;
		this.gw = new GameWorld();
		this.mv = new MapView(this.gw, this);
		this.sv = new ScoreView();
		
		/* Add the observers to the game world */
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		// set the layout of the Form as border layout
		this.setLayout(new BorderLayout());
	
	// BEGINING OF WEST CONTAINER CREATION
		westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		accelerateButton = new Button("Accelerate");
		changeHeadLeftButton =  new Button("Left");
		
		accelerate = new AccelerateCommand(this.gw);
		changeHeadLeft = new ChangeHeadingLeftCommand(this.gw);
		
		// STYLE THE WEST CONTAINER
		westContainer.getAllStyles().setPadding(Component.TOP, 100);
		westContainer.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		
		// STYLE THE ACCELERATE BUTTON
		accelerateButton.getAllStyles().setPadding(TOP, 5);
		accelerateButton.getAllStyles().setPadding(BOTTOM, 5);
		accelerateButton.getAllStyles().setBgTransparency(255);
		accelerateButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		accelerateButton.getDisabledStyle().setBgColor(ColorUtil.GRAY);
		accelerateButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		accelerateButton.getDisabledStyle().setFgColor(ColorUtil.BLACK);
		accelerateButton.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));
		
		// STYLE THE CHANGE HEADING BUTTON
		changeHeadLeftButton.getAllStyles().setPadding(TOP, 5);
		changeHeadLeftButton.getAllStyles().setPadding(BOTTOM, 5);
		changeHeadLeftButton.getAllStyles().setBgTransparency(255);
		changeHeadLeftButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		changeHeadLeftButton.getDisabledStyle().setBgColor(ColorUtil.GRAY);
		changeHeadLeftButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		changeHeadLeftButton.getDisabledStyle().setFgColor(ColorUtil.BLACK);
		changeHeadLeftButton.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));
		
		// CONNECT BUTTONS TO COMMANDS
		accelerateButton.setCommand(accelerate);
		changeHeadLeftButton.setCommand(changeHeadLeft);
		
		// ADD BUTTONS TO CONTAINER
		westContainer.add(accelerateButton);
		westContainer.add(changeHeadLeftButton);		
	// END OF WEST CONTAINER CREATION

	// BEGINING OF EAST CONTAINER CREATION	
		eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		brakeButton =  new Button("Brake");
		changeHeadRightButton =  new Button("Right");	
		
		changeHeadRight = new ChangeHeadingRightCommand(this.gw);
		brake = new BrakeCommand(this.gw);
		
		// STYLE THE EAST CONTAINER
		eastContainer.getAllStyles().setPadding(Component.TOP, 100);
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		
		// STYLE THE CHANGE HEADING BUTTON
		changeHeadRightButton.getAllStyles().setPadding(TOP, 5);
		changeHeadRightButton.getAllStyles().setPadding(BOTTOM, 5);
		changeHeadRightButton.getAllStyles().setBgTransparency(255);
		changeHeadRightButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		changeHeadRightButton.getDisabledStyle().setBgColor(ColorUtil.GRAY);
		changeHeadRightButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		changeHeadRightButton.getDisabledStyle().setFgColor(ColorUtil.BLACK);
		changeHeadRightButton.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));
		
		// STYLE THE BRAKE BUTTON
		brakeButton.getAllStyles().setPadding(TOP, 5);
		brakeButton.getAllStyles().setPadding(BOTTOM, 5);
		brakeButton.getAllStyles().setBgTransparency(255);
		brakeButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		brakeButton.getDisabledStyle().setBgColor(ColorUtil.GRAY);
		brakeButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		brakeButton.getDisabledStyle().setFgColor(ColorUtil.BLACK);
		brakeButton.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));
	
		// CONNECT COMMANDS TO BUTTONS
		changeHeadRightButton.setCommand(changeHeadRight);
		brakeButton.setCommand(brake);
		 
		// ADD BUTTONS TO CONTAINER
		eastContainer.add(brakeButton);
		eastContainer.add(changeHeadRightButton);
	// END OF EAST CONTAINER CREATION
		
	// BEGINING OF BOTTOM CONTAINER CREATION
		bottomContainer = new Container(new FlowLayout(CENTER));
		
		playPauseButton = new Button("Pause");
		positionButton = new Button("Position");
		
		positionCommand = new PositionCommand(this, this.gw, this.mv);
		playPauseCommand = new PlayPauseCommand(this);
		
		
		// STYLE THE FLOW LAYOUT CONTAINER
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		
		// STYLE THE PLAY/PAUSE  BUTTON
		playPauseButton.getAllStyles().setPadding(TOP, 5);
		playPauseButton.getAllStyles().setPadding(BOTTOM, 5);
		playPauseButton.getAllStyles().setPadding(LEFT, 5);
		playPauseButton.getAllStyles().setPadding(RIGHT, 5);
		playPauseButton.getAllStyles().setBgTransparency(255);
		playPauseButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		playPauseButton.getDisabledStyle().setBgColor(ColorUtil.GRAY);
		playPauseButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		playPauseButton.getDisabledStyle().setFgColor(ColorUtil.BLACK);
		playPauseButton.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));
		
		// STYLE THE POSITION BUTTON
		positionButton.getAllStyles().setPadding(TOP, 5);
		positionButton.getAllStyles().setPadding(BOTTOM, 5);
		positionButton.getAllStyles().setBgTransparency(255);
		positionButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		positionButton.getDisabledStyle().setBgColor(ColorUtil.GRAY);
		positionButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		positionButton.getDisabledStyle().setFgColor(ColorUtil.BLACK);
		positionButton.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));
			
		// CONNECT THE COMMAND WITH CORRESPONDING BUTTON
		playPauseButton.setCommand(playPauseCommand);
		positionButton.setCommand(positionCommand);
		

		// ADD THE BUTTONS TO THE CONTAINER
		bottomContainer.add(positionButton);
		bottomContainer.add(playPauseButton);
	// END OF BOTTOM CONTAINER CREATION
		
	// BEGINING OF TOOL BAR CREATION
		myToolbar = new Toolbar();
		sound = new SoundCommand(this.gw);
		about = new AboutCommand();
		help = new HelpCommand();
		exit = new ExitGameCommand();
		soundComponent = new CheckBox("Sound");
		
		// INIT TOOL BAR
		this.setToolbar(myToolbar);
		myToolbar.setTitle("The Path Game");
		
		// STYLE THE SOUND CHECK BOX
		soundComponent.getAllStyles().setBgTransparency(255);
		soundComponent.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		soundComponent.getAllStyles().setFgColor(ColorUtil.WHITE);
		soundComponent.setSelected(true);
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
		
		this.show();
		// generate a game world based on the width and height of
		// the map view.
		gw.init(this.mv.getWidth(), this.mv.getHeight());
		//instantiate necessary audio files.
		gw.createSounds();
		// update the mapView and ScoreView with current information
		gw.notifyObservers();
		
		
		//create timer and provide a runnable (which is this form)
		UITimer timer = new UITimer(this);
		
		//make the timer tick every second and bind it to this form
		timer.schedule(20, true, this);
	}
	
	/* The run function is responsible for running the game.
	 * Its purpose is to call the clock tick function in
	 * game world to have the moving objects constantly moving.
	 * If the game is not paused, all other buttons, commands,
	 * and key listeners besides the position command/button
	 * should be available and vise-versa if the game is paused.
	 *  */
	public void run() {
		/* Check if the game is paused */
		if(gameIsPaused == false) {
			/* DISABLE POSTION BUTTON AND COMMAND*/
			this.positionCommand.setEnabled(false);
			this.positionButton.setEnabled(false);
			
			/* ENABLE NECESSARY COMMANDS*/
			this.accelerate.setEnabled(true);
			this.brake.setEnabled(true);
			this.changeHeadRight.setEnabled(true);
			this.changeHeadLeft.setEnabled(true);
			this.sound.setEnabled(true);
			
			/* ENABLE BUTTONS */
			this.accelerateButton.setEnabled(true);
			this.brakeButton.setEnabled(true);
			this.changeHeadRightButton.setEnabled(true);
			this.changeHeadLeftButton.setEnabled(true);
			this.soundComponent.setEnabled(true);
			
			/* CHANGE THE TEXT OF THE PLAY/PAUSE BUTTON */
			this.playPauseButton.setText("Pause");
			
			/* ADD KEY LISTENERS */
			addKeyListener('r', changeHeadRight);
			addKeyListener('b', brake);
			addKeyListener('a', accelerate);
			addKeyListener('l', changeHeadLeft);
			
			/* RETRIEVE MAP VIEW DIMENSIONS*/
			Dimension dCmpSize = new Dimension(mv.getWidth(),mv.getHeight()-mv.getY());
			
			/* TICK THE GAME WORLD */
			gw.clockTick(dCmpSize, 100);
			
			/* NOTIFY SCORE VIEW AND MAP VIEW WITH NEW INFORMATION */
			gw.notifyObservers();
		}
		else {
			/* ENABLE POSTION BUTTON AND COMMAND*/
			this.positionCommand.setEnabled(true);
			this.positionButton.setEnabled(true);
			
			/* DISABLE NECESSARY COMMANDS*/
			this.accelerate.setEnabled(false);
			this.brake.setEnabled(false);
			this.changeHeadRight.setEnabled(false);
			this.changeHeadLeft.setEnabled(false);
			this.sound.setEnabled(false);
			
			/* DISABLE BUTTONS */
			this.accelerateButton.setEnabled(false);
			this.brakeButton.setEnabled(false);
			this.changeHeadRightButton.setEnabled(false);
			this.changeHeadLeftButton.setEnabled(false);
			this.soundComponent.setEnabled(false);
			
			/* CHANGE THE TEXT OF THE PLAY/PAUSE BUTTON */
			this.playPauseButton.setText("Play");
			
			/* REMOVE KEY LISTENERS */
			this.removeKeyListener('a', accelerate);
			this.removeKeyListener('b', brake);
			this.removeKeyListener('l', changeHeadLeft);
			this.removeKeyListener('r', changeHeadRight);
		}
	}
	
	
	public Boolean getGameIsPaused() {
		return gameIsPaused;
	}

	public void setGameIsPaused(Boolean gameIsPaused) {
		this.gameIsPaused = gameIsPaused;
	}
	
	/**
	 * @return the gw
	 */
	public GameWorld getGw() {
		return gw;
	}
	
	/**
	 * @return the gw
	 */
	public MapView getMV() {
		return mv;
	}
		
 }
