//Checks pictograph, runs to appropiate spot then drops block
//Takes all motors being used as parameters
//For now the distances will be hardcoded

//imports

package segments;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import commands.CheckImg;
import commands.Command;
import commands.MoveArm;
import commands.MoveClaw;
import commands.MoveForward;
import commands.MoveTurn;
import commands.RotateTurret;

//import Commands

public class CheckPicto extends Segment {

	//Variables
	
	//Command Array
	private Command[] commands = new Command[6];
	//blank arrays here

	//Spot
	//Corner is 0, straight is 1
	private int spot;

	//Motors
	//Arm motors
	private DcMotor motor1;
	private DcMotor motor2;
	private DcMotor motor3;
	//Wheel motors
	private DcMotor motorR;
	private DcMotor motorL;
	//Turret motor
	private DcMotor turret;

	//Servos
	//Claw servos
	private Servo claw;
	private Servo lateral;
	private Servo vertical;

	CheckImg checkImage = new CheckImg();

	//Spot 0 Commands
	MoveForward s0mF = new MoveForward(0, 0, 0, 0);
	MoveTurn s0mT = new MoveTurn(0, 0, 0);
	//1 moveForward per image
	MoveForward s0mF1 = new MoveForward(0, 0, 0, 0);
	MoveForward s0mF2 = new MoveForward(0, 0, 0, 0);
	MoveForward s0mF3 = new MoveForward(0, 0, 0, 0);
	RotateTurret s0rT = new RotateTurret(0, 0);
	MoveArm s0mA = new MoveArm(0, 0, 0, 0, 0, 0);
	MoveClaw s0mC = new MoveClaw(0, 0, 0);

	//Spot 1 Commands
	MoveForward s1mF1 = new MoveForward(0, 0, 0, 0);
	MoveForward s1mF2 = new MoveForward(0, 0, 0, 0);
	MoveForward s1mF3 = new MoveForward(0, 0, 0, 0);
	RotateTurret s1rT = new RotateTurret(0, 0);
	MoveArm s1mA = new MoveArm(0, 0, 0, 0, 0, 0);
	MoveClaw s1mC = new MoveClaw(0, 0, 0);

	private int index;

	//Constructor
	//Add values to be taken here
	public CheckPicto(DcMotor m1, DcMotor m2, DcMotor m3, DcMotor mR, DcMotor mL, DcMotor t, Servo c, Servo l, Servo v, int spt) {
		//Set passed values to object values here

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
		//Turret motor
		turret = t;

		//Servos
		//Claw Servos
		claw = c;
		lateral = l;
		vertical = v;
	}

	//Setup
	public void init() {
		//Initialize Objects here

		//Spot 0 Commands
		s0mF.setMotors(motorR, motorL);
		s0mT.setMotors(motorR, motorL);
		//1 moveForward per image
		s0mF1.setMotors(motorR, motorL);
		s0mF2.setMotors(motorR, motorL);
		s0mF3.setMotors(motorR, motorL);
		s0rT.setMotor(turret);
		s0mA.setMotors(motor1, motor2, motor3);
		s0mC.setServos(claw, lateral, vertical);

		//Spot 1 Commands
		s1mF1.setMotors(motorR, motorL);
		s1mF2.setMotors(motorR, motorL);
		s1mF3.setMotors(motorR, motorL);
		s1rT.setMotor(turret);
		s1mA.setMotors(motor1, motor2, motor3);
		s1mC.setServos(claw, lateral, vertical);
	}

	//Runs at start
	//Runs once
	public void start() {
		int imageNumber;
		imageNumber = checkImage.getValue();

		//Assume CheckImage returns int 1, 2, or 3
		//Move next to shelf then place box directly to side instead of moving the arm a lot
		//if spot=0, moveForward, moveTurn, moveForward depending on picto, rotateTurret to face shelf, extend arm, open claw
		if (spot == 0) {
			//Inititalizes array for Image 1
			//Fill in parameters once we get the measurements
			commands[0] = s0mF;
			commands[1] = s0mT;
			commands[2] = s0mF1;
			commands[3] = s0rT;
			commands[4] = s0mA;
			commands[5] = s0mC;

			//Changes second moveForward depending on which image was scanned
			if (imageNumber == 2)
				commands[2] = s0mF2;
			else if (imageNumber == 3)
				commands[2] = s0mF3;
		}		


		//if spot=1, moveForward depending on picto, rotateTurret to face shelf, extend arm, open claw
		if (spot == 1) {
			//Initializes array for Image 1
			//Fill in parameters once we get the measurments
			commands[0] = s1mF1;
			commands[1] = s1rT;
			commands[2] = s1mA;
			commands[3] = s1mC;

			//Changes first moveForward depending on which image was scanned
			if (imageNumber == 2)
				commands[0] = s1mF2;
			else if (imageNumber == 3)
				commands[0] = s1mF3;
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
		motor1.setPower(0);
		motor2.setPower(0);
		motor3.setPower(0);
		turret.setPower(0);
		motorR.setPower(0);
		motorL.setPower(0);
	}
}
