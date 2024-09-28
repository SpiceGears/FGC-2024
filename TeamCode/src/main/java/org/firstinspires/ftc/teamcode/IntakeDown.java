package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Bucket;

@TeleOp(name="IntakeDown", group="Linear Opmode")
public class IntakeDown extends LinearOpMode {

    private final Bucket bucket = new Bucket(this);

    @Override
    public void runOpMode() throws InterruptedException {

        bucket.init();
        waitForStart();
        bucket.setIsStarting(false);

        while (opModeIsActive()) {
            if (gamepad1.dpad_up || gamepad2.dpad_up) {
                bucket.setServoPower(1.0);
            } else if (gamepad1.dpad_down || gamepad2.dpad_down) {
                bucket.setServoPower(-1.0);
            } else {
                bucket.setServoPower(0.0);
            }
        }
    }
}
