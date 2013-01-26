/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Relay.*;

/**
 *
 * @author Philip2
 */
public class Shooter {
   int elevatorStatus = 1;
   Team3373 team;
   DriverStationLCD dsLCD;
    public Shooter(Team3373 t){
       team = t;
   }
   
     /**************
    * Shooter code
    * *************/

    public double start(){
        double a = 0.1;
        return a;
    }
    public double increaseSpeed(double a){ //increases stage 2 by 1/10 of possible speed
        a += 0.1;
        if (a >= 1) {a = 1;}
        return a;
    }
    public double decreaseSpeed(double a){//decreases stage 2 by 1/10 of possible speed
        a -= 0.1;
        if (a <= 0){ a = 0;}  
        return a;
    }
    public double increasePercentage(double a){//increases percentage of what stage 2 is multiplyied by to get 1
        a += 0.05;
        if (a >= 1) {a = 1;}
        return a;
    }
    public double decreasePercentage(double a){//decreases percentage of what stage 2 is multiplyied by to get 1
        a -= 0.05;
        if (a <= 0 ) {a = 0;}
        return a;
    }
    public double stop(){
        double a = 0;
        return a;
    }
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
            team.GrabSpike.set(Value.kReverse);
        } else if (team.shootRB && team.flagRight){
            team.GrabSpike.set(Value.kForward);
        }        
        
        switch (elevatorStatus){
            case 1: //not moving
                team.ShootSpike.set(Value.kForward);
                if (target > team.pot1.getVoltage()){
                    elevatorStatus = 2;
                } else if (target < team.pot1.getVoltage()){
                    elevatorStatus = 3;
                }   
                break;
            case 2: //going up
                team.ShootSpike.set(Value.kForward);
                if (Math.abs(target - team.pot1.getVoltage()) <= .05){
                    elevatorStatus = 0;
                }
                break;
            case 3: //going down
                team.ShootSpike.set(Value.kReverse);
                if (Math.abs(target - team.pot1.getVoltage()) <= .05){
                    elevatorStatus = 0;
                }
                break;
    }
      
        
        
        /*if(team.shootA && !team.shootB){
            team.GrabSpike.set(Value.kForward);
        } else if(!team.shootA && team.shootB){
            team.GrabSpike.set(Value.kReverse);
        } else {
            team.GrabSpike.set(Value.kOff);
        } */  
    }
  }