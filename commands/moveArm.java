//Moves arm
//Takes 3 motors, powers and angles

//import
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class MoveArm extends Command{

	//Variables
	
	//Motors
	private DcMotor motor1;
	private DcMotor motor2;
	private DcMotor motor3;
	//Motor Powers
	private int motor1P;
	private int motor2P;
	private int motor3P;
	//Motor Angles
	private int motor1A;
	private int motor2A;
	private int motor3A;
	//Motor Encoder Values
	private int motor1E;
	private int motor2E;
	private int motor3E;

	//Constructor
	public MoveArm(DcMotor m1, DcMotor m2, DcMotor m3, int p1, int p2, int p3, int a1, int a2, int a3) {
		//Set to passed variables
		
		//Motors
		motor1 = m1;
		motor2 = m2;
		motor3 = m3;
		//Power
		motor1P = p1;
		motor2P = p2;
		motor3P = p3;
		//Angles
		motor1A = a1;
		motor2A = a2;
		motor3A = a3;
	}

	//Setup
	public void init() {
		//Encoders
		motor1E = (motor1A / 360) * Command.ENCODERTICKS;
		motor2E = (motor2A / 360) * Command.ENCODERTICKS;
		motor3E = (motor3A / 360) * Command.ENCODERTICKS;

		//Sets encoders
		motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);

		//Elapsed Time
		runTime = new ElapsedTime();
	}

	//Runs at start
	//Runs once
	public void start() {
		//Set motor powers
		motor1.setPower(motor1P);
		motor2.setPower(motor2P);
		motor3.setPower(motor3P);

		//Set motor positions
		motor1.setPosition(motor1E);
		motor2.setPosition(motor2E);
		motor3.setPosition(motor3E);

		//Reset Elapsed Time
		runTime.reset();
	}

	//Loops
	public void loop() {
		//Wait for motors to finish moving
		while (motor1.isBusy() || motor2.isBusy() || motor3.isBusy()) {}
	}

	//Stops motors
	public void stop(){
		//Stop motors
		motor1.setPower(0);
		motor2.setPower(0);
		motor3.setPower(0);
	}
}
