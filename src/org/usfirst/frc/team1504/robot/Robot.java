package org.usfirst.frc.team1504.robot;

/*

* Copyright (C) 2016 Team 1504, The Desperate Penguins

*

* This program is free software: you can redistribute it and/or modify

* it under the terms of the GNU General Public License as published by

* the Free Software Foundation, either version 3 of the License, or

* (at your option) any later version.

*

* You may obtain a copy of the License at

*

* http://www.gnu.org/licenses/

*


*/
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.communication.UsageReporting;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary.tInstances;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary.tResourceType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * This class conducts communication between the robot and the Driver Station,
 * which allows all of the code to run.
 */
public class Robot extends RobotBase
{

	/**
	 * Creates a new Robot.
	 */
	public Robot()
	{
		super();
	}

	/**
	 * This is where code for robot-wide initialization should be put.
	 * 
	 * Will always be called ONCE when the robot first starts up, and ONCE when
	 * a competition starts.
	 */
	protected void robotInit()
	{
		// do things here
	}

	/**
	 * What happens when the robot is disabled; this will happen once at that
	 * time.
	 */
	protected void disabled()
	{
		// do things here!
	}

	/**
	 * Where autonomous code will go. If the autonomous code becomes lengthy,
	 * consider making a separate class and calling that class up in this
	 * method.
	 * 
	 * Called once when the robot goes into autonomous state.
	 */
	public void autonomous()
	{
		// do the things
	}

	/**
	 * Code for when the robot is in the teleoperated mode.
	 * 
	 * Called once when the robot goes into the teleop mode.
	 */
	public void operatorControl()
	{
		// do stuff
	}

	/**
	 * Code for when the robot is in test mode; like the others, called once
	 * when the robot enters the mode.
	 */
	public void test()
	{
		// do stuff and things
	}

	/**
	 * Start a competition. This code tracks the order of the field starting to
	 * ensure that everything happens in the right order. Repeatedly run the
	 * correct method, either Autonomous or OperatorControl when the robot is
	 * enabled. After running the correct method, wait for some state to change,
	 * either the other mode starts or the robot is disabled. Then go back and
	 * wait for the robot to be enabled again.
	 */
	public void startCompetition()
	{
		UsageReporting.report(tResourceType.kResourceType_Framework, tInstances.kFramework_Simple);

		// first and one-time initialization
		LiveWindow.setEnabled(false);
		robotInit();

		while (true)
		{
			if (isDisabled())
			{
				m_ds.InDisabled(true);

				disabled();

				while (isDisabled())
				{
					Timer.delay(0.01);
				}

				m_ds.InDisabled(false);

			} else if (isAutonomous())
			{
				m_ds.InAutonomous(true);

				autonomous();

				while (isAutonomous() && !isDisabled())
				{
					Timer.delay(0.01);
				}

				m_ds.InAutonomous(false);

			} else if (isTest())
			{
				LiveWindow.setEnabled(true);
				m_ds.InTest(true);

				test();

				while (isTest() && isEnabled())
					Timer.delay(0.01);

				m_ds.InTest(false);
				LiveWindow.setEnabled(false);

			} else
			{
				m_ds.InOperatorControl(true);

				operatorControl();

				while (isOperatorControl() && !isDisabled())
				{
					m_ds.waitForData(150); // Blocks until we get new data or
											// 150ms elapse
					// Timer.delay(0.01);
				}

				m_ds.InOperatorControl(false);
			}
		} /* while loop */
	}

}
