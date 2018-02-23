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
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import commands.Command;


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

@TeleOp(name="IntakeRemote", group="Linear Opmode")
public class IntakeRemote extends LinearOpMode {

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
        DcMotor intakeLeft;
        DcMotor intakeRight;
        DcMotor flip;

        boolean pressed = false;
        int flipEncoderDistance = (int)(Command.ENCODERTICKS * (2 / 3));


        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        intakeLeft  = hardwareMap.get(DcMotor.class, "intakeLeft");
        intakeRight  = hardwareMap.get(DcMotor.class, "intakeRight");
        flip = hardwareMap.get(DcMotor.class, "flip");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        intakeLeft.setDirection(DcMotor.Direction.REVERSE);
        intakeRight.setDirection(DcMotor.Direction.FORWARD);
        flip.setDirection(DcMotor.Direction.FORWARD);
        flip.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flip.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        int direction = -1;

        while (opModeIsActive()) {

            //Motor powers
            double leftFrontPower;
            double leftBackPower;
            double rightFrontPower;
            double rightBackPower;
            double intakeLeftPower;
            double intakeRightPower;
            double flipPower;

            //Arcade style driving
            //Left Front is Y value minus X value. When X is to the left, left wheel goes faster. When X is to the right, left wheel goes slower, then negative.
            //Clip value to make sure they're within range
            leftFrontPower = gamepad1.left_stick_y + gamepad1.left_stick_x;
            leftFrontPower = Range.clip(leftFrontPower, -1, 1);
            //Left Back is Y value plus X value. When X is to the left, left wheel goes faster. When X is to the right, left wheel goes slower, then negative.
            //Clip value to make sure they're within range
            leftBackPower = gamepad1.left_stick_y - gamepad1.left_stick_x;
            leftBackPower = Range.clip(leftBackPower, -1, 1);
            //Right Front is Y value plus X value. When X is to the left, right wheel goes slower, then negative. When X is to the right, right wheel goes faster.
            //Clip value to make sure they're within range
            rightFrontPower = gamepad1.left_stick_y - gamepad1.left_stick_x;
            rightFrontPower = Range.clip(rightFrontPower, -1, 1);
            //Right Front is Y value minus X value. When X is to the left, right wheel goes slower, then negative. When X is to the right, right wheel goes faster.
            //Clip value to make sure they're within range
            rightBackPower = gamepad1.left_stick_y + gamepad1.left_stick_x;
            rightBackPower = Range.clip(rightBackPower, -1, 1);

            //Rotation of robot
            if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y == 0 && gamepad1.right_stick_y == 0) {
                leftFrontPower = -gamepad1.right_stick_x;
                leftFrontPower = Range.clip(leftFrontPower, -1, 1);
                leftBackPower = -gamepad1.right_stick_x;
                leftBackPower = Range.clip(leftBackPower, -1, 1);
                rightFrontPower = gamepad1.right_stick_x;
                rightFrontPower = Range.clip(rightFrontPower, -1, 1);
                rightBackPower = gamepad1.right_stick_x;
                rightBackPower = Range.clip(rightBackPower, -1, 1);
            }

            if (gamepad1.dpad_up) {
                leftFrontPower = 0.75;
                leftBackPower = 0.75;
                rightFrontPower = 0.75;
                rightBackPower = 0.75;
            }
            if (gamepad1.dpad_left) {
                leftFrontPower = -0.75;
                leftBackPower = 0.75;
                rightFrontPower = 0.75;
                rightBackPower = -0.75;
            }
            if (gamepad1.dpad_down) {
                leftFrontPower = -0.75;
                leftBackPower = -0.75;
                rightFrontPower = -0.75;
                rightBackPower = -0.75;
            }
            if (gamepad1.dpad_right) {
                leftFrontPower = 0.75;
                leftBackPower = -0.75;
                rightFrontPower = -0.75;
                rightBackPower = 0.75;
            }

            if (gamepad1.left_bumper) {
                intakeLeftPower = 0.5;
                intakeRightPower = 0.5;
            }
            else {
                intakeLeftPower = 0;
                intakeRightPower = 0;
            }

            if (!pressed && gamepad1.right_trigger > 0) {
                flip.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                flip.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                pressed = true;
                flip.setPower(0.5 * direction);
                flip.setTargetPosition(flipEncoderDistance * direction);
            }

            if (pressed && gamepad1.right_trigger == 0) {
                direction *= -1;
                pressed = false;
            }

            //flipPower = Range.clip(gamepad1.right_stick_y / 2, -0.5, 0.5);

            //Set motor powers
            leftFrontDrive.setPower(leftFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightFrontDrive.setPower(rightFrontPower);
            rightBackDrive.setPower(rightBackPower);
            intakeLeft.setPower(intakeLeftPower);
            intakeRight.setPower(intakeRightPower);
            //flip.setPower(flipPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Intake", "left (%.2f), right (%.2f)", intakeLeftPower, intakeRightPower);
            telemetry.addData("Pressed", "Bool: " + pressed);
            telemetry.update();
        }
    }
}
