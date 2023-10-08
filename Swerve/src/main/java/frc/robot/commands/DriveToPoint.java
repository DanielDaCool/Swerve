
package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

import frc.robot.Util.Trapezoid;
//import frc.robot.Util.Trapezoid;
import frc.robot.subsystems.Chassis;

public class DriveToPoint extends CommandBase {
  Chassis chassis;
  double wantedX;
  double wantedY;
  Rotation2d wantedAngle = new Rotation2d();
  double remainingDistance;
  Pose2d pose = new Pose2d();
  double driveSpeed = 3;
  double maxVelocity = 2;
  double wantedAccel = 1;
  Translation2d translationFinal = new Translation2d();

  Trapezoid driveTrapezoid = new Trapezoid(wantedAccel, maxVelocity);
  //Trapezoid rotationTrapezoid = new Trapezoid(degreesPerSecond, 180);
  Trapezoid trap = new Trapezoid(wantedAccel, maxVelocity);


  public DriveToPoint(Chassis chassis) {
    this.chassis = chassis;
    addRequirements(chassis);
    SmartDashboard.putData(this);



  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.addDoubleProperty("wanted X", () -> wantedX , (wantedX) -> this.wantedX = wantedX);
    builder.addDoubleProperty("wanted Y", () -> wantedY , (wantedY) -> this.wantedY = wantedY);
    builder.addDoubleProperty("wanted Angle", () -> wantedAngle.getDegrees() , (wantedAngle) -> this.wantedAngle = Rotation2d.fromDegrees(wantedAngle));
    builder.addDoubleProperty("wanted Accel", () -> wantedAccel , (wantedAccel) -> this.wantedAccel = wantedAccel);
    builder.addDoubleProperty("wanted Velo", () -> maxVelocity , (maxVelocity) -> this.maxVelocity = maxVelocity);


  }

  @Override
  public void initialize() {
    translationFinal = new Translation2d(wantedX, wantedY);

  }


  @Override
  public void execute() {

    pose = chassis.getPose();
    Translation2d vector = translationFinal.minus(pose.getTranslation());
    double angleError = wantedAngle.minus(pose.getRotation()).getDegrees();
    remainingDistance = vector.getNorm();
    double velocity = driveTrapezoid.calculate(remainingDistance, chassis.getVelocity().getNorm(), 0);
    Translation2d driveAngle = vector.div(remainingDistance).times(velocity);


    ChassisSpeeds chassisSpeeds = new ChassisSpeeds(driveAngle.getX() , driveAngle.getY(), Math.toRadians(angleError * 0.03 ));
    chassis.setVelocity(chassisSpeeds);



    }


  @Override
  public void end(boolean interrupted) {
    chassis.stop();
  }


  @Override
  public boolean isFinished() {
    if (pose.getTranslation().getDistance(translationFinal) <= 0.20){
      
      if (wantedAngle.minus(pose.getRotation()).getDegrees() <= 5){
        
        return true;
      }
      return false;
    }
    return false;
    
  }
}
