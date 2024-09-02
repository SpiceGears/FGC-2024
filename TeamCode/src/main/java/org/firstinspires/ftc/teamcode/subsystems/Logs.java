package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Logs {

    private Telemetry telemetry;

    public Logs(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.HTML);
    }

    public void addLine(String caption, Object line, String color) {
        String text = "<font color='" + color + "'>" + line + "</font>";

        telemetry.addData(caption, text);
    }
    public void addLine(String caption, Object line){
        telemetry.addData(caption,line);
    }

    public void send() {
        telemetry.update();
    }

}
