//Checks ball, hits ball with arm
//Takes arm motors, servos and turret
//For now the distances will be hardcoded

//imports

package segments;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import commands.Command;
import commands.MoveClaw;
import commands.MoveMotor;
import commands.MoveServo;


//import Commands

public class CheckBallDrop extends Segment {

	//Variables

	private int index;

	//Command Array
	private Command[] commands = new Command[5];
	//blank arrays here

	//Motors
	//Arm motors
	private DcMotor lift;

	//Servos
	//Claw servos
	private Servo clawR;
	private Servo clawL;

	private Servo ballHolder;
	private Servo ballArm;
	private Servo ballHitter;

	//Initialization
	private MoveServo closeClaw = new MoveServo(0, 1);
	private MoveMotor moveMotor = new MoveMotor(0.6 ,360);
	private MoveServo setH = new MoveServo(0, 0.3);
	private MoveServo openHolder = new MoveServo(1, 0);
	private MoveServo dropArm = new MoveServo(0, 0.8);

	//Constructor
	//Add values to be taken here
	public CheckBallDrop(DcMotor l, Servo bHl, Servo bA, Servo bHt, Servo cR, Servo cL) {
		//Set passed values to object values here

		clawR = cR;
		clawL = cL;
		ballHolder = bHl;
		ballArm = bA;
		ballHitter = bHt;
		lift = l;
	}

	//Setup
	public void init () {
		//Make commands
		setH.setServos(ballHitter);
		openHolder.setServos(ballHolder);
		dropArm.setServos(ballArm);
		closeClaw.setServos(clawR);
		moveMotor.setMotor(lift);
	}

	//Runs at start
	//Runs once
	public void start() {

		//Intialize commands. Defualted to rotate right
		commands[0] = closeClaw;
		commands[1] = moveMotor;
		commands[2] = setH;
		commands[3] = openHolder;
		commands[4] = dropArm;

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
