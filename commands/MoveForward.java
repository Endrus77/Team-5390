//Move robot forward
//Takes 2 motor values, powers, and distances

//imports

package commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class MoveForward extends Command{

	//Variables
	
	//Motors
	private DcMotor motorR;
	private DcMotor motorL;
	private DcMotor motorRB;
	private DcMotor motorLB;
	//Motor Powers
	private double motorRP;
	private double motorLP;
	//Motor Distances
	private double motorRD;
	private double motorLD;
	//Motor Encoder Values
	private int motorRE;
	private int motorLE;
	//Is Four Wheeled?
	boolean fourWheel;

	//Initialization
	//Constructor

	public MoveForward(double pR, double pL, double dR, double dL) {
		//Sets to passed variables

		//Power
		motorRP = pR;
		motorLP = pL;
		//Distance
		motorRD = dR;
		motorLD = dL;
	}

	public MoveForward(double pR, double pL, double dR, double dL, boolean isFourWheel) {
	//Power
		motorRP = pR;
		motorLP = pL;
		//Distance
		motorRD = dR;
		motorLD = dL;
		fourWheel = isFourWheel;
	}

	public void setMotors(DcMotor mR, DcMotor mL) {
		//Motors
		motorR = mR;
		motorL = mL;
	}

	public void setMotors(DcMotor mRF, DcMotor mRB, DcMotor mLF, DcMotor mLB) {
		//Motors
		motorR = mRF;
		motorL = mLF;
		motorRB = mRB;
		motorLB = mLB;
	}

	//Setup
	@Override
	public void init() {
		//Encoders
		motorRE = (int)((motorRD / Command.CIRCUMFRENCE) * Command.ENCODERTICKS);
		motorLE = (int)((motorLD / Command.CIRCUMFRENCE) * Command.ENCODERTICKS);
		
		//Set directions
		motorR.setDirection(DcMotor.Direction.FORWARD);
		motorL.setDirection(DcMotor.Direction.REVERSE);
		//Sets encoders
		motorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		motorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

		if (fourWheel) {
			motorRB.setDirection(DcMotor.Direction.FORWARD);
			motorLB.setDirection(DcMotor.Direction.REVERSE);
			motorRB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
			motorRB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
			motorLB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
			motorLB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		}
	}

	//Runs at start
	//Runs once
	@Override
	public void start() {
		//Set motor powers
		motorR.setPower(motorRP);
		motorL.setPower(motorLP);

		//Set motor positions
		motorR.setTargetPosition(motorRE);
		motorL.setTargetPosition(motorLE);

		if (fourWheel) {
			motorRB.setPower(motorRP);
			motorLB.setPower(motorLP);
			motorRB.setTargetPosition(motorRE);
			motorLB.setTargetPosition(motorLE);
		}
	}

	//Loops
	@Override
	public boolean loop() {
		//Wait for motors to stop moving
		if (fourWheel)
			return (motorR.isBusy() || motorL.isBusy() || motorRB.isBusy() || motorLB.isBusy());
		else
			return (motorR.isBusy() || motorL.isBusy());
	}

	//Stops
	@Override
	public void stop(){
		//Stop motors
		motorR.setPower(0);
		motorL.setPower(0);
		if (fourWheel) {
			motorRB.setPower(0);
			motorLB.setPower(0);
		}
	}
}
