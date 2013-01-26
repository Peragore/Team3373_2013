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
    Joystick drivestick = new Joystick(1);
    RobotDrive drive = new RobotDrive(1,2);
    boolean driveA = drivestick.getRawButton(1);
    double driveLX =  drivestick.getRawAxis(1);
    double driveLY =  drivestick.getRawAxis(2);
    double driveRX = drivestick.getRawAxis(3);
    Team3373 team;
    public drive(Team3373 t){
       team = t;
   }
    public void Driving(double a){
    if (driveLY >= 0 && driveLY <= 1){
        
    }
    if (driveLY <= 0 && driveLY >= -1 ){
        
    }
    
    drive.mecanumDrive_Cartesian(driveLX, driveLY, driveRX, 0);
    
    }
    
 }
}
