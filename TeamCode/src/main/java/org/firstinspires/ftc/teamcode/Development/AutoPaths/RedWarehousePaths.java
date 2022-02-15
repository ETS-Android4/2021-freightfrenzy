package org.firstinspires.ftc.teamcode.Development.AutoPaths;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Controllers.CupFinder;
import org.firstinspires.ftc.teamcode.Development.MainAutoV1;
import org.firstinspires.ftc.teamcode.Development.PoseStorage;
import org.firstinspires.ftc.teamcode.Development.TeleOPV1;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

public class RedWarehousePaths {

    public Pose2d startingPose = new Pose2d(7, -62.2, Math.toRadians(90));

    public TrajectorySequence toHubLeft, toHubCenter,  toHubRight,  intoWarehouseLeft, intoWarehouseCenter, intoWarehouseRight, toHub2, intoWarehouse2, toHub3, intoWarehouse3 ;

    public enum Paths {
        start, toHub, delivering, intoWarehouse, intaking, toHub2, delivering2, intoWarehouse2, toHub3, delivering3, intoWarehouse3;
    }

    public Paths path = Paths.start;

    private int cycle = 0;

    private final SampleMecanumDrive drive;



    public RedWarehousePaths(SampleMecanumDrive drive) {

        this.drive = drive;

        toHubLeft = drive.trajectorySequenceBuilder(startingPose)
                .addDisplacementMarker(0, 0.25, () -> TeleOPV1.armController.setPosition((int) MainAutoV1.armBottomPos))
                .splineToLinearHeading(new Pose2d(2, -48, Math.toRadians(-45)), Math.toRadians(90),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL * .75,
                                DriveConstants.MAX_ANG_VEL * .75, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL*.5))
                .build();



        toHubCenter = drive.trajectorySequenceBuilder(startingPose)
                .addDisplacementMarker(.1, 0, () -> TeleOPV1.armController.setPosition((int) MainAutoV1.armMiddlePos))
                .splineToLinearHeading(new Pose2d(-.25, -47.25, Math.toRadians(-45)), Math.toRadians(90),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL * .75,
                                DriveConstants.MAX_ANG_VEL * .75, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL*.5))
                .build();

        toHubRight = drive.trajectorySequenceBuilder(startingPose)
                .addDisplacementMarker(.2, 0, () -> TeleOPV1.armController.setPosition((int) MainAutoV1.armTopPos))
                .splineToLinearHeading(new Pose2d(-5, -42.4, Math.toRadians(-45)), Math.toRadians(90),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL * .75,
                                DriveConstants.MAX_ANG_VEL * .75, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL*.5))
                .build();

        intoWarehouseLeft = drive.trajectorySequenceBuilder(toHubLeft.end())
                .splineToLinearHeading(new Pose2d(0, -66, Math.toRadians(0)), Math.toRadians(0))
                .addDisplacementMarker(() -> TeleOPV1.armController.setPosition((int) MainAutoV1.armStartPos))
                .splineToSplineHeading(new Pose2d(22, -66, 0), Math.toRadians(0))
                .addDisplacementMarker(.3, 0, () -> {
                    MainAutoV1.intakeOn = true;
                    MainAutoV1.intakeReverse = false;
                })
                .splineToConstantHeading(new Vector2d(46, -66), Math.toRadians(0),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL*.25,
                                DriveConstants.MAX_ANG_VEL*.25, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL*.25))
                .build();

        intoWarehouseCenter = drive.trajectorySequenceBuilder(toHubCenter.end())
                .splineToLinearHeading(new Pose2d(0, -65.75, Math.toRadians(0)), Math.toRadians(0))
                .addDisplacementMarker(() -> TeleOPV1.armController.setPosition((int) MainAutoV1.armStartPos))
                .splineToSplineHeading(new Pose2d(22, -65.75, 0), Math.toRadians(0))
                .addDisplacementMarker(.3, 0, () -> {
                    MainAutoV1.intakeOn = true;
                    MainAutoV1.intakeReverse = false;
                })
                .splineToConstantHeading(new Vector2d(46, -66), Math.toRadians(0),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL*.25,
                                DriveConstants.MAX_ANG_VEL*.25, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL*.25))
                .build();

        intoWarehouseRight = drive.trajectorySequenceBuilder(toHubRight.end())
                .splineToLinearHeading(new Pose2d(0, -66, Math.toRadians(0)), Math.toRadians(0))
                .addDisplacementMarker(() -> TeleOPV1.armController.setPosition((int) MainAutoV1.armStartPos))
                .splineToSplineHeading(new Pose2d(22, -66, 0), Math.toRadians(0))
                .addDisplacementMarker(.3, 0, () -> {
                    MainAutoV1.intakeOn = true;
                    MainAutoV1.intakeReverse = false;
                })
                .splineToConstantHeading(new Vector2d(46, -66), Math.toRadians(0),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL*.25,
                                DriveConstants.MAX_ANG_VEL*.25, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL*.25))
                .build();




        toHub2 = drive.trajectorySequenceBuilder(intoWarehouseRight.end().minus(new Pose2d(0, 0, 0)))
                .lineToConstantHeading(new Vector2d(-2, -65.5))
                .addDisplacementMarker(() -> {
                    if(MainAutoV1.position == CupFinder.PositionEnum.LEFT && PoseStorage.startingPosition == PoseStorage.StartingPosition.WAREHOUSE) {
                        drive.setPoseEstimate(drive.getPoseEstimate().plus(new Pose2d(0, -.25, 0)));
                    }
                })
                .addDisplacementMarker(.2, 0, () -> {
                    MainAutoV1.intakeOn = true;
                    MainAutoV1.intakeReverse = true;
                })
                .addDisplacementMarker(() -> TeleOPV1.armController.setPosition((int) MainAutoV1.armTopPos))
                .lineToLinearHeading(new Pose2d(-1, -41, Math.toRadians(-45)),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL * .75,
                                DriveConstants.MAX_ANG_VEL * .75, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL*.5))
                .addDisplacementMarker(.9, 0, () -> MainAutoV1.intakeOn = false)
                .build();

        intoWarehouse2 = drive.trajectorySequenceBuilder(toHub2.end())
                .splineToLinearHeading(new Pose2d(0, -66, Math.toRadians(0)), Math.toRadians(0))
                .addDisplacementMarker(() -> TeleOPV1.armController.setPosition((int) MainAutoV1.armStartPos))
                .splineToSplineHeading(new Pose2d(27, -66, 0), Math.toRadians(0))
                .addDisplacementMarker(.3, 0, () -> {
                    MainAutoV1.intakeOn = true;
                    MainAutoV1.intakeReverse = false;
                })
                .splineToConstantHeading(new Vector2d(53, -66), Math.toRadians(0),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL*.25,
                                DriveConstants.MAX_ANG_VEL*.25, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL*.25))
                .build();

        toHub3 = drive.trajectorySequenceBuilder(intoWarehouse2.end().minus(new Pose2d(0, 0, 0)))
                .lineToLinearHeading(new Pose2d(-1.5, -66.75, Math.toRadians(-1)))
                .addDisplacementMarker(() -> {
                    if(MainAutoV1.position == CupFinder.PositionEnum.LEFT && PoseStorage.startingPosition == PoseStorage.StartingPosition.WAREHOUSE) {
                        drive.setPoseEstimate(drive.getPoseEstimate().plus(new Pose2d(0, 0, 0)));
                    }
                })
                .addDisplacementMarker(.2, 0, () -> {
                    MainAutoV1.intakeOn = true;
                    MainAutoV1.intakeReverse = true;
                })
                .addDisplacementMarker(() -> TeleOPV1.armController.setPosition((int) MainAutoV1.armTopPos))
                .lineToLinearHeading(new Pose2d(.5, -42, Math.toRadians(-45)),
                SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL * .75,
                        DriveConstants.MAX_ANG_VEL * .75, DriveConstants.TRACK_WIDTH),
                SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL*.5))
                .addDisplacementMarker(.9, 0, () -> MainAutoV1.intakeOn = false)
                .build();

        intoWarehouse3 = drive.trajectorySequenceBuilder(toHub3.end())
                .splineToLinearHeading(new Pose2d(0, -66.5, Math.toRadians(0)), Math.toRadians(0))
                .addDisplacementMarker(() -> TeleOPV1.armController.setPosition((int) MainAutoV1.armStartPos))
                .splineToSplineHeading(new Pose2d(36, -67.5, 0), Math.toRadians(0))
                .build();





    }

    public void setPath(Paths path) {
        this.path = path;
    }

    public Paths getPath() {
        return path;
    }

    public int getCycle() {
        return cycle;
    }

    public void addCycle() {
        this.cycle++;
    }

    public void generateToHub2() {
        toHub2 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .lineToConstantHeading(new Vector2d(-1, -65))
                .addDisplacementMarker(() -> {
                    if(MainAutoV1.position == CupFinder.PositionEnum.LEFT && PoseStorage.startingPosition == PoseStorage.StartingPosition.WAREHOUSE) {
                        drive.setPoseEstimate(drive.getPoseEstimate().plus(new Pose2d(0, -.25, 0)));
                    }
                })
                .addDisplacementMarker(.2, 0, () -> {
                    MainAutoV1.intakeOn = true;
                    MainAutoV1.intakeReverse = true;
                })
                .addDisplacementMarker(() -> TeleOPV1.armController.setPosition((int) MainAutoV1.armTopPos))
                .lineToLinearHeading(new Pose2d(-1, -41, Math.toRadians(-45)),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL * .75,
                                DriveConstants.MAX_ANG_VEL * .75, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL*.5))
                .addDisplacementMarker(.9, 0, () -> MainAutoV1.intakeOn = false)
                .build();
    }

    public void generateToHub3() {
        toHub3 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .lineToLinearHeading(new Pose2d(-1.5, -65, Math.toRadians(-1)))
                .addDisplacementMarker(() -> {
                    if(MainAutoV1.position == CupFinder.PositionEnum.LEFT && PoseStorage.startingPosition == PoseStorage.StartingPosition.WAREHOUSE) {
                        drive.setPoseEstimate(drive.getPoseEstimate().plus(new Pose2d(0, 0, 0)));
                    }
                })
                .addDisplacementMarker(.2, 0, () -> {
                    MainAutoV1.intakeOn = true;
                    MainAutoV1.intakeReverse = true;
                })
                .addDisplacementMarker(() -> TeleOPV1.armController.setPosition((int) MainAutoV1.armTopPos))
                .lineToLinearHeading(new Pose2d(.5, -42, Math.toRadians(-45)),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL * .75,
                                DriveConstants.MAX_ANG_VEL * .75, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL*.5))
                .addDisplacementMarker(.9, 0, () -> MainAutoV1.intakeOn = false)
                .build();
    }
}
