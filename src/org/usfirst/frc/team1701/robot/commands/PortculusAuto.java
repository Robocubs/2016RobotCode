package org.usfirst.frc.team1701.robot.commands;

import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class PortculusAuto extends Command{

	Timer t;
	boolean finished;
	boolean drive;
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		t = new Timer();
		finished = false;
		drive = false;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if(!drive){
			RobotMap.pickupRightArmMotor.set(-0.65);
			RobotMap.pickupLeftArmMotor.set(0.65);
		}
		if(!drive&&(Math.abs(RobotMap.pickupArmAngleEncoderRight.get())+Math.abs(RobotMap.pickupArmAngleEncoderLeft.get()))/2 >= 105){
			drive = true;
			RobotMap.pickupRightArmMotor.set(0);
			RobotMap.pickupLeftArmMotor.set(0);
			t.start();
		}
		if(drive){
			System.out.println("Driving, Time is " + t.get());
			if(t.get() < 3)
				RobotMap.drivetrainRDrive.arcadeDrive(0, 0.6);
			else if (t.get() < 6.5){
				RobotMap.drivetrainRDrive.arcadeDrive(0, 0.7);
				if (!RobotMap.pickupTopLimitSwitch.get()){
					RobotMap.pickupRightArmMotor.set(0);
					RobotMap.pickupLeftArmMotor.set(0);
					RobotMap.pickupArmAngleEncoderRight.reset();
					RobotMap.pickupArmAngleEncoderLeft.reset();
				}else{
					RobotMap.pickupRightArmMotor.set(0.65);
					RobotMap.pickupLeftArmMotor.set(-0.65);
				}	
			}else{
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
