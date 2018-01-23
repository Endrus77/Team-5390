//Checks pictograph, runs to appropiate spot then drops block
//Takes all motors being used as parameters
//For now the distances will be hardcoded

//imports

package segments;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuMarkIdentification;

import commands.CheckImg;
import commands.Command;
import commands.MoveClaw;
import commands.MoveForward;
import commands.MoveTurn;
import commands.MoveServo;

//import Commands

public class CheckPicto extends Segment {

	//Variables

	//Command Array
	private Command[] commands = new Command[6];
	//blank arrays here

	//Spot: blue is 1, red is 2
    //Location: side is 1, corner is 2
	private int spot;
	private int cameraId;
	private int location;

	//Motors
	//Wheel motors
	private DcMotor motorR;
	private DcMotor motorL;

	//Servos
	//Claw servos
	private Servo clawR;
	private Servo clawL;

	//CheckImg checkImage = new CheckImg();

	//Spot 0 Commands
	private MoveForward moveForward1 = new MoveForward(0.5, 0.5, 5, 5); //red side right
	private MoveForward moveForward2 = new MoveForward(0.5, 0.5, 11, 11); //red side center
	private MoveForward moveForward3 = new MoveForward(0.5, 0.5, 16, 16); //red side left
	private MoveForward moveForward4 = new MoveForward(0.5, 0.5, 3, 3); //blue side constant
	private MoveForward moveForward5 = new MoveForward(0.5, 0.5, 5, 5); //red corner constant
	private MoveForward moveForward6 = new MoveForward(0.5, 0.5, 5, 5); //red corner right
	private MoveForward moveForward7 = new MoveForward(0.5, 0.5, 5, 5); //red corner center
	private MoveForward moveForward8 = new MoveForward(0.5, 0.5, 5, 5); //red corner left
	private MoveForward moveForward9 = new MoveForward(0.5, 0.5, 5, 5); //blue corner right
	private MoveForward moveForward10 = new MoveForward(0.5, 0.5, 5, 5); //blue corner center
	private MoveForward moveForward11 = new MoveForward(0.5, 0.5, 5, 5); //blue corner left
	private MoveForward moveForward12 = new MoveForward(0.5, 0.5, 5, 5); //blue corner constant
	private MoveForward backUp = new MoveForward(-0.5, -0.5, 1, 1); //back up slightly
	private MoveForward empty = new MoveForward(0.5, 0.5, 0, 0); //used to pad the command array so nothing breaks
	private MoveForward moveBack1 = new MoveForward(-0.5, -0.5, -7, -7); //blue side right
	private MoveForward moveBack2 = new MoveForward(-0.5, -0.5, -10, -10); //blue side center
	private MoveForward moveBack3 = new MoveForward(-0.5, -0.5, -14, -14); //blue side left
	//p- power of motors. w- which wheel will be powered (1 is left, 0 is right). a- angle of rotation.
	private MoveTurn moveTurnF = new MoveTurn(0.5, 1, 95); //red side turn into crypto box
	private MoveTurn moveTurnB = new MoveTurn(-0.5, 0, -95); // blue side turn away from crypto box
	private MoveTurn moveTurnE = new MoveTurn(0.5, 1, 95); // red corner turn into crypto box
	private MoveTurn moveTurnA = new MoveTurn(0.5, 0, -95); //red corner turn off of balance board
	private MoveTurn moveTurnD = new MoveTurn(0.5, 0, -95); //blue corner turn off of balance board
	private MoveTurn moveTurnC = new MoveTurn(0.5, 0, -95); //blue corner turn into crypto box
	private MoveServo openClaw = new MoveServo(1, 0); //opens claw and drops the cube
	private CheckImg checkImg = new CheckImg(); //runs the program for checking the pictograpgh


	private int index;

	//Constructor
	//Add values to be taken here
	public CheckPicto(DcMotor mR, DcMotor mL, Servo cR, Servo cL, int spt, int id, int loc) {
		//Set passed values to object values here

		//Spot
		spot = spt;
		cameraId = id;
		location = loc;

		//Motors
		//Wheel motors
		motorR = mR;
		motorL = mL;

		clawR = cR;
		clawL = cL;

		//Servos
		//Claw Servos\
	}

	//Setup
	public void init() {
		//Initialize Objects here
		moveForward1.setMotors(motorR, motorL);
		moveForward2.setMotors(motorR, motorL);
		moveForward3.setMotors(motorR, motorL);
		moveForward4.setMotors(motorR, motorL);
        moveForward5.setMotors(motorR, motorL);
        moveForward6.setMotors(motorR, motorL);
        moveForward7.setMotors(motorR, motorL);
        moveForward8.setMotors(motorR, motorL);
        moveForward9.setMotors(motorR, motorL);
        moveForward10.setMotors(motorR, motorL);
        moveForward11.setMotors(motorR, motorL);
        moveForward12.setMotors(motorR, motorL);
		moveBack1.setMotors(motorR, motorL);
		moveBack2.setMotors(motorR, motorL);
		moveBack3.setMotors(motorR, motorL);
		moveTurnF.setMotors(motorR, motorL);
		moveTurnB.setMotors(motorR, motorL);
        moveTurnE.setMotors(motorR, motorL);
        moveTurnA.setMotors(motorR, motorL);
        moveTurnD.setMotors(motorR, motorL);
        moveTurnC.setMotors(motorR, motorL);
		empty.setMotors(motorR, motorL);
		openClaw.setServos( clawR);
		backUp.setMotors(motorR, motorL);
		checkImg.setId(cameraId);
	}

	//Runs at start
	//Runs once
	public void start() {
		checkImg.init();
		checkImg.start();

		commands[4] = openClaw;
	}
    //Constructs the command array
	public boolean conditional() {
		if (checkImg.loop())
			return true;
		else {
            int imageNumber;
            imageNumber = checkImg.getValue();
            //Red Team
            if (spot == 2)
                //red side
                if (location == 1) {
                    //Right
                    if (imageNumber == 1)
                        commands[0] = moveForward1;
                    //Center
                    if (imageNumber == 2)
                        commands[0] = moveForward2;
                    //Left
                    else if (imageNumber == 3)
                        commands[0] = moveForward3;
                    commands[1] = moveTurnF;
                    commands[2] = empty;
                    commands[3] = empty;
                    commands[5] = backUp;
                }

                //red corner
                else {
                    // still needs to be changed and calibrated
                    commands[0] = moveForward5;
                    commands[1] = moveTurnA;
                    //Right
                    if (imageNumber == 1)
                        commands[2] = moveForward6;
                    //Center
                    if (imageNumber == 2)
                        commands[2] = moveForward7;
                    //Left
                    else if (imageNumber == 3)
                        commands[2] = moveForward8;
                    commands[3] = moveTurnE;
                    commands[5] = backUp;
                }
            //Blue Team
            else
                //blue side
                if (location == 1) {
                    // still needs to be changed and calibrated
                    //Right
                    if (imageNumber == 1)
                        commands[0] = moveBack1;
                    //Center
                    if (imageNumber == 2)
                        commands[0] = moveBack2;
                    //Left
                    else if (imageNumber == 3)
                        commands[0] = moveBack3;
                    commands[1] = moveTurnB;
                    commands[2] = moveForward4;
                    commands[3] = empty;
                    commands[5] = backUp;
                }
                //blue corner
                else {
                    //needs to be changed and calibrated
                    commands[0] = moveTurnD;
                    //Right
                    if (imageNumber == 1)
                        commands[1] = moveForward9;
                    //Center
                    if (imageNumber == 2)
                        commands[1] = moveForward10;
                    //Left
                    else if (imageNumber == 3)
                        commands[1] = moveForward11;
                    commands[2] = moveTurnC;
                    commands[3] = moveForward12;
                    commands[5] = backUp;
                }
        }
			index = 0;
			commands[index].init();
			commands[index].start();
			return false;
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
			}
			return true;
		}
	}

	//Stops
	public void stop(){
		//Stop motors
		motorR.setPower(0);
		motorL.setPower(0);
	}
}
