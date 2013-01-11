/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.Shooter_underneath;
/**
 *
 * @author Philip2
 */
public class Shooter extends Team3373 {
    Shooter_underneath define = new Shooter_underneath();
   
     /**************
    * Shooter code
    * *************/
   
   public void shootInit() { //to be called at beginning of teleoperated
       LCD = DriverStationLCD.getInstance();
       LCD.updateLCD();
       target = ShooterSpeedMax;
   }
       
       /******************
        * Initialization *
        * ****************/
 public void Start(){ //Initialization code, used to turn motors on and off
       if (shootStart){
           StageTwoTalon.set(idle);
           StageOneTalon.set(idle * stageOneScaler);
           LCD.println(Line.kUser1, 7, "On");
           LCD.updateLCD();
       } else if (shootBack){
           StageTwoTalon.set(off);
           StageOneTalon.set(off *stageOneScaler);
           LCD.println(Line.kUser1, 7, "Off");
           LCD.updateLCD();
       }
 }
       /*********************
        * Increase/Decrease *
        * *******************/
public void speedIncrease(){ //increases speed by amount/second designated. Needs the per second part
       
           define.RPMTarget(1);
           define.RPMTarget(stageOneScaler);
           LCD.println(Line.kUser2, 1, "Adding " + define.target + "RPM");
           LCD.updateLCD();
 }
       
 public void speedDecrease() { //decrease speed by set number. Works like speedIncrease() in reverse
           //This code is used to subtrack the current speed of Stage 2
           StageTwoTalon.set(define.target);
           StageOneTalon.set(define.target *.5);       
           LCD.println(Line.kUser2, 1, "Removing " + define.target + "RPM.");
           LCD.updateLCD();
           if (StageTwoTalon.get() <= 0){
               StageTwoTalon.set(off);
               //if the speed is less than 0, turn off
           }
       }

public void percentageAdd() { //adds 5% to the scaler of stage one       
           stageOneScaler += 0.05;
           //changes stage1 percentage of stage2 adds 5%
           LCD.println(Line.kUser6, 1, "Adding 5% to Stage One Percentile");
           LCD.updateLCD();
       } 

public void percentageSubtract() {//reduces percentage, subtracts 5%. i.e. 45% - 40%
           stageOneScaler -= 0.05;
           //changes stage1 percentage of stage2 subtracts 5%
           LCD.println(Line.kUser6, 1, "Subtracting 5% to Stage One Percentile");
           LCD.updateLCD();
       }
       
       
public void shooterPrint() { // prints different variables. NYI
        if (target > currentRPMT2) {
           LCD.println(Line.kUser5, 1, "Accelerating");
           LCD.updateLCD();
       } else if (target < currentRPMT2) {
           LCD.println(Line.kUser5, 1, "Decelerating");
           LCD.updateLCD();
       }
       LCD.println(Line.kUser1, 1, "Motors ");
       LCD.updateLCD();
       LCD.println(Line.kUser3, 1, "Stage One Speed Percentile: " + (currentRPMT1/currentRPMT2)*100 + "%");
       LCD.updateLCD();
       LCD.println(Line.kUser4, 1, "Target Speed: " + (target) + "RPM");
       LCD.updateLCD();        
    }
}
