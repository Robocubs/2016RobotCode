package org.usfirst.frc.team1701.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDRotate implements PIDOutput {
	@Override
	/* This function is invoked periodically by the PID Controller, */
	/* based upon navX MXP yaw angle input and PID Coefficients. */
	public void pidWrite(double output) {
		RobotMap.rotateToAngleRate = output;
	}
}
