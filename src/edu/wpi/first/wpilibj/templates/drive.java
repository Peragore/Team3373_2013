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
    Joystick drive = new Joystick(1);
    boolean XBOX_A = drive.getRawButton(1);
    double XBOX_LT = drive.getRawAxis(1);
 
    public void Driving(){
     
 }
}
