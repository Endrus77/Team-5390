//Rotates turret
//Takes 1 motor, power, and angle

//imports

package commands;

import com.qualcomm.robotcore.hardware.DcMotor;

import robots.Robot;

public class MoveMotor extends Command {

	//Variables

	//Robot
	private Robot robot;
	//Motor
	private DcMotor motorTest;
	//Power
	private double motorPTest;
	//Angle
	private int motorATest;


	//Constructor
	public MoveMotor(Robot r, double p, int a) {
		//Set to passed variables

		//Robot
		robot = r;
		//Power
		motorPTest = p;
		//Angle
		motorATest = a;
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

		//Set motor position
		motor.setTargetPosition(motorE);
	}

	//Loops
	public boolean loop() {
		//Wait for motors to finish moving
		return (motor.isBusy());
	}

	//Stops motor
	public void stop(){
		//Stop motor
		motor.setPower(0);
	}
}
