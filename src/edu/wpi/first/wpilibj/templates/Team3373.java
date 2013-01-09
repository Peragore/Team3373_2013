/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;



import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.templates.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Team3373 extends SimpleRobot implements Shooter {
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    
    RobotDrive drive = new RobotDrive(1,2);
    Joystick leftStick = new Joystick(1);
    Joystick rightStick = new Joystick(2);
    MotorSafety safety = new MotorSafetyImpl();
    Shooter Shooter = new Shooter();
    public Team3373(){
        
    }
    
    
    public void RoboDemo() {
    getWatchdog().setEnabled(false);
    safety.setSafetyEnabled(false);
}
    public void autonomous() {
        for (int i = 0; i < 4; i++)  {
            drive.tankDrive(1, 0x1);
            }
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        Shooter();
    }

    private static class MotorSafetyImpl implements MotorSafety {

        public MotorSafetyImpl() {
        }

        public void setExpiration(double d) {
        }

        public double getExpiration() {
            return 0;
        }

        public boolean isAlive() {
            return false;
        }

        public void stopMotor() {
        }

        public void setSafetyEnabled(boolean bln) {
        }

        public boolean isSafetyEnabled() {
            return false;
        }

        public String getDescription() {
            return null;
        }
    }
}
