package org.firstinspires.ftc.teamcode.subsystems;

import android.net.wifi.p2p.WifiP2pManager;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RiceeTeleOp;
import org.firstinspires.ftc.teamcode.utils.Constants;

import java.util.Timer;
import java.util.TimerTask;

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
//        int delay = 1000;
//        leftServoBalance.setPosition(Constants.Balance.closeLeftPosition);
////        try {
////            wait(100);
////        } catch (InterruptedException e) {
////            throw new RuntimeException(e);
////        }
//        ActionListener taskPerformer = new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                rightServoBalance.setPosition(Constants.Balance.closeRightPosition);
//            }
//        };
//        new Timer(delay, taskPerformer).start();

        Timer timer = new Timer();
        leftServoBalance.setPosition(Constants.Balance.closeLeftPosition);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                rightServoBalance.setPosition(Constants.Balance.closeRightPosition);
            }
        }, 750);
    }

    public String getPositions() {
        return leftServoBalance.getPosition() + " | " + rightServoBalance.getPosition();
    }

    public double angleToServo(double angle) {
        return ((135+angle)/270);
    }

}
