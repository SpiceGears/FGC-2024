package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Bucket {

    private final LinearOpMode opMode;
    private DcMotorEx leftIntake;
    private DcMotorEx rightIntake;

    public Bucket(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {
        leftIntake = opMode.hardwareMap.get(DcMotorEx.class, "leftIntake");
        rightIntake = opMode.hardwareMap.get(DcMotorEx.class, "rightIntake");
    }

}
