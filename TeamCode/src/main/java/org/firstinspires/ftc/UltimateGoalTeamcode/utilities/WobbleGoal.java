package org.firstinspires.ftc.UltimateGoalTeamcode.utilities;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.SkystoneTeamcode.helper.Constants12907;
import org.firstinspires.ftc.SkystoneTeamcode.helper.NavigationHelper;
import org.firstinspires.ftc.UltimateGoalTeamcode.helper.Constants2020;
import org.firstinspires.ftc.UltimateGoalTeamcode.helper.DetectionHelper;
import org.firstinspires.ftc.UltimateGoalTeamcode.opmode.UltimateAuto;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.HashMap;

import static java.lang.Thread.sleep;

public class WobbleGoal {

    NavigationHelper navigater = new NavigationHelper();

    public void moveToTgtZone(HashMap<String, Object> variableMap){
        Telemetry telemetry = (Telemetry) variableMap.get(Constants2020.TELEMETRY);
        telemetry.addLine("Inside method in Wobble Goal Class");
        telemetry.update();
        boolean isWall = (boolean)(variableMap.get(Constants2020.WALL_FLAG));
        boolean isBlue = (boolean)(variableMap.get(Constants2020.BLUE_FLAG));
        DcMotor backLeft = (DcMotor)variableMap.get(Constants2020.BACK_LEFT_MOTOR);
        DcMotor backRight = (DcMotor)variableMap.get(Constants2020.BACK_RIGHT_MOTOR);
        DcMotor frontLeft = (DcMotor)variableMap.get(Constants2020.FRONT_LEFT_MOTOR);
        DcMotor frontRight = (DcMotor)variableMap.get(Constants2020.FRONT_RIGHT_MOTOR);
        BNO055IMU imu = (BNO055IMU)variableMap.get(Constants2020.IMU);
        Constants2020.TargetZone position = (Constants2020.TargetZone)variableMap.get(Constants2020.POSITION);

        //If isWall is true, strafe right (red) or left (blue)
        if(isBlue&&isWall){
            navigater.navigate(6, Constants12907.Direction.LEFT, 0,0.5, backLeft, backRight, frontRight,frontLeft, imu, telemetry,true);
        } else if(!isBlue&&isWall){
            navigater.navigate(6, Constants12907.Direction.RIGHT, 0,0.5, backLeft, backRight, frontRight,frontLeft, imu, telemetry,true);
        }

        //move to tgt zone A, B, or C
        if(position.equals(Constants2020.TargetZone.ALPHA)){
            telemetry.addLine("BLUE - WALL - ALPHA");
            telemetry.update();
            navigater.navigate(70.75-12.75, Constants12907.Direction.STRAIGHT, 0,0.4, backLeft, backRight, frontRight,frontLeft, imu, telemetry,true);
            //navigater.navigate(0, Constants12907.Direction.TURN, 90,0.25, backLeft, backRight, frontRight,frontLeft, imu, telemetry,true);
        } else if(position.equals(Constants2020.TargetZone.BETA)){
            telemetry.addLine("BLUE - WALL - BETA");
            telemetry.update();
            navigater.navigate(94.625-12.75, Constants12907.Direction.STRAIGHT, 0,0.4, backLeft, backRight, frontRight,frontLeft, imu, telemetry,true);
            //navigater.navigate(0, Constants12907.Direction.TURN, 90,0.25, backLeft, backRight, frontRight,frontLeft, imu, telemetry,true);
        } else if(position.equals(Constants2020.TargetZone.CHARLIE)){
            telemetry.addLine("BLUE - WALL - CHARLIE");
            telemetry.update();
            navigater.navigate(118.372-12.75, Constants12907.Direction.STRAIGHT, 0,0.4, backLeft, backRight, frontRight,frontLeft, imu, telemetry,true);
            //navigater.navigate(0, Constants12907.Direction.TURN, 90,0.25, backLeft, backRight, frontRight,frontLeft, imu, telemetry,true);
        }
    }
}
