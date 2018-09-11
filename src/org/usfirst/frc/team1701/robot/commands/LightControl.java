package org.usfirst.frc.team1701.robot.commands;

import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LightControl extends Command {

	public LightControl() {

		requires(Robot.lights);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {

		boolean aimed = false;
		if (RobotMap.goalFound) {
			if (RobotMap.goalX > -0.06) {
				if (RobotMap.goalX < 0.16) {
							aimed = true;
				}
			}
		}

		SmartDashboard.putBoolean("Locked On", aimed);

		if (RobotMap.allLightsOn) {
			RobotMap.lightsDisplay.set(Relay.Value.kOn);
			RobotMap.lightsCameraLight.set(Relay.Value.kOn);
			return;// override the remaining
		}
		// TODO Auto-generated method stub
		if (RobotMap.pickupShooterBallSensor.get()) {
			switch (RobotMap.lightsDisplay.get()) {
			case kOff:
				RobotMap.lightsDisplay.set(Relay.Value.kForward);
				break;
			case kForward:
				break;
			case kReverse:
				RobotMap.lightsDisplay.set(Relay.Value.kOn);
				break;
			case kOn:
				break;
			}
		} else {
			switch (RobotMap.lightsDisplay.get()) {
			case kOff:
				break;
			case kForward:
				RobotMap.lightsDisplay.set(Relay.Value.kOff);
				break;
			case kReverse:
				break;
			case kOn:
				RobotMap.lightsDisplay.set(Relay.Value.kReverse);
				break;
			}
		}
		if (aimed) {
			switch (RobotMap.lightsCameraLight.get()) {
			case kOff:
				RobotMap.lightsCameraLight.set(Relay.Value.kReverse);
				break;
			case kForward:
				RobotMap.lightsCameraLight.set(Relay.Value.kOn);
				break;
			case kReverse:
				break;
			case kOn:
				break;
			}

		} else {
			switch (RobotMap.lightsCameraLight.get()) {
			case kOff:
				break;
			case kForward:
				break;
			case kReverse:
				RobotMap.lightsCameraLight.set(Relay.Value.kOff);
				break;
			case kOn:
				RobotMap.lightsCameraLight.set(Relay.Value.kForward);
				break;
			}
		}

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
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
