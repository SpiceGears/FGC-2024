package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

public class Drivetrain {

    private LinearOpMode opMode;
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor rearLeftDrive;
    private DcMotor rearRightDrive;


    public Drivetrain(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {
        frontLeftDrive = opMode.hardwareMap.get(DcMotor.class, Constants.frontLeftDriveMotor);
        frontRightDrive = opMode.hardwareMap.get(DcMotor.class, Constants.frontRightDriveMotor);
        rearLeftDrive = opMode.hardwareMap.get(DcMotor.class, Constants.rearLeftDriveMotor);
        rearRightDrive = opMode.hardwareMap.get(DcMotor.class, Constants.rearRightDriveMotor);

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rearRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void drive(double drive, double turn) {

        double leftPower = Range.clip(drive + turn, -Constants.maxDriveSpeed, Constants.maxDriveSpeed);
        double rightPower = Range.clip(drive - turn, -Constants.maxDriveSpeed, Constants.maxDriveSpeed);

        frontLeftDrive.setPower(leftPower);
        frontRightDrive.setPower(rightPower);
        rearLeftDrive.setPower(leftPower);
        rearRightDrive.setPower(rightPower);

    }

}
