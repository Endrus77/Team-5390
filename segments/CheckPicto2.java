//Checks pictograph, runs to appropiate spot then drops block
//Takes all motors being used as parameters
//For now the distances will be hardcoded

//imports

package segments;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import commands.CheckImg;
import commands.Command;
import commands.MoveForward;
import commands.MoveForwardFour;
import commands.MoveMotor;
import commands.MoveServo;
import commands.MoveTurnInPlace;

//import Commands

public class CheckPicto2 extends Segment {

	//Variables

	//Command Array
	private Command[] commands = new Command[12];
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

	//Left Straight
	private MoveForwardFour mFBSRightCrypto = new MoveForwardFour(-0.75, -0.75, -0.75, -0.75, -31.2, -31.2, -31.2, -31.2); //blue corner right
	private MoveForwardFour mFBSCenterCrypto = new MoveForwardFour(-0.75, -0.75, -0.75, -0.75, -27, -27, -27, -27); //blue corner center
	private MoveForwardFour mFBSLeftCrypto = new MoveForwardFour(-0.75, -0.75, -0.75, -0.75, -22.5, -22.5, -22.5, -22.5); //blue corner left
	private MoveForwardFour mFBSAwayBeforeTurn = new MoveForwardFour(-0.5, 0.5, 0.5, -0.5, -6, 6, 6, -6); //blue side move away from box
	private MoveTurnInPlace mTBSIntoBox = new MoveTurnInPlace(-0.5, -68.5); // blue side turn away from crypto box

	//All
	private MoveForwardFour mFABackBeforeBlock1 = new MoveForwardFour(-0.5, -0.5, -0.5, -0.5, -30, -30, -30, -30);
	private MoveForwardFour mFAForwardAfterBlock1 = new MoveForwardFour(0.5, 0.5, 0.5, 0.5, 40, 40, 40, 40);
	private MoveForwardFour mFABackBeforeBlock2 = new MoveForwardFour(-0.5, -0.5, -0.5, -0.5, -40, -40, -40, -40);
	private MoveForwardFour mFAForwardAfterBlock2 = new MoveForwardFour(0.5, 0.5, 0.5, 0.5, 50, 50, 50, 50);
	private MoveForwardFour mFABackAfterBlock2 = new MoveForwardFour(-0.5, -0.5, -0.5, -0.5, -3, -3, -3, -3);
	private MoveMotor mMAFlipFlipper = new MoveMotor(-0.5, -90);
	private MoveMotor mMAResetFlipper = new MoveMotor(0.5, 90);

	private CheckImg checkImg = new CheckImg(); //runs the program for checking the pictograpgh

	//Misc
	private MoveForward empty = new MoveForward(0.5, 0.5, 0, 0); //used to pad the command array so nothing breaks


	private int index;

	//Constructor
	//Add values to be taken here
	public CheckPicto2(DcMotor mRF, DcMotor mLF, DcMotor mRB, DcMotor mLB, DcMotor iL, DcMotor iR, DcMotor f, int clr, int id, int loc) {
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

		//Blue Straight
		mFBSRightCrypto.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFBSCenterCrypto.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFBSLeftCrypto.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFBSAwayBeforeTurn.setMotors(motorRF, motorRB, motorLF, motorLB);
		mTBSIntoBox.setMotors(motorRF, motorRB, motorLF, motorLB);

		//All
		mFABackBeforeBlock1.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFAForwardAfterBlock1.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFABackBeforeBlock2.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFAForwardAfterBlock2.setMotors(motorRF, motorRB, motorLF, motorLB);
		mFABackAfterBlock2.setMotors(motorRF, motorRB, motorLF, motorLB);
		mMAFlipFlipper.setMotor(flip);
		mMAResetFlipper.setMotor(flip);
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
			if (color == 1) {
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
					commands[3] = mFABackBeforeBlock1;
					commands[4] = mFAForwardAfterBlock1;
					commands[5] = mMAFlipFlipper;
					commands[6] = mMAResetFlipper;
					commands[7] = mFABackBeforeBlock2;
					commands[8] = mFAForwardAfterBlock2;
					commands[9] = mMAFlipFlipper;
					commands[10] = mMAResetFlipper;
					commands[11] = mFABackAfterBlock2;
				}
			}
			//Blue Team
			else {
				//Blue Side
				if (location == 1) {
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
					commands[3] = mFABackBeforeBlock1;
					commands[4] = mFAForwardAfterBlock1;
					commands[5] = mMAFlipFlipper;
					commands[6] = mMAResetFlipper;
					commands[7] = mFABackBeforeBlock2;
					commands[8] = mFAForwardAfterBlock2;
					commands[9] = mMAFlipFlipper;
					commands[10] = mMAResetFlipper;
					commands[11] = mFABackAfterBlock2;
				}
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
