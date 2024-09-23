package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RiceeTeleOp;
import org.firstinspires.ftc.teamcode.utils.Constants;

public class Balance {
    private final LinearOpMode opMode;
    private Servo leftServoBalance;
    private Servo rightServoBalance;

    public Balance(LinearOpMode opMode) { this.opMode = opMode; }

    public void init() {
        leftServoBalance = opMode.hardwareMap.get(Servo.class, "leftServoBalance");
        rightServoBalance = opMode.hardwareMap.get(Servo.class, "rightServoBalance");
    }

    public void openBalance() {
        leftServoBalance.setPosition(Constants.Balance.openLeftPosition);
        rightServoBalance.setPosition(Constants.Balance.openRightPosition);
    }

    public void closeBalance() {
        leftServoBalance.setPosition(Constants.Balance.closeLeftPosition);
        rightServoBalance.setPosition(Constants.Balance.closeRightPosition);
    }

}
