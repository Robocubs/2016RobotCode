// RobotBuilder Version: 2.0
package org.usfirst.frc.team1701.robot.commands;

import org.usfirst.frc.team1701.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Fire extends CommandGroup {

	boolean finished = false;
	
	public Fire() {
		addSequential(new AimAndRamp());
		addSequential(new BallDrop());
		addSequential(new ShooterIdle());
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		finished = false;
		RobotMap.vision.putBoolean("Shooting", true);
		if(!RobotMap.goalFound || !RobotMap.pickupShooterBallSensor.get()){
			finished = true;
		}
	}
	
	@Override
	protected void end(){
		super.end();
		RobotMap.vision.putBoolean("Shooting", false);
	}
	
	@Override
	protected boolean isFinished() {
		return super.isFinished() || finished;
	}
}
