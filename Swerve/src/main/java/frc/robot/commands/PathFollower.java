
package frc.robot.commands;




import frc.robot.subsystems.Chassis;

import com.pathplanner.lib.commands.FollowPathHolonomic;
import com.pathplanner.lib.commands.FollowPathWithEvents;

import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;

import edu.wpi.first.wpilibj2.command.Command;



public class PathFollower {
    private static Chassis thisChassis;
    private static ReplanningConfig replanningConfig = new ReplanningConfig();
    private static HolonomicPathFollowerConfig config = new HolonomicPathFollowerConfig(new PIDConstants(0.5), new PIDConstants(180), 4, 1, replanningConfig);

    public static Command test(Chassis chassis, String file){
        thisChassis = chassis;
        PathPlannerPath path = PathPlannerPath.fromPathFile(file);
        return new FollowPathHolonomic(path, thisChassis::getPose, thisChassis::getSpeeds, thisChassis::setVelocity, config, thisChassis);
    }
}
