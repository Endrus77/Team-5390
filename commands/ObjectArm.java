//Moves Claw
//Takes 4 sero positions

//imports

package commands;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import robots.Robot;

public class ObjectArm extends Command {

	//Variables

	//Robot
	private Robot robot;
	//Servos
	private Servo servo;
	//Servo Position
	private double servoPosition;
	private double startPosition;
	//Elapsed Time
	private ElapsedTime runTime;

	//Constructor
	public MoveServo(Robot r, double sP, double p) {
		//Sets to passed variables

		//Robot
		robot = r;
		//Servo Positions
		servoPosition = p;
		startPosition = sP;
	}

	public void setServos(String s) {
		//Servos
		servo = (Servo)robot.getHardware().get(s);
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
		return (runTime.time() < 0.5);
	}

	//Stops
	@Override
	public void stop(){
		//Empty
	}
}
