package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.utils.Constants;

public class Drive {

    private final LinearOpMode opMode;
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor rearLeftDrive;
    private DcMotor rearRightDrive;
    private double speedModifier;
    private double leftPower;
    private double rightPower;


    public Drive(LinearOpMode opMode) {
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

}
