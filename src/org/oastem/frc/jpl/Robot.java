
package org.oastem.frc.jpl;


import org.oastem.frc.sensor.QuadratureEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the RobotDrive class.
 * The SampleRobot class is the base of a robot application that will automatically call your
 * Autonomous and OperatorControl methods at the right time as controlled by the switches on
 * the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
	
    private DigitalOutput led;
    private SmartDashboard dash;
    private QuadratureEncoder topEnc;
    private QuadratureEncoder botEnc;
    private Victor topShooter;
    private Victor botShooter;
    private DigitalInput switchBoyz;
    private DigitalInput switchBoyz2;
    
    private static final int SHOOTER_ENC_CPR = 2048;
    protected static final double DISTANCE_PER_REVOLUTION = 6 * Math.PI; // FOR DEFAULT DRIVE WHEELS
    private static final int TOP_ENC_I = 4;
	private static final int TOP_ENC_A = 5;
	private static final int TOP_ENC_B = 3;
	private static final int BOT_ENC_I = 1;
	private static final int BOT_ENC_A = 2;
	private static final int BOT_ENC_B = 0;
	private static final int SWITCHERINO = 0;
	private static final int SWITCHERINO2 = 1;
	
	private static final double SHOOTING_SPEED = DISTANCE_PER_REVOLUTION * 5;

    public Robot() {
    	
    	dash = new SmartDashboard();
    	/*led = new DigitalOutput(0);
    	topEnc = new QuadratureEncoder(TOP_ENC_A, TOP_ENC_B, TOP_ENC_I, true, SHOOTER_ENC_CPR);
    	botEnc = new QuadratureEncoder(BOT_ENC_A, BOT_ENC_B, BOT_ENC_I, false, SHOOTER_ENC_CPR);
    	topEnc.setDistancePerPulse(DISTANCE_PER_REVOLUTION);
        botEnc.setDistancePerPulse(DISTANCE_PER_REVOLUTION);
        topEnc.reset();
        botEnc.reset();
    	//*/
    	
    	topShooter = new Victor(5);
    	botShooter = new Victor(4);
    	
    	switchBoyz = new DigitalInput(SWITCHERINO);
    	switchBoyz2 = new DigitalInput(SWITCHERINO2);
    }

    /**
     * Drive left & right motors for 2 seconds then stop
     */
    public void autonomous() {
        while (true)
        {
        	topShooter.set(-1.0);
        	botShooter.set(1.0);
        }
    }

    /**
     * Runs the motors with arcade steering.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
        	if (switchBoyz2.get()){
        		if (!switchBoyz.get()){
        			topShooter.set(1.0);
        			botShooter.set(-1.0);
        		}
        		else {
        			topShooter.set(0.5);
        			botShooter.set(-0.5);
        		}
        	}
        	else {
        		topShooter.set(0.0);
        		botShooter.set(0.0);
        	}
        		
        	
        	dash.putNumber("TOP", topShooter.get());
        	dash.putNumber("BOTTOM", botShooter.get());
        	
        	/*
        	if (topEnc.getRate() > SHOOTING_SPEED && botEnc.getRate() > SHOOTING_SPEED)
        		led.set(true);
        	else
        		led.set(false);
        	//*/
        }
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }
}
