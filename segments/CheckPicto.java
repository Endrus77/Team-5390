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
import commands.MoveForwardFour;
import commands.MoveTurnInPlace;

//import Commands

public class CheckPicto extends Segment {

	//Variables

	//Command Array
	private Command[] commands = new Command[6];
	//blank arrays here

	//Spot: blue is 1, red is 2
    //Location: side is 1, corner is 2
	private int color;
	private int cameraId;
	private int location;

	//Motors
	//Wheel motors
	private DcMotor motorRF;
	private DcMotor motorLF;
	private DcMotor motorRB;
	private DcMotor motorLB;

	//Servos
	//Claw servos
	private Servo clawR;
	private Servo clawL;

	//CheckImg checkImage = new CheckImg();

	//Spot 0 Commands
	//Key:
	//mF = MoveForward, mT = MoveTurn
	//R = Red, B = Blue, A = all
	//S = Straight, C = Corner
	//Red Straight
	private MoveForwardFour mFRSRightCrypto = new MoveForwardFour(0.75, -0.75, -0.75, 0.75, 7, -7, -7, 7); //red side right
	private MoveForwardFour mFRSCenterCrypto = new MoveForwardFour(0.75, -0.75, -0.75, 0.75, 15.5, -15.5, -15.5, 15.5); //red side center
	private MoveForwardFour mFRSLeftCrypto = new MoveForwardFour(0.75, -0.75, -0.75, 0.75, 22, -22, -22, 22); //red side left
	private MoveTurnInPlace mTRSIntoBox = new MoveTurnInPlace(0.75, 180); //red side turn into crypto box

	//Red Corner
	private MoveForwardFour mFRCOffBoard = new MoveForwardFour(0.75, -0.75, -0.75, 0.75, 10, -10, -10, 10); //red corner off board
	private MoveForwardFour mFRCRightCrypto = new MoveForwardFour(0.5, 0.5, 0.5, 0.5, 3, 3, 3, 3); //red corner right
	private MoveForwardFour mFRCCenterCrypto = new MoveForwardFour(0.5, 0.5, 0.5, 0.5, 8, 8, 8, 8); //red corner center
	private MoveForwardFour mFRCLeftCrypto = new MoveForwardFour(0.5, 0.5, 0.5, 0.5, 13, 13, 13, 13); //red corner left
	private MoveTurnInPlace mTRCIntoBox = new MoveTurnInPlace(0.5, 90); // red corner turn into crypto box

	//Left Straight
	private MoveForwardFour mFBSRightCrypto = new MoveForwardFour(-0.75, 0.75, 0.75, -0.75, -15, 15, 15, -15); //blue corner right
	private MoveForwardFour mFBSCenterCrypto = new MoveForwardFour(-0.75, 0.75, 0.75, -0.75, -23.5, 23.5, 23.5, -23.5); //blue corner center
	private MoveForwardFour mFBSLeftCrypto = new MoveForwardFour(-0.75, 0.75, 0.75, -0.75, -30, 30, 30, -30); //blue corner left
	private MoveTurnInPlace mTBSIntoBox = new MoveTurnInPlace(-0.5, -180); // blue side turn away from crypto box

	//Left Corner
	private MoveForwardFour mFBCOffBoard = new MoveForwardFour(-0.75, 0.75, 0.75, -0.75, -25, 25, 25, -25); //blue corner over board
	private MoveForwardFour mFBCRightCrypto = new MoveForwardFour(0.5, 0.5, 0.5, 0.5, 3, 3, 3, 3); //blue side right
	private MoveForwardFour mFBCCenterCrypto = new MoveForwardFour(0.5, 0.5, 0.5, 0.5, 8, 8, 8, 8); //blue side center
	private MoveForwardFour mFBCLeftCrypto = new MoveForwardFour(0.5, 0.5, 0.5, 0.5, 13, 13, 13, 13); //blue side left
	private MoveTurnInPlace mTBCIntoBox = new MoveTurnInPlace(0.5, -90); //red corner turn into crypto box

	//All
	private MoveForwardFour mFAForwardBeforeTurn = new MoveForwardFour(0.5, 0.5, 0.5, 0.5, 2, 2, 2, 2);
	private MoveForwardFour mFAForwardAfterTurn = new MoveForwardFour(0.5, 0.5, 0.5, 0.5, 4, 4, 4, 4);
	private MoveForwardFour mFABackAfterTurn = new MoveForwardFour(-0.5, -0.5, -0.5, -0.5,-2, -2, -2, -2); //back up slightly
	private MoveServo openClaw = new MoveServo(1, 0); //opens claw and drops the cube
	private CheckImg checkImg = new CheckImg(); //runs the program for checking the pictograpgh

	//Misc
	private MoveForward empty = new MoveForward(0.5, 0.5, 0, 0); //used to pad the command array so nothing breaks


	private int index;

	//Constructor
	//Add values to be taken here
	public CheckPicto(DcMotor mRF, DcMotor mLF, DcMotor mRB, DcMotor mLB, Servo cR, Servo cL, int clr, int id, int loc) {
		//Set passed values to object values here

		//Spot
		color = clr;
		cameraId = id;
		location = loc;

		//Motors
		//Wheel motors
		motorRF = mRF;
		motorLF = mLF;
		motorRB = mRB;
		motorLB = mLB;

		clawR = cR;
		clawL = cL;

		//Servos
		//Claw Servos\
	}

	//Setup
	public void init() {
		//Initialize Objects here

		//Red Straight
		mFRSRightCrypto.setMotors(motorRF, motorLF, motorRB, motorLB);
		mFRSCenterCrypto.setMotors(motorRF, motorLF, motorRB, motorLB);
		mFRSLeftCrypto.setMotors(motorRF, motorLF, motorRB, motorLB);
		mTRSIntoBox.setMotors(motorRF, motorLF, motorRB, motorLB);

		//Red Corner
		mFRCOffBoard.setMotors(motorRF, motorLF, motorRB, motorLB);
		mFRCRightCrypto.setMotors(motorRF, motorLF, motorRB, motorLB);
		mFRCCenterCrypto.setMotors(motorRF, motorLF, motorRB, motorLB);
		mFRCLeftCrypto.setMotors(motorRF, motorLF, motorRB, motorLB);
		mTRCIntoBox.setMotors(motorRF, motorLF, motorRB, motorLB);

		//Blue Straight
		mFBSRightCrypto.setMotors(motorRF, motorLF, motorRB, motorLB);
		mFBSCenterCrypto.setMotors(motorRF, motorLF, motorRB, motorLB);
		mFBSLeftCrypto.setMotors(motorRF, motorLF, motorRB, motorLB);
		mTBSIntoBox.setMotors(motorRF, motorLF, motorRB, motorLB);

		//Blue Corner
		mFBCOffBoard.setMotors(motorRF, motorLF, motorRB, motorLB);
		mFBCRightCrypto.setMotors(motorRF, motorLF, motorRB, motorLB);
		mFBCCenterCrypto.setMotors(motorRF, motorLF, motorRB, motorLB);
		mFBCLeftCrypto.setMotors(motorRF, motorLF, motorRB, motorLB);
		mTBCIntoBox.setMotors(motorRF, motorLF, motorRB, motorLB);

		//All
		mFAForwardBeforeTurn.setMotors(motorRF, motorLF, motorRB, motorLB);
		mFAForwardAfterTurn.setMotors(motorRF, motorLF, motorRB, motorLB);
		mFABackAfterTurn.setMotors(motorRF, motorLF, motorRB, motorLB);
		openClaw.setServos( clawR);
		checkImg.setId(cameraId);

		//Misc
		empty.setMotors(motorRF, motorLF);
	}

	//Runs at start
	//Runs once
	public void start() {
		checkImg.init();
		checkImg.start();
	}
    //Constructs the command array
	public boolean conditional() {
		if (checkImg.loop())
			return true;
		else {
            int imageNumber;
            imageNumber = checkImg.getValue();
            //Red Team
            if (color == 1)
                //Red Side
                if (location == 1) {
                    //Right
                    if (imageNumber == 1)
                        commands[0] = mFRSRightCrypto;
                    //Center
                    if (imageNumber == 2)
                        commands[0] = mFRSCenterCrypto;
                    //Left
                    else if (imageNumber == 3)
                        commands[0] = mFRSLeftCrypto;
                    commands[1] = mFAForwardBeforeTurn;
                    commands[2] = mTRSIntoBox;
                    commands[3] = mFAForwardAfterTurn;
                    commands[4] = openClaw;
                    commands[5] = mFABackAfterTurn;
                }

                //Red Corner
                else {
                    // still needs to be changed and calibrated
                    commands[0] = mFRCOffBoard;
                    //Right
                    if (imageNumber == 1)
                        commands[1] = mFRCRightCrypto;
                    //Center
                    if (imageNumber == 2)
                        commands[1] = mFRCCenterCrypto;
                    //Left
                    else if (imageNumber == 3)
                        commands[1] = mFRCLeftCrypto;
                    commands[2] = mFAForwardBeforeTurn;
                    commands[3] = mTRCIntoBox;
                    commands[4] = mFAForwardAfterTurn;
                    commands[5] = openClaw;
                    commands[6] = mFABackAfterTurn;
                }
            //Blue Team
            else
                //Blue Side
                if (location == 1) {
                    // still needs to be changed and calibrated
                    //Right
                    if (imageNumber == 1)
                        commands[0] = mFBSRightCrypto;
                    //Center
                    if (imageNumber == 2)
                        commands[0] = mFBSCenterCrypto;
                    //Left
                    else if (imageNumber == 3)
                        commands[0] = mFBSLeftCrypto;
                    commands[1] = mFAForwardBeforeTurn;
                    commands[2] = mTBSIntoBox;
                    commands[3] = mFAForwardAfterTurn;
					commands[4] = openClaw;
                    commands[5] = mFABackAfterTurn;
                }
                //Blue Corner
                else {
                    //needs to be changed and calibrated
                    commands[0] = mFBCOffBoard;
                    //Right
                    if (imageNumber == 1)
                        commands[1] = mFBCRightCrypto;
                    //Center
                    if (imageNumber == 2)
                        commands[1] = mFBCCenterCrypto;
                    //Left
                    else if (imageNumber == 3)
                        commands[1] = mFBCLeftCrypto;
                    commands[2] = mFAForwardBeforeTurn;
                    commands[3] = mTBCIntoBox;
                    commands[4] = mFAForwardAfterTurn;
                    commands[5] = openClaw;
                    commands[6] = mFABackAfterTurn;
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
	}
}
