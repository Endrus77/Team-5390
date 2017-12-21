//Checks pictograph, runs to appropiate spot then drops block
//Takes all motors being used as parameters
//For now the distances will be hardcoded

//imports

package segments;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuMarkIdentification;

import commands.CheckImg;
import commands.Command;
import commands.MoveClaw;
import commands.MoveForward;
import commands.MoveTurn;

//import Commands

public class CheckPicto extends Segment {

	//Variables

	//Command Array
	private Command[] commands = new Command[6];
	//blank arrays here

	//Spot
	//Corner is 0, straight is 1
	private int spot;
	private int cameraId;
	private int location;

	//Motors
	//Wheel motors
	private DcMotor motorR;
	private DcMotor motorL;

	//Servos
	//Claw servos
	private Servo clawR;
	private Servo clawL;
	private Servo ballHolder;
	private Servo ballArm;
	private Servo ballHitter;

	//CheckImg checkImage = new CheckImg();

	//Spot 0 Commands
	MoveForward moveForward1 = new MoveForward(0.5, 0.5, 5, 5);
	MoveForward moveForward2 = new MoveForward(0.5, 0.5, 11, 11);
	MoveForward moveForward3 = new MoveForward(0.5, 0.5, 16, 16);
	MoveForward moveForward4 = new MoveForward(0.5, 0.5, 3, 3);
	MoveForward backUp = new MoveForward(-0.5, -0.5, 1, 1);
	MoveForward empty = new MoveForward(0.5, 0.5, 0, 0);
	MoveForward moveBack1 = new MoveForward(-0.5, -0.5, -7, -7);
	MoveForward moveBack2 = new MoveForward(-0.5, -0.5, -10, -10);
	MoveForward moveBack3 = new MoveForward(-0.5, -0.5, -14, -14);
	MoveTurn moveTurnF = new MoveTurn(0.5, 1, 95);
	MoveTurn moveTurnB = new MoveTurn(-0.5, 0, -95);
	MoveClaw openClaw = new MoveClaw(1, 0);
	CheckImg checkImg = new CheckImg();

	ConceptVuMarkIdentification pictoScan = new ConceptVuMarkIdentification();


	private int index;

	//Constructor
	//Add values to be taken here
	public CheckPicto(DcMotor mR, DcMotor mL, Servo bHl, Servo bA, Servo bHt, Servo cR, Servo cL, int spt, int id, int loc) {
		//Set passed values to object values here

		//Spot
		spot = spt;
		cameraId = id;
		location = loc;

		//Motors
		//Wheel motors
		motorR = mR;
		motorL = mL;

		ballHolder = bHl;
		ballArm = bA;
		ballHitter = bHt;

		clawR = cR;
		clawL = cL;

		//Servos
		//Claw Servos\
	}

	//Setup
	public void init() {
		//Initialize Objects here
		moveForward1.setMotors(motorR, motorL);
		moveForward2.setMotors(motorR, motorL);
		moveForward3.setMotors(motorR, motorL);
		moveForward4.setMotors(motorR, motorL);
		moveBack1.setMotors(motorR, motorL);
		moveBack2.setMotors(motorR, motorL);
		moveBack3.setMotors(motorR, motorL);
		moveTurnF.setMotors(motorR, motorL);
		moveTurnB.setMotors(motorR, motorL);
		empty.setMotors(motorR, motorL);
		openClaw.setServos(clawR, clawL);
		backUp.setMotors(motorR, motorL);
		checkImg.setId(cameraId);
	}

	//Runs at start
	//Runs once
	public void start() {
		checkImg.init();
		checkImg.start();

		commands[3] = openClaw;
	}

	public boolean conditional() {
		if (checkImg.loop())
			return true;
		else {
			int imageNumber;
			imageNumber = checkImg.getValue();
			if (spot == 2) {
				if (imageNumber == 1)
					commands[0] = moveForward1;
				if (imageNumber == 2)
					commands[0] = moveForward2;
				else if (imageNumber == 3)
					commands[0] = moveForward3;
				commands[1] = moveTurnF;
				commands[2] = empty;
				commands[4] = backUp;
			}
			else {
				if (imageNumber == 1)
					commands[0] = moveBack1;
				if (imageNumber == 2)
					commands[0] = moveBack2;
				else if (imageNumber == 3)
					commands[0] = moveBack3;
				commands[1] = moveTurnB;
				commands[2] = moveForward4;
				commands[4] = empty;
			}

			index = 0;
			commands[index].init();
			commands[index].start();
			return false;
		}
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
		//Stop motors
		motorR.setPower(0);
		motorL.setPower(0);
	}
}
