
package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Test;
import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {
  Chassis chassis;
  Test test = new Test(chassis);



  private final CommandXboxController driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);


  public RobotContainer() {

    configureBindings();
  }


  private void configureBindings() {



  }


  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return test;
  }
}
