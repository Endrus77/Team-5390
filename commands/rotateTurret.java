//Rotates turret
//Takes 1 motor, power, and angle

//imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class rotateTurret extends Command {

	//Variables
	
	//Motor
	private DcMotor motor;
	//Power
	private int motorP;
	//Angle
	private int motorA;
	//Motor Encoder Value
	private int motorE;


	//Constructor
	public rotateTurret(DcMotor m, int p, int a) {
		//Set to passed variables

		//Motor
		motor = m;
		//Power
		motorP = p;
		//Angle
		motorA = a;
	}

	//Setup
	public void init() {
		//Encoder
		motorE = (motorA / 360) * Command.ENCODERTICKS;

		//Sets encoders
		motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
	
	}

	//Runs at start
	//Runs once
	public void start() {
		//Set motor power
		motor.setPower(motorP);

		//Set motor position
		motor.setPosition(motorE);
	}

	//Loops
	public void loop() {
		//Wait for motors to finish moving
		while (motor.isBusy()) {}
	}

	//Stops motor
	public void stop(){
		//Stop motor
		motor.setPower(0);
	}
}
