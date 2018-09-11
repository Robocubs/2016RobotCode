// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc.team1701.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public AutonomousCommand() {
		addParallel(new ShooterIdle());
		// if(SmartDashboard.getBoolean("Delay", false))
		// Timer.delay(5);
		double choice = SmartDashboard.getNumber("Auton", 0);
		boolean shoot = SmartDashboard.getBoolean("Auton Shoot", false);
		int autonType = (int)SmartDashboard.getNumber("Auton Type", 0);
		switch(autonType){
		case 1:
			addSequential(new PortculusAuto());
			if (shoot) {
				addParallel(new RampShooter());
				addParallel(new LoadPosition());
				addSequential(new WaitForRecognition());
				addSequential(new Fire());
			}
			break;
		case 2:
			addSequential(new ChevalAuto());
			if (shoot) {
				addParallel(new RampShooter());
				addParallel(new LoadPosition());
				addSequential(new WaitForRecognition());
				addSequential(new Fire());
			}
			break;
		default:
			if (choice <= 0) {
				addSequential(new DriveForward(0.5));
			} else if (choice > 0) {
				addSequential(new DriveForward(choice));
				// addSequential(new DriveBack());
				if (shoot) {
					addParallel(new RampShooter());
					addParallel(new LoadPosition());
					addSequential(new WaitForRecognition());
					addSequential(new Fire());
				}
			}
		}
	}
}