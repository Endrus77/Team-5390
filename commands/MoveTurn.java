//ADD DESCRIPTION OF PROGRAM HERE

//imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class MoveTurn extends Command {

	//Variables
	//Motors
	private DcMotor motorR;
	private DcMotor motorL;
	//Motor Powers
	private int motorP;
	//Motor Distance
	private int motorD;
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
	public MoveTurn(DcMotor mR, DcMotor mL, int p, int w, int a) {
		//Set passed values to object values here

		//Motors
		motorR = mR;
		motorL = mL;
		//Power
		motorP = p;

		//Angle
		angle = a;
		//Wheel
		wheel = w;
	}

	//Setup
	@Override
	public void init() {
		//Distances
		motorD = (angle / 360) * Command.WHEELCIRCUMFRENCE;
		//Encoders
		motorE = (motorD / Command.CIRCUMFRENCE) * Command.ENCODERTICKS;

		//Set Motors up
		if (wheel = 0) {
			motorR.setDirection(DcMotor.Direction.REVERSE);
			motorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODERS);
			motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		}
		else {
			motorL.setDirection(DcMotor.Directions.FORWARD);
			motorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODERS);
			motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		}
	}

	//Runs at start
	//Runs once
	public void start() {
		//Set speed and position
		if (wheel == 0) {
			motorR.setPower(motorP);
			motorR.setPosition(motorE);
		}
		else {
			motorL.setPower(motorP);
			motorL.setPosition(motorE);
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
