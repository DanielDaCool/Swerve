
package frc.robot.commands;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class Test extends CommandBase {
  Chassis chassis;
  double vx;
  double vy;
  int i = 0;
  double rotationSpeed;
  public Test(Chassis chassis) {
    this.chassis = chassis;
    addRequirements(chassis);

    SmartDashboard.putData(this);


  }

  @Override
  public void initialize() {}


  @Override
  public void execute() {
    ChassisSpeeds speeds = new ChassisSpeeds(3, 3, 0.5);
    chassis.setVelocity(speeds);
    i++;
  }


  @Override
  public void end(boolean interrupted) {
    chassis.stop();
  }

  @Override
  public boolean isFinished() {
    if (i > 300){
      return true;
    }
    return false;
  }
}
