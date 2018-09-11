package org.usfirst.frc.team1701.robot.commands;

import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CheckSensors extends Command{

	public CheckSensors(){
		requires(Robot.sensors);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		RobotMap.goalFoundHistory.add(0, RobotMap.vision.getBoolean("Found", false));
		if(RobotMap.goalFoundHistory.size() > 10){
			RobotMap.goalFoundHistory.remove(RobotMap.goalFoundHistory.size()-1);
		}
		RobotMap.goalXHistory.add(0, RobotMap.vision.getNumber("X", 0));
		if(RobotMap.goalXHistory.size() > 10){
			RobotMap.goalXHistory.remove(RobotMap.goalXHistory.size()-1);
		}
		RobotMap.goalYHistory.add(0, RobotMap.vision.getNumber("Y", 0));
		if(RobotMap.goalYHistory.size() > 10){
			RobotMap.goalYHistory.remove(RobotMap.goalYHistory.size()-1);
		}
		boolean found = false;
		for(int i = 0; i < RobotMap.goalFoundHistory.size(); i++){
			if(RobotMap.goalFoundHistory.get(i)){
				RobotMap.goalFound=true;
				RobotMap.goalX= RobotMap.goalXHistory.get(i);
				RobotMap.goalY= RobotMap.goalYHistory.get(i);
				found = true;
				break;
			}
		}
		if(!found){
			RobotMap.goalFound=false;
			RobotMap.goalX= 0;
			RobotMap.goalY= 0;
		}
		SmartDashboard.putBoolean("Goal Found", RobotMap.goalFound);
		SmartDashboard.putNumber("Goal X", RobotMap.goalX);
		SmartDashboard.putNumber("Goal Y", RobotMap.goalY);
		SmartDashboard.putBoolean("Pickup Limit", !RobotMap.pickupTopLimitSwitch.get());
		SmartDashboard.putBoolean("Pickup Banner", RobotMap.pickupShooterBallSensor.get());
		SmartDashboard.putBoolean("Shooter Banner", RobotMap.shooterBarrelBallSensor.get());
		SmartDashboard.putNumber("Right Pickup Encoder", RobotMap.pickupArmAngleEncoderRight.get());
		SmartDashboard.putNumber("Left Pickup Encoder", RobotMap.pickupArmAngleEncoderLeft.get());
		SmartDashboard.putNumber("Shooter Encoder Rate", RobotMap.shooterEncoder.getRate());
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
