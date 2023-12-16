
package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Chassis;
import static frc.robot.Constants.*;

public class Drive extends Command {
  Chassis chassis;
  CommandXboxController controller;
  double x;
  double y;
  double rotation;
  

  public Drive(Chassis chassis, CommandXboxController controller) {

    this.chassis = chassis;
    this.controller = controller;
    addRequirements(chassis);
  }


  @Override
  public void initialize() {}

  @Override
  public void execute() {
    x = controller.getRawAxis(0);
    y = -controller.getRawAxis(1);
    rotation = controller.getRightX();
    ChassisSpeeds speeds = new ChassisSpeeds(x * 2, y * 2, rotation * degreesPerSecond);
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
