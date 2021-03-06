/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.TurnOffMotorCommand;
import frc.robot.subsystems.SetMotorToColor;
import edu.wpi.first.wpilibj.DriverStation;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  SetMotorToColor controlPanelSubsystem = new SetMotorToColor();

  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  /**
   * A Rev Color Sensor V3 object is constructed with an I2C port as a parameter.
   * The device will be automatically initialized with default parameters.
   */
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.531, 0.343, 0.14);
  private final Color kYellowTarget = ColorMatch.makeColor(0.31597, 0.57, 0.11425);
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  public static RobotContainer oi;

  /**
   * A Rev Color Match object is used to register and detect known colors. This
   * can be calibrated ahead of time or during operation.
   * 
   * This object uses a simple euclidian distance to estimate the closest match
   * with given confidence range.
   */

  @Override
  public void robotInit() {
    Robot.oi = new RobotContainer();
    controlPanelSubsystem.colorMatcher.setConfidenceThreshold(1.0);
    controlPanelSubsystem.colorMatcher.addColorMatch(kBlueTarget);
    controlPanelSubsystem.colorMatcher.addColorMatch(kGreenTarget);
    controlPanelSubsystem.colorMatcher.addColorMatch(kRedTarget);
    controlPanelSubsystem.colorMatcher.addColorMatch(kYellowTarget);
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // SmartDashboard.putNumber("encoder value",
    // controlPanelSubsystem._motor.getSelectedSensorPosition(0) *
    // controlPanelSubsystem.kDriveTick2Feet);

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString line to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure below with additional strings. If using the SendableChooser
   * make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // controlPanelSubsystem.encoder();
    switch (m_autoSelected) {
    case kCustomAuto:
      // Put custom auto code here
      break;
    case kDefaultAuto:
    default:
      // Put default auto code here
      break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */

  @Override
  public void teleopPeriodic() {
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if (gameData.length() > 0) {
      switch (gameData.charAt(0)) {
      case 'B':
        controlPanelSubsystem.targetColor = kBlueTarget;
        SmartDashboard.putString("Blue", "B");
        break;
      case 'G':
        controlPanelSubsystem.targetColor = kGreenTarget;

        // Green case code
        SmartDashboard.putString("Green", "G");

        break;
      case 'R':
        controlPanelSubsystem.targetColor = kRedTarget;

        SmartDashboard.putString("Red", "R");

        break;
      case 'Y':
        controlPanelSubsystem.targetColor = kYellowTarget;

        SmartDashboard.putString("Yellow", "Y");

        break;
      default:
        // This is corrupt data
        break;
      }
    } else {
      // Code for no data received yet
    }
    CommandScheduler.getInstance().run();

    Color detectedColor = colorSensor.getColor();
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    int proximity = colorSensor.getProximity();

    SmartDashboard.putNumber("Proximity", proximity);
    /**
     * Run the color match algorithm on our detected color
     */

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
