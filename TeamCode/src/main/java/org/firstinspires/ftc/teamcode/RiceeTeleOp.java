package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Balance;
import org.firstinspires.ftc.teamcode.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Logs;
import org.firstinspires.ftc.teamcode.utils.Constants;
import org.firstinspires.ftc.teamcode.utils.ElevatorMode;

@TeleOp(name="RobotAteny", group="Linear Opmode")
public class RiceeTeleOp extends LinearOpMode {

    private final Drive drivetrain = new Drive(this);
    private final Elevator elevator = new Elevator(this);
    private final Bucket bucket = new Bucket(this);
    private final Balance balance = new Balance(this);
    private final Logs log = new Logs(telemetry);
    private final ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        // SUBSYSTEMS INIT //
        drivetrain.init();
        elevator.init();
        log.init();
        bucket.init();
        balance.init();

        waitForStart();
        runtime.reset();

        bucket.setIsStarting(true);

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

            // auto control
            if(elevator.getMode() == ElevatorMode.AUTO) {

                if(gamepad2.ps){
                    elevator.setPosition(0);
                }
                if(gamepad2.cross) {
                    elevator.setPosition(Constants.Elevator.elevatorFirstLevel);
                }
                if(gamepad2.circle) {
                    elevator.setPosition(Constants.Elevator.elevatorSecondLevel);
                }
                if(gamepad2.triangle) {
                    elevator.setPosition(Constants.Elevator.elevatorThirdLevel);
                }
                if(gamepad2.square) {
                    elevator.setPosition(Constants.Elevator.elevatorFourLevel);
                }

                elevator.setPower(Constants.Elevator.elevatorAutoSpeed);
            }

            // switch elevator control
            if(gamepad2.right_bumper) {
                elevator.setMode(ElevatorMode.MANUAL);
            }
            if(gamepad2.left_bumper) {
                elevator.setMode(ElevatorMode.AUTO);
            }
            if(gamepad2.share) {
                elevator.resetEncoder();
                elevator.setMode(ElevatorMode.MANUAL);
            }

            elevator.checkSensors();
            elevator.checkPositions();

            if(gamepad1.right_trigger > 0.2 || gamepad2.right_trigger > 0.2) {
                bucket.setMotorPower(-1.0);
            }
            else if(gamepad1.left_trigger > 0.2 || gamepad2.left_trigger > 0.2) {
                bucket.setMotorPower(1.0);
            } else {
                bucket.setMotorPower(0);
            }

            if(bucket.isStartingMode()) {
                if(runtime.seconds() < 4.0) {
                    //bucket.setServoPower(1.0); //dziala
                } else {
                    bucket.setServoPower(0);
                    bucket.setIsStarting(false);
                }
            } else {
                if(gamepad1.triangle || gamepad2.dpad_up) {
                    bucket.setServoPower(1.0);
                }
                else if(gamepad1.cross || gamepad2.dpad_down) {
                    bucket.setServoPower(-1.0);
                }
                else {
                    bucket.setServoPower(0.0);
                }
            }

            // BALANCE SUBSYSTEM //

            //VALUES TO FINE-TUNE

            //switching balance
            if(gamepad2.dpad_left) {
                balance.openBalance();
            }
             else if(gamepad2.dpad_right) {
                balance.closeBalance();
            }


            sendTelemetry();

        }

    }

    void sendTelemetry() {
        log.addLine("Servo", balance.getPositions(), true);
        log.addLine("Runtime", runtime.seconds(), true);
        log.addLine("Starting Mode", bucket.isStartingMode());
        log.addLine("ElevatorLeftPosition", elevator.getLeftPosition(), Constants.Logs.showElevatorPositions);
        log.addLine("ElevatorRightPosition", elevator.getRightPosition(), Constants.Logs.showElevatorPositions);
        log.addLine("ElevatorLeftSensor", elevator.getLeftTouchState(), Constants.Logs.showElevatorSensors);
        log.addLine("ElevatorRightSensor", elevator.getRightTouchState(), Constants.Logs.showElevatorSensors);
        log.addLine("ElevatorMode", elevator.getMode(), Constants.Logs.showElevatorMode);
        log.addLine("ElevatorMotorModes", elevator.getMotorsMode(), Constants.Logs.showElevatorMotorModes);
//        log.addLine("ElevatorMotorState", elevator.getMotors(), Constants.Logs.showElevatorMotorPower);

        log.addLine("DriveLeftPower", drivetrain.getLeftPower(), Constants.Logs.showDrivetrainMotorPower);
        log.addLine("DriveRightPower", drivetrain.getRightPower(), Constants.Logs.showDrivetrainMotorPower);
        log.addLine("DriveSpeedModifier", drivetrain.getSpeedModifier(), Constants.Logs.showSpeedModifier);

        log.addLine("BucketLeftPower", bucket.getLeftPower(), Constants.Logs.showIntakeMotorPower);
        log.addLine("BucketRightPower", bucket.getRightPower(), Constants.Logs.showIntakeMotorPower);

        log.addLine("BatteryVoltage", hardwareMap.voltageSensor.iterator().next().getVoltage(),
                Constants.Logs.showBatteryVoltage);


        log.send();
    }
}
