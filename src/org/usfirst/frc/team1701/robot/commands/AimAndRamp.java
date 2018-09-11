package org.usfirst.frc.team1701.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AimAndRamp extends CommandGroup{
	public AimAndRamp(){
		addParallel(new RampShooter());
		addParallel(new LoadPosition());
		addSequential(new Aim());
	}
}
