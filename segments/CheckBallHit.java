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
import commands.MoveServo;


//import Commands

public class CheckBallHit extends Segment {

	//Variables

	private int index;

	//Command Array
	private Command[] commands = new Command[3];
	//blank arrays here

	//Color
	//1 is blue, 2 is red
	private int color;
	private int spot;

	//Motors
	//Arm motors
	private DcMotor motorR;
	private DcMotor motorL;

	//Servos
	//Claw servos
	private Servo ballArm;
	private Servo ballHitter;

	private ColorSensor colorSensor;

	//Initialization
	private MoveServo hitR = new MoveServo(0.5, 0); //hit right ball
	private MoveServo hitL = new MoveServo(0.5, 1); //hit left ball
	private MoveServo empty = new MoveServo(0.5, 0.5); //pad command array so nothng breaks
	private MoveForward moveForward = new MoveForward(0.5, 0.5, 3, 3); //move fowards
	private MoveForward moveBackwards = new MoveForward(-0.5, -0.5, -3, -3); //move back
	private MoveServo raiseArm = new MoveServo(0.5, 0); //raise the ball hitting arm

	//Constructor
	//Add values to be taken here
	public CheckBallHit(DcMotor mL, DcMotor mR, DcMotor l, Servo bA, Servo bHt, ColorSensor cS, int clr, int spt){
		//Set passed values to object values here

		//Color
		color = clr;
		spot = spt;

		//Motors
		motorR = mR;
		motorL = mL;

		ballArm = bA;
		ballHitter = bHt;

		colorSensor = cS;
	}

	//Setup
	public void init () {
		//Make commands
		//First move arm and turret
		moveForward.setMotors(motorR, motorL);
		moveBackwards.setMotors(motorR, motorL);
		hitR.setServos(ballHitter);
		hitL.setServos(ballHitter);
		empty.setServos(ballArm);
		raiseArm.setServos(ballArm);
		colorSensor.enableLed(true);
		//ReadBall.init();
	}

	//Runs at start
	//Runs once
	public void start() {
		int imageNumber;
		imageNumber = -1;
		if (colorSensor.red() >= 1)
			imageNumber = 1;
		else if (colorSensor.blue() >= 1)
			imageNumber = 0;

		//Intialize commands. Defualted to rotate right
		commands[0] = hitR;
		commands[1] = raiseArm;
		commands[2] = moveForward;
		//Switch to roate left if ball is on other side
		if (imageNumber == color) {
			commands[0] = hitL;
		}
		else if (imageNumber == -1)
			commands[0] = empty;
		if (spot == 2) {
			commands[2] = moveBackwards;
		}
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
		//Stop motors
		motorR.setPower(0);
		motorL.setPower(1);
	}
}
