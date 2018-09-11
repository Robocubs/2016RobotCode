package org.usfirst.frc.team1701.robot.commands;

import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleLights extends Command{

	
	public ToggleLights() {
        requires(Robot.lights);
	}
	
	
	int state = 3;
	@Override
	protected void initialize() {
		state++;
		if(state > 3)
			state = 0;
		switch(state){
		case 0:
			RobotMap.lightsDisplay.set(Relay.Value.kOff);
			break;
		case 1:
			RobotMap.lightsDisplay.set(Relay.Value.kForward);
			break;
		case 2:
			RobotMap.lightsDisplay.set(Relay.Value.kReverse);
			break;
		case 3:
			RobotMap.lightsDisplay.set(Relay.Value.kOn);
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
