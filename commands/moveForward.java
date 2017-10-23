//Move robot forward
//Takes 2 motor values, powers, and distances

//imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class MoveForward extends Command{

	//Variables
	
	//Motors
	private DcMotor motorR;
	private DcMotor motorL;
	//Motor Powers
	private int motorRP;
	private int motorLP;
	//Motor Distances
	private int motorRD;
	private int motorLD;
	//Motor Encoder Values
	private int motorRE;
	private int motorLE;

	//Initialization

	//Constructor
	public MoveForward(DcMotor mR, DcMotor mL, int pR, int pL, int dR, int dL) {
		//Sets to passed variables
		
		//Motors
		motorR = mR;
		motorL = mL;
		//Power
		motorRP = pR;
		motorLP = pL;
		//Distance
		motorRD = dR;
		motorLD = dR;
	}

	//Setup
	@Override
	public void init() {
		motorR.setDirection(DcMotor.Direction.REVERSE);	
		motorL.setDirection(DcMotor.Direction.FORWARD);		

		//Encoders
		motorRE = ((motorRD * Command.CIRCUMFRENCE) * Command.ENCODERTICKS;
		motorLE = ((motorLD * Command.CIRCUMFRENCE) * Command.ENCODERTICKS;
		
		//Sets encoders
		motorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODERS);
		motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		motorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODERS);
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
		motorR.setPosition(motorRE);
		motorL.setPosition(motorLE);
	}

	//Loops
	@Override
	public void loop() {
		//Wait for motors to stop moving
		while (motorR.isBusy() || motorL.isBusy()) {}
	}

	//Stops
	@Override
	public void stop(){
		//Stop motors
		motorR.setPower(0);
		motorL.setPower(0);
	}
}
