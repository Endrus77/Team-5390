//Checks pictograph, runs to appropiate spot then drops block
//Takes all motors being used as parameters
//For now the distances will be hardcoded

//imports

package segments;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import commands.Command;
import commands.MoveClaw;
import commands.MoveForward;
import commands.MoveTurn;
import commands.CheckImg;
import colorDetection.ConceptVuMarkIdentification;

//import Commands

public class CheckPicto extends Segment {

	//Variables

	//Command Array
	private Command[] commands = new Command[4];
	//blank arrays here

	//Spot
	//Corner is 0, straight is 1
	private int spot;
	private int cameraId;

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
	MoveForward moveForward1 = new MoveForward(0.5, 0.5, 6, 6);
	MoveForward moveForward2 = new MoveForward(0.5, 0.5, 11, 11);
	MoveForward moveForward3 = new MoveForward(0.5, 0.5, 15, 15);
	MoveForward moveBack1 = new MoveForward(-0.5, -0.5, -6, -6);
	MoveForward moveBack2 = new MoveForward(-0.5, -0.5, -11, -11);
	MoveForward moveBack3 = new MoveForward(-0.5, -0.5, -15, -15);
	MoveTurn moveTurnF = new MoveTurn(0.5, 1, 85);
	MoveTurn moveTurnB = new MoveTurn(-0.5, 1, -85);
	MoveClaw openClaw = new MoveClaw(1, 0);
	MoveForward moveBackF = new MoveForward(0.3, 0.3, 2, 2);
	MoveForward moveBackB = new MoveForward(-0.3, -0.3, -2, -2);
	CheckImg checkImg = new CheckImg();

	ConceptVuMarkIdentification pictoScan = new ConceptVuMarkIdentification();


	private int index;

	//Constructor
	//Add values to be taken here
	public CheckPicto(DcMotor mR, DcMotor mL, Servo bHl, Servo bA, Servo bHt, Servo cR, Servo cL, int spt, int id) {
		//Set passed values to object values here

		//Spot
		spot = spt;
		cameraId = id;

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
		moveBack1.setMotors(motorR, motorL);
		moveBack2.setMotors(motorR, motorL);
		moveBack3.setMotors(motorR, motorL);
		moveTurnF.setMotors(motorR, motorL);
		moveTurnB.setMotors(motorR, motorL);
		openClaw.setServos(clawR, clawL);
		moveBackF.setMotors(motorR, motorL);
		moveBackB.setMotors(motorR, motorL);
		checkImg.setId(cameraId);
	}

	//Runs at start
	//Runs once
	public void start() {
		int imageNumber;
		checkImg.init();
		checkImg.start();
		/*imageNumber = pictoScan.pictoNum;*/
		imageNumber = 2;

		commands[2] = openClaw;

		if (spot == 2) {
			if (imageNumber == 1)
				commands[0] = moveForward1;
			if (imageNumber == 2)
				commands[0] = moveForward2;
			else if (imageNumber == 3)
				commands[0] = moveForward3;
			commands[1] = moveTurnF;
			commands[3] = moveBackF;
		}
		else {
			if (imageNumber == 1)
				commands[0] = moveBack1;
			if (imageNumber == 2)
				commands[0] = moveBack2;
			else if (imageNumber == 3)
				commands[0] = moveBack3;
			commands[1] = moveTurnB;
			commands[3] = moveBackB;
		}

		index = 0;
		commands[index].init();
		commands[index].start();
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
