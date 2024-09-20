package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

public class Balance {
    private LinearOpMode opMode;
    private CRServo leftServoBalance;
    private CRServo rightServoBalance;

    public Balance() { this.opMode = opMode;}

    public void init() {
        leftServoBalance = opMode.hardwareMap.get(CRServo.class, "leftServoBalance");
        rightServoBalance = opMode.hardwareMap.get(CRServo.class, "rightServoBalance");

        leftServoBalance.setDirection(CRServo.Direction.REVERSE);
        rightServoBalance.setDirection(CRServo.Direction.FORWARD);

        boolean isStarting = false;
    }
    public void setServoPower(double power) {
        leftServoBalance.setPower(power);
        rightServoBalance.setPower(power);
    }

}
