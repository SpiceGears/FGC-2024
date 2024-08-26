package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Logs {

    private final Telemetry telemetry;
    private StringBuilder builder;

    public Logs(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    public void init() {
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
