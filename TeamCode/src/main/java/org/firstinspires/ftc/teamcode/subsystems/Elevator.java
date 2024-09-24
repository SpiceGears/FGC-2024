package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.utils.Constants;
import org.firstinspires.ftc.teamcode.utils.ElevatorMode;

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
        mode = ElevatorMode.MANUAL;

    }

    public void setPosition(int position) {
        leftElevator.setTargetPosition(position);
        rightElevator.setTargetPosition(position);
        setMode(ElevatorMode.AUTO);
    }

    public void setManualPower(double left) {
        leftElevator.setPower(left);
        rightElevator.setPower(left);

        leftElevator.setTargetPosition(leftElevator.getCurrentPosition());
        rightElevator.setTargetPosition(rightElevator.getCurrentPosition());
    }

    public void setPower(double power) {
        leftElevator.setPower(power);
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
    public  void checkSensors() {
        if (mode == ElevatorMode.MANUAL) return;
        if (leftTouch.isPressed()) {
            leftElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        if (rightTouch.isPressed()) {
            rightElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

    }

    public void checkPositions() {
        if(mode == ElevatorMode.MANUAL) {
            if(leftElevator.getCurrentPosition() >= Constants.Elevator.maxPosition || rightElevator.getCurrentPosition() >= Constants.Elevator.maxPosition) {
                leftElevator.setPower(0);
                rightElevator.setPower(0);
            }
        } else {
            if(Math.abs(leftElevator.getTargetPosition() - leftElevator.getCurrentPosition()) <= 20 && leftElevator.getTargetPosition() != leftElevator.getCurrentPosition()) {
                setMode(ElevatorMode.MANUAL);
            }
            if(Math.abs(rightElevator.getTargetPosition() - rightElevator.getCurrentPosition()) <= 20 && rightElevator.getTargetPosition() != rightElevator.getCurrentPosition()) {
                setMode(ElevatorMode.MANUAL);
            }
        }
    }
    public boolean getLeftTouchState(){ return leftTouch.isPressed(); }

    public  boolean getRightTouchState(){ return rightTouch.isPressed(); }

//    public String getMotors(){
//        return leftElevator.isBusy() + " | " + rightElevator.isBusy() + " | " + leftElevator.getCurrent(CurrentUnit.AMPS) + " | "+ rightElevator.getCurrent(CurrentUnit.AMPS);
//    }

    public String getMotorsMode() {
        return leftElevator.getMode() + " | " + rightElevator.getMode();
    }


}
