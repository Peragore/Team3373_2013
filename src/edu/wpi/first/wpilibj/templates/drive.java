/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;

/**
 *
 * @author RoboHawks
 */
public class drive {
    Team3373 testing = new Team3373();
    Joystick drivestick = new Joystick(1);
    RobotDrive drive = new RobotDrive(1,2);
    boolean driveA = drivestick.getRawButton(1);
    int driveLstickY = (int) drivestick.getRawAxis(2);
    
    public void Driving(){
    switch (driveLstickY) {
        case 1: if (0>= driveLstickY & driveLstickY >= -1){
            
        }
            break;
        case 2: if (1>= driveLstickY & driveLstickY >= 0){
            
        }
        break;
    }
 }
}
