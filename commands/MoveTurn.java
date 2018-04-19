//ADD DESCRIPTION OF PROGRAM HERE

//imports

package commands;

import com.qualcomm.robotcore.hardware.DcMotor;

import robots.Robot;

public class MoveTurn extends Command {

	//Variables
	//Robot
	private Robot robot;
	//Motors
	private DcMotor motorR;
	private DcMotor motorL;
	//Motor Powers
	private double motorP;
	//Motor Encoder
	private int motorE;

	//Rotation angle
	private double angle;
	//Wheel to turn
	//0 is right, 1 is left
	private int wheel;

	//Initialization

	//Constructor
	//Add values to be taken here
	public MoveTurn(Robot r, double p, int w, double a) {
		//Set passed values to object values here

		//Robot
		robot = r;
		//Power
		motorP = p;

		//Angle
		angle = a;
		//Wheel
		wheel = w;
	}

	public void setMotors(String mR, String mL) {
		//Motors
		motorR = (DcMotor)robot.getHardware().get(mR);
		motorL = (DcMotor)robot.getHardware().get(mL);
	}

	//Setup
	@Override
	public void init() {
		double motorD;
		//Distances
		motorD = ((angle / 360) * Command.WHEELCIRCUMFRENCE) * Command.FIX;

		//Encoders
		motorE = (int)((motorD / Command.CIRCUMFRENCE) * Command.ENCODERTICKS);

		//Set Motors up
		if (wheel == 0) {
			motorR.setDirection(DcMotor.Direction.REVERSE);
			motorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
			motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		}
		else {
			motorL.setDirection(DcMotor.Direction.FORWARD);
			motorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
			motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		}
	}

	//Runs at start
	//Runs once
	public void start() {
		//Set speed and position
		if (wheel == 0) {
			motorR.setPower(motorP);
			motorR.setTargetPosition(motorE);
		}
		else {
			motorL.setPower(motorP);
			motorL.setTargetPosition(motorE);
		}
	}

	//Loops
	public boolean loop() {
		//Wait for motors to stop moving
		return (motorR.isBusy() || motorL.isBusy());
	}

	//Stops
	public void stop(){
		//Stop motors
		if (wheel == 0)
			motorR.setPower(0);
		else
			motorL.setPower(0);
	}
}
