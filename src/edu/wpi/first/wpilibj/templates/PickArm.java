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
    Team3373 team;
    Relay ArmSpike = new Relay(1);

    public PickArm(Team3373 t){
       team = t;
    }
    

    
    public void extend() {


        if (team.armLimit.get()) {
            team.LCD.println(DriverStationLCD.Line.kUser1, 1, "1");
            team.LCD.updateLCD();
        } else if (!team.armLimit.get()) {
            team.LCD.println(DriverStationLCD.Line.kUser5, 1, "0");
            team.LCD.updateLCD();
        }
        
        if (team.shootLB){
            ArmSpike.set(Value.kForward);
        } else if (team.shootRB){
            ArmSpike.set(Value.kReverse);
        } else {
            ArmSpike.set(Value.kOff);
        }
        if (team.shootA) {
            team.LCD.free();
        }
    }
}
