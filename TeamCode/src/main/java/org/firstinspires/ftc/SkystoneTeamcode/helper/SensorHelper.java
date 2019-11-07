package org.firstinspires.ftc.SkystoneTeamcode.helper;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class SensorHelper {

    /*public boolean isYellow (ColorSensor color){
        float hsvValues[] = {0F, 0F, 0F};

        final float values[] = hsvValues;

        final double SCALE_FACTOR = 1;

        int red = (int) (color.red() * SCALE_FACTOR);
        int green = (int) (color.green()*SCALE_FACTOR);
        if (red>100&&green>100){
            return true;
        }
        else{
            return false;
        }
    }*/

    public Constants12907.SkystonePosition getBlackBlock (ColorSensor colorLeft, ColorSensor colorRight){
        float hsvValues[] = {0F, 0F, 0F};

        final float values[] = hsvValues;

        int rightSensorRed = (int)(colorRight.red());
        int rightSensorGreen = (int) (colorRight.green());
        int leftSensorRed = (int)(colorLeft.red());
        int leftSensorGreen = (int)(colorLeft.green());
        if((leftSensorRed > 1000) && (leftSensorGreen> 1000) && (rightSensorRed > 1000) && (rightSensorGreen > 1000)){
            return Constants12907.SkystonePosition.LEFT;

        }else if((rightSensorRed>leftSensorRed) && (rightSensorGreen>leftSensorGreen)){
            return Constants12907.SkystonePosition.CENTER;

        }else{
            return Constants12907.SkystonePosition.RIGHT;
        }

    }

}
