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

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Rotate extends Command {
	
	double rotateAmount = 0;
	
    public Rotate(double rotateAmount) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drivetrain);
        this.rotateAmount = rotateAmount;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    public boolean finished;
    double [] averages;
    protected void initialize() {
    	SmartDashboard.putBoolean("Rotate Running", true);
        //this.rotateAmount = SmartDashboard.getNumber("Rotate Level", 0);
    	finished = false;
    	SmartDashboard.putNumber("Rotate Amount", rotateAmount);
        double goal = (rotateAmount+RobotMap.ahrs.getYaw())%360;
        if(goal < -180){
        	goal+=360;
        }if(goal > 180){
        	goal-=360;
        }
    	SmartDashboard.putNumber("Setpoit", goal);
        RobotMap.turnController.setSetpoint(goal);
    	RobotMap.turnController.enable();
    	averages = new double[5];
    	for(int i = 0; i < averages.length; i++){
    		averages[i] = rotateAmount;
    	}
    	//RobotMap.ahrs.reset();
    }

    double currentRotationRate = 0;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double avgError = 0;
    	for(int i = 1; i < averages.length; i++){
    		averages[i-1] = averages[i];
    		avgError += Math.abs(averages[i-1]);
    	}
    	averages[averages.length-1] = RobotMap.turnController.getError();
	    avgError += Math.abs(averages[averages.length-1]);
	    avgError /= averages.length;
        currentRotationRate = RobotMap.rotateToAngleRate;
    	RobotMap.drivetrainRDrive.arcadeDrive(currentRotationRate*0.9, 0);
    	SmartDashboard.putNumber("Nav X Angle", RobotMap.ahrs.getYaw());
    	SmartDashboard.putNumber("Rotate Rate", RobotMap.rotateToAngleRate);
    	SmartDashboard.putNumber("Rotate Error", RobotMap.turnController.getError());
//    	if(Math.abs(RobotMap.turnController.getAvgError()) < 4){
//    		finished = true;
//    	}
    	if(RobotMap.turnController.onTarget()){
    		finished = true;
    	}
    	if(Math.abs(avgError) < 0.5){
    		finished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.drivetrainRDrive.arcadeDrive(0, 0);
    	RobotMap.turnController.disable();
    	SmartDashboard.putBoolean("Rotate Running", false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.drivetrainRDrive.arcadeDrive(0, 0);
    	RobotMap.turnController.disable();
    }
}
