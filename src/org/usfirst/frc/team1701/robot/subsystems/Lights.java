package org.usfirst.frc.team1701.robot.subsystems;

import org.usfirst.frc.team1701.robot.RobotMap;
import org.usfirst.frc.team1701.robot.commands.LightControl;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lights extends Subsystem {

	private final Relay display = RobotMap.lightsDisplay;
	private final Relay cameraLight = RobotMap.lightsCameraLight;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new LightControl());
	}

}
