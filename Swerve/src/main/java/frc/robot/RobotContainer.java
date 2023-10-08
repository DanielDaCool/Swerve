
package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Drive;
import frc.robot.commands.Test;
import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {
  XboxController controller = new XboxController(OperatorConstants.kDriverControllerPort);
  Chassis chassis = new Chassis();
  Test test = new Test(chassis);
  Drive drive = new Drive(chassis, controller);



  private final CommandXboxController driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);


  public RobotContainer() {
    chassis.setDefaultCommand(drive);
    

    configureBindings();
  }


  private void configureBindings() {

    




  }


  public Command getAutonomousCommand() {
    return test;
  }
}
