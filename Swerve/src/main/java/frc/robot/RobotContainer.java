
package frc.robot;

import static frc.robot.Constants.*;

import frc.robot.commands.Drive;
import frc.robot.commands.DriveToPoint;
import frc.robot.commands.Route;
import frc.robot.commands.Test;
import frc.robot.subsystems.Chassis;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


public class RobotContainer {
  CommandXboxController driverController = new CommandXboxController(DriverControllerPort);
  Chassis chassis = new Chassis();
  Test test = new Test(chassis);
  Drive drive = new Drive(chassis, driverController);
  Pose2d pointA = new Pose2d(5, 8, Rotation2d.fromDegrees(30));
  Pose2d pointB = new Pose2d(3, 4, Rotation2d.fromDegrees(85));
  Pose2d pointC = new Pose2d(9, 2, Rotation2d.fromDegrees(40));
  Pose2d pointD = new Pose2d(2, 5, Rotation2d.fromDegrees(94));
  Pose2d pointE = new Pose2d(5, 8, Rotation2d.fromDegrees(320));

  Pose2d[] positions = new Pose2d[]{pointA, pointB, pointC, pointD, pointE};
  Route route = new Route(chassis, positions);



  



  


  public RobotContainer() {
    chassis.setDefaultCommand(drive);
    SmartDashboard.putData(drive);
    
    

    configureBindings();
  }


  private void configureBindings() {
    driverController.a().onTrue(route);
    




  }


  public Command getAutonomousCommand() {
    return route;
  }
}
