//ADD DESCRIPTION OF PROGRAM HERE

//imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Command {

	//Variables
	//Declare variables here

	//Initialization
	//Set global variables here (prob wont use)

	//Constructor
	//Add values to be taken here
	public Command() {
		//Set passed values to object values here
	}

	//Setup
	public void init() {
		//Run any initialization procedures: motor encoders, reset runtime, Etc.
	}

	//Runs at start
	//Runs once
	public void start() {
		//Run any start time processes: set motor positions, start elapsed time, Etc.
	}

	//Loops
	public void loop() {
		//Loop. Will most likely just be a while loop waiting for motors to reach position, or waiting set time for servos
	}

	//Stops
	public void stop(){
		//Stop motors
	}
}
