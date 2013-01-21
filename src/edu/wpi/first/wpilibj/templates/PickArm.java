/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.DriverStationLCD.*;
import edu.wpi.first.wpilibj.Relay.*;
import edu.wpi.first.wpilibj.templates.*;
/**
 *
 * @author Philip2
 */
public class PickArm {
    int grabStatus = 0;
    Team3373 team;
    String grabString;
    public PickArm(Team3373 t){
       team = t;
    }
    

    /*
    public void extend() {

        
        if (team.armLimit.get()) {
            team.LCD.println(DriverStationLCD.Line.kUser1, 1, "1");
            team.LCD.updateLCD();
        } else if (!team.armLimit.get()) {
            team.LCD.println(DriverStationLCD.Line.kUser5, 1, "0");
            team.LCD.updateLCD();
        }
        
        if (team.armLimit.get()){ 
            team.GrabSpike.set(Value.kForward);
        } else if (team.shootRB){
            team.GrabSpike.set(Value.kReverse);
        } else {
            team.GrabSpike.set(Value.kOff);
        }
        

    } */
    public void grabFrisbee() {//used to grab frisbee after exteneded

        
        grabString = Integer.toString(grabStatus);
        team.LCD.println(DriverStationLCD.Line.kUser1, 1, grabString);       
        
        switch(grabStatus){
            case 0: //init stage, not running and no signal to run
            if (team.shootY && team.flagY){
                grabStatus = 1;
                team.flagY = false;
            }
                break;
            case 1:// parked and signal to run
                team.GrabSpike.set(Value.kForward);
                if (!team.armLimit.get()){
                grabStatus = 2;
                }
                break;
            case 2: //running
                team.GrabSpike.set(Value.kForward);
                if(team.armLimit.get()){
                    grabStatus = 3;  
                }
                break;
            case 3: //back home, off
                team.GrabSpike.set(Value.kOff);
                grabStatus = 0;
            }
        
        team.LCD.updateLCD();
    }
}
