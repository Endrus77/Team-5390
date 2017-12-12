//Overall structure of segments
//Will be extended by indivisual objects
//All commands must have these functions and variables

//imports

package segments;

//import Commands


public class Segment {

	//Variables
	//Command Array
	//Command[] commands = new Command[];

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

	//Runs at start
	//Runs once
	public void start() {
		//Run any start time processes: set motor positions, start elapsed time, Etc.
	}

	public boolean conditional() {
		return false;
	}

	//Loops
	public boolean loop() {
		//Loop. Will most likely just be a while loop waiting for motors to reach position, or waiting set time for servos
		return true;
	}

	//Stops
	public void stop(){
		//Stop motors
	}
}
