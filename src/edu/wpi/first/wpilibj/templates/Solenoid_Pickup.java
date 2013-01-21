/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;

/**
 *
 * @author RoboHawks
 */
public class Solenoid_Pickup {
    
   Team3373 team;
   
   public Solenoid_Pickup(Team3373 t){
       team = t;
   }
    
    public void solenoid(){
        
        if (team.shootA){
            team.grabSolenoid.set(true);
        } else if (team.shootB)
            team.grabSolenoid.set(false);
        }
    }
