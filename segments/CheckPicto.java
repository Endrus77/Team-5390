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


	//Constructor
	//Add values to be taken here
	public CheckPicto(m1, m2, m3, mR, mL, t, c, l, v, spt) {
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
		CheckImage checkImage = new CheckImage();

		//Spot 0 Commands
		moveForward s0mF = new moveForward(motorR, motorL);
		moveTurn s0mT = new moveTurn(motorR, motorL);
		//1 moveForward per image
		moveForward s0mF1 = new moveForward(motorR, motorL);
		moveForward s0mF2 = new moveForward(motorR, motorL);
		moveForward s0mF3 = new moveForward(motorR, motorL);
		rotateTurret s0rT = new rotateTurret(turret);
		moveArm s0mA = new moveArm(motor1, motor2, motor3);
		moveClaw s0mC = new moveClaw(claw, lateral, vertical);

		//Spot 1 Commands
		moveForward s1mF1 = new moveForward(motorR, motorL);
		moveForward s1mF2 = new moveForward(motorR, motorL);
		moveForward s1mF3 = new moveForward(motorR, motorL);
		rotateTurret s1rT = new rotateTurret(turret);
		moveArm s1mA = new moveArm(motor1, motor2, motor3);
		moveClaw s1mC = new moveClaw(claw, lateral, vertical);
	}

	//Runs at start
	//Runs once
	public void start() {
		int imageNumber;
		imageNumber = checkImage;

		//Assume CheckImage returns int 1, 2, or 3
		//Move next to shelf then place box directly to side instead of moving the arm a lot
		//if spot=0, moveForward, moveTurn, moveForward depending on picto, rotateTurret to face shelf, extend arm, open claw
		if (spot == 0) {
			//Inititalizes array for Image 1
			//Fill in parameters once we get the measurements
			commands = {s0mF, s0mT, s0mF1, s0rT, s0mA, s0mC};

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
			commands = {s1mF1, s1rT, s1mA, s1mc};

			//Changes first moveForward depending on which image was scanned
			if (imageNumber == 2)
				commands[0] = s1mF2;
			else if (imageNumber == 3)
				commands[0] = s1mF3;
		}	
	}

	//Loops
	public void loop() {
		for (int i = 0; i < commands.length; i++) {
				commands[i].init();
				commands[i].start();
				commands[i].loop();
				commands[i].stop();
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
