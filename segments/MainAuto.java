//Overall structure of segments
//Will be extended by indivisual objects
//All commands must have these functions and variables

//imports

package segments;

//import Commands


import java.util.ArrayList;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

import commands.Command;
import commands.MoveForward;
import commands.MoveServo;
import commands.*;

public class Segment {

    //Variables
    private int index = 0;

    //Command ArrayList
    private Command[] commands = new Command[4];

    //motors
    private DcMotor motorR;
    private DcMotor motorL;

    //Servos
    private Servo objectArm;

    //Initialization
    //private MoveServo dropRobot = new MoveServo(1, 1);

    private MoveForward forward = new MoveForward(.5, .5, 20, 20);
    private MoveForward turnLeftD = new MoveForward(.5, 0, 15, 0);
    private MoveForward forwardDC = new MoveForward(.5, .5, 35, 35);


    private MoveForward turnLeftC = new MoveForward(.5, 0, 20, 0);
    private MoveForward forwardCD = new MoveForward(.5, .5, 30, 30);
    private MoveForward backward = new MoveForward(-.5, -.5, -35, -35);

    private ObjectArm dropObject = new ObjectArm(0, 1);


    //Constructor
    //Add values to be taken here
    public Segment(DcMotor mR, DcMotor mL, Servo oA) {
        //Set passed values to object values here
        motorR = mR;
        motorL = mL;
        objectArm = oA;


    }

    //Setup
    public void init() {
        //Run any initialization procedures: motor encoders, reset runtime, Etc.
        forward.setMotors(motorR, MotorL);
        turnLeftD.setMotors(motorR, MotorL);
        forwardDC.setMotors(motorR, MotorL);
        turnLeftC.setMotors(motorR, MotorL);
        forwardCD.setMotors(motorR, MotorL);
        backward.setMotors(motorR, MotorL);
        dropArm.setServos(objectArm);
    }

    public boolean conditional() {
        //Conditional loop
        return false;
    }

    public void build() {
        //Create the command array here
        if (position == 0) {
            command[0]=forward;
            command[1]=dropObject;
            command[2]=turnLeftD;
            command[3]=forwardDC;
        }
        if (position == 1) {
            command[0]=turnLeftC;
            command[1]=forwardCD;
            command[2]=dropObject;
            command[3]=backward;
        }
    }

    //Loops
    public boolean loop() {
        if (commands.get(index).loop())
            return true;
        else {
            commands.get(index).stop();
            index++;
            if (index == commands.size())
                return false;
            else {
                commands.get(index).init();
                commands.get(index).start();
            }
            return true;
        }
    }

    public void addCommand(Command c) {
        //Add command to commands array
        commands.add(c);
    }
}
