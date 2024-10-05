package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.utils.ElevatorMode;

@TeleOp(name="Pokazy", group = "Linear Opmode")
public class Pokazy extends LinearOpMode {

    private final Drive drivetrain = new Drive(this);
    private final Elevator elevator = new Elevator(this);
    private final Bucket bucket = new Bucket(this);
    private final ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        // SUBSYSTEMS INIT //
        drivetrain.init();
        elevator.init();
        elevator.setMode(ElevatorMode.MANUAL);
        bucket.init();

        waitForStart();
        runtime.reset();

        while(opModeIsActive()) {

            // DRIVE SUBSYSTEM //

            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;

            drivetrain.drive(drive, turn);

            if(gamepad1.right_bumper) {
                drivetrain.setSpeedModifier(0.5);
            } else {
                drivetrain.setSpeedModifier(1.0);
            }

            // ELEVATOR SUBSYSTEM //

            // manual control
            if(elevator.getMode() == ElevatorMode.MANUAL) {
                elevator.setManualPower(-gamepad2.left_stick_y);
            }

            // switch elevator control
            if(gamepad2.right_bumper) {
                elevator.setMode(ElevatorMode.MANUAL);
            }

            elevator.checkPositions();

            // BUCKET SUBSYSTEM //

            if(gamepad1.right_trigger > 0.2 || gamepad2.right_trigger > 0.2) {
                bucket.setMotorPower(-1.0);
            }
            else if(gamepad1.left_trigger > 0.2 || gamepad2.left_trigger > 0.2) {
                bucket.setMotorPower(1.0);
            } else {
                bucket.setMotorPower(0);
            }

            if(gamepad1.dpad_up || gamepad2.dpad_up) {
                bucket.setServoPower(1.0);
            }
            else if(gamepad1.dpad_down || gamepad2.dpad_down) {
                bucket.setServoPower(-1.0);
            }
            else {
                bucket.setServoPower(0.0);
            }

        }

    }
}
