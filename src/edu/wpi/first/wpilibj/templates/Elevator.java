/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Nick
 */
public class Elevator {
    
    Team3373 team;
    int elevatorStatus = 0;
    
        public void elevator(double target){

        
        if (team.pot1.getVoltage() >= 4.8 ) {
            team.flagRight = false;
        } else if (team.pot1.getVoltage() <= .2){
            team.flagLeft = false;
        } else if (team.pot1.getVoltage() > .2){
            team.flagLeft = true;
        } else if (team.pot1.getVoltage() < 4.8){
            team.flagRight = true;
        }
        if (team.shootLB && team.flagLeft){
            team.GrabSpike.set(Relay.Value.kReverse);
        } else if (team.shootRB && team.flagRight){
            team.GrabSpike.set(Relay.Value.kForward);
        }        
        
        switch (elevatorStatus){
            case 0: //at required spot
                team.ShootSpike.set(Relay.Value.kOff);
                if (target > team.pot1.getVoltage()  && (Math.abs(target - team.pot1.getVoltage()) <= .05)){
                    elevatorStatus = 1;
                } else if ((target < team.pot1.getVoltage()) && (Math.abs(target - team.pot1.getVoltage()) <= .05)){
                    elevatorStatus = 2;
                } else {
                    elevatorStatus = 0;
                }
                break;
            case 1: //moving up
                team.ShootSpike.set(Relay.Value.kForward);
                if (Math.abs(target - team.pot1.getVoltage()) <= .05){
                    elevatorStatus = 0;
                }                    
                break;
            case 2: //moving down
                team.ShootSpike.set(Relay.Value.kReverse);
                if (Math.abs(target - team.pot1.getVoltage()) <= .05){
                    elevatorStatus = 0;
                }
                break;
        }
        }
}
