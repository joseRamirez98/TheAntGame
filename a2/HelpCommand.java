package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command {

	public HelpCommand() {
		super("Help");
	}
	
	public void actionPerformed(ActionEvent ev) {
		// command to close the dialog box
		Command close = new Command("Close");
		// string to display to the Game screen
		String body = "Command: Key Binding\n" +
					  "--------------------\n" +
					  "Accelerate: a\n" +
					  "Brake: b\n" +
					  "Left Turn : l\n" +
					  "Right Turn: r\n" +
					  "Collide With Food Station: f\n" +
					  "Collide With Spider: g\n" +
					  "Tick: t\n";
		// display the dialog box, and allow the user
		// to close the dialog box with a button
        Dialog.show("Key Bindings", body, close);
	}

}
