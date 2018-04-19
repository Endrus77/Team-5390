//Rotates turret
//Takes 1 motor, power, and angle

//imports

package commands;

import com.qualcomm.robotcore.hardware.DcMotor;

import robots.Robot;

public class SetMotor extends Command {

	//Variables

	//Robot
	private Robot robot;
	//Motor
	private DcMotor motor;
	//Power
	private double motorP;


	//Constructor
	public SetMotor(Robot r, double p) {
		//Set to passed variables

		//Robot
		robot = r;
		//Power
		motorP = p;
	}

	public void setMotor(String m) {
		//Motor
		motor = (DcMotor)robot.getHardware().get(m);
	}

	//Setup
	public void init() {
		//Sets direction
		motor.setDirection(DcMotor.Direction.REVERSE);
	}

	//Runs at start
	//Runs once
	public void start() {
		//Set motor power
		motor.setPower(motorP);
	}

	//Loops
	public boolean loop() {
		//Wait for motors to finish moving
		return (motor.isBusy());
	}

	//Stops motor
	public void stop(){}
}
