package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

public class ExitGameCommand extends Command {
	public ExitGameCommand() {
		super("Exit");
	}

	public void actionPerformed(ActionEvent ev) {
		Boolean bOk = Dialog.show("Confirm Exit", "Are you sure you want to exit the game?",
				"Ok", "Cancel");
        /*
         * If user presses Ok then bOK = true.
         * If user presses Cancel then bOk = false
         * */
		if (bOk){
		   //instead of System.exit(0), CN1 recommends using:
		   Display.getInstance().exitApplication();
		}
	}

}
