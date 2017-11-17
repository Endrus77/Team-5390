//Checks ball, hits ball with arm
//Takes arm motors, servos and turret
//For now the distances will be hardcoded

//imports

package segments;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import colorDetection.BeaconAnalyzer;

import commands.Command;
import commands.MoveArm;
import colorDetection.ReadBall;
import commands.RotateTurret;

//import Commands

public class CheckBall extends Segment {

	//Variables

	private int index;

	//Command Array
	private Command[] commands = new Command[4];
	//blank arrays here

	//Color
	//1 is blue, 2 is red
	private int color;

	//Screen Orientation
	private final double angle = 90;

	//Motors
	//Arm motors
	private DcMotor motor1;
	private DcMotor motor2;
	private DcMotor motor3;
	
	//Turret
	private DcMotor turret;

	//Servos
	//Claw servos
	private Servo claw;
	private Servo lateral;
	private Servo vertical;

	//Initialization
	private MoveArm moveUp = new MoveArm(0, 0, 0, 0, 0, 0); //Still needs values
	private RotateTurret rotateTo = new RotateTurret(0, 0); //Still needs values
	private MoveArm moveBetween = new MoveArm(0, 0, 0, 0, 0, 0); //Still needs values

	//Branch
	//If right
	private RotateTurret rotateRight = new RotateTurret(0, 0); //Still needs values;
	//If left
	private RotateTurret rotateLeft = new RotateTurret(0, 0); //Still needs values;

	//Color Detection
	private ReadBall ReadBall = new ReadBall();

	//Constructor
	//Add values to be taken here
	public CheckBall(DcMotor m1, DcMotor m2, DcMotor m3, DcMotor t, Servo c, Servo l, Servo v, int clr){
		//Set passed values to object values here

		//Color
		color = clr;

		//Motors
		//Arm Motors
		motor1 = m1;
		motor2 = m2;
		motor3 = m3;
		//Turret
		turret = t;

		//Servos
		//Claw Servos
		claw = c;
		lateral = l;
		vertical = v;
	}

	//Setup
	public void init () {
		//Make commands
		//First move arm and turret
		moveUp.setMotors(motor1, motor2, motor3);
		rotateTo.setMotor(turret);
		moveBetween.setMotors(motor1, motor2, motor3);

		//Branch
		//If right
		rotateRight.setMotor(turret);
		//If left
		rotateLeft.setMotor(turret);

		ReadBall.init();
	}

	//Runs at start
	//Runs once
	public void start() {

		ReadBall.loop();

		int imageNumber;
		imageNumber = BeaconAnalyzer.bColor;

		//Intialize commands. Defualted to rotate right
		commands[0] = moveUp;
		commands[1] = rotateTo;
		commands[2] = moveBetween;
		commands[3] = rotateRight;
		//Switch to roate left if ball is on other side
		if (imageNumber == color)
			commands[3] = rotateLeft;	
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
		//Stop motors
		motor1.setPower(0);
		motor2.setPower(0);
		motor3.setPower(0);
		turret.setPower(0);
	}
}
