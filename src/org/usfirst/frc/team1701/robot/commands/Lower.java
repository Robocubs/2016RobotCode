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

import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Lower extends Command {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public Lower() {

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.pickup);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	boolean finished;
	Timer t;

	// Called just before this Command runs the first time
	protected void initialize() {
		finished = false;
		t = new Timer();
		t.start();
		if((Math.abs(RobotMap.pickupArmAngleEncoderRight.get())+Math.abs(RobotMap.pickupArmAngleEncoderLeft.get()))/2 >= 125){
			finished = true;
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		if (t.get() >= 1)
//			finished = true;
//		else 
		if((Math.abs(RobotMap.pickupArmAngleEncoderRight.get())+Math.abs(RobotMap.pickupArmAngleEncoderLeft.get()))/2 >= 125){
			finished = true;
		}
		if (!Robot.oi.Lower.get())
			finished = true;
		RobotMap.pickupRightArmMotor.set(-0.65);
		RobotMap.pickupLeftArmMotor.set(0.65);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return finished;
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.pickupRightArmMotor.set(0);
		RobotMap.pickupLeftArmMotor.set(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		finished = true;
	}
}