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

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

  public final static ColorMatch colorMatcher = new ColorMatch();
  Color detectedColor = colorSensor.getColor();
  double IR = colorSensor.getIR();

  /**
   * Note: Any example colors should be calibrated as the user needs, these
   * are here as a basic example.
   */
  public static Color targetColor = ColorMatch.makeColor(0.0, 0.0, 0.0);


  boolean stop = false;

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
  
  Color detectedColor = colorSensor.getColor();
  int proximity = colorSensor.getProximity();

  /**
   * Run the color match algorithm on our detected color
   */
  String colorString;
  ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

  colorMatcher.matchClosestColor(detectedColor);
//for targetColor, in gamedata in setup type B, Y, R, G for the colors it needs to detect
  if (match.color == targetColor) {
    _motor.set(0);
    stop=true;
    
  } 
  else if(match.color!=targetColor){
    _motor.set(0.75);
    if(stop){
      _motor.set(0.0);
    }

  }


}
public void disable(){
  _motor.set(0);
  stop=false;
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
