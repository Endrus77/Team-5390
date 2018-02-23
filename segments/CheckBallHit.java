//Checks ball, hits ball with arm
//Takes arm motors, servos and turret
//For now the distances will be hardcoded

//imports

package segments;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

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
	int imageNumber;

	private ElapsedTime runTime;

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
	private MoveServo empty = new MoveServo(0.5, 0.5); //pad command array so nothng breaks
	private MoveForwardFour moveForward = new MoveForwardFour(0.75, 0.75, 0.75, 0.75, 2.1, 2.1, 2.1, 2.1); //move forward
	private MoveForwardFour moveForwardAfterBack = new MoveForwardFour(0.75, 0.75, 0.75, 0.75, 4.2, 4.2, 4.2, 4.2); //move forward
	private MoveForwardFour moveBack = new MoveForwardFour(-0.75, -0.75, -0.75, -0.75, -2.1, -2.1, -2.1, -2.1); //move back
	private MoveServo raiseArm = new MoveServo(1, 0); //raise the ball hitting arm

	//Constructor
	//Add values to be taken here
	public CheckBallHit(DcMotor mRF, DcMotor mLF, DcMotor mRB, DcMotor mLB, Servo bA, ColorSensor cS, int clr){
		//Set passed values to object values here

		//Color
		color = clr;

		//Motors
		motorRF = mRF;
		motorRB = mRB;
		motorLF = mLF;
		motorLB = mLB;

		ballArm = bA;

		colorSensor = cS;
	}

	//Setup
	public void init () {
		//Make commands
		//First move arm and turret
		moveForward.setMotors(motorRF, motorRB, motorLF, motorLB);
		moveBack.setMotors(motorRF, motorRB, motorLF, motorLB);
		empty.setServos(ballArm);
		raiseArm.setServos(ballArm);
		colorSensor.enableLed(true);
		//ReadBall.init();
		runTime = new ElapsedTime();
	}

	//Runs at start
	//Runs once
	public void start() {
		imageNumber = -1;
	}

	public boolean conditional() {
		if (imageNumber == -1 && runTime.time() < 2) {
			if (colorSensor.red() >= 1)
				imageNumber = 1;
			else if (colorSensor.blue() >= 1)
				imageNumber = 0;
		}
		else {
			//Intialize commands. Defualted to rotate right
			commands[0] = moveForward;
			commands[1] = raiseArm;
			commands[2] = empty;
			//Switch to rotate left if ball is on other side
			if (imageNumber == color) {
				commands[0] = moveBack;
				commands[2] = moveForwardAfterBack;
			}
			else if (imageNumber == -1)
				commands[2] = moveForward;
			commands[index].init();
			commands[index].start();
			return false;
		}
		return true;
	}


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
