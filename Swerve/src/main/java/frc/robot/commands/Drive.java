
package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class Drive extends CommandBase {
  Chassis chassis;
  XboxController controller;
  double x;
  double y;
  double rotation;
  

  public Drive(Chassis chassis, XboxController controller) {
    this.chassis = chassis;
    this.controller = controller;
  }


  @Override
  public void initialize() {}

  @Override
  public void execute() {
    x = controller.getRawAxis(0);
    y = controller.getRawAxis(1);
    rotation = controller.getRightX();
    ChassisSpeeds speeds = new ChassisSpeeds(x, y, rotation);
    chassis.setVelocity(speeds);
  }


  @Override
  public void end(boolean interrupted) {}


  @Override
  public boolean isFinished() {
    return false;
  }
}
