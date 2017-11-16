//Moves arm
//Takes 3 motors, powers and angles

//import

package commands;

import com.qualcomm.robotcore.hardware.DcMotor;

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
	public MoveArm(int p1, int p2, int p3, int a1, int a2, int a3) {
		//Set to passed variables

		//Power
		motor1P = p1;
		motor2P = p2;
		motor3P = p3;
		//Angles
		motor1A = a1;
		motor2A = a2;
		motor3A = a3;
	}

	public void setMotors(DcMotor m1, DcMotor m2, DcMotor m3) {
		//Motors
		motor1 = m1;
		motor2 = m2;
		motor3 = m3;
	}

	//Setup
	@Override
	public void init() {
		//Encoders
		motor1E = (int)((motor1A / 360) * Command.ENCODERTICKS);
		motor2E = (int)((motor2A / 360) * Command.ENCODERTICKS);
		motor3E = (int)((motor3A / 360) * Command.ENCODERTICKS);

		//Sets direction
		motor1.setDirection(DcMotor.Direction.FORWARD);
		motor2.setDirection(DcMotor.Direction.FORWARD);
		motor3.setDirection(DcMotor.Direction.FORWARD);

		//Sets encoders
		motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);

	}

	//Runs at start
	//Runs once
	@Override
	public void start() {
		//Set motor powers
		motor1.setPower(motor1P);
		motor2.setPower(motor2P);
		motor3.setPower(motor3P);

		//Set motor positions
		motor1.setTargetPosition(motor1E);
		motor2.setTargetPosition(motor2E);
		motor3.setTargetPosition(motor3E);

	}

	//Loops
	@Override
	public boolean loop() {
		//Wait for motors to finish moving
		return (motor1.isBusy() || motor2.isBusy() || motor3.isBusy());
	}

	//Stops motors
	@Override
	public void stop(){
		//Stop motors
		motor1.setPower(0);
		motor2.setPower(0);
		motor3.setPower(0);
	}
}
