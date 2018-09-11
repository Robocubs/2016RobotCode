package org.usfirst.frc.team1701.robot.commands;

import org.usfirst.frc.team1701.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ChevalAuto extends Command{

	Timer t;
	boolean finished;
	boolean lowered;
	double offset;
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		t = new Timer();
		finished = false;
		lowered = false;
		offset = 0;
		t.start();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if(t.get() < 3)
			RobotMap.drivetrainRDrive.arcadeDrive(0, 0.6);
		else if(t.get() < 3.25)
			RobotMap.drivetrainRDrive.arcadeDrive(0, -0.4);
		else if (!lowered && (Math.abs(RobotMap.pickupArmAngleEncoderRight.get())+Math.abs(RobotMap.pickupArmAngleEncoderLeft.get()))/2 <= 120){
			RobotMap.pickupRightArmMotor.set(-0.65);
			RobotMap.pickupLeftArmMotor.set(0.65);	
			RobotMap.drivetrainRDrive.arcadeDrive(0, 0);
			offset = t.get();
		}else if(t.get() < offset + 0.3){
			lowered = true;
			RobotMap.pickupRightArmMotor.set(0);
			RobotMap.pickupLeftArmMotor.set(0);	
			RobotMap.drivetrainRDrive.arcadeDrive(0, 0.7);
		}else if(t.get() < offset + 2.5){
			if (!RobotMap.pickupTopLimitSwitch.get()){
				RobotMap.pickupRightArmMotor.set(0);
				RobotMap.pickupLeftArmMotor.set(0);
				RobotMap.pickupArmAngleEncoderRight.reset();
				RobotMap.pickupArmAngleEncoderLeft.reset();
			}else{
				RobotMap.pickupRightArmMotor.set(0.65);
				RobotMap.pickupLeftArmMotor.set(-0.65);
			}	
			RobotMap.drivetrainRDrive.arcadeDrive(0, 0.75);
		}else{
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
		RobotMap.pickupRightArmMotor.set(0);
		RobotMap.pickupLeftArmMotor.set(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		finished = true;
	}

}
