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
public class Shooter extends Team3373 {
   int StageOneMotorPWM = 1; //Declares channel of StageOne PWM
   int StageTwoMotorPWM = 2; //Declares channel of StageTwo PWM
   Talon StageOneTalon = new Talon(StageOneMotorPWM); //Creates instance of StageOne PWM
   Talon StageTwoTalon = new Talon(StageTwoMotorPWM); //Creates instance of StageTwo PWM 
   DriverStationLCD dsLCD;
   Joystick shootStick = new Joystick(2);
   
   /************************
    * XBOX Shooter Buttons *
    * *********************/
   
   boolean shootA = shootStick.getRawButton(1);
   boolean shootB = shootStick.getRawButton(2);
   boolean shootX = shootStick.getRawButton(3);
   boolean shootY = shootStick.getRawButton(4);
   boolean shootRB = shootStick.getRawButton(5);
   boolean shootLB = shootStick.getRawButton(6);
   boolean shootBack = shootStick.getRawButton(7); 
   boolean shootStart = shootStick.getRawButton(8);
   boolean test;
   
   /************************
    * XBOX Shooter Axes *
    * *********************/
   
   double shootLX = shootStick.getRawAxis(1); 
   double shootLY = shootStick.getRawAxis(2);
   double shootTriggers = shootStick.getRawAxis(3);
   double shootRX = shootStick.getRawAxis(4);
   double shootRY = shootStick.getRawAxis(5);
   double shootDP = shootStick.getRawAxis(6);
   
  /*********************************
   * Math/Shooter Action Variables *
   *********************************/
   
   double stageOneScaler = .5; //What stage one is multiplied by in order to make it a pecentage of stage 2
   double PWMMax = 1; //maximum voltage sent to motor
   double shootSpeedScale = PWMMax/5300; //Scaler for voltage to RPM. Highly experimental!!
   double currentRPMT2 = StageTwoTalon.get()*shootSpeedScale;
   double currentRPMT1 = currentRPMT2*stageOneScaler;
   double target;
   double RPMIncrease = 250;
   double idle = 1 * shootSpeedScale;
   double off = 0;

     /**************
    * Shooter code *
    * *************/
   
   public void Shooter() {
       dsLCD = DriverStationLCD.getInstance();
       dsLCD.updateLCD();
       
       /******************
        * Initialization *
        * ****************/
       
       if (shootStart){
           StageTwoTalon.set(idle);
           StageOneTalon.set(idle * stageOneScaler);
           dsLCD.println(Line.kUser1, 7, "On");
       } else if (shootBack){
           StageTwoTalon.set(off);
           StageOneTalon.set(off *stageOneScaler);
           dsLCD.println(Line.kUser1, 7, "Off");
       }
       /*********************
        * Increase/Decrease *
        * *******************/
       
       if (shootA){
           StageTwoTalon.set(currentRPMT2 + (RPMIncrease*shootSpeedScale));
           StageOneTalon.set((currentRPMT2 + RPMIncrease) *stageOneScaler);
           target = currentRPMT2 + RPMIncrease;
           dsLCD.println(Line.kUser2, 1, "Adding " + RPMIncrease + "RPM");
       }
       
       if (shootB){
           //This code is used to subtrack the current speed of Stage 2
           StageTwoTalon.set(currentRPMT2 - RPMIncrease);
           StageOneTalon.set((currentRPMT2 - RPMIncrease) *.5);       
           target = currentRPMT2 - RPMIncrease;
           dsLCD.println(Line.kUser2, 1, "Removing " + RPMIncrease + "RPM.");
           if (StageTwoTalon.get() < 0){
               StageTwoTalon.set(off);
               //if the speed is less than 0, turn off
           }
       }
       
       if (shootX){
           stageOneScaler += 0.05;
           //changes stage1 percentage of stage2 adds 5%
           dsLCD.println(Line.kUser6, 1, "Adding 5% to Stage One Percentile");
       } else if (shootY){
           stageOneScaler -= 0.05;
           //changes stage1 percentage of stage2 subtracts 5%
           dsLCD.println(Line.kUser6, 1, "Subtracting 5% to Stage One Percentile");
       }
       
       if (target > currentRPMT2) {
           dsLCD.println(Line.kUser5, 1, "Accelerating");
       } else if (target < currentRPMT2) {
           dsLCD.println(Line.kUser5, 1, "Decelerating");
       }
       
       dsLCD.println(Line.kUser1, 1, "Motors ");
       dsLCD.println(Line.kUser3, 1, "Stage One Speed Percentile: " + (currentRPMT1/currentRPMT2)*100 + "%");
       dsLCD.println(Line.kUser4, 1, "Target Speed: " + (target) + "RPM");
       
       dsLCD.updateLCD();
        
    }
}
