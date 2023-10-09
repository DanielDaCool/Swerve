
package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Chassis;

public class Route extends SequentialCommandGroup {
  Chassis chassis;
  //double aX, aY, bX, bY, cX, cY, dX, dY, eX, eY;
  //Rotation2d aR, bR, cR, dR, eR;
  
  public Route(Chassis chassis, Pose2d[] positions) {
    Command command = new InstantCommand();
    for (int i = 0; i < positions.length; i++) {
      System.out.println("X " +  positions[i].getX());
      System.out.println("Y " + positions[i].getY());
      System.out.println("angle " + positions[i].getRotation());
      command = command.andThen(new DriveToPoint(chassis, positions[i].getX(), positions[i].getY(), positions[i].getRotation()));
    }


    addCommands(command);
  }


}
