//Rotates turret
//Takes 1 motor, power, and angle

//imports

package commands;

import com.qualcomm.robotcore.hardware.DcMotor;

public class RotateTurret extends Command {

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
	public RotateTurret(int p, int a) {
		//Set to passed variables

		//Power
		motorP = p;
		//Angle
		motorA = a;
	}

	public void setMotor(DcMotor m) {
		//Motor
		motor = m;
	}

	//Setup
	public void init() {
		//Encoder
		motorE = (int)((motorA / 360) * Command.ENCODERTICKS);

		//Sets direction
		motor.setDirection(DcMotor.Direction.FORWARD);

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
		motor.setTargetPosition(motorE);
	}

	//Loops
	public boolean loop() {
		//Wait for motors to finish moving
		return (motor.isBusy());
	}

	//Stops motor
	public void stop(){
		//Stop motor
		motor.setPower(0);
	}
}
