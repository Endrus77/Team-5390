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
import commands.SetMotor;

//import Commands

public class CheckPicto extends Segment {

	//Variables

	//Command Array
	private Command[] commands = new Command[9];
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
	private DcMotor intakeLeft;
	private DcMotor intakeRight;
	private DcMotor flip;

	//Servos
	//Claw servos
	private Servo clawR;

	//CheckImg checkImage = new CheckImg();

	//Spot 0 Commands
	//Key:
	//mF = MoveForward, mT = MoveTurn
	//R = Red, B = Blue, A = all
	//S = Straight, C = Corner
	//Red Straight
	private MoveForwardFour mFRSRightCrypto = new MoveForwardFour(0.75, 0.75, 0.75, 0.75, 16, 16, 16, 16); //red side right
	private MoveForwardFour mFRSCenterCrypto = new MoveForwardFour(0.75, 0.75, 0.75, 0.75, 22.5, 22.5, 22.5, 22.5); //red side center
	private MoveForwardFour mFRSLeftCrypto = new MoveForwardFour(0.75, 0.75, 0.75, 0.75, 27, 27, 27, 27); //red side left
	private MoveForwardFour mFRSAwayBeforeTurn = new MoveForwardFour(-0.5, 0.5, 0.5, -0.5, -6, 6, 6, -6); //red side move away from box
	private MoveTurnInPlace mTRSIntoBox = new MoveTurnInPlace(0.5, -67.5); //red side turn into crypto box

	//Red Corner
	private MoveForwardFour mFRCOffBoard = new MoveForwardFour(0.75, 0.75, 0.75, 0.75, 8, 8, 8, 8); //red corner off board
	private MoveForwardFour mFRCLeftCrypto = new MoveForwardFour(-0.5, 0.5, 0.5, -0.5, -13, 13, 13, -13); //red corner right
	private MoveForwardFour mFRCCenterCrypto = new MoveForwardFour(-0.5, 0.5, 0.5, -0.5, -25, 25, 25, -25); //red corner center
	private MoveForwardFour mFRCRightCrypto = new MoveForwardFour(-0.5, 0.5, 0.5, -0.5, -37, 37, 37, -37); //red corner left
	private MoveTurnInPlace mTRCIntoBox = new MoveTurnInPlace(0.5, 0); // red corner turn into crypto box

	//Left Straight
	private MoveForwardFour mFBSRightCrypto = new MoveForwardFour(-0.75, -0.75, -0.75, -0.75, -31.2, -31.2, -31.2, -31.2); //blue corner right
	private MoveForwardFour mFBSCenterCrypto = new MoveForwardFour(-0.75, -0.75, -0.75, -0.75, -27, -27, -27, -27); //blue corner center
	private MoveForwardFour mFBSLeftCrypto = new MoveForwardFour(-0.75, -0.75, -0.75, -0.75, -22.5, -22.5, -22.5, -22.5); //blue corner left
	private MoveForwardFour mFBSAwayBeforeTurn = new MoveForwardFour(-0.5, 0.5, 0.5, -0.5, -6, 6, 6, -6); //blue side move away from box
	private MoveTurnInPlace mTBSIntoBox = new MoveTurnInPlace(-0.5, -68.5); // blue side turn away from crypto box

	//Left Corner
	private MoveForwardFour mFBCOffBoard = new MoveForwardFour(-0.75, -0.75, -0.75, -0.75, -15, -15, -15, -15); //blue corner over board
	private MoveForwardFour mFBCLeftCrypto = new MoveForwardFour(0.5, -0.5, -0.5, 0.5, 13, -13, -13, 13); //blue side right
	private MoveForwardFour mFBCCenterCrypto = new MoveForwardFour(0.5, -0.5, -0.5, 0.5, 25, -25, -25, 25); //blue side center
	private MoveForwardFour mFBCRightCrypto = new MoveForwardFour(0.5, -0.5, -0.5, 0.5, 37, -37, -37, 37); //blue side left
	private MoveTurnInPlace mTBCIntoBox = new MoveTurnInPlace(0.5, 180); //red corner turn into crypto box

	//All
	private MoveForwardFour mFAForwardAfterTurn = new MoveForwardFour(0.5, 0.5, 0.5, 0.5, 10, 10, 10, 10);
	private MoveForwardFour mFAForwardBeforeHit = new MoveForwardFour(0.5, 0.5, 0.5, 0.5, 8, 8, 8, 8); //move forward befor hit
	private MoveForwardFour mFABackAfterTurn = new MoveForwardFour(-0.5, -0.5, -0.5, -0.5,-2, -2, -2, -2); //back up slightly
	private MoveForwardFour mFABackBeforeHit = new MoveForwardFour(-0.5, -0.5, -0.5, -0.5, -8, -8, -8, -8); //back up more
	private MoveServo openClaw = new MoveServo(1, 0); //opens claw and drops the cube
	private MoveServo closeClaw = new MoveServo(0, 1); //closes claw
	private CheckImg checkImg = new CheckImg(); //runs the program for checking the pictograpgh

	//Misc
	private MoveForward empty = new MoveForward(0.5, 0.5, 0, 0); //used to pad the command array so nothing breaks


	private int index;

	//Constructor
	//Add values to be taken here
	public CheckPicto(DcMotor mRF, DcMotor mLF, DcMotor mRB, DcMotor mLB, DcMotor iL, DcMotor iR, DcMotor f, Servo cR, int clr, int id, int loc) {
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
		intakeLeft = iL;
		intakeRight = iR;
		flip = f;

		clawR = cR;

		//Servos
		//Claw Servos\
	}

	//Setup
	public void init() {
		//Initialize Objects here

		//Red Straight
		mFRSRightCrypto.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFRSCenterCrypto.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFRSLeftCrypto.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFRSAwayBeforeTurn.setMotors(motorRF, motorRB, motorLF, motorLB);
		mTRSIntoBox.setMotors(motorRF, motorRB, motorLF, motorLB);

		//Red Corner
		mFRCOffBoard.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFRCRightCrypto.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFRCCenterCrypto.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFRCLeftCrypto.setMotors(motorRF, motorRB, motorLF, motorLB);
		mTRCIntoBox.setMotors(motorRF, motorRB, motorLF, motorLB);

		//Blue Straight
		mFBSRightCrypto.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFBSCenterCrypto.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFBSLeftCrypto.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFBSAwayBeforeTurn.setMotors(motorRF, motorRB, motorLF, motorLB);
		mTBSIntoBox.setMotors(motorRF, motorRB, motorLF, motorLB);

		//Blue Corner
		mFBCOffBoard.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFBCRightCrypto.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFBCCenterCrypto.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFBCLeftCrypto.setMotors(motorRF, motorRB, motorLF, motorLB);
		mTBCIntoBox.setMotors(motorRF, motorRB, motorLF, motorLB);

		//All
		mFAForwardAfterTurn.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFAForwardBeforeHit.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFABackAfterTurn.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFABackBeforeHit.setMotors(motorRF, motorRB, motorLF, motorLB);
		openClaw.setServos( clawR);
		closeClaw.setServos(clawR);
		checkImg.setId(cameraId);

		//Misc
		empty.setMotors(motorRF, motorLF);
	}

	//Runs at start
	//Runs once
	public void start() {
		intakeLeft.setDirection(DcMotor.Direction.REVERSE);
		intakeRight.setDirection(DcMotor.Direction.FORWARD);
		intakeLeft.setPower(0.5);
		intakeRight.setPower(0.5);
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
                    commands[1] = mFRSAwayBeforeTurn;
                    commands[2] = mTRSIntoBox;
                    commands[3] = mFAForwardAfterTurn;
                    commands[4] = openClaw;
                    commands[5] = mFABackBeforeHit;
                    commands[6] = closeClaw;
                    commands[7] = mFAForwardBeforeHit;
                    commands[8] = mFABackAfterTurn;
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
                    commands[2] = mTRCIntoBox;
                    commands[3] = mFAForwardAfterTurn;
                    commands[4] = openClaw;
					commands[5] = mFABackBeforeHit;
					commands[6] = closeClaw;
					commands[7] = mFAForwardBeforeHit;
					commands[8] = mFABackAfterTurn;
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
                    commands[1] = mFBSAwayBeforeTurn;
                    commands[2] = mTBSIntoBox;
                    commands[3] = mFAForwardAfterTurn;
					commands[4] = openClaw;
					commands[5] = mFABackBeforeHit;
					commands[6] = closeClaw;
					commands[7] = mFAForwardBeforeHit;
					commands[8] = mFABackAfterTurn;
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
                    commands[2] = mTBCIntoBox;
                    commands[3] = mFAForwardAfterTurn;
                    commands[4] = openClaw;
					commands[5] = mFABackBeforeHit;
					commands[6] = closeClaw;
					commands[7] = mFAForwardBeforeHit;
					commands[8] = mFABackAfterTurn;
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
		intakeLeft.setPower(0);
		intakeRight.setPower(0);
	}
}
