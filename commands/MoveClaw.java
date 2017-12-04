//Moves Claw
//Takes 4 sero positions

//imports

package commands;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class MoveClaw extends Command {

	//Variables
	
	//Servos
	private Servo clawR;
	private Servo clawL;
	//Servo Position
	private int clawPositionR;
	private int clawPositionL;
	//Elapsed Time
	private ElapsedTime runTime;

	//Constructor
	public MoveClaw(int cRP, int cLP) {
		//Sets to passed variables

		//Servo Positions
		clawPositionR = cRP;
		clawPositionL = cLP;
	}

	public void setServos(Servo cR, Servo cL) {
		//Servos
		clawR = cR;
		clawL = cL;
	}
	//Setup
	@Override
	public void init() {
		//Reset servo positions
		clawR.setPosition(0);
		clawL.setPosition(1);
		//Elapsed Time
		runTime = new ElapsedTime();
	}

	//Runs at start
	//Runs once
	@Override
	public void start() {
		//Set servo positionss
		clawR.setPosition(clawPositionR);
		clawL.setPosition(clawPositionL);

		//Reset Elapsed Time
		runTime.reset();
	}

	//Loops
	@Override
	public boolean loop() {
		//Wait for 1 second
		return (runTime.time() < 1);
	}

	//Stops
	@Override
	public void stop(){
		//Empty
	}
}
