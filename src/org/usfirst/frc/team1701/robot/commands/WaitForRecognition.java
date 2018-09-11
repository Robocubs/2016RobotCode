package org.usfirst.frc.team1701.robot.commands;

import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WaitForRecognition extends Command {

	WaitForRecognition() {
		requires(Robot.drivetrain);
	}

	boolean finished;
	double degrees;
	double offset;
	boolean flip;
	Timer t;

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		RobotMap.drivetrainRDrive.arcadeDrive(0, 0);
		finished = false;
		if (RobotMap.goalFound) {
			finished = true;
		}
		RobotMap.ahrs.reset();
		degrees = 20;
		flip = false;
		t = new Timer();
		t.start();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		SmartDashboard.putNumber("Auto Timer", t.get());
		double turnSpeed = SmartDashboard.getNumber("Auton Turn Speed", 0.5);
		double angle = RobotMap.ahrs.getAngle();
		if (angle > 180) {
			angle -= 360;
		}
		angle *= -1;
		double goal = (degrees) % 360;
		if (goal < -180)
			goal += 360;
		SmartDashboard.putNumber("Angle", angle);
		SmartDashboard.putNumber("Goal", goal);

		if (t.get() > 0.3) {
			if (flip) {
				RobotMap.drivetrainRDrive.arcadeDrive(-1 * turnSpeed, 0);
				if (angle < goal) {
					flip = false;
					degrees *= -1.5;
				}
			} else {
				RobotMap.drivetrainRDrive.arcadeDrive(turnSpeed, 0);
				if (angle > goal) {
					flip = true;
					degrees *= -1.5;
				}
			}
		}
		if (degrees > 120) {
			finished = true;
		}
		if (RobotMap.goalFound) {
			RobotMap.AimX = RobotMap.goalX;
			finished = true;
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		RobotMap.drivetrainRDrive.arcadeDrive(0, 0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		finished = true;
	}

}
