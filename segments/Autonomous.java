//Overall structure of segments
//Will be extended by indivisual objects
//All commands must have these functions and variables

//imports

package segments;

//import Commands


import java.util.ArrayList;

import commands.Command;

public class Segment {

	//Variables
	private int index = 0;

	//Command ArrayList
	private ArrayList<Command> commands = new ArrayList<Command>(0);

	//Initialization

	//Constructor
	//Add values to be taken here
	public Segment() {
		//Set passed values to object values here
	}

	//Setup
	public void init() {
		//Run any initialization procedures: motor encoders, reset runtime, Etc.
	}

	public boolean conditional() {
		//Conditional loop
		return false;
	}

	public void build() {
		//Create the command array here
	}

	//Loops
	public boolean loop() {
		if (commands.get(index).loop())
			return true;
		else {
			commands.get(index).stop();
			index++;
			if (index == commands.size())
				return false;
			else {
				commands.get(index).init();
				commands.get(index).start();
			}
			return true;
		}
	}

	public void addCommand(Command c) {
		//Add command to commands array
		commands.add(c);
	}
}
