package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class DriveSubsystem extends SubsystemBase{

private Joystick joy1= new Joystick(0);

private SparkMax leftFrontMotor = new SparkMax(0, MotorType.kBrushed);
private SparkMax leftBackMotor = new SparkMax(1, MotorType.kBrushed);

private SparkMax rightFrontMotor = new SparkMax(2, MotorType.kBrushed);
private SparkMax rightBackMotor = new SparkMax(3, MotorType.kBrushed);

private RelativeEncoder leftFrontEncoder = leftFrontMotor.getEncoder();
private RelativeEncoder rightFrontEncoder = rightFrontMotor.getEncoder();
private MotorControllerGroup lefMotorControllerGroup = new MotorControllerGroup(leftFrontMotor, leftBackMotor);
    private MotorControllerGroup rightMotorControllerGroup = new MotorControllerGroup(rightFrontMotor, rightBackMotor);

DifferentialDrive differentialDrive = new DifferentialDrive(lefMotorControllerGroup, rightMotorControllerGroup);

public DriveSubsystem() {

    lefMotorControllerGroup.setInverted(false);
    rightMotorControllerGroup.setInverted(true);

    rightFrontEncoder.setPosition(0);
    leftFrontEncoder.setPosition(0);
}
    @Override
    public void periodic() {
        SmartDashboard.putNumber("right encoder", rightFrontEncoderValue());
        SmartDashboard.putNumber("left encoder", leftFrontEncoderValue());
    }

    public double leftFrontEncoderValue() {
        return leftFrontEncoder.getPosition();
    }

    public double rightFrontEncoderValue() {
        return rightFrontEncoder.getPosition();
    }

    public void tankMode(double left, double right) {
        differentialDrive.tankDrive(left, right);
    }

    public void stop(){
        differentialDrive.stopMotor();
    }
}
