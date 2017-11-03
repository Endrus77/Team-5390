//Checks ball, hits ball with arm
//Takes arm motors, servos and turret
//For now the distances will be hardcoded

//imports

package segments;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import commands.CheckImg;
import commands.MoveArm;
import commands.RotateTurret;

//import Commands
import commands.*;

public class CheckBall extends Segment {

	//Variables
	
	//Command Array
	private Command[] commands = new Command[4];
	//blank arrays here

	//Color
	//1 is blue, 2 is red
	private int color;

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

	//Commands
	private CheckImg checkImage = new CheckImg();

	//Initialization
	private MoveArm moveUp = new MoveArm(0, 0, 0, 0, 0, 0); //Still needs values
	private RotateTurret rotateTo = new RotateTurret(0, 0); //Still needs values
	private MoveArm moveBetween = new MoveArm(0, 0, 0, 0, 0, 0); //Still needs values

	//Branch
	//If right
	private RotateTurret rotateRight = new RotateTurret(0, 0); //Still needs values;
	//If left
	private RotateTurret rotateLeft = new RotateTurret(0, 0); //Still needs values;

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
	}

	//Runs at start
	//Runs once
	public void start() {
		int imageNumber;
		imageNumber = checkImage.getValue();

		//Intialize commands. Defualted to rotate right
		commands[0] = moveUp;
		commands[1] = rotateTo;
		commands[2] = moveBetween;
		commands[3] = rotateRight;
		//Switch to roate left if ball is on other side
		if (imageNumber == color)
			commands[3] = rotateLeft;
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
	}
}
