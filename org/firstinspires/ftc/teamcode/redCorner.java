/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import segments.CheckBallDrop;
import segments.CheckBallHit;
import segments.CheckPicto;
import segments.Segment;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="redCorner", group="Linear Opmode")
public class redCorner extends LinearOpMode {

    // Declare OpMode members.
    //Array
    private Segment[] commands = new Segment[3];

    //Motors
    private DcMotor mR;
    private DcMotor mL;
    private DcMotor l;
    private Servo bHl;
    private Servo bA;
    private Servo bHt;
    private Servo cR;
    private Servo cL;

    //Loop Counter
    private int loop;
    private int id;

    private ColorSensor cS;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        mR = hardwareMap.get(DcMotor.class, "right_drive");
        mL = hardwareMap.get(DcMotor.class, "left_drive");
        l = hardwareMap.get(DcMotor.class, "lift");
        bHl = hardwareMap.get(Servo.class, "bHl");
        bA = hardwareMap.get(Servo.class, "bA");
        bHt = hardwareMap.get(Servo.class, "bHt");
        cS = hardwareMap.get(ColorSensor.class, "cS");
        cR = hardwareMap.get(Servo.class, "one");
        cL = hardwareMap.get(Servo.class, "two");
        id = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        //loc - 0 is corner 1 is straight
        int loc = 0;
        //clr - 0 is blue side , 1 is red side
        int clr = 1;

        //Segments
        //Check individual objects to see the required variables
        //Moves the arm between the two balls
        CheckBallDrop drop = new CheckBallDrop(l, bHl, bA, bHt, cR, cL);
        //Checks ball color, then hits one of the balls, then moves in front of the pictogram
        CheckBallHit hit = new CheckBallHit(mR, mL, l, bHl, bA, bHt, cS, clr);
        //Checks pictogram then moves to crypto box and drops block before backing up.
        CheckPicto picto = new CheckPicto(mR, mL, cR, cL, clr, id, loc);


        //Array
        //Place commands in command array
        commands[0] = drop;
        commands[1] = hit;
        commands[2] = picto;

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        loop = 0;
        int looping = 0;
        telemetry.addData("Loop", "Loop: " + loop);
        telemetry.update();
        //Loop through command array while Op Mode is running
        while (loop < commands.length && opModeIsActive()) {
            //Initialize and start current segment
            commands[loop].init();
            commands[loop].start();
            looping = 0;
            telemetry.addData("Init and Started", "Segment: " + loop);
            telemetry.update();
            //Run conditional loop of current segment
            //Does things like check the ball or check pictogram
            while (commands[loop].conditional() && opModeIsActive()) {}
            //Run the loop of the current segment
            while (commands[loop].loop() && opModeIsActive()) {
                telemetry.addData("Looping", "Loop" + looping);
                telemetry.update();
                looping++;
            }
            telemetry.addData("About to stop", 0);
            telemetry.update();
            //Stop the current segment
            commands[loop].stop();
            telemetry.addData("Stopped", "Segment: " + loop);
            //Move onto the next segment
            loop++;
            telemetry.addData("Loop", "Loop: " + loop);
            telemetry.update();
        }
    }
}
