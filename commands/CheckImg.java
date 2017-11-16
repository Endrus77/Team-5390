//checkImg()
//Compares passed image to phone image
//Returns bool

package commands;

public class CheckImg extends Command {

	//variables
	boolean match;
	//placeholder
	int image;

	//Constructor
	public CheckImg() {
		match = false;
		//image = passedImage;
	}

	//Setup
	@Override
	public void init() {
		//empty
	}

	//Runs at start
	//Runs once
	@Override
	public void start() {
		//empty
	}

	//Loops
	@Override
	public boolean loop() {
		//compare images here
		return true;
	}

	//Stops
	@Override
	public void stop() {
		//stop compare
	}

	public int getValue(){
		return (0);
	}
}
