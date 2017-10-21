//Checks pictograph, runs to appropiate spot then drops block
//Takes all motors being used as parameters
//For now the distances will be hardcoded

//imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
//import Commands

public class CheckPicto extends Segment {

	//Variables
	
	//Command Array
	Command[] commands = new Command[];

	//Spot
	//Corner is 0, straight is 1
	private int spot;

	//Color
	//Red is 0, blue is 1
	private int color;

	//Motors
	//Arm motors
	private DcMotor motor1;
	private DcMotor motor2;
	private DcMotor motor3;
	//Wheel motors
	private DcMotor motorR;
	private DcMotor motorL;

	//Servos
	//Claw servos
	private Servo claw;
	private Servo lateral;
	private Servo vertical;

	//Commands
	CheckImage checkImage = new CheckImage();
	MoveArm hitBallR = new MoveArm();
	MoveArm hitBallL = new MoveArm();


	//Initialization

	//Constructor
	//Add values to be taken here
	public CheckPicto(m1, m2, m3, mR, mL, c, l, v, col, spt) {
		//Set passed values to object values here

		//Color
		color = col;

		//Spot
		spot = spt;

		//Motors
		//Arm Motors
		motor1 = m1;
		motor2 = m2;
		motor3 = m3;
		//Wheel motors
		motorR = mR;
		motorL = mL;

		//Servos
		//Claw Servos
		claw = c;
		lateral = l;
		vertical = v;
	}

	//Setup
	public void init() {
		//Run any initialization procedures: motor encoders, reset runtime, Etc.
	}

	//Runs at start
	//Runs once
	public void start() {
		//Run any start time processes: set motor positions, start elapsed time, Etc.
	}

	//Loops
	public void loop() {
		//Loop. Will most likely just be a while loop waiting for motors to reach position, or waiting set time for servos
	}

	//Stops
	public void stop(){
		//Stop motors
	}
}
