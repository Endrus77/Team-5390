//checkImg()
//Compares passed image to phone image
//Returns bool
public class CheckImg extends Command {

	//variables
	boolean match;
	//placeholder
	int image;

	//Constructor
	CheckImg() {
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
	public void loop() {
		//compare images here
	}

	//Stops
	@Override
	public void stop() {
		//stop compare
	}
}
