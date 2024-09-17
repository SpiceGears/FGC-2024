package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.utils.Constants;

public class Drivetrain {

    private final LinearOpMode opMode;
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor rearLeftDrive;
    private DcMotor rearRightDrive;
    private double speedModifier;
    private double leftPower;
    private double rightPower;

    private double frontLeftLastPosition;
    private double frontRightLastPosition;
    private double rearLeftLastPosition;
    private double rearRightLastPosition;
    private double lastTime;


    public Drivetrain(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {
        frontLeftDrive = opMode.hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = opMode.hardwareMap.get(DcMotor.class, "frontRightDrive");
        rearLeftDrive = opMode.hardwareMap.get(DcMotor.class, "rearLeftDrive");
        rearRightDrive = opMode.hardwareMap.get(DcMotor.class, "rearRightDrive");

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rearRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        speedModifier = 1.0;
    }

    public void drive(double drive, double turn) {

        //double leftCurrentPower = (frontLeftDrive.getPower() + rearLeftDrive.getPower()) / 2;
        //double rightCurrentPower = (frontRightDrive.getPower() + rearRightDrive.getPower()) / 2;
//        double frontLeftPower = frontLeftDrive.getPower() + (clip(drive + turn)
//                - frontLeftDrive.getPower()) * Constants.Drivetrain.accelerationCoefficient;
//        double frontRightPower = frontRightDrive.getPower() + ( clip(drive - turn)
//                - frontRightDrive.getPower()) * Constants.Drivetrain.accelerationCoefficient;
//        double rearLeftPower = rearLeftDrive.getPower() + ( clip(drive + turn)
//                - rearLeftDrive.getPower()) * Constants.Drivetrain.accelerationCoefficient;
//        double rearRightPower = rearRightDrive.getPower() + ( clip(drive - turn)
//                - rearRightDrive.getPower()) * Constants.Drivetrain.accelerationCoefficient;

//        double targetLeftPower = Range.clip(drive + turn, -1.0, 1.0);
//        if (targetLeftPower > leftPower) {
//            leftPower += (targetLeftPower - leftPower) * Constants.Drivetrain.accelerationCoefficient;
//        } else if (targetLeftPower < leftPower) {
//            leftPower += (targetLeftPower - leftPower) * Constants.Drivetrain.decelerationCoefficient;
//        }
//
//        double targetRightPower = Range.clip(drive - turn, -1.0, 1.0);
//        if (targetRightPower > rightPower) {
//            rightPower += (targetRightPower - rightPower) * Constants.Drivetrain.accelerationCoefficient;
//        } else if (targetRightPower < rightPower) {
//            rightPower += (targetRightPower - rightPower) * Constants.Drivetrain.decelerationCoefficient;
//        }

//        leftPower = leftCurrentPower + ( Range.clip(drive + turn, -1.0, 1.0) - leftCurrentPower)
//                * Constants.Drivetrain.accelerationCoefficient;
//        rightPower = rightCurrentPower + ( Range.clip(drive - turn, -1.0, 1.0) - rightCurrentPower)
//                * Constants.Drivetrain.accelerationCoefficient;

        leftPower = Range.clip(drive + turn, -1.0, 1.0) * speedModifier;
        rightPower = Range.clip(drive - turn, -1.0, 1.0) * speedModifier;

        frontLeftDrive.setPower(leftPower);
        frontRightDrive.setPower(rightPower);
        rearLeftDrive.setPower(leftPower);
        rearRightDrive.setPower(rightPower);

    }

    public void stop() {
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        rearLeftDrive.setPower(0);
        rearRightDrive.setPower(0);
    }

    public void setSpeedModifier(double modifier) {
        this.speedModifier = modifier;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public double getLeftPower() { return leftPower; }
    public double getRightPower() { return rightPower; }

    public double getCurrentVelocity(double time) {
        double deltaPosition = ((frontLeftLastPosition - frontLeftDrive.getCurrentPosition()) +
                (frontRightLastPosition - frontRightDrive.getCurrentPosition()) +
                (rearLeftLastPosition - rearLeftDrive.getCurrentPosition()) +
                (rearRightLastPosition - rearRightDrive.getCurrentPosition())) / 4.0;
        double deltaTime = time - lastTime;

        lastTime = time;
        frontLeftLastPosition = frontLeftDrive.getCurrentPosition();
        frontRightLastPosition = frontRightDrive.getCurrentPosition();
        rearLeftLastPosition = rearLeftDrive.getCurrentPosition();
        rearRightLastPosition = rearRightDrive.getCurrentPosition();

        return (deltaPosition / deltaTime) * Constants.Drivetrain.meterTicks;
    }

}
