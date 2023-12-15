
package frc.robot.commands;

import java.io.IOException;
import java.nio.file.Path;


import edu.wpi.first.math.controller.HolonomicDriveController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;


public class PathFollow extends CommandBase {
  Chassis chassis;
  PathPlannerTrajectory trajectory;

   public PathFollow(Chassis chassis, String file) {
       this.chassis = chassis;
       try {
            Path trackPath = Filesystem.getDeployDirectory().toPath().resolve(file);
           //Path trackPath = Filesystem.getDeployDirectory().toPath().resolve(file);
           trajectory = PathPlannerPath.fromPathFile(file)
           //trajectory = TrajectoryUtil.fromPathweaverJson(trackPath);
       } catch (IOException e) {
           throw new RuntimeException("Unable to load path", e);
       }

       addRequirements(chassis);
   }

   @Override
   public void initialize() {
       
   }

   @Override
   public void execute() {
        
       Trajectory.State state = trajectory.sample(chassis.getTime());
       
        PPSwerveControllerCommand drive = new PPSwerveControllerCommand(trajectory, chassis::getPose,
         new PIDController(0.5, 0, 0), new PIDController(0.5, 0, 0), new PIDController(0.5, 0, 0), chassis::setVelocity, chassis);
      
       //HolonomicDriveController drive = new HolonomicDriveController(new PIDController(0.5, 0, 0), new PIDController(0.5, 0, 0), new TrapezoidProfile.Constraints(6.28, 3.14));
       //ChassisSpeeds speeds = new ChassisSpeeds(xVel, yVel, rot);
       /*chassis.setWheelAngles(state.poseMeters.getRotation().getDegrees());
       chassis.setVelocity();*/
       

   }

   @Override
   public void end(boolean interrupted) {
       chassis.stop();
   }

   @Override
   public boolean isFinished() {
       return chassis.getTime() >= trajectory.getTotalTimeSeconds();
   }
}