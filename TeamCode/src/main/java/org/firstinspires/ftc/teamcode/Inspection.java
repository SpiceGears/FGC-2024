package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Balance;
import org.firstinspires.ftc.teamcode.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Logs;
import org.firstinspires.ftc.teamcode.utils.ElevatorMode;

@TeleOp(name="Inspection", group = "Linear Opmode")
public class Inspection extends LinearOpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    private final Logs logs = new Logs(telemetry);
    private final Drive drivetrain = new Drive(this);
    private final Elevator elevator = new Elevator(this);
    private final Bucket bucket = new Bucket(this);
    private final Balance balance = new Balance(this);

    @Override
    public void runOpMode() {

        logs.init();
        drivetrain.init();
        elevator.init();
        elevator.setMode(ElevatorMode.MANUAL);
        bucket.init();
        balance.init();

        waitForStart();
        runtime.reset();

        if(opModeIsActive()) {
            sendLog("Click button A");
            waitForButton("A");
            execute(this::driveForward, 2);
            drivetrain.stop();
            sendLog("Click button B");

            waitForButton("B");
            sendLog("B Clicked");
            execute(this::driveBackward, 2);
            drivetrain.stop();

            sendLog("Click button A");

            waitForButton("A");
            execute(this::elevatorUp, 4);
            elevatorStop();

            sendLog("Click button B");

            waitForButton("B");
            execute(this::elevatorDown, 4);
            elevatorStop();

            sendLog("Click button A");

            waitForButton("A");
            execute(this::releaseBall, 2);
            stopIntake();

            sendLog("Click button B");

            waitForButton("B");
            execute(this::intakeBall, 2);
            stopIntake();

            sendLog("Click button A");

            waitForButton("A");
            openBalance();

            sendLog("Click button B");

            waitForButton("B");
            closeBalance();

            sleep(1000);
        }
    }

    private void waitForButton(String button) {
        boolean buttonPressed = false;
        while(opModeIsActive() && !buttonPressed) {
            switch(button) {
                case "A":
                    buttonPressed = gamepad1.a;
                    break;

                case "B":
                    buttonPressed = gamepad1.b;
                    break;
            }
        }
    }

    private void execute(Runnable action, double time) {
        long startTime = System.currentTimeMillis();

        while(opModeIsActive() && (System.currentTimeMillis() - startTime) < time * 1000) {
            action.run();
        }
    }

    private void sendLog(String log) {
        logs.addLine(">>", log);
        logs.send();
    }

    private void driveForward() {
        drivetrain.drive(1.0, 0);
    }
    private void driveBackward() { drivetrain.drive(-1.0, 0); }

    private void elevatorUp() {
        elevator.setManualPower(1.0);
    }
    private void elevatorDown() {
        elevator.setManualPower(-1.0);
    }
    private void elevatorStop() {
        elevator.setManualPower(0);
    }
    private void releaseBall() {
        bucket.setMotorPower(1.0);
    }
    private void intakeBall() {
        bucket.setMotorPower(-1.0);
    }
    private void stopIntake() {
        bucket.setMotorPower(0);
    }
    private void openBalance() { balance.openBalance(); }
    private void closeBalance() { balance.closeBalance(); }
}
