package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Logs;
import org.firstinspires.ftc.teamcode.utils.Constants;
import org.firstinspires.ftc.teamcode.utils.ElevatorMode;

@TeleOp(name="RobotAteny", group="Linear Opmode")
public class RobotAteny extends LinearOpMode {

    private final Drivetrain drivetrain = new Drivetrain(this);
    private final Elevator elevator = new Elevator(this);
    private final Logs log = new Logs(telemetry);
    private final Bucket bucket = new Bucket(this);
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        // SUBSYSTEMS INIT //
        drivetrain.init();
        elevator.init();
        log.init();
        bucket.init();

        waitForStart();
        runtime.reset();


        while(opModeIsActive()) {

            // DRIVE SUBSYSTEM //

            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;

            drivetrain.drive(drive, turn);

            if(gamepad1.right_bumper) {
                drivetrain.setSpeedModifier(1.0);
            } else {
                drivetrain.setSpeedModifier(0.5);
            }

            if(!(gamepad1.triangle || gamepad1.cross) && runtime.seconds() < 3.5 ) {
                bucket.setServoPower(1.0);
            }

//            if(runtime.seconds() > 3.5) {
//                bucket.setServoPower(0);
//            }

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

            if(gamepad1.right_trigger > 0.2) {
                bucket.setMotorPower(-gamepad1.right_trigger);
            }
            else if(gamepad1.left_trigger > 0.2) {
                bucket.setMotorPower(gamepad1.left_trigger);
            } else {
                bucket.setMotorPower(0);
            }

            if(gamepad1.triangle) {
                bucket.setServoPower(1.0);
            }
            else if(gamepad1.cross) {
                bucket.setServoPower(-1.0);
            } else {
                bucket.setServoPower(0.0);
            }

//            elevator.checkMotors();
            //elevator.check();
            //elevator.checkSensors();
            sendTelemetry();

        }

    }

    void sendTelemetry() {
        log.addLine("ElevatorLeftPosition", elevator.getLeftPosition(), Constants.Logs.showElevatorPositions);
        log.addLine("ElevatorRightPosition", elevator.getRightPosition(), Constants.Logs.showElevatorPositions);
        log.addLine("ElevatorLeftSensor", elevator.getLeftTouchState(), Constants.Logs.showElevatorSensors);
        log.addLine("ElevatorRightSensor", elevator.getRightTouchState(), Constants.Logs.showElevatorSensors);
        log.addLine("ElevatorMode", elevator.getMode(), Constants.Logs.showElevatorMode);
        log.addLine("ElevatorMotorModes", elevator.getMotorsMode(), Constants.Logs.showElevatorMotorModes);
        log.addLine("ElevatorMotorState", elevator.getMotors(), Constants.Logs.showElevatorMotorPower);

        log.addLine("DriveLeftPower", drivetrain.getLeftPower(), Constants.Logs.showDrivetrainMotorPower);
        log.addLine("DriveRightPower", drivetrain.getRightPower(), Constants.Logs.showDrivetrainMotorPower);

        log.addLine("DriveSpeedModifier", drivetrain.getSpeedModifier(), Constants.Logs.showSpeedModifier);

        log.addLine("BatteryVoltage", hardwareMap.voltageSensor.iterator().next().getVoltage(),
                Constants.Logs.showBatteryVoltage);

        log.send();
    }
}
