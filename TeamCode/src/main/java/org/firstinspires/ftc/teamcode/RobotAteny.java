package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="RobotAteny", group="Linear Opmode")
public class RobotAteny extends LinearOpMode {

    private final Drivetrain drivetrain = new Drivetrain(this);
    private final Elevator elevator = new Elevator(this);

    @Override
    public void runOpMode() throws InterruptedException {

        // SUBSYSTEMS INIT //
        drivetrain.init();
        elevator.init();

        waitForStart();

        while(opModeIsActive()) {

            // DRIVE SUBSYSTEM //

            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;

            drivetrain.drive(drive, turn);

            // ELEVATOR SUBSYSTEM //

            // manual control
            if(elevator.getMode() == ElevatorMode.MANUAL) {
                elevator.setManualPower(-gamepad2.left_stick_y, -gamepad2.right_stick_y);
            }

            // auto control
            if(elevator.getMode() == ElevatorMode.AUTO) {

                if(gamepad2.ps){ //home
                    elevator.setPosition(0);
                }
                if(gamepad2.cross) {
                    elevator.setPosition(Constants.elevatorFirstLevel);
                }
                if(gamepad2.circle) {
                    elevator.setPosition(Constants.elevatorSecondLevel);
                }
                if(gamepad2.triangle) {
                    elevator.setPosition(Constants.elevatorThirdLevel);
                }
                if(gamepad2.square) {
                    elevator.setPosition(Constants.elevatorFourLevel);
                }

                elevator.setPower(Constants.elevatorAutoSpeed);
            }

            // switch elevator control
            if(gamepad2.right_bumper) {
                elevator.setMode(ElevatorMode.MANUAL);
            }
            if(gamepad2.left_bumper) {
                elevator.setMode(ElevatorMode.AUTO);
            }

//            elevator.setLeftPower(-gamepad1.left_stick_y);
//            elevator.setRightPower(-gamepad1.right_stick_x);


            telemetry.addData("prawa pozycja", elevator.getRightPosition());
            telemetry.addData("lewa pozycja", elevator.getLeftPosition());
            telemetry.addData("button A", gamepad1.a);
            telemetry.addData("button B", gamepad1.b);
            telemetry.addData("button X", gamepad1.x);
            telemetry.addData("ElevatorMode", elevator.getMode());
            telemetry.update();



        }

    }
}
