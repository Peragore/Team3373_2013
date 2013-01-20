/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Relay.*;
import edu.wpi.first.wpilibj.templates.*;
/**
 *
 * @author Philip2
 */
public class PickArm {
    Team3373 team;
    Relay ArmSpike = new Relay(1, Direction.kBoth);
    public PickArm(Team3373 t){
       team = t;
    }
    

    
    public void extend() {
        if (team.shootLB){
            ArmSpike.set(Value.kForward);
        } else if (team.shootRB){
            ArmSpike.set(Value.kReverse);
        } else {
            ArmSpike.set(Value.kOff);
        }
    }
}
