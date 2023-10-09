
package frc.robot.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Chassis;

public class Route extends SequentialCommandGroup {
  Chassis chassis;
  double aX, aY, bX, bY, cX, cY, dX, dY, eX, eY;
  Rotation2d aR, bR, cR, dR, eR;
  public Route(Chassis chassis) {
    DriveToPoint driveToPointA = new DriveToPoint(chassis, aX, aY, aR);
    DriveToPoint driveToPointB = new DriveToPoint(chassis, bX, bY, bR);
    DriveToPoint driveToPointC = new DriveToPoint(chassis, cX, cY, cR);
    DriveToPoint driveToPointD = new DriveToPoint(chassis, dX, dY, dR);
    DriveToPoint driveToPointE = new DriveToPoint(chassis, eX, eY, eR);

    addCommands(driveToPointA, driveToPointB, driveToPointC, driveToPointD, driveToPointE);
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.addDoubleProperty("ax", () -> aX, (aX) -> this.aX = aX);
    builder.addDoubleProperty("ay", () -> aY, (aY) -> this.aY = aY);
    builder.addDoubleProperty("ar", () -> aR.getDegrees(), (aR) -> this.aR = Rotation2d.fromDegrees(aR));

    builder.addDoubleProperty("bx", () -> bX, (bX) -> this.bX = bX);
    builder.addDoubleProperty("by", () -> bY, (bY) -> this.bY = bY);
    builder.addDoubleProperty("br", () -> bR.getDegrees(), (bR) -> this.bR = Rotation2d.fromDegrees(bR));

    builder.addDoubleProperty("cx", () -> cX, (cX) -> this.cX = cX);
    builder.addDoubleProperty("cy", () -> cY, (cY) -> this.cY = cY);
    builder.addDoubleProperty("cr", () -> cR.getDegrees(), (cR) -> this.cR = Rotation2d.fromDegrees(cR));
    
    builder.addDoubleProperty("dx", () -> dX, (dX) -> this.dX = dX);
    builder.addDoubleProperty("dy", () -> dY, (dY) -> this.dY = dY);
    builder.addDoubleProperty("dr", () -> dR.getDegrees(), (dR) -> this.dR = Rotation2d.fromDegrees(dR));

    builder.addDoubleProperty("ex", () -> eX, (eX) -> this.eX = eX);
    builder.addDoubleProperty("ey", () -> eY, (eY) -> this.eY = eY);
    builder.addDoubleProperty("er", () -> eR.getDegrees(), (eR) -> this.eR = Rotation2d.fromDegrees(eR));
  }
}
