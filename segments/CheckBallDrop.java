//Checks ball, hits ball with arm
//Takes arm motors, servos and turret
//For now the distances will be hardcoded

//imports

package segments;

import com.qualcomm.robotcore.hardware.Servo;

import commands.Command;
import commands.MoveClaw;
import commands.MoveServo;


//import Commands

public class CheckBallDrop extends Segment {

	//Variables

	private int index;

	//Command Array
	private Command[] commands = new Command[4];
	//blank arrays here

	//Motors
	//Arm motors

	//Servos
	//Claw servos
	private Servo clawR;
	private Servo clawL;

	private Servo ballHolder;
	private Servo ballArm;
	private Servo ballHitter;

	//Initialization
	private MoveClaw closeClaw = new MoveClaw(0, 1);
	private MoveServo setH = new MoveServo(0, 0.3);
	private MoveServo openHolder = new MoveServo(1, 0);
	private MoveServo dropArm = new MoveServo(0, 0.5);

	//Constructor
	//Add values to be taken here
	public CheckBallDrop(Servo bHl, Servo bA, Servo bHt, Servo cR, Servo cL) {
		//Set passed values to object values here

		clawR = cR;
		clawL = cL;
		ballHolder = bHl;
		ballArm = bA;
		ballHitter = bHt;
	}

	//Setup
	public void init () {
		//Make commands
		//First move arm and turret
		setH.setServos(ballHitter);
		openHolder.setServos(ballHolder);
		dropArm.setServos(ballArm);
		closeClaw.setServos(clawR, clawL);
	}

	//Runs at start
	//Runs once
	public void start() {

		//Intialize commands. Defualted to rotate right
		commands[0] = closeClaw;
		commands[1] = setH;
		commands[2] = openHolder;
		commands[3] = dropArm;

		index = 0;
		commands[index].init();
		commands[index].start();}

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
