package org.usfirst.frc.team1701.robot.subsystems;

import org.usfirst.frc.team1701.robot.commands.CheckSensors;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Sensors extends Subsystem{

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new CheckSensors());
	}

}
