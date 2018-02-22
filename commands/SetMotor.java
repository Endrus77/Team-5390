//Rotates turret
//Takes 1 motor, power, and angle

//imports

package commands;

import com.qualcomm.robotcore.hardware.DcMotor;

public class SetMotor extends Command {

	//Variables

	//Motor
	private DcMotor motor;
	//Power
	private double motorP;


	//Constructor
	public SetMotor(double p) {
		//Set to passed variables

		//Power
		motorP = p;
	}

	public void setMotor(DcMotor m) {
		//Motor
		motor = m;
	}

	//Setup
	public void init() {
		//Sets direction
		motor.setDirection(DcMotor.Direction.REVERSE);
	}

	//Runs at start
	//Runs once
	public void start() {
		//Set motor power
		motor.setPower(motorP);
	}

	//Loops
	public boolean loop() {
		//Wait for motors to finish moving
		return (motor.isBusy());
	}

	//Stops motor
	public void stop(){}
}
