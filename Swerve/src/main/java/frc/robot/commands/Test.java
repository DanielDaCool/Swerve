
package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;
import frc.robot.subsystems.Chassis;

public class Test extends CommandBase {
  Chassis chassis;
  double vx;
  double vy;
  double rotationSpeed;
  public Test(Chassis chassis) {
    this.chassis = chassis;
    SmartDashboard.putData(this);


  }

  @Override
  public void initialize() {}

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.addDoubleProperty("Wanted Rotation Speed", () -> rotationSpeed, (rotationSpeed) -> this.rotationSpeed = rotationSpeed);
    builder.addDoubleProperty("Wanted vy", () -> vy, (vy) -> this.vy = vy);
    builder.addDoubleProperty("Wanted vx", () -> vx, (vx) -> this.vx = vx);
  }

  @Override
  public void execute() {
    ChassisSpeeds speeds = new ChassisSpeeds(3, 0, 0);
    chassis.setVelocity(speeds);
    
  }


  @Override
  public void end(boolean interrupted) {
    chassis.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
