//Checks ball, hits ball with arm
//Takes arm motors, servos and turret
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
	CheckImage checkImage = new CheckImage();

	//Initialization

	//Constructor
	//Add values to be taken here
	public CheckBall(m1, m2, m3, c, l, v, clr){
		//Set passed values to object values here

		//Color
		color = clr;

		//Motors
		//Arm Motors
		motor1 = m1;
		motor2 = m2;
		motor3 = m3;

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
		MoveArm moveUp = new MoveArm(motor1, motor2, motor3); //Still needs values
		MoveTurret rotateTo = new MoveTurret(turret); //Still needs values
		MoveArm moveBetween = new MoveArm(motor1, motor2, motor3); //Still needs values

		//Branch
		//If right
		MoveTurret rotateRight = new MoveTurret(turret); //Still needs values;
		//If left
		MoveTurret rotateLeft = new MoveTurret(turret); //Still needs values;
	}

	//Runs at start
	//Runs once
	public void start() {
		int imageNumber;
		imageNumber = checkImage;

		//Intialize commands. Defualted to rotate right
		commands = {moveUp, rotateTo, moveBetween, rotateRight};
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
