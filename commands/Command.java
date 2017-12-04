//Overall structure of commands
//Will be extended by individual objects
//All commands musts have these functions and variables

//imports

package commands;

public class Command {

	//Variables
	public static double DIAMETER = 4;
	public static double CIRCUMFRENCE = 4 * Math.PI;
	public static double ENCODERTICKS = 1680;
	public static double WHEELDISTANCE = 16.5;
	public static double WHEELCIRCUMFRENCE = 16.5 * 2 * Math.PI;
	public static double FIX = (1 / 1.48);

	//Initialization

	//Constructor
	public Command() {

	}

	//Setup
	public void init() {

	}

	//Runs at start
	//Runs once
	public void start() {

	}

	//Loops
	public boolean loop() {
		return true;
	}

	//Stops
	public void stop(){

	}
}
