//Checks ball, hits ball with arm
//Takes arm motors, servos and turret
//For now the distances will be hardcoded

//imports

package segments;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import commands.Command;
import commands.MoveForwardFour;
import commands.MoveServo;


//import Commands

public class CheckPictoFoward extends Segment {

	//Variables

	private int index;

	//Command Array
	private Command[] commands = new Command[1];
	//blank arrays here

	//Motors
	private DcMotor motorRF;
	private DcMotor motorLF;
	private DcMotor motorRB;
	private DcMotor motorLB;

	//Initialization
	private MoveForwardFour forward = new MoveForwardFour(0.5, 0.5, 0.5, 0.5, 2.1, 2.1, 2.1, 2.1);

	//Constructor
	//Add values to be taken here
	public CheckPictoFoward(DcMotor mRF, DcMotor mLF, DcMotor mRB, DcMotor mLB) {
		//Set passed values to object values here
		motorRF = mRF;
		motorLF = mLF;
		motorRB = mRB;
		motorLB = mLB;
	}

	//Setup
	public void init () {
		forward.setMotors(motorRF, motorRB, motorLF, motorLB);
	}

	//Runs at start
	//Runs once
	public void start() {

		//Intialize commands. Defaulted to rotate right
		commands[0] = forward;

		index = 0;
		commands[index].init();
		commands[index].start();
	}

	public boolean conditional() { return false; }

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
	}
}
