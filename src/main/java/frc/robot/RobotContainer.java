package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.SetEncoderCommand;
import frc.robot.commands.TurnOffEncoderCommand;


public class RobotContainer {

    //public XboxController1 controller1;
    XboxController controller2 = new XboxController(Constants.XBOX_CONTROLLER_2_PORT);
    XboxController controller1 = new XboxController(Constants.XBOX_CONTROLLER_1_PORT);


    public RobotContainer(){
        configureButtonBindings();

    }
  


    private void configureButtonBindings() {
        final JoystickButton encoderButton = new JoystickButton(controller2, XboxController.Button.kY.value);
        
        encoderButton.whenPressed(new SetEncoderCommand());
        encoderButton.whenReleased(new TurnOffEncoderCommand());
    }
  
  
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
//     public Command getAutonomousCommand() {
//       // An ExampleCommand will run in autonomous
//      // return m_autoCommand;
//     }
 }