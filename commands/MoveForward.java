//Move robot forward
//Takes 2 motor values, powers, and distances

//imports

package commands;

import com.qualcomm.robotcore.hardware.DcMotor;

import robots.Robot;

public class MoveForward extends Command{

	//Variables

	//Robot
	private Robot robot;
	//Motors
	private DcMotor motorR;
	private DcMotor motorL;
	//Motor Powers
	private double motorRP;
	private double motorLP;
	//Motor Distances
	private double motorRD;
	private double motorLD;
	//Motor Encoder Values
	private int motorRE;
	private int motorLE;

	//Initialization
	//Constructor

	public MoveForward(Robot r, double pR, double pL, double dR, double dL) {
		//Sets to passed variables

		//Robot
		robot = r;
		//Power
		motorRP = pR;
		motorLP = pL;
		//Distance
		motorRD = dR;
		motorLD = dL;
	}

	public void setMotors(String mR, String mL) {
		//Motors
		motorR = (DcMotor)robot.getHardware().get(mR);
		motorL = (DcMotor)robot.getHardware().get(mL);
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
	}

	//Loops
	@Override
	public boolean loop() {
		//Wait for motors to stop moving
		return (motorR.isBusy() || motorL.isBusy());
	}

	//Stops
	@Override
	public void stop(){
		//Stop motors
		motorR.setPower(0);
		motorL.setPower(0);
	}
}
