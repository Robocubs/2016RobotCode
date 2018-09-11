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
public class Aim extends Command {
	
	double rotateAmount = 0;
	
    public Aim() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drivetrain);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    public boolean finished;
    double [] averages;
    double oldX = 0;
    protected void initialize() {
    	finished = false;
    	SmartDashboard.putBoolean("Aiming", true);
    	double offset = SmartDashboard.getNumber("Aim Offset", 0.0);
    	if(RobotMap.AimX != 0){
        	rotateAmount = (RobotMap.AimX-0.054)*30;
            oldX = RobotMap.AimX;
        	RobotMap.AimX = 0;
    	}else{
        	rotateAmount = (RobotMap.goalX-0.054)*30;
            oldX = RobotMap.goalX;
    	}
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
    	if(RobotMap.goalFound && oldX != RobotMap.goalX){
        	double offset = SmartDashboard.getNumber("Aim Offset", 0.0);
    		rotateAmount = (RobotMap.goalX-0.054)*30;
        	SmartDashboard.putNumber("Rotate Amount", rotateAmount);
            double goal = (rotateAmount+RobotMap.ahrs.getYaw())%360;
            oldX = RobotMap.goalX;
            if(goal < -180){
            	goal+=360;
            }if(goal > 180){
            	goal-=360;
            }
        	SmartDashboard.putNumber("Setpoit", goal);
            RobotMap.turnController.setSetpoint(goal);
    	}
    	double avgError = 0;
    	for(int i = 1; i < averages.length; i++){
    		averages[i-1] = averages[i];
    		avgError += Math.abs(averages[i-1]);
    	}
    	averages[averages.length-1] = RobotMap.turnController.getError();
	    avgError += Math.abs(averages[averages.length-1]);
	    avgError /= averages.length;
        currentRotationRate = RobotMap.rotateToAngleRate;
        if(Math.abs(currentRotationRate) < 0.55){
        	currentRotationRate = currentRotationRate<0? -0.55: 0.55;
        }
        RobotMap.drivetrainRDrive.arcadeDrive(currentRotationRate, 0);
    	SmartDashboard.putNumber("Nav X Angle", RobotMap.ahrs.getYaw());
    	SmartDashboard.putNumber("Rotate Rate", RobotMap.rotateToAngleRate);
    	SmartDashboard.putNumber("Rotate Error", RobotMap.turnController.getError());
//    	if(Math.abs(RobotMap.turnController.getAvgError()) < 4){
//    		finished = true;
//    	}
//    	if(RobotMap.turnController.onTarget()){
//    		finished = true;
//    	}
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
    	SmartDashboard.putBoolean("Aiming", false);
    	RobotMap.drivetrainRDrive.arcadeDrive(0, 0);
    	RobotMap.turnController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.drivetrainRDrive.arcadeDrive(0, 0);
    	RobotMap.turnController.disable();
    	finished = true;
    }
}