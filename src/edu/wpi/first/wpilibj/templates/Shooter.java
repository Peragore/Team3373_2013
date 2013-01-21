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
    public void elevator(){
        if(team.shootA && !team.shootB){
            team.GrabSpike.set(Value.kForward);
        } else if(!team.shootA && team.shootB){
            team.GrabSpike.set(Value.kReverse);
        } else {
            team.GrabSpike.set(Value.kOff);
        }   
    }
  }