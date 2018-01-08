// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1701.RobotBuild2015.commands;

import org.usfirst.frc1701.RobotBuild2015.Robot;
import org.usfirst.frc1701.RobotBuild2015.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class  AutonomousCommand extends Command {

	boolean finished = false;
	Timer timer;
	int auton = 0;
	int stage = 0;
	double timeold = 0;
	
	boolean init = false;
	
    public AutonomousCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drivetrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println ("Initializing...");
    	timer = new Timer();
    	timer.start();
    	stage = 0;
    	RobotMap.elevatorHeight = 0;
    	auton = (int)SmartDashboard.getNumber("Auton", 10);
    	System.out.println ("Read dashboard.  Auton = " + auton);
    	if(auton == 10){
    		SmartDashboard.putNumber("Auton", 10);
    	}
    	RobotMap.initialzed = false;
    	init = true;
    	System.out.println ("Initialization complete. auton = " + auton);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println ("Executing.\t" + init + "\t" + auton );
    	finished = false;
    	if(timer.get() > 15){
    		finished = true;
    	}
    	if(!RobotMap.initialzed){
			RobotMap.drivetrainSteerEncoderFL.reset();
			RobotMap.drivetrainSteerEncoderFR.reset();
			RobotMap.drivetrainSteerEncoderBL.reset();
			RobotMap.drivetrainSteerEncoderBR.reset();
			RobotMap.initialzed = true;
		}
    	SmartDashboard.putBoolean("ir thing", RobotMap.elevatorIRToteIn.get());
    	//SmartDashboard.putNumber("stage", stage);
    	
    	//if initialize didn't run, set auton to do nothing
    	if (!init)
    		auton = 10;
    	
    	switch((int)auton){
    	case 0:
    		new AutonDriveForward().start();
    		RobotMap.yawOffset = 0;
    		break;
    	case 1:
    		new AutonOneTote().start();
    		RobotMap.yawOffset = 0;
    		break;
    	case 2:
    		new AutonThreeTote().start();
    		RobotMap.yawOffset = 0;
    		break;
    	case 3:
    		new LiftBinAuton().start();
    		RobotMap.yawOffset = 0;
    		break;
    	default:
    		RobotMap.yawOffset = 0;
    		finished = true;
    		break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	stage = 0;
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}