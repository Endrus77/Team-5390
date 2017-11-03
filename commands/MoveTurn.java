//ADD DESCRIPTION OF PROGRAM HERE

//imports

package commands;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MoveTurn extends Command {

	//Variables
	//Motors
	private DcMotor motorR;
	private DcMotor motorL;
	//Motor Powers
	private int motorP;
	//Motor Encoder
	private int motorE;

	//Rotation angle
	private int angle;
	//Wheel to turn
	//0 is right, 1 is left
	private int wheel;

	//Initialization

	//Constructor
	//Add values to be taken here
	public MoveTurn(int p, int w, int a) {
		//Set passed values to object values here

		//Power
		motorP = p;

		//Angle
		angle = a;
		//Wheel
		wheel = w;;
	}

	public void setMotors(DcMotor mR, DcMotor mL) {
		//Motors
		motorR = mR;
		motorL = mL;
	}

	//Setup
	@Override
	public void init() {
		int motorD;
		//Distances
		motorD = (int)((angle / 360) * Command.WHEELCIRCUMFRENCE);

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
	public void loop() {
		//Wait for motors to stop moving
		while (motorR.isBusy() || motorL.isBusy()) {}
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
