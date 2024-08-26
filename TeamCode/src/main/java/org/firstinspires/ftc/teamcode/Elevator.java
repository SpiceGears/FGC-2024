package org.firstinspires.ftc.teamcode;

import android.text.method.Touch;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class Elevator {

    private final LinearOpMode opMode;
    private DcMotor leftElevator;
    private DcMotor rightElevator;
    private ElevatorMode mode;
    private TouchSensor leftTouch;
    private TouchSensor rightTouch;

    public Elevator(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {
        // MOTORS SETUP //
        leftElevator = opMode.hardwareMap.get(DcMotor.class, "leftElevator");
        rightElevator = opMode.hardwareMap.get(DcMotor.class, "rightElevator");
        leftTouch = opMode.hardwareMap.get(TouchSensor.class, "leftTouch");
        rightTouch = opMode.hardwareMap.get(TouchSensor.class, "rightTouch");

        leftElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftElevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightElevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftElevator.setDirection(DcMotorSimple.Direction.REVERSE);
        rightElevator.setDirection(DcMotorSimple.Direction.FORWARD);

        // MODE SETUP //
        mode = ElevatorMode.AUTO;

//        leftElevator.setTargetPosition(0);
//        rightElevator.setTargetPosition(0);
//
//        leftElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        leftElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void setPosition(int position) {
        leftElevator.setTargetPosition(position);
        rightElevator.setTargetPosition(position);
        setMode(ElevatorMode.AUTO);
        //setPower(Constants.elevatorAutoSpeed);
    }

    public void checkMotors() {
        if(leftElevator.isBusy()) {
            if(Math.abs(leftElevator.getCurrentPosition() - leftElevator.getTargetPosition()) < 10) {
                leftElevator.setPower(0);
            }
        }

        if(rightElevator.isBusy()) {
            if(Math.abs(rightElevator.getCurrentPosition() - rightElevator.getTargetPosition()) < 10) {
                rightElevator.setPower(0);
            }
        }
    }

    public void setManualPower(double left, double right) {
        leftElevator.setPower(left);
        rightElevator.setPower(left);

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

    public void resetEncoder() {
        leftElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public  void checkSensors(){
        if (!leftTouch.isPressed()){
            leftElevator.setPower(0);
        }
        if (!rightTouch.isPressed()){
            rightElevator.setPower(0);
        }

    }

}
