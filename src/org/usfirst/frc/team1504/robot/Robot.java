package org.usfirst.frc.team1504.robot;


import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program for learning basic Java and Git for the 2016 FRC season.
 * 
 * TODO: Add in code to run the right side of the robot.
 * TODO: Add in speed dividers:
 *           Left trigger: Speed / 2
 *           Right trigger: Speed / 3.5
 */
public class Robot extends SampleRobot {
	Joystick leftStick;  // set to ID 1 in DriverStation
	Joystick rightStick; // set to ID 2 in DriverStation
	
	CANTalon leftMotor;
	
	public Robot() {
		// Joystick on DriverStation port 0
		leftStick = new Joystick(0);
		// Joystick on DriverStaton port 1
		rightStick = new Joystick(1);
		
		// Motor controller with CAN ID 10
		leftMotor = new CANTalon(10);
	}
	
	
	/**
	 * Runs the motors with tank steering.
	 */
	public void operatorControl() {
		while (isOperatorControl() && isEnabled()) {
			// Create a variable for the left motor output value
			// and set it equal to the left joystick input
			double leftOutput = leftStick.getY();
			
			// Set the left motor to the correct value
			leftMotor.set(leftOutput);
			
			// Wait for a little while - run this loop ~200 times a second
			Timer.delay(0.005);
		}
	}

}
