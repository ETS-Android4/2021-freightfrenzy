package org.firstinspires.ftc.teamcode.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(preselectTeleOp = "TeleOPV1")
public class BlueWarehouseAuto extends MainAutoV1 {
    @Override
    Position findPosition() {
        return new Position(PoseStorage.Alliance.BLUE, PoseStorage.StartingPosition.WAREHOUSE);
    }
}
