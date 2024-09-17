package org.firstinspires.ftc.teamcode.utils;

import com.acmerobotics.dashboard.config.Config;

public class Constants {

    // ELEVATOR
    @Config
    public static class Elevator {
        public static int elevatorAutoSpeed = 1;

        public static int elevatorFirstLevel = 1000;
        public static int elevatorSecondLevel = 2000;
        public static int elevatorThirdLevel = 3000;
        public static int elevatorFourLevel = 4000;
    }

    // DRIVETRAIN
    @Config
    public static class Drivetrain {
        public static double maxDriveSpeed = 1.0; // < 0.1 ; 1.0 >
        public static double accelerationCoefficient = 0.2;
        public static double decelerationCoefficient = 0.2;

        static double gearbox = 3 * 5;
        static double distance = 100; // 1 meter
        static double wheelPerimeter = 9;
        public static double meterTicks = (4 * gearbox * distance) / wheelPerimeter;
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
    }

}
