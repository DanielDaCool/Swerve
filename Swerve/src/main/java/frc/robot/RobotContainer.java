
package frc.robot;

import static frc.robot.Constants.*;

import frc.robot.commands.Drive;
import frc.robot.commands.DriveToPoint;
import frc.robot.commands.PathFollow;
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
  PathFollow pathFollow = new PathFollow(chassis, "deploy/pathplanner/generatedJSON/TEST.wpilib.json");





  



  


  public RobotContainer() {
    chassis.setDefaultCommand(drive);
    SmartDashboard.putData(drive);

  }





  public Command getAutonomousCommand() {
    return pathFollow;
  }
}
