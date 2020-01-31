/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SetMotorToColor extends SubsystemBase {
  /**
   * Creates a new SetMotorToColor.
   */
  public static WPI_TalonSRX _motor = new WPI_TalonSRX(2);
  public static WPI_TalonSRX _motor2 = new WPI_TalonSRX(3);

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

  private final ColorMatch colorMatcher = new ColorMatch();
  Color detectedColor = colorSensor.getColor();
  double IR = colorSensor.getIR();

  /**
   * Note: Any example colors should be calibrated as the user needs, these
   * are here as a basic example.
   */
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  public static final double kP = 0.5;
  public static final double kI = 0.4;
  public static final double kD = 0.1;
  public static final double iLimit = 1;
  boolean stop = false;

  public static double setpoint = 0;
  public static double errorSum = 0;
  public static double lastTimestamp = 0;
  public static double lastError = 0;
  public static final double kDriveTick2Feet = 1.0 / 128 * 6 * Math.PI / 12;

  public SetMotorToColor() {

  }
  public void init(){
    // _motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    // _motor.configOpenloopRamp(0.2);
    // _motor.configClosedloopRamp(0.2);
    // // Talon setup. Setting up + and - output for max and nominal.
    // // These should never really change
    // _motor.configNominalOutputForward(0, 0);
    // _motor.configNominalOutputReverse(0, 0);
    // _motor.configPeakOutputForward(1, 0);
    // _motor.configPeakOutputReverse(-1, 0);
    // _motor.setSensorPhase(true);
    // _motor.setInverted(true);
  }

public void identify(){
  colorMatcher.setConfidenceThreshold(0.01);
  colorMatcher.addColorMatch(kBlueTarget);
  colorMatcher.addColorMatch(kGreenTarget);
  colorMatcher.addColorMatch(kRedTarget);
  colorMatcher.addColorMatch(kYellowTarget);   
  Color detectedColor = colorSensor.getColor();
  int proximity = colorSensor.getProximity();

  /**
   * Run the color match algorithm on our detected color
   */
  String colorString;
  ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

  colorMatcher.matchClosestColor(detectedColor);

  if (match.color == kBlueTarget) {
    colorString = "Blue"; 
    _motor.set(0);
    stop=true;
    
  } 
  else if(match.color!=kBlueTarget){
    _motor.set(0.75);
    if(stop){
      _motor.set(0.0);
    }

  }
  // else if (match.color == kRedTarget) {
  //   colorString = "Red";
    
  // } else if (match.color == kGreenTarget) {
  //   colorString = "Green";

  // } else if (match.color == kYellowTarget) {
  //   colorString = "Yellow";

  // } else {
  //   colorString = "Unknown";
  // }

}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
