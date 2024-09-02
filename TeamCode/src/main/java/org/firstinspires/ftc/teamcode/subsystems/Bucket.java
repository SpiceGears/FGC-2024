package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Bucket {

    private final LinearOpMode opMode;
    private DcMotorEx leftIntake;
    private DcMotorEx rightIntake;
    private CRServo leftServo;
    private CRServo rightServo;

    public Bucket(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {
        leftIntake = opMode.hardwareMap.get(DcMotorEx.class, "leftIntake");
        rightIntake = opMode.hardwareMap.get(DcMotorEx.class, "rightIntake");

        leftServo = opMode.hardwareMap.get(CRServo.class, "leftServo");
        rightServo = opMode.hardwareMap.get(CRServo.class, "rightServo");

        leftServo.setDirection(CRServo.Direction.REVERSE);
        rightServo.setDirection(CRServo.Direction.FORWARD);
    }

    public void setMotorPower(double power) {
        leftIntake.setPower(power);
        rightIntake.setPower(power);
    }

    public double getLeftPower() {
        return leftIntake.getPower();
    }

    public double getRightPower() {
        return rightIntake.getPower();
    }

    public void setServoPower(double power) {
        leftServo.setPower(power);
        rightServo.setPower(power);
    }

}
