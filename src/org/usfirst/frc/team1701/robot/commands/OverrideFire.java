package org.usfirst.frc.team1701.robot.commands;

import org.usfirst.frc.team1701.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OverrideFire extends CommandGroup{

	public OverrideFire(){
		addSequential(new RampShooter());
		addSequential(new LoadPosition());
		addSequential(new BallDrop());
		addSequential(new ShooterIdle());
	}
	
	@Override
	protected void initialize() {
		super.initialize();
	}
}
