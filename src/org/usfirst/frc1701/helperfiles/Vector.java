/*
 * Vector for 2d motion
 * Has both X and Y component;
 * */
package org.usfirst.frc1701.helperfiles;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vector {
	private double x, y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getH() {
		if(x*y > 0)
			return Math.sqrt(x * x + y * y);
		else
			return -1 *Math.sqrt(x * x + y * y);
	}

	public double getAngleDeg() {
		if(x != 0 || y != 0)
			if (Math.abs(x) > Math.abs(y)) {
				if(x > 0)
					return (Math.atan(y / x) * 180 / Math.PI + 90)%360;
				else
					return (Math.atan(y / x) * 180 / Math.PI + 270)%360;
			} else {
				if(y > 0)
					return (-Math.atan(x / y) * 180 / Math.PI + 180)%360;
				else
					return (-Math.atan(x / y) * 180 / Math.PI + 360)%360;
			}
		else{
			return 0.0;
		}
	}
	
	public void addAngle(double angle){
		SmartDashboard.putNumber("Xo", x);
		SmartDashboard.putNumber("Yo", y);
		double tempAngle = getAngleDeg() + angle -180;
		tempAngle  %= 360;
		if(tempAngle < 0)
			tempAngle += 360;
		SmartDashboard.putNumber("Temp Angle", tempAngle);
		double H = Math.abs(getH());
		x = Math.sin(tempAngle*Math.PI/180)*H;
		y = Math.cos(tempAngle*Math.PI/180)*H;
		SmartDashboard.putNumber("Cos", Math.cos(tempAngle*Math.PI/180));
		SmartDashboard.putNumber("Sin", Math.sin(tempAngle*Math.PI/180));
		SmartDashboard.putNumber("X", x);
		SmartDashboard.putNumber("Y", y);
	}

	public void add(Vector vector) {
		x += vector.getX();
		y += vector.getY();
	}
}
