package org.firstinspires.ftc.teamcode.utils;

import com.acmerobotics.dashboard.config.Config;

public class Constants {

    // ELEVATOR
    @Config
    public static class Elevator {
        public static int elevatorAutoSpeed = 1;
        public static int maxPosition = 8900;

        public static int elevatorFirstLevel = 1000;
        public static int elevatorSecondLevel = 2000;
        public static int elevatorThirdLevel = 3000;
        public static int elevatorFourLevel = 4000;
    }

    // DRIVETRAIN
    @Config
    public static class Drivetrain {

    }

    // LOGS
    @Config
    public static class Logs {
        public static boolean showElevatorPositions = true;
        public static boolean showElevatorSensors = true;
        public static boolean showElevatorMode = true;
        public static boolean showElevatorMotorPower = true;
        public static boolean showElevatorMotorModes = true;
        public static boolean showSpeedModifier = true;
        public static boolean showDrivetrainMotorPower = true;
        public static boolean showBatteryVoltage = true;

        public static boolean showIntakeMotorPower = true;
    }

    @Config
    public static class Balance {
        public static double closeLeftPosition = 0;
        public static double closeRightPosition = 0.5;
        public static double openLeftPosition = 1;
        public static double openRightPosition = -1;
    }

}
