package org.usfirst.frc.team1504.robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.GregorianCalendar;

import edu.wpi.first.wpilibj.DriverStation;

public class Logger{
	private File _outfile;
	private FileOutputStream _file_output;
	
	private DriverStation _ds = DriverStation.getInstance();
	
	private long _start_time;
	private volatile byte[][] _logged_data = null;
	private volatile boolean _logging = false;
	
	private static final Logger instance = new Logger();
	
	protected Logger()
	{
		System.out.println("Logger initialized");
	}
	
	public static Logger getInstance()
	{
		return instance;
	}
	
	/**
	 *Start logger
	 *@param prefix - The filename prefix to log under (Format: Prefix-Time.log)
	 */
	
	public void start(String prefix){
		Calendar cal = new GregorianCalendar();
		String filetime = Long.toString(cal.getTimeInMillis());
		_outfile = new File("/home/lvuser/log" + prefix + "-" + filetime + ".log");
		
		try {
			_file_output = new FileOutputStream(_outfile);
		} catch (FileNotFoundException e){
			System.out.println("Could not open logging file.\n" + _outfile);
		}
		
		_start_time = System.currentTimeMillis();
		
		byte[] robot_start_time = new byte[8];
		ByteBuffer.wrap(robot_start_time).putLong(IO.ROBOT_START_TIME);
		try{
			_file_output.write("Log-".getBytes());
			_file_output.write(robot_start_time);
		} catch (IOException e){
			e.printStackTrace();
		}
		
		System.out.println("Logger started @ " + _start_time + " using \"~/log/" + prefix + "-" + filetime + ".log\"");
	}

}
