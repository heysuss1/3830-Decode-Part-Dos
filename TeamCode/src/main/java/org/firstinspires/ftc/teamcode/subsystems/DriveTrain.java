package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
/**
 * drive train
 *
 * @author Cade Gebo
 * @version August 20
 */

public class DriveTrain {
    DcMotorEx bl;
    DcMotorEx br;
    DcMotorEx fl;
    DcMotorEx fr;
    public DriveTrain(HardwareMap hwMap){
        bl = hwMap.get(DcMotorEx.class, "backLeft");
        br = hwMap.get(DcMotorEx.class, "backRight");
        fl = hwMap.get(DcMotorEx.class, "foreLeft");
        fr = hwMap.get(DcMotorEx.class, "foreRight");
        bl.setDirection(DcMotorSimple.Direction.FORWARD);
        br.setDirection(DcMotorSimple.Direction.FORWARD);
        fl.setDirection(DcMotorSimple.Direction.FORWARD);
        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        bl.setPower(0);
        br.setPower(0);
        fl.setPower(0);
        fr.setPower(0);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void setPower(double blPower, double brPower, double flPower, double frPower) {
        bl.setPower(blPower);
        br.setPower(brPower);
        fl.setPower(flPower);
        fr.setPower(frPower);
    }
    public void drive(double forward, double strafe, double turn) {
        double blPower = forward - strafe + turn;
        double brPower = forward + strafe - turn;
        double flPower = forward + strafe + turn;
        double frPower = forward - strafe - turn;
        setPower(blPower, brPower, flPower, frPower);
    }
    public void stop() {
        setPower(0, 0, 0, 0);
    }
    public int getBLPosition() {
        return bl.getCurrentPosition();
    }
    public int getBRPosition() {
        return br.getCurrentPosition();
    }
    public int getFLPosition() {
        return fl.getCurrentPosition();
    }
    public int getFRPosition() {
        return fr.getCurrentPosition();
    }

}