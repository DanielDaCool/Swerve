
package frc.robot;

import static frc.robot.Constants.*;

import frc.robot.commands.Drive;
import frc.robot.commands.DriveToPoint;
import frc.robot.commands.Test;
import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


public class RobotContainer {
  CommandXboxController driverController = new CommandXboxController(DriverControllerPort);
  Chassis chassis = new Chassis();
  Test test = new Test(chassis);
  Drive drive = new Drive(chassis, driverController);
  DriveToPoint driveToPoint = new DriveToPoint(chassis);



  


  public RobotContainer() {
    chassis.setDefaultCommand(drive);
    SmartDashboard.putData(drive);
    
    

    configureBindings();
  }


  private void configureBindings() {
    driverController.a().onTrue(driveToPoint);
    




  }


  public Command getAutonomousCommand() {
    return test;
  }
}
