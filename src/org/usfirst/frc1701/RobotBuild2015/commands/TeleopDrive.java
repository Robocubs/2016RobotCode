// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc1701.RobotBuild2015.commands;

import org.usfirst.frc1701.RobotBuild2015.OI;
import org.usfirst.frc1701.RobotBuild2015.Robot;
import org.usfirst.frc1701.RobotBuild2015.RobotMap;
import org.usfirst.frc1701.helperfiles.Vector;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TeleopDrive extends Command {

	public static final int OFFSETSTEERENCODERFL = 90;
	public static final int OFFSETSTEERENCODERFR = 270;
	public static final int OFFSETSTEERENCODERBR = 180;
	public static final int OFFSETSTEERENCODERBL = 180;
	private final double STEERSPEEDCOEFFICIENT = 1.0/180.0;
	private Vector wheelFL, wheelFR, wheelBR, wheelBL, driveStick;
	private boolean inverseFL, inverseFR, inverseBL, inverseBR;
	private double deadzone = 0.15;
	private double driveX = 0;
	private double driveY = 0;
	private double strafeX = 0;
	private double strafeY = 0;
	private double rotateX = 0;
	private double angleFL = 0;
	private double angleFR = 0;
	private double angleBR = 0;
	private double angleBL = 0;
	private double steerSpeedFL = 0;
	private double steerSpeedFR = 0;
	private double steerSpeedBR = 0;
	private double steerSpeedBL = 0;

	public TeleopDrive() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drivetrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		wheelFL = new Vector(0, 1);
		wheelFR = new Vector(0, 1);
		wheelBR = new Vector(0, 1);
		wheelBL = new Vector(0, 1);
		driveStick = new Vector(0, 0);
		System.out.println("Teleop Started");
		if(!RobotMap.initialzed){
			RobotMap.drivetrainSteerEncoderFL.reset();
			RobotMap.drivetrainSteerEncoderFR.reset();
			RobotMap.drivetrainSteerEncoderBL.reset();
			RobotMap.drivetrainSteerEncoderBR.reset();
			RobotMap.initialzed = true;
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(!RobotMap.TeleopRunning){
			return;
		}
		System.out.println("Teleop Executing");
		// All values must be greater than deadzone or they will be set to zero
		driveX = Math.abs(Robot.oi.getDriveJoystick().getX()) < deadzone ? 0 : 1 * Robot.oi
				.getDriveJoystick().getX()/0.85;
		driveY = Math.abs(Robot.oi.getDriveJoystick().getY()) < deadzone ? 0 : -1 * Robot.oi
				.getDriveJoystick().getY()/0.85;
		rotateX = Math.abs(Robot.oi.getStick2().getX()) < 0.2 ? 0 : -2 * Robot.oi
				.getStick2().getX();
		
		strafeY = 0;
		strafeX = 0;
		
		if(Robot.oi.StrafeBack.get()){
			strafeY = -0.6;
		}
		if(Robot.oi.StrafeForward.get()){
			strafeY = 0.6;
		}
		if(Robot.oi.StrafeLeft.get()){
			strafeX = 0.6;
		}
		if(Robot.oi.StrafeRight.get()){
			strafeX = -0.6;
		}
			
		inverseFL = false;
		inverseFR = false;
		inverseBL = false;
		inverseBR = false;
		
		
		
		//SmartDashboard.putNumber("Stick Angle", driveStick.getAngleDeg());
//		if((int)driveStick.getAngleDeg() == 90 || (int)driveStick.getAngleDeg() == 270){
//			inverseFL = !inverseFL;
//			inverseFR = !inverseFR;
//			inverseBL = !inverseBL;
//			inverseBR = !inverseBR;
//		}
//		if((driveStick.getAngleDeg() > 180 && driveStick.getAngleDeg() < 270)|| (driveStick.getAngleDeg() > 0 && driveStick.getAngleDeg() < 90)){
//			inverseFL = !inverseFL;
//			inverseFR = !inverseFR;
//			inverseBL = !inverseBL;
//			inverseBR = !inverseBR;
//		}
//		if((int)driveStick.getAngleDeg() == 0 || (int)driveStick.getAngleDeg() == 180){
//			inverseFL = !inverseFL;
//			inverseFR = !inverseFR;
//			inverseBL = !inverseBL;
//			inverseBR = !inverseBR;
//		}
		if(Math.abs(strafeX) > 0 || Math.abs(strafeY) > 0){
			driveStick.setXY(strafeX, strafeY);
			driveX = strafeX;
			driveY = strafeY;
		}else{
			driveStick.setXY(driveX, driveY);
			driveStick.addAngle(RobotMap.imu.getYaw());
		}
		
		SmartDashboard.putNumber("driveX", driveX);
		SmartDashboard.putNumber("driveY", driveY);
		SmartDashboard.putNumber("rotateX", rotateX);
		
		if (rotateX == 0) {
			wheelFL.setXY(0, 0);
			wheelFR.setXY(0, 0);
			wheelBR.setXY(0, 0);
			wheelBL.setXY(0, 0);
			wheelFL.add(driveStick);
			wheelFR.add(driveStick);
			wheelBR.add(driveStick);
			wheelBL.add(driveStick);
		} else {
			wheelFL.setXY(rotateX, -rotateX);
			wheelFR.setXY(rotateX, rotateX);
			wheelBR.setXY(-rotateX, rotateX);
			wheelBL.setXY(-rotateX, -rotateX);
			wheelFL.add(driveStick);
			wheelFR.add(driveStick);
			wheelBR.add(driveStick);
			wheelBL.add(driveStick);
		}
		// set drive motor speed
		SmartDashboard.putNumber("DriveFL Speed", RobotMap.drivetrainDriveMotorFL.get());
		SmartDashboard.putNumber("DriveFR Speed", RobotMap.drivetrainDriveMotorFR.get());
		SmartDashboard.putNumber("DriveBR Speed", RobotMap.drivetrainDriveMotorBR.get());
		SmartDashboard.putNumber("DriveBL Speed", RobotMap.drivetrainDriveMotorBL.get());
		// set steer motors
		angleFL = (RobotMap.drivetrainSteerEncoderFL.getDistance()/1.153 - OFFSETSTEERENCODERFL) % 360.0;
		angleFR = (RobotMap.drivetrainSteerEncoderFR.getDistance()/1.153 - OFFSETSTEERENCODERFR) % 360.0;
		angleBR = (RobotMap.drivetrainSteerEncoderBR.getDistance()/1.153 - OFFSETSTEERENCODERBR) % 360.0;
		angleBL = (RobotMap.drivetrainSteerEncoderBL.getDistance()/1.153 - OFFSETSTEERENCODERBL) % 360.0;
		
		if(angleFL < 0){
			angleFL = angleFL+360;
		}
		if(angleFR < 0){
			angleFR = angleFR+360;
		}
		if(angleBL < 0){
			angleBL = angleBL+360;
		}
		if(angleBR < 0){
			angleBR = angleBR+360;
		}
		
//		if(wheelFL.getAngleDeg()%180 == wheelFL.getAngleDeg())
//			inverseFL = !inverseFL;
//		if(wheelFR.getAngleDeg()%180 == wheelFR.getAngleDeg())
//			inverseFR = !inverseFR;
//		if(wheelBL.getAngleDeg()%180 == wheelBL.getAngleDeg())
//			inverseBL = !inverseBL;
//		if(wheelBR.getAngleDeg()%180 == wheelBR.getAngleDeg())
//			inverseBR = !inverseBR;
//		
//		if((int)wheelFL.getAngleDeg()%180 == 0)
//			inverseFL = !inverseFL;
//		if((int)wheelFR.getAngleDeg()%180 == 0)
//			inverseFR = !inverseFR;
//		if((int)wheelBL.getAngleDeg()%180 == 0)
//			inverseBL = !inverseBL;
//		if((int)wheelBR.getAngleDeg()%180 == 0)
//			inverseBR = !inverseBR;
//		
//		if((int)wheelFL.getAngleDeg()%90 != 0)
//			inverseFL = !inverseFL;
//		if((int)wheelFR.getAngleDeg()%90 != 0)
//			inverseFR = !inverseFR;
//		if((int)wheelBL.getAngleDeg()%90 != 0)
//			inverseBL = !inverseBL;
//		if((int)wheelBR.getAngleDeg()%90 != 0)
//			inverseBR = !inverseBR;
//		
//		if((int)wheelFL.getAngleDeg()%180 == 90)
//			inverseFL = !inverseFL;
//		if((int)wheelFR.getAngleDeg()%180 == 90)
//			inverseFR = !inverseFR;
//		if((int)wheelBL.getAngleDeg()%180 == 90)
//			inverseBL = !inverseBL;
//		if((int)wheelBR.getAngleDeg()%180 == 90)
//			inverseBR = !inverseBR;
		
		
//		if(Math.abs(wheelFL.getAngleDeg()%180 - angleFL) > Math.abs(wheelFL.getAngleDeg()%180 - 360)- angleFL){
//			steerSpeedFL = -STEERSPEEDCOEFFICIENT
//					* ((wheelFL.getAngleDeg()%180 - 360)- angleFL);
//		}else{
//			steerSpeedFL = -STEERSPEEDCOEFFICIENT
//					* (wheelFL.getAngleDeg()%180 - angleFL);
//		}
//		if(Math.abs(wheelFR.getAngleDeg()%180 - angleFR) > Math.abs(wheelFR.getAngleDeg()%180 - 360)- angleFR){
//			steerSpeedFR = -STEERSPEEDCOEFFICIENT
//					* ((wheelFR.getAngleDeg()%180 - 360)- angleFR);
//		}else{
//			steerSpeedFR = -STEERSPEEDCOEFFICIENT
//					* (wheelFR.getAngleDeg()%180 - angleFR);
//		}
//		if(Math.abs(wheelBR.getAngleDeg()%180 - angleBR) > Math.abs(wheelBR.getAngleDeg()%180 - 360)- angleBR){
//			steerSpeedBR = -STEERSPEEDCOEFFICIENT
//					* ((wheelBR.getAngleDeg()%180 - 360)- angleBR);
//		}else{
//			steerSpeedBR = -STEERSPEEDCOEFFICIENT
//					* (wheelBR.getAngleDeg()%180 - angleBR);
//		}
//		if(Math.abs(wheelBL.getAngleDeg()%180 - angleBL) > Math.abs(wheelBL.getAngleDeg()%180 - 360)- angleBL){
//			steerSpeedBL = -STEERSPEEDCOEFFICIENT
//					* ((wheelBL.getAngleDeg()%180 - 360)- angleBL);
//		}else{
//			steerSpeedBL = -STEERSPEEDCOEFFICIENT
//					* (wheelBL.getAngleDeg()%180 - angleBL);
//		}
		
		double distanceFL = wheelFL.getAngleDeg() - angleFL;
		distanceFL = (distanceFL + 180) % 360 - 180;
		double compdistanceFL = (wheelFL.getAngleDeg()+180)%360 - angleFL;
		compdistanceFL = (compdistanceFL + 180) % 360 - 180;
		if(Math.abs(distanceFL) < Math.abs(compdistanceFL)){
			steerSpeedFL = 1*-STEERSPEEDCOEFFICIENT*distanceFL;
		}else{
			steerSpeedFL = 1*-STEERSPEEDCOEFFICIENT*compdistanceFL;
			inverseFL = true;
		}
		
		double distanceFR = wheelFR.getAngleDeg() - angleFR;
		distanceFR = (distanceFR + 180) % 360 - 180;
		double compdistanceFR = (wheelFR.getAngleDeg()+180)%360 - angleFR;
		compdistanceFR = (compdistanceFR + 180) % 360 - 180;
		if(Math.abs(distanceFR) < Math.abs(compdistanceFR)){
			steerSpeedFR = 1*-STEERSPEEDCOEFFICIENT*distanceFR;
		}else{
			steerSpeedFR = 1*-STEERSPEEDCOEFFICIENT*compdistanceFR;
			inverseFR = true;
		}
		
		double distanceBL = wheelBL.getAngleDeg() - angleBL;
		distanceBL = (distanceBL + 180) % 360 - 180;
		double compdistanceBL = (wheelBL.getAngleDeg()+180)%360 - angleBL;
		compdistanceBL = (compdistanceBL + 180) % 360 - 180;
		if(Math.abs(distanceBL) < Math.abs(compdistanceBL)){
			steerSpeedBL = 1*-STEERSPEEDCOEFFICIENT*distanceBL;
		}else{
			steerSpeedBL = 1*-STEERSPEEDCOEFFICIENT*compdistanceBL;
			inverseBL = true;
		}
		
		double distanceBR = wheelBR.getAngleDeg() - angleBR;
		distanceBR = (distanceBR + 180) % 360 - 180;
		double compdistanceBR = (wheelBR.getAngleDeg()+180)%360 - angleBR;
		compdistanceBR = (compdistanceBR + 180) % 360 - 180;
		if(Math.abs(distanceBR) < Math.abs(compdistanceBR)){
			steerSpeedBR = 1*-STEERSPEEDCOEFFICIENT*distanceBR;
		}else{
			steerSpeedBR = 1*-STEERSPEEDCOEFFICIENT*compdistanceBR;
			inverseBR = true;
		}
		
//		if(angleFL < wheelFL.getAngleDeg()) {
//		    if(Math.abs(angleFL - wheelFL.getAngleDeg())<180)
//		    	steerSpeedFL = 1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleFL - wheelFL.getAngleDeg()));
//		    else steerSpeedFL = -1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleFL - wheelFL.getAngleDeg()-360));
//		}
//
//		else {
//		    if(Math.abs(angleFL - wheelFL.getAngleDeg())<180)
//		    	steerSpeedFL = -1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleFL - wheelFL.getAngleDeg()));
//		    else steerSpeedFL= 1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleFL - wheelFL.getAngleDeg()-360));
//		}
//		
//		if(angleFR < wheelFR.getAngleDeg()) {
//		    if(Math.abs(angleFR - wheelFR.getAngleDeg())<180)
//		    	steerSpeedFR = 1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleFR - wheelFR.getAngleDeg()));
//		    else steerSpeedFR = -1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleFR - wheelFR.getAngleDeg()-360));
//		}
//
//		else {
//		    if(Math.abs(angleFR - wheelFR.getAngleDeg())<180)
//		    	steerSpeedFR = -1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleFR - wheelFR.getAngleDeg()));
//		    else steerSpeedFR= 1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleFR - wheelFR.getAngleDeg()-360));
//		}
//		
//		if(angleBL < wheelBL.getAngleDeg()) {
//		    if(Math.abs(angleBL - wheelBL.getAngleDeg())<180)
//		    	steerSpeedBL = 1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleBL - wheelBL.getAngleDeg()));
//		    else steerSpeedBL = -1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleBL - wheelBL.getAngleDeg()-360));
//		}
//
//		else {
//		    if(Math.abs(angleBL - wheelBL.getAngleDeg())<180)
//		    	steerSpeedBL = -1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleBL - wheelBL.getAngleDeg()));
//		    else steerSpeedBL= 1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleBL - wheelBL.getAngleDeg()-360));
//		}
//		
//		if(angleBR < wheelBR.getAngleDeg()) {
//		    if(Math.abs(angleBR - wheelBR.getAngleDeg())<180)
//		    	steerSpeedBR = 1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleBR - wheelBR.getAngleDeg()));
//		    else steerSpeedBR = -1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleBR - wheelBR.getAngleDeg()-360));
//		}
//
//		else {
//		    if(Math.abs(angleBR - wheelBR.getAngleDeg())<180)
//		    	steerSpeedBR = -1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleBR - wheelBR.getAngleDeg()));
//		    else steerSpeedBR = 1*-STEERSPEEDCOEFFICIENT*(Math.abs(angleBR - wheelBR.getAngleDeg()-360));
//		}
		
		if(Math.abs(steerSpeedFL) < 0.01){
			steerSpeedFL = 0;
		}else if(Math.abs(steerSpeedFL) < 0.1){
			steerSpeedFL = steerSpeedFL*0.15/(Math.abs(steerSpeedFL));
		}else{
			steerSpeedFL = steerSpeedFL*1.0/(Math.abs(steerSpeedFL));
		}
		
		if(Math.abs(steerSpeedFR) < 0.01){
			steerSpeedFR = 0;
		}else if(Math.abs(steerSpeedFR) < 0.1){
			steerSpeedFR = steerSpeedFR*0.15/(Math.abs(steerSpeedFR));
		}else{
			steerSpeedFR = steerSpeedFR*1.0/(Math.abs(steerSpeedFR));
		}
		
		if(Math.abs(steerSpeedBR) < 0.01){
			steerSpeedBR = 0;
		}else if(Math.abs(steerSpeedBR) < 0.1){
			steerSpeedBR = steerSpeedBR*0.15/(Math.abs(steerSpeedBR));
		}else{
			steerSpeedBR = steerSpeedBR*1.0/(Math.abs(steerSpeedBR));
		}
		
		if(Math.abs(steerSpeedBL) < 0.01){
			steerSpeedBL = 0;
		}else if(Math.abs(steerSpeedBL) < 0.1){
			steerSpeedBL = steerSpeedBL*0.15/(Math.abs(steerSpeedBL));
		}else{
			steerSpeedBL = steerSpeedBL*1.0/(Math.abs(steerSpeedBL));
		}
		
		SmartDashboard.putNumber("FL Angle", angleFL);
		SmartDashboard.putNumber("FR Angle", angleFR);
		SmartDashboard.putNumber("BR Angle", angleBR);
		SmartDashboard.putNumber("BL Angle", angleBL);
		
		SmartDashboard.putNumber("FL Wanted Angle", wheelFL.getAngleDeg());
		SmartDashboard.putNumber("FR Wanted Angle", wheelFR.getAngleDeg());
		SmartDashboard.putNumber("BR Wanted Angle", wheelBR.getAngleDeg());
		SmartDashboard.putNumber("BL Wanted Angle", wheelBL.getAngleDeg());

		if(driveX != 0 || driveY != 0 || rotateX != 0){
		RobotMap.drivetrainSteerMotorFL.set(steerSpeedFL > 1 ? 1
				: steerSpeedFL < -1 ? -1 : steerSpeedFL);
		RobotMap.drivetrainSteerMotorFR.set(steerSpeedFR > 1 ? 1
				: steerSpeedFR < -1 ? -1 : steerSpeedFR);
		RobotMap.drivetrainSteerMotorBR.set(steerSpeedBR > 1 ? 1
				: steerSpeedBR < -1 ? -1 : steerSpeedBR);
		RobotMap.drivetrainSteerMotorBL.set(steerSpeedBL > 1 ? 1
				: steerSpeedBL < -1 ? -1 : steerSpeedBL);
		}else{
			RobotMap.drivetrainSteerMotorFL.set(0);
			RobotMap.drivetrainSteerMotorFR.set(0);
			RobotMap.drivetrainSteerMotorBR.set(0);
			RobotMap.drivetrainSteerMotorBL.set(0);
		}
		
		SmartDashboard.putNumber("SteerFL Speed", steerSpeedFL);
		SmartDashboard.putNumber("SteerFR Speed", steerSpeedFR);
		SmartDashboard.putNumber("SteerBR Speed", steerSpeedBR);
		SmartDashboard.putNumber("SteerBL Speed", steerSpeedBL);
		
		if(Math.abs(steerSpeedFL) + Math.abs(steerSpeedFR) + Math.abs(steerSpeedBR) + Math.abs(steerSpeedBL) <= 0.6){
			double speedFL = Math.abs(wheelFL.getH());
			if(speedFL < -1)
				speedFL = -1;
			if(speedFL > 1)
				speedFL = 1;
			
			double speedFR = Math.abs(wheelFR.getH());
			if(speedFR < -1)
				speedFR = -1;
			if(speedFR > 1)
				speedFR = 1;
			
			double speedBR = Math.abs(wheelBR.getH());
			if(speedBR < -1)
				speedBR = -1;
			if(speedBR > 1)
				speedBR = 1;
			
			double speedBL = Math.abs(wheelBL.getH());
			if(speedBL < -1)
				speedBL = -1;
			if(speedBL > 1)
				speedBL = 1;
			
			RobotMap.drivetrainDriveMotorFL.set(inverseFL ? speedFL* RobotMap.speedCoefficient : -speedFL* RobotMap.speedCoefficient);
			RobotMap.drivetrainDriveMotorFR.set(inverseFR ? -speedFR* RobotMap.speedCoefficient : speedFR* RobotMap.speedCoefficient);
			RobotMap.drivetrainDriveMotorBR.set(inverseBR ? -speedBR* RobotMap.speedCoefficient : speedBR* RobotMap.speedCoefficient);
			RobotMap.drivetrainDriveMotorBL.set(inverseBL ? speedBL* RobotMap.speedCoefficient : -speedBL* RobotMap.speedCoefficient);
			}else{
				RobotMap.drivetrainDriveMotorFL.set(0);
				RobotMap.drivetrainDriveMotorFR.set(0);
				RobotMap.drivetrainDriveMotorBR.set(0);
				RobotMap.drivetrainDriveMotorBL.set(0);
			}
		
		SmartDashboard.putNumber("Speed Co", RobotMap.speedCoefficient);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
