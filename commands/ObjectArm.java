//Moves Claw
//Takes 4 sero positions

//imports

package commands;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import robots.Robot;

public class MoveServo extends Command {

	//Variables

	//Robot
	private Robot robot;
	//Servos
	private Servo servoA;
	//Servo Position
	private double servoAPosition;
	private double startAPosition;
	//Elapsed Time
	private ElapsedTime runTime;

	//Constructor
	public MoveServo(double sP, double p) {
		//Sets to passed variables

		//Robot
		robot = r;
		//Servo Positions
		servoAPosition = p;
		startAPosition = sP;
	}

	public void setServos(String s) {
		//Servos
		servoA = (Servo)robot.getHardware().get(s);
	}
	//Setup
	@Override
	public void init() {
		//Reset servo positions
		servoA.setPosition(startAPosition);
		//Elapsed Time
		runTime = new ElapsedTime();
	}

	//Runs at start
	//Runs once
	@Override
	public void start() {
		//Set servo positionss
		servoA.setPosition(servoAPosition);

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
