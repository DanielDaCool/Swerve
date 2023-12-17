
package frc.robot;

import static frc.robot.Constants.*;

import com.pathplanner.lib.commands.FollowPathHolonomic;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;

import frc.robot.commands.Drive;

import frc.robot.commands.PathFollower;

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
  

  public RobotContainer() {
    
    SmartDashboard.putData(drive);

  }
  public Command getAutonomousCommand() {
    return PathFollower.test(chassis, "New Path");
  }
}
