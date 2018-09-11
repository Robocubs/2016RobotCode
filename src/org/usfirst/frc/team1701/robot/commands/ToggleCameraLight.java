package org.usfirst.frc.team1701.robot.commands;

import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleCameraLight extends Command{

	
	public ToggleCameraLight() {
        requires(Robot.lights);
	}
	
	
	@Override
	protected void initialize() {
		switch(RobotMap.lightsCameraLight.get()){
		case kOff:
			RobotMap.lightsCameraLight.set(Relay.Value.kForward);
			break;
		case kForward:
			RobotMap.lightsCameraLight.set(Relay.Value.kOff);
			break;
		case kReverse:
			RobotMap.lightsCameraLight.set(Relay.Value.kOn);
			break;
		case kOn:
			RobotMap.lightsCameraLight.set(Relay.Value.kReverse);
			break;
		}
		
	}

	@Override
	protected void execute() {
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
