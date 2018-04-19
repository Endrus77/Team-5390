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
	private DcMotor motor;
	//Power
	private double motorP;
	//Angle
	private int motorA;
	//Motor Encoder Value
	private int motorE;


	//Constructor
	public MoveMotor(Robot r, double p, int a) {
		//Set to passed variables

		//Robot
		robot = r;
		//Power
		motorP = p;
		//Angle
		motorA = a;
	}

	public void setMotor(String m) {
		//Motor
		motor = (DcMotor)robot.getHardware().get(m);
	}

	//Setup
	public void init() {
		//Encoder
		motorE = (int)((motorA / 360) * Command.ENCODERTICKS);

		//Sets direction
		motor.setDirection(DcMotor.Direction.REVERSE);

		//Sets encoders
		motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
	
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
