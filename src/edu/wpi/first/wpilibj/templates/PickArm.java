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
    Relay ArmSpike = new Relay(1);
    public PickArm(Team3373 t){
       team = t;
    }
    

    
    public void extend() {
        if (team.shootLB){
            ArmSpike.setDirection(Direction.kForward);
        } else if (team.shootRB){
            ArmSpike.setDirection(Direction.kReverse);
        }
    }
}
