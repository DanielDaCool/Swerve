
package frc.robot;

import static frc.robot.Constants.*;

import frc.robot.commands.Drive;
import frc.robot.commands.Test;
import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;


public class RobotContainer {
  XboxController driverController = new XboxController(DriverControllerPort);
  Chassis chassis = new Chassis();
  Test test = new Test(chassis);
  Drive drive = new Drive(chassis, driverController);



  


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
