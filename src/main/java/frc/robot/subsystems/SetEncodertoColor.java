/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SetEncodertoColor extends SubsystemBase {
  /**
   * Creates a new SetEncodertoColor.
   */
  public static WPI_TalonSRX _motor = new WPI_TalonSRX(2);

  public static final double kP = 0.5;
  public static final double kI = 0.4;
  public static final double kD = 0.1;
  public static final double iLimit = 1;

  public static double setpoint = 0;
  public static double errorSum = 0;
  public static double lastTimestamp = 0;
  public static double lastError = 0;
  public static final double kDriveTick2Feet = 1.0 / 128 * 6 * Math.PI / 12;

  public SetEncodertoColor() {

  }
  public void init(){
    _motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    _motor.configOpenloopRamp(0.2);
    _motor.configClosedloopRamp(0.2);
    // Talon setup. Setting up + and - output for max and nominal.
    // These should never really change
    _motor.configNominalOutputForward(0, 0);
    _motor.configNominalOutputReverse(0, 0);
    _motor.configPeakOutputForward(1, 0);
    _motor.configPeakOutputReverse(-1, 0);
    _motor.setSensorPhase(true);
    _motor.setInverted(true);
  }
  public void blue() {
    setpoint = 30; //degree to set encoder to
    
    

    // get sensor position
    double sensorPosition = _motor.getSelectedSensorPosition(0) * kDriveTick2Feet;

    // calculations
    double error = setpoint - sensorPosition;
    double dt = Timer.getFPGATimestamp() - lastTimestamp;

    if (Math.abs(error) < iLimit) {
      errorSum += error * dt;

    }
    double errorRate = (error - lastError) / dt;

    double outputSpeed = kP * error + kI * errorSum + kD * errorRate;

    // output to motors
    _motor.set(outputSpeed);

    // update last- variables
    lastTimestamp = Timer.getFPGATimestamp();
    lastError = error;
  }

  public void red(){
    setpoint = 60;
   
    

    // get sensor position
    double sensorPosition = _motor.getSelectedSensorPosition(0) * kDriveTick2Feet;

    // calculations
    double error = setpoint - sensorPosition;
    double dt = Timer.getFPGATimestamp() - lastTimestamp;

    if (Math.abs(error) < iLimit) {
      errorSum += error * dt;

    }
    double errorRate = (error - lastError) / dt;

    double outputSpeed = kP * error + kI * errorSum + kD * errorRate;

    // output to motors
    _motor.set(outputSpeed);

    // update last- variables
    lastTimestamp = Timer.getFPGATimestamp();
    lastError = error;

  }
  public void yellow(){
    setpoint = 50;
   
    

    // get sensor position
    double sensorPosition = _motor.getSelectedSensorPosition(0) * kDriveTick2Feet;

    // calculations
    double error = setpoint - sensorPosition;
    double dt = Timer.getFPGATimestamp() - lastTimestamp;

    if (Math.abs(error) < iLimit) {
      errorSum += error * dt;

    }
    double errorRate = (error - lastError) / dt;

    double outputSpeed = kP * error + kI * errorSum + kD * errorRate;

    // output to motors
    _motor.set(outputSpeed);

    // update last- variables
    lastTimestamp = Timer.getFPGATimestamp();
    lastError = error;

  }
  public void green(){
    setpoint=25;
   
    

    // get sensor position
    double sensorPosition = _motor.getSelectedSensorPosition(0) * kDriveTick2Feet;

    // calculations
    double error = setpoint - sensorPosition;
    double dt = Timer.getFPGATimestamp() - lastTimestamp;

    if (Math.abs(error) < iLimit) {
      errorSum += error * dt;

    }
    double errorRate = (error - lastError) / dt;

    double outputSpeed = kP * error + kI * errorSum + kD * errorRate;

    // output to motors
    _motor.set(outputSpeed);

    // update last- variables
    lastTimestamp = Timer.getFPGATimestamp();
    lastError = error;

  }
public void setTo0(){
  setpoint=0;
   


  // output to motors
  _motor.set(0);

  // update last- variables

}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
