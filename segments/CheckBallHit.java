//Checks ball, hits ball with arm
//Takes arm motors, servos and turret
//For now the distances will be hardcoded

//imports

package segments;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import commands.Command;
import commands.MoveForward;
import commands.MoveServo;;
import commands.MoveForwardFour;


//import Commands

public class CheckBallHit extends Segment {

	//Variables

	private int index;

	//Command Array
	private Command[] commands = new Command[4];
	//blank arrays here

	//Color
	//0 is blue, 1 is red
	private int color;

	//Motors
	//Arm motors
	private DcMotor motorRF;
	private DcMotor motorRB;
	private DcMotor motorLF;
	private DcMotor motorLB;

	//Servos
	//Claw servos
	private Servo ballHolder;
	private Servo ballArm;
	private Servo ballHitter;

	private ColorSensor colorSensor;

	//Initialization
	private MoveServo hitR = new MoveServo(0.5, 0); //hit right ball
	private MoveServo hitL = new MoveServo(0.5, 1); //hit left ball
	private MoveServo empty = new MoveServo(0.5, 0.5); //pad command array so nothng breaks
	private MoveForwardFour moveRight = new MoveForwardFour(0.75, -0.75, -0.75, 0.75, 4.24, -4.24, -4.24, 4.24); //move Right
	private MoveServo raiseArm = new MoveServo(0.8, 0); //raise the ball hitting arm
	private MoveServo closeHolder = new MoveServo(0, 0);

	//Constructor
	//Add values to be taken here
	public CheckBallHit(DcMotor mRF, DcMotor mLF, DcMotor mRB, DcMotor mLB, DcMotor l, Servo bHl, Servo bA, Servo bHt, ColorSensor cS, int clr){
		//Set passed values to object values here

		//Color
		color = clr;

		//Motors
		motorRF = mRF;
		motorRB = mRB;
		motorLF = mLF;
		motorLB = mLB;

		ballHolder = bHl;
		ballArm = bA;
		ballHitter = bHt;

		colorSensor = cS;
	}

	//Setup
	public void init () {
		//Make commands
		//First move arm and turret
		moveRight.setMotors(motorRF, motorRB, motorLF, motorLB);
		hitR.setServos(ballHitter);
		hitL.setServos(ballHitter);
		empty.setServos(ballArm);
		raiseArm.setServos(ballArm);
		closeHolder.setServos(ballHolder);
		colorSensor.enableLed(true);
		//ReadBall.init();
	}

	//Runs at start
	//Runs once
	public void start() {
		int imageNumber;
		imageNumber = -1;
		if (colorSensor.red() >= 1)
			imageNumber = 0;
		else if (colorSensor.blue() >= 1)
			imageNumber = 1;

		//Intialize commands. Defualted to rotate right
		commands[0] = hitR;
		commands[1] = raiseArm;
		commands[2] = closeHolder;
		commands[3] = moveRight;
		//Switch to rotate left if ball is on other side
		if (imageNumber == color) {
			commands[0] = hitL;
		}
		else if (imageNumber == -1)
			commands[0] = empty;
		index = 0;
		commands[index].init();
		commands[index].start();
	}

	public boolean conditional() { return false; }


	//Loops
	public boolean loop() {
		if (commands[index].loop())
			return true;
		else {
			commands[index].stop();
			index++;
			if (index == commands.length)
				return false;
			else {
				commands[index].init();
				commands[index].start();
			}
			return true;
		}
	}

	//Stops
	public void stop(){
	}
}
