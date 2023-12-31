
package frc.robot.subsystems;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.util.SwerveModule;
import frc.robot.simulation.Gyro;
import static frc.robot.Constants.SwerveChassisConstants.*;
import edu.wpi.first.wpilibj.Timer;

import java.util.Arrays;



public class Chassis extends SubsystemBase{
    private Gyro gyro;
    private SwerveModule[] modules;

    private Timer timer = new Timer();
    
    private SwerveDrivePoseEstimator poseEstimator;
    Field2d fieldPosition;

    public Chassis(){
        gyro = new Gyro();

        
        modules = new SwerveModule[]{
          new SwerveModule(), // LFront
          new SwerveModule(), // RFront
          new SwerveModule(), // LBack
          new SwerveModule() // RBack
        };


        fieldPosition = new Field2d();
        poseEstimator = new SwerveDrivePoseEstimator(KINEMATICS, getAngle(), getModulePositions(), new Pose2d());
        SmartDashboard.putData(fieldPosition);
        timer.start();

    }

    public void stop() {
        for (SwerveModule module : modules) module.stop();
        gyro.setVelocity(0);
    }

    public void setDriveVelocity(double velocity) {
        Arrays.stream(modules).forEach((module) -> module.setVelocity(velocity));
    }

    public void setWheelAngles(double x) {
        Arrays.stream(modules).forEach((module) -> module.setAngle(Rotation2d.fromDegrees(x)));
    }

    public void setVelocity(ChassisSpeeds speed) {
        ChassisSpeeds newSpeed = ChassisSpeeds.fromFieldRelativeSpeeds(speed.vxMetersPerSecond, speed.vyMetersPerSecond, speed.omegaRadiansPerSecond, getAngle());
        SwerveModuleState[] states = KINEMATICS.toSwerveModuleStates(newSpeed);

        for (int i = 0; i < 4; i++) modules[i].setState(states[i]);
        gyro.setVelocity(Math.toDegrees(speed.omegaRadiansPerSecond));
    }



    public Pose2d getPose(){
        return poseEstimator.getEstimatedPosition();
    }

    public Translation2d getVelocity() {
        SwerveModuleState[] states = new SwerveModuleState[4];
        for (int i = 0; i < 4; i++) {
            states[i] = new SwerveModuleState(modules[i].getVelocity(), modules[i].getAngle());
        }
        ChassisSpeeds speeds = KINEMATICS.toChassisSpeeds(states);
        return new Translation2d(speeds.vxMetersPerSecond, speeds.vyMetersPerSecond);
    }
    
    public Rotation2d getAngle() {
        return Rotation2d.fromDegrees(gyro.getAngle());
    }
    
    public ChassisSpeeds getSpeeds(){
        return KINEMATICS.toChassisSpeeds();
    }

    public SwerveModuleState[] getModuleState(){
        return Arrays.stream(modules).map(SwerveModule::getState).toArray(SwerveModuleState[]::new);

    }
    public SwerveModulePosition[] getModulePositions() {
        return Arrays.stream(modules).map(SwerveModule::getPosition).toArray(SwerveModulePosition[]::new);
    }

    public void periodic() {
        
        for (SwerveModule module : modules) module.update();
        gyro.update();

        poseEstimator.update(getAngle(), getModulePositions());
        fieldPosition.setRobotPose(poseEstimator.getEstimatedPosition());
    }

    public double getTime(){
        return timer.get();
    }



}
