package org.firstinspires.ftc.UltimateGoalTeamcode.helper;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class SensorHelper{

    public void redOrBlue(boolean isRed, ColorSensor pFrontColor, Telemetry telemetry){
        //special scale factor for Red so that Red can be recognized via the "value" value.
        //NOTE: THIS AFFECTS ALL VALUE READINGS
        final float RED_SCALE_FACTOR = 2;

        // hsvValues is an array that will hold the hue, saturation, and value information.
        // the "value" value is multiplied by 2
        float hsvValues[] = {0F, 0F, 0F * RED_SCALE_FACTOR};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        //unecessary but it looks cool and makes things consistent
        final double SCALE_FACTOR = 1;

        // convert the RGB values to HSV values.
        // multiply by the SCALE_FACTOR (for fun)
        // then cast it back to int (because SCALE_FACTOR is a double)

        Color.RGBToHSV((int) (pFrontColor.red() * SCALE_FACTOR),
                (int) (pFrontColor.green() * SCALE_FACTOR),
                (int) (pFrontColor.blue() * SCALE_FACTOR),
                hsvValues);

        // if Statements - These Should Be Tested and Adjusted Accordingly
        //you could also make an additional class for this but I just put it in one
        if (pFrontColor.red() > 59) {
            isRed = true;
            telemetry.addLine("LINE COLOR: RED");
            telemetry.update();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (pFrontColor.red() < 59) {
            isRed = false;
            telemetry.addLine("LINE COLOR: BLUE");
            telemetry.update();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void whiteOrBlack(boolean isWhite, ColorSensor pFrontColor, Telemetry telemetry){
        //special scale factor for Red so that Red can be recognized via the "value" value.
        //NOTE: THIS AFFECTS ALL VALUE READINGS
        final float RED_SCALE_FACTOR = 2;

        // hsvValues is an array that will hold the hue, saturation, and value information.
        // the "value" value is multiplied by 2
        float hsvValues[] = {0F, 0F, 0F * RED_SCALE_FACTOR};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        //unecessary but it looks cool and makes things consistent
        final double SCALE_FACTOR = 1;

        // convert the RGB values to HSV values.
        // multiply by the SCALE_FACTOR (for fun)
        // then cast it back to int (because SCALE_FACTOR is a double)

        Color.RGBToHSV((int) (pFrontColor.red() * SCALE_FACTOR),
                (int) (pFrontColor.green() * SCALE_FACTOR),
                (int) (pFrontColor.blue() * SCALE_FACTOR),
                hsvValues);

        // if Statements - These Should Be Tested and Adjusted Accordingly
        //you could also make an additional class for this but I just put it in one
        if (pFrontColor.red() > 30) {
            isWhite = true;
            telemetry.addLine("LINE COLOR: WHITE");
            telemetry.update();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (pFrontColor.red() < 30) {
            isWhite = false;
            telemetry.addLine("LINE COLOR: BLACK");
            telemetry.update();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void distanceReading(DistanceSensor pFrontDistance, double distanceAmount){
       distanceAmount = pFrontDistance.getDistance(DistanceUnit.INCH);
    }
}


