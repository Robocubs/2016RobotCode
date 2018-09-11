package org.usfirst.frc.team1701.robot.commands;

import org.usfirst.frc.team1701.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleShooterLight extends Command{

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		switch(RobotMap.lightsAimLight.get()){
		case kReverse:
			RobotMap.lightsAimLight.set(Relay.Value.kOn);
			break;
		default:
			RobotMap.lightsAimLight.set(Relay.Value.kReverse);
			Timer.delay(0.1);
			RobotMap.lightsAimLight.set(Relay.Value.kOn);
			Timer.delay(0.1);
			RobotMap.lightsAimLight.set(Relay.Value.kReverse);
			Timer.delay(0.1);
			RobotMap.lightsAimLight.set(Relay.Value.kOn);
			Timer.delay(0.1);
			RobotMap.lightsAimLight.set(Relay.Value.kReverse);
			break;
		}
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
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
