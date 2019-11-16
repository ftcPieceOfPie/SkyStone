/*package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.helper.MotorHelper;

@Autonomous(name = "AutonomousTest_Encoders", group = "autonomous")
public class Autonomous12907 extends LinearOpMode {
    //Naming the motors
    DcMotor frontRightMotor;
    DcMotor frontLeftMotor;
    DcMotor backRightMotor;
    DcMotor backLeftMotor;
    MotorHelper motorHelper;

    public void initialize() {
        //Configuration of the motors
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        //setting the directions of the motors
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        motorHelper = new MotorHelper();
        double powerRight = 0.25;
        double powerLeft = 0.25;
        double targetPositionLeft = 12;
        double targetPositionRight = 12;
        double timeoutS = 5;
        if (opModeIsActive()) {
            motorHelper.movingWithEncoders(frontRightMotor, frontLeftMotor, backRightMotor, backLeftMotor,
                    powerRight, powerLeft,
                    targetPositionRight, targetPositionLeft,
                    timeoutS, telemetry);
        }
    }
}
*/

package org.firstinspires.ftc.SkystoneTeamcode.opmode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.SkystoneTeamcode.helper.NavigationHelper;
import org.firstinspires.ftc.SkystoneTeamcode.utillities.Parking;
import org.firstinspires.ftc.SkystoneTeamcode.utillities.SkystoneDelivery;
import org.firstinspires.ftc.SkystoneTeamcode.utillities.SkystoneDetection;

//import java.util.Scanner;

//@Disabled
//Adding Source Code to GitHub

@Autonomous(name = "Autonomous: OUTER RED", group = "autonomous")
public class Autonomous12907OuterRed extends LinearOpMode {

    boolean isBlue = false;
    boolean isOuter = true;
    boolean isPos2 = false;

    //final long SLEEP_TIME_250 = 250;

    //Naming the motors
    DcMotor frontLeft;
    DcMotor backLeft;
    DcMotor frontRight;
    DcMotor backRight;
    BNO055IMU imu;
    SkystoneDetection skystoneDetection;
    NavigationHelper navigationHelper;
    ColorSensor colorRight;
    DistanceSensor distanceRight;
    ColorSensor colorLeft;
    DistanceSensor distanceLeft;
    DistanceSensor quarryDistance;
    Servo pivotGrabber;
    Servo blockClamper;

    //Initializes motors from the hardware map

    public void initialize() {

        /*Scanner scanner = new Scanner(System.in);

        isBlue = scanner.nextBoolean();
        isOuter = scanner.nextBoolean();
        isPos2 = scanner.nextBoolean();

         */

        //Configuration of the Motors/Servos
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backLeft = hardwareMap.get(DcMotor.class, "backLeftMotor");
        frontRight = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backRight = hardwareMap.get(DcMotor.class, "backRightMotor");
        pivotGrabber = hardwareMap.get(Servo.class, "pivotGrabber");
        blockClamper = hardwareMap.get(Servo.class, "blockClamper");

        //Setting the direction of the motors
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        //Initializing color and distance sensors
        colorRight=hardwareMap.get(ColorSensor.class,"sensor_color_distance_right");
        distanceRight=hardwareMap.get(DistanceSensor.class,"sensor_color_distance_right");
        colorLeft=hardwareMap.get(ColorSensor.class,"sensor_color_distance_left");
        distanceLeft=hardwareMap.get(DistanceSensor.class,"sensor_color_distance_left");
        quarryDistance=hardwareMap.get(DistanceSensor.class,"sensor_distance_quarry");

        //Initializing the IMU
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.mode = BNO055IMU.SensorMode.IMU;
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        imu = hardwareMap.get(BNO055IMU.class, "imu 1");
        imu.initialize(parameters);

    }
    @Override
    public void runOpMode() throws InterruptedException {

        try {
            initialize();
            navigationHelper = new NavigationHelper();
            skystoneDetection = new SkystoneDetection();
           SkystoneDelivery skystoneDelivery = new SkystoneDelivery();
            Parking parking = new Parking();

            waitForStart();

            if (opModeIsActive()) {
                if (isOuter==true) {
                    skystoneDetection.moveToSkystoneOuter(frontLeft, frontRight, backLeft, backRight, navigationHelper, imu, telemetry, colorRight, colorLeft, distanceRight, distanceLeft, quarryDistance, pivotGrabber, blockClamper, isBlue, isPos2);
                    skystoneDelivery.placeSkystoneOuter(frontLeft, frontRight, backLeft, backRight, navigationHelper, imu, telemetry, blockClamper, pivotGrabber, isBlue);
                    parking.parkSkystone(frontLeft, frontRight, backLeft, backRight, navigationHelper, imu, telemetry, isBlue);
                }else {
                    skystoneDetection.moveToSkystoneInner(frontLeft, frontRight, backLeft, backRight, navigationHelper, imu, telemetry, colorRight, colorLeft, distanceRight, distanceLeft, quarryDistance, pivotGrabber, blockClamper, isBlue, isPos2);
                    skystoneDelivery.placeSkystoneInner(frontLeft, frontRight, backLeft, backRight, navigationHelper, imu, telemetry, blockClamper, pivotGrabber, isBlue);
                    parking.parkSkystone(frontLeft, frontRight, backLeft, backRight, navigationHelper, imu, telemetry, isBlue);

                }


               /* if(isBlue==true){
                    if (isOuter==true) {
                        skystoneDetection.moveToSkystoneOuterBlue(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, navigationHelper, imu, telemetry, colorRight, colorLeft, distanceRight, distanceLeft, pivotGrabber, blockClamper);
                        skystoneDelivery.placeSkystoneOuterBlue(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, navigationHelper, imu, telemetry, blockClamper, pivotGrabber);
                        parking.parkSkystoneOuterBlue(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, navigationHelper, imu, telemetry);
                    }
                    if (isOuter==false) {
                        skystoneDetection.moveToSkystoneInnerBlue(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, navigationHelper, imu, telemetry, colorRight, colorLeft, distanceRight, distanceLeft, pivotGrabber, blockClamper);
                        skystoneDelivery.placeSkystoneInnerBlue(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, navigationHelper, imu, telemetry, blockClamper, pivotGrabber);
                        parking.parkSkystoneInnerBlue(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, navigationHelper, imu, telemetry);
                    }
                }
                else if(isBlue==false){
                    if (isOuter==true) {
                        skystoneDetection.moveToSkystoneOuterRed(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, navigationHelper, imu, telemetry, colorRight, colorLeft, distanceRight, distanceLeft, pivotGrabber, blockClamper);
                        skystoneDelivery.placeSkystoneOuterRed(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, navigationHelper, imu, telemetry, blockClamper, pivotGrabber);
                        parking.parkSkystoneOuterRed(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, navigationHelper, imu, telemetry);
                    }
                    if (isOuter==false) {
                        skystoneDetection.moveToSkystoneInnerRed(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, navigationHelper, imu, telemetry, colorRight, colorLeft, distanceRight, distanceLeft, pivotGrabber, blockClamper);
                        skystoneDelivery.placeSkystoneInnerRed(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, navigationHelper, imu, telemetry, blockClamper, pivotGrabber);
                        parking.parkSkystoneInnerRed(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, navigationHelper, imu, telemetry);
                    }
                } */

            }
        }catch (Exception bad){
            telemetry.addData("EXCEPTION:", bad.toString());
            telemetry.update();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}


