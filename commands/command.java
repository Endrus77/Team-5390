//Overall structure of commands
//Will be extended by individual objects
//All commands musts have these functions and variables

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
	private static int DIAMETER;
	private static int CIRCUMFRENCE;
	private static int ENCODERTICKS;

	//Initialization
	DIAMETER = 4;
	CIRCUMFRENCE = 4 * Math.PI;
	ENCODERTICKS = 1680;

	//Constructor
	public Command() {

	}

	//Setup
	public void init() {

	}

	//Runs at start
	//Runs once
	public void start() {

	}

	//Loops
	public void loop() {

	}

	//Stops
	public void stop(){

	}
}
