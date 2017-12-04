//Moves Claw
//Takes 4 sero positions

//imports

package commands;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class MoveServo extends Command {

	//Variables

	//Servos
	private Servo servo;
	//Servo Position
	private double servoPosition;
	private double startPosition;
	//Elapsed Time
	private ElapsedTime runTime;

	//Constructor
	public MoveServo(double sP, double p) {
		//Sets to passed variables

		//Servo Positions
		servoPosition = p;
		startPosition = sP;
	}

	public void setServos(Servo s) {
		//Servos
		servo = s;
	}
	//Setup
	@Override
	public void init() {
		//Reset servo positions
		servo.setPosition(startPosition);
		//Elapsed Time
		runTime = new ElapsedTime();
	}

	//Runs at start
	//Runs once
	@Override
	public void start() {
		//Set servo positionss
		servo.setPosition(servoPosition);

		//Reset Elapsed Time
		runTime.reset();
	}

	//Loops
	@Override
	public boolean loop() {
		//Wait for 1 second
		return (runTime.time() < 1.4);
	}

	//Stops
	@Override
	public void stop(){
		//Empty
	}
}
