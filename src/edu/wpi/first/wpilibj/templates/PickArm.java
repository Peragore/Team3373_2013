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
    DigitalInput armLimit = new DigitalInput(1);

    public PickArm(Team3373 t){
       team = t;
    }
    

    
    public void extend() {
        boolean armlimit = armLimit.get();

        if (armlimit) {
            team.LCD.println(DriverStationLCD.Line.kUser1, 1, "true");
        } else {
            team.LCD.println(DriverStationLCD.Line.kUser1, 1, "false");
        }

        if (team.shootLB){
            ArmSpike.set(Value.kForward);
        } else if (team.shootRB){
            ArmSpike.set(Value.kReverse);
        } else {
            ArmSpike.set(Value.kOff);
        }
    }
}
