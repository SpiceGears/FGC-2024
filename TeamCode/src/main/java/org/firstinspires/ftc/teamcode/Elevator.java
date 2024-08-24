package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Elevator {

    private final LinearOpMode opMode;
    private DcMotor leftElevator;
    private DcMotor rightElevator;
    private ElevatorMode mode;

    public Elevator(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {
        // MOTORS SETUP //
        leftElevator = opMode.hardwareMap.get(DcMotor.class, "leftElevator");
        rightElevator = opMode.hardwareMap.get(DcMotor.class, "rightElevator");

        leftElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftElevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightElevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftElevator.setDirection(DcMotorSimple.Direction.REVERSE);
        rightElevator.setDirection(DcMotorSimple.Direction.FORWARD);

        // MODE SETUP //
        mode = ElevatorMode.AUTO;

        leftElevator.setTargetPosition(0);
        rightElevator.setTargetPosition(0);

        leftElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void setPosition(int position) {
        leftElevator.setTargetPosition(position);
        rightElevator.setTargetPosition(position);
        setMode(ElevatorMode.AUTO);
        //setPower(Constants.elevatorAutoSpeed);
    }

    public void setManualPower(double left, double right) {
        leftElevator.setPower(left);
        rightElevator.setPower(right);

        leftElevator.setTargetPosition(leftElevator.getCurrentPosition());
        rightElevator.setTargetPosition(rightElevator.getCurrentPosition());
    }

    public void setPower(double power) {
        leftElevator.setPower(power);
        rightElevator.setPower(power);
    }

    public void setLeftPower(double power) {
        leftElevator.setPower(power);
    }

    public void setRightPower(double power) {
        rightElevator.setPower(power);
    }

    public void setMode(ElevatorMode mode) {
        this.mode = mode;
        if(mode == ElevatorMode.AUTO) {
            leftElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        } else {
            leftElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
    public ElevatorMode getMode() { return mode; }

    public int getRightPosition() {
        return rightElevator.getCurrentPosition();
    }

    public int getLeftPosition() {
        return leftElevator.getCurrentPosition();
    }

}
