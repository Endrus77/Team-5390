//Checks pictograph, runs to appropiate spot then drops block
//Takes all motors being used as parameters
//For now the distances will be hardcoded

//imports

package segments;

import com.qualcomm.robotcore.hardware.DcMotor;

import commands.Command;
import commands.MoveForward;
import commands.MoveTurn;

//import Commands

public class TestSegment extends Segment {

	//Variables

	//Command Array
	private Command[] commands = new Command[8];
	//blank arrays here


	//Motors
	//Wheel motors
	private DcMotor motorR;
	private DcMotor motorL;

	private MoveForward f1 = new MoveForward(0.8, 0.8, 10, 10);
	private MoveTurn t1 = new MoveTurn(0.8, 0, 90);
	private MoveForward f2 = new MoveForward(0.8, 0.8, 10, 10);
	private MoveTurn t2 = new MoveTurn(0.8, 0, 90);
	private MoveForward f3 = new MoveForward(0.8, 0.8, 10, 10);
	private MoveTurn t3 = new MoveTurn(0.8, 0, 90);
	private MoveForward f4 = new MoveForward(0.8, 0.8, 10, 10);
	private MoveTurn t4 = new MoveTurn(0.8, 0, 90);



	private int index;

	//Constructor
	//Add values to be taken here
	public TestSegment(DcMotor mR, DcMotor mL) {
		//Set passed values to object values here

		//Wheel motors
		motorR = mR;
		motorL = mL;
	}

	//Setup
	public void init() {
		//Initialize Objects here

		f1.setMotors(motorR, motorL);
		f2.setMotors(motorR, motorL);
		f3.setMotors(motorR, motorL);
		f4.setMotors(motorR, motorL);
		t1.setMotors(motorR, motorL);
		t2.setMotors(motorR, motorL);
		t3.setMotors(motorR, motorL);
		t4.setMotors(motorR, motorL);
	}

	//Runs at start
	//Runs once
	public void start() {

		commands[0] = f1;
		commands[1] = t1;
		commands[2] = f2;
		commands[3] = t2;
		commands[4] = f3;
		commands[5] = t3;
		commands[6] = f4;
		commands[7] = t4;

		index = 0;
		commands[index].init();
		commands[index].start();
	}

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
				return true;
			}
		}
	}

	//Stops
	public void stop(){
		//Stop motors
		motorR.setPower(0);
		motorL.setPower(0);
	}
}
