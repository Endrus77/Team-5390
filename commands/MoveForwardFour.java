//Move robot forward
//Takes 2 motor values, powers, and distances

//imports

package commands;

import com.qualcomm.robotcore.hardware.DcMotor;

import robots.Robot;

public class MoveForwardFour extends Command{

	//Variables

	//Robot
	private Robot robot;
	//Motors
	private DcMotor motorRF;
	private DcMotor motorLF;
	private DcMotor motorRB;
	private DcMotor motorLB;
	//Motor Powers
	private double motorRPF;
	private double motorLPF;
	private double motorRPB;
	private double motorLPB;
	//Motor Distances
	private double motorRDF;
	private double motorLDF;
	private double motorRDB;
	private double motorLDB;
	//Motor Encoder Values
	private int motorREF;
	private int motorLEF;
	private int motorREB;
	private int motorLEB;

	//Initialization
	//Constructor

	public MoveForwardFour(Robot r, double pRF, double pLF, double pRB, double pLB, double dRF, double dLF, double dRB, double dLB) {
		//Sets to passed variables

		//Robot
		robot = r;
		//Power
		motorRPF = pRF;
		motorLPF = pLF;
		motorRPB = pRB;
		motorLPB = pLB;
		//Distance
		motorRDF = dRF;
		motorLDF = dLF;
		motorRDB = dRB;
		motorLDB = dLB;
	}

	public void setMotors(String mRF, String mRB, String mLF, String mLB) {
		//Motors
		motorRF = (DcMotor)robot.getHardware().get(mRF);
		motorLF = (DcMotor)robot.getHardware().get(mRB);
		motorRB = (DcMotor)robot.getHardware().get(mLF);
		motorLB = (DcMotor)robot.getHardware().get(mLB);
	}

	//Setup
	@Override
	public void init() {
		//Encoders
		motorREF = (int)((motorRDF / Command.CIRCUMFRENCE) * Command.ENCODERTICKS);
		motorLEF = (int)((motorLDF / Command.CIRCUMFRENCE) * Command.ENCODERTICKS);
		motorREB = (int)((motorRDB / Command.CIRCUMFRENCE) * Command.ENCODERTICKS);
		motorLEB = (int)((motorLDB / Command.CIRCUMFRENCE) * Command.ENCODERTICKS);
		
		//Set directions
		motorRF.setDirection(DcMotor.Direction.REVERSE);
		motorLF.setDirection(DcMotor.Direction.FORWARD);
		motorRB.setDirection(DcMotor.Direction.REVERSE);
		motorLB.setDirection(DcMotor.Direction.FORWARD);
		//Sets encoders
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
	@Override
	public void start() {
		//Set motor powers
		motorRF.setPower(motorRPF);
		motorLF.setPower(motorLPF);
		motorRB.setPower(motorRPB);
		motorLB.setPower(motorLPB);

		//Set motor positions
		motorRF.setTargetPosition(motorREF);
		motorLF.setTargetPosition(motorLEF);
		motorRB.setTargetPosition(motorREB);
		motorLB.setTargetPosition(motorLEB);
	}

	//Loops
	@Override
	public boolean loop() {
		//Wait for motors to stop moving
		return (motorRF.isBusy() || motorLF.isBusy() || motorRB.isBusy() || motorLB.isBusy());
	}

	//Stops
	@Override
	public void stop(){
		//Stop motors
		motorRF.setPower(0);
		motorLF.setPower(0);
		motorRB.setPower(0);
		motorLB.setPower(0);
	}
}
