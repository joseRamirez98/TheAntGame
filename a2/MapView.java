package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {

	public MapView() {
		// create a red border line around the map view
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.rgb(255,0,0)));
	}

	@Override
	public void update(Observable gameWorld, Object gameObjectsCollection) {
        // type cast the Observable object into a Game World object
		GameWorld realGameWorld = (GameWorld)gameWorld;
		realGameWorld.printAllObjects();
	}

}
