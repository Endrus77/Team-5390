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

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import segments.Segment;
import segments.TestSegment;


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

@TeleOp(name="testLoop", group="Linear Opmode")
@Disabled
public class testLoop extends LinearOpMode {

    // Declare OpMode members.
    //Array
    private Segment[] commands = new Segment[2];

    //Motors
    private DcMotor mR;
    private DcMotor mL;

    //Loop Counter
    private int loop;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        mR = hardwareMap.get(DcMotor.class, "right_drive");
        mL = hardwareMap.get(DcMotor.class, "left_drive");

        //Segments
        TestSegment seg = new TestSegment(mR, mL);
        TestSegment seg2 = new TestSegment(mR, mL);

        //Array
        commands[0] = seg;
        commands[1] = seg2;


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        loop = 0;
        int looping = 0;
        telemetry.addData("Loop", "Loop: " + loop);
        telemetry.update();
        while (loop < commands.length && opModeIsActive()) {
            commands[loop].init();
            commands[loop].start();
            looping = 0;
            telemetry.addData("Init and Started", "Segment: " + loop);
            telemetry.update();
            while (commands[loop].loop() && opModeIsActive()) {
                telemetry.addData("Looping", "Loop" + looping);
                telemetry.update();
                looping++;
            }
            telemetry.addData("About to stop", 0);
            telemetry.update();
            commands[loop].stop();
            telemetry.addData("Stopped", "Segment: " + loop);
            loop++;
            telemetry.addData("Loop", "Loop: " + loop);
            telemetry.update();
        }
    }
}
