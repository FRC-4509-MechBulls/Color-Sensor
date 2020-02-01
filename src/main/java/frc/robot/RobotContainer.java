package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.InactiveMotorCommand;
import frc.robot.commands.TurnOffMotorCommand;
import frc.robot.subsystems.SetMotorToColor;


public class RobotContainer {

    //public XboxController1 controller1;
    XboxController controller2 = new XboxController(Constants.XBOX_CONTROLLER_2_PORT);
    XboxController controller1 = new XboxController(Constants.XBOX_CONTROLLER_1_PORT);

    SetMotorToColor setColor = new SetMotorToColor();
    public RobotContainer(){
        configureButtonBindings();

    }
  


    private void configureButtonBindings() {
        final JoystickButton colorButton = new JoystickButton(controller1, XboxController.Button.kY.value);
        
        colorButton.whenPressed(new TurnOffMotorCommand(setColor));
        colorButton.whenReleased(new InactiveMotorCommand(setColor));
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