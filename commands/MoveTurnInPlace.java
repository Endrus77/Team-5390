//ADD DESCRIPTION OF PROGRAM HERE

//imports

package commands;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MoveTurnInPlace extends Command {

	//Variables
	//Motors
	private DcMotor motorRF;
	private DcMotor motorLF;
	private DcMotor motorRB;
	private DcMotor motorLB;
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
	public MoveTurnInPlace(double p, double a) {
		//Set passed values to object values here

		//Power
		motorP = p;

		//Angle
		angle = a;
	}

	public void setMotors(DcMotor mRF, DcMotor mLF, DcMotor mRB, DcMotor mLB) {
		//Motors
		motorRF = mRF;
		motorLF = mLF;
		motorRB = mRB;
		motorLB = mLB;
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
		motorRF.setDirection(DcMotor.Direction.REVERSE);
		motorLF.setDirection(DcMotor.Direction.FORWARD);
		motorRB.setDirection(DcMotor.Direction.REVERSE);
		motorLB.setDirection(DcMotor.Direction.FORWARD);

		motorRF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motorRF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		motorLF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motorLF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		motorRB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motorRB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		motorLB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motorLB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
	}

	//Runs at start
	//Runs once
	public void start() {
		//Set speed and position
		motorRF.setPower(motorP);
		motorRF.setTargetPosition(motorE);
		motorLF.setPower(motorP);
		motorLF.setTargetPosition(motorE);
		motorRB.setPower(motorP);
		motorRB.setTargetPosition(motorE);
		motorLB.setPower(motorP);
		motorLB.setTargetPosition(motorE);
	}

	//Loops
	public boolean loop() {
		//Wait for motors to stop moving
		return (motorRF.isBusy() || motorLF.isBusy() || motorRF.isBusy() || motorLF.isBusy());
	}

	//Stops
	public void stop(){
		//Stop motors
		motorRF.setPower(0);
		motorLF.setPower(0);
		motorRB.setPower(0);
		motorLB.setPower(0);
	}
}
