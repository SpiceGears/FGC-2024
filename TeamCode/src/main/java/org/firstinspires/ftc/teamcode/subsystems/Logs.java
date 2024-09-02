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

    public void addLine(String caption, Object line, boolean show, String color) {
        if(!show) return;
        String text = "<font color='" + color + "'>" + line + "</font>";

        telemetry.addData(caption, text);
    }
    public void addLine(String caption, Object line, boolean show){
        if(!show) return;
        telemetry.addData(caption,line);
    }

    public void send() {
        telemetry.update();
    }

}
