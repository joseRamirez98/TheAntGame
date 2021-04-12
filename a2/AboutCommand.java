package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {

	public AboutCommand() {
		super("About");
	}

	public void actionPerformed(ActionEvent ev) {
		Command close = new Command("Close");
		// string to display to the screen
		String aboutString = "Jose Ramirez\n" + 
							 "Spring 2021 CSC 133 Section 6\n" + 
							 "Version Number: 2.0";
        Dialog.show("About", aboutString, close);
	}
}
