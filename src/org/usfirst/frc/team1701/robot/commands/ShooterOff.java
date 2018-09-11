package org.usfirst.frc.team1701.robot.commands;

import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterOff extends Command{

	
	public ShooterOff() {
        requires(Robot.shooter);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		RobotMap.shooterShooterMotor.set(0);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		RobotMap.shooterShooterMotor.set(0);
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		RobotMap.shooterShooterMotor.set(0);
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
