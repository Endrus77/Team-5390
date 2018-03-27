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
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


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

@TeleOp(name="Remote", group="Linear Opmode")
public class RemoteControlTemplate extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        DcMotor leftFrontDrive;
        DcMotor leftBackDrive;
        DcMotor rightFrontDrive;
        DcMotor rightBackDrive;
        DcMotor lift;
        DcMotor rotation;

        Servo bA;
        Servo bHl;
        //DcMotor board;

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        lift = hardwareMap.get(DcMotor.class, "lift");
        bA = hardwareMap.get(Servo.class, "bA");
        bHl = hardwareMap.get(Servo.class, "bHl");
        //rotation = hardwareMap.get(DcMotor.class, "rotation");

        //board = hardwareMap.get(DcMotor.class, "board");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        lift.setDirection(DcMotor.Direction.REVERSE);
        //rotation.setDirection(DcMotor.Direction.FORWARD);

        Servo clawR;
        Servo clawL;
        Servo ballArm;

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        clawR = hardwareMap.get(Servo.class, "one");
        //clawL = hardwareMap.get(Servo.class, "two");
        //ballArm = hardwareMap.get(Servo.class, "bA");


        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        //Color Sensor
        //Only for testing - not used in remote
        ColorSensor colorSensor;

        colorSensor = hardwareMap.get(ColorSensor.class, "cS");

        colorSensor.enableLed(true);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();


        //Initial claw positions
        double clawRP = 0;
        //double clawLP = 0;

        //Set initial claw postions
        clawR.setPosition(clawRP);
        //clawL.setPosition(clawLP);
        //ballArm.setPosition(0);

        //Variable to keep track or direction of lift rotation
        int rot = 1;
        boolean pressed = false;
        // run until the end of the match (driver presses STOP)

        //rotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //rotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //rotation.setPower(0.25 * rot);
        //rotation.setTargetPosition((int)(Command.ENCODERTICKS / 8 * rot));

        while (opModeIsActive()) {

            //Motor powers
            double leftFrontPower;
            double leftBackPower;
            double rightFrontPower;
            double rightBackPower;
            double liftPower;
            double rotationPower;
            //double boardPower;


            //Controller stuff here (needs better comments)

            if (gamepad1.a) {

            }

            if (gamepad1.b) {

            }

            if (gamepad1.x) {

            }

            if (gamepad1.y) {

            }

            if (gamepad1.dpad_down) {

            }

            if (gamepad1.dpad_left) {

            }

            if (gamepad1.dpad_right) {

            }

            if (gamepad1.dpad_up) {

            }

            if (gamepad1.left_bumper) {

            }

            if (gamepad1.left_trigger != 0) {

            }


            if (gamepad1.right_bumper) {

            }

            if (gamepad1.right_trigger != 0) {

            }

            if (gamepad1.right_stick_button) {

            }

            if (gamepad1.left_stick_button) {

            }

            if (gamepad1.back) {

            }

            if (gamepad1.start) {

            }

            if (gamepad1.guide) {

            }

            //This is for a toggle of the bumpers
            //Repeat this with desired button you want as a toggle
            /*
            if (gamepad1.left_bumper && !pressed) {
                if (clawRP == 1)
                    clawRP = 0;
                else
                    clawRP = 1;
                pressed = true;
            }

            if (gamepad1.right_bumper && !pressed) {
                if (clawRP == 1)
                    clawRP = 0;
                else
                    clawRP = 1;
                pressed = true;
            }
            */


            //Driving goes here

            //Set motor powers here


            //Set claw powers
            clawR.setPosition(clawRP);
            //clawL.setPosition(clawLP);

            // Show the elapsed game time and wheel power.
            /* telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftFrontPower, rightFrontPower);
            telemetry.addData("Lift", "Power: " + liftPower);
            telemetry.addData("Y pressed", "" + gamepad1.dpad_up);
            telemetry.addData("Color", "Red Value: " + colorSensor.red());
            telemetry.update();
            */
        }
    }
}
