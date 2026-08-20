package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Transfer {
    DcMotorEx intake;
    DcMotorEx uptake;
    Servo rightBlock;
    Servo leftBlock;
    RevColorSensorV3 sensorOne;
    RevColorSensorV3 sensorTwo;
    RevColorSensorV3 sensorThree;
    TransferStates transferState = TransferStates.HOLD;
    double blockUptakePosition;
    double openUptakePosition;
    final double HAS_BALL_THRESHOLD = 38.30;
    int ballNumber;
// ask about reversing it or just smacking it dead in the middle
    public Transfer(HardwareMap hwMap){
        intake = hwMap.get(DcMotorEx.class, "intakes");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setPower(0);

        uptake = hwMap.get(DcMotorEx.class, "uptake");
        uptake.setDirection(DcMotorSimple.Direction.FORWARD);
        uptake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        uptake.setPower(0);

        rightBlock = hwMap.get(Servo.class, "rightBlock");
        leftBlock = hwMap.get(Servo.class, "leftBlcok");

        sensorOne = hwMap.get(RevColorSensorV3.class, "sensorOne");
        sensorTwo = hwMap.get(RevColorSensorV3.class, "sensorTwo");
        sensorThree = hwMap.get(RevColorSensorV3.class, "sensorThree");
    }
    // lowkey forgot what an enum does
    enum TransferStates{
        INTAKE, HOLD, CLEAR, STRAIGHTUP
    }
    public TransferStates getTransferState(){
        return transferState;
    }
    public void setTransferStates(TransferStates transferState){
        this.transferState = transferState;
    }

    public void setBlockUptakePosition(){
        leftBlock.setPosition(blockUptakePosition);
        rightBlock.setPosition(blockUptakePosition);
    }
    public void setOpenUptakePosition(){
        leftBlock.setPosition(openUptakePosition);
        rightBlock.setPosition(openUptakePosition);
    }

    public void transferUpdate(){
        switch(transferState){
            case HOLD:
                setBlockUptakePosition();
                intake.setPower(0);
                uptake.setPower(0);
                break;
            case INTAKE:
                setBlockUptakePosition();
                intake.setPower(0);
                uptake.setPower(0);
                break;
            case STRAIGHTUP:
                setOpenUptakePosition();
                intake.setPower(1);
                uptake.setPower(1);
                break;
            case CLEAR:
                setOpenUptakePosition();
                intake.setPower(-1);
                uptake.setPower(-1);
        }
    }
    public void setHoldMode(){
        setTransferStates(TransferStates.HOLD);
    }
    public void setIntakeMode(){
        setTransferStates(TransferStates.INTAKE);
    }
    public void setStraightUpMode(){
        setTransferStates(TransferStates.STRAIGHTUP);
    }
    public void setClearMode(){
        setTransferStates(TransferStates.CLEAR);
    }
    // I apologize in advance for this
    public int getBallNumber(){
        if((sensorOne.getDistance(DistanceUnit.INCH) < HAS_BALL_THRESHOLD) && (sensorTwo.getDistance(DistanceUnit.INCH) > HAS_BALL_THRESHOLD)
        && (sensorThree.getDistance(DistanceUnit.INCH) > HAS_BALL_THRESHOLD)){
            return ballNumber = 1;
        } else if ((sensorOne.getDistance(DistanceUnit.INCH) < HAS_BALL_THRESHOLD) && (sensorTwo.getDistance(DistanceUnit.INCH) < HAS_BALL_THRESHOLD)
                && (sensorThree.getDistance(DistanceUnit.INCH) > HAS_BALL_THRESHOLD)){
            return ballNumber = 2;
        } else if ((sensorOne.getDistance(DistanceUnit.INCH) < HAS_BALL_THRESHOLD) && (sensorTwo.getDistance(DistanceUnit.INCH) < HAS_BALL_THRESHOLD)
                && (sensorThree.getDistance(DistanceUnit.INCH) < HAS_BALL_THRESHOLD)){
            return ballNumber = 3;
        }
        return ballNumber = 0;
    }

}
