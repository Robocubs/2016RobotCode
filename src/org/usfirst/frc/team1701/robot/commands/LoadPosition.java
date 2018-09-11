package org.usfirst.frc.team1701.robot.commands;

import org.usfirst.frc.team1701.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class LoadPosition extends Command {

	boolean negative;
	boolean finished = false;

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		double average = (Math.abs(RobotMap.pickupArmAngleEncoderRight.get())
				+ Math.abs(RobotMap.pickupArmAngleEncoderLeft.get())) / 2;
		finished = false;
		if (average > 45) {
			negative = true;
		} else if (average < 37) {
			negative = false;
		} else {
			finished = true;
		}
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		double average = (Math.abs(RobotMap.pickupArmAngleEncoderRight.get())
				+ Math.abs(RobotMap.pickupArmAngleEncoderLeft.get())) / 2;
		if (negative) {
			double distance = average - 40;
			if(distance < 10){
				RobotMap.pickupRightArmMotor.set(0.2);
				RobotMap.pickupLeftArmMotor.set(-0.2);
			}else{
				RobotMap.pickupRightArmMotor.set(0.4);
				RobotMap.pickupLeftArmMotor.set(-0.4);
			}
			if (average < 40) {
				finished = true;
			}
		} else {
			double distance = 32 - average;
			if(distance < 10){
				RobotMap.pickupRightArmMotor.set(-0.2);
				RobotMap.pickupLeftArmMotor.set(0.2);
			}else{
				RobotMap.pickupRightArmMotor.set(-0.4);
				RobotMap.pickupLeftArmMotor.set(0.4);
			}
			if (average > 32) {
				finished = true;
			}
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
		RobotMap.pickupRightArmMotor.set(0);
		RobotMap.pickupLeftArmMotor.set(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		finished = true;
	}

}
