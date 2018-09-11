package org.usfirst.frc.team1701.robot.commands;

import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AllLights extends Command{

	
	public AllLights() {
        requires(Robot.lights);
	}
	
	
	@Override
	protected void initialize() {
		RobotMap.allLightsOn = !RobotMap.allLightsOn;
		SmartDashboard.putBoolean("All Lights On", RobotMap.allLightsOn);;
	}

	@Override
	protected void execute() {
		;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
