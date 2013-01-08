/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;

/**
 *
 * @author Philip2
 */
public class Shooter {
   int StageOneMotorPWM = 1; //Declares channel of StageOne PWM
   int StageTwoMotorPWM = 2; //Declares channel of StageTwo PWM
   Talon StageOneTalon = new Talon(StageOneMotorPWM); //Creates instance of StageOne PWM
   Talon StageTwoTalon = new Talon(StageTwoMotorPWM); //Creates instance of StageTwo PWM 
   DriverStationLCD dsLCD;
   Joystick shootStick = new Joystick(2);
   
   boolean shootStart = shootStick.getRawButton(8);


  
   
   public Shooter() {
       dsLCD = DriverStationLCD.getInstance();
       dsLCD.println(Line.kUser1, StageOneMotorPWM, "");
       if (shootStart){
           
       }
    
        
    }
}
