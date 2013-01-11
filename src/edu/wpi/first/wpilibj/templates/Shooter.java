/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Philip2
 */
public class Shooter extends Team3373 {

   
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
       } else if (shootBack){
           StageTwoTalon.set(off);
           StageOneTalon.set(off *stageOneScaler);
           LCD.println(Line.kUser1, 7, "Off");
       }
 }
       /*********************
        * Increase/Decrease *
        * *******************/
public void speedIncrease(){ //increases speed by amount/second designated. Needs the per second part
       
           StageTwoTalon.set(currentRPMT2 + (RPMIncrease*ShooterSpeedScale));
           StageOneTalon.set((currentRPMT2 + RPMIncrease) *stageOneScaler);
           target = currentRPMT2 + RPMIncrease;
           LCD.println(Line.kUser2, 1, "Adding " + RPMIncrease + "RPM");
 }
       
 public void speedDecrease() { //decrease speed by set number. Works like speedIncrease() in reverse
           //This code is used to subtrack the current speed of Stage 2
           StageTwoTalon.set(currentRPMT2 - RPMIncrease);
           StageOneTalon.set((currentRPMT2 - RPMIncrease) *.5);       
           target = currentRPMT2 - RPMIncrease;
           LCD.println(Line.kUser2, 1, "Removing " + RPMIncrease + "RPM.");
           if (StageTwoTalon.get() <= 0){
               StageTwoTalon.set(off);
               //if the speed is less than 0, turn off
           }
       }

public void percentageAdd() { //adds 5% to the scaler of stage one       
           stageOneScaler += 0.05;
           //changes stage1 percentage of stage2 adds 5%
           LCD.println(Line.kUser6, 1, "Adding 5% to Stage One Percentile");
       } 

public void percentageSubtract() {//reduces percentage, subtracts 5%. i.e. 45% - 40%
           stageOneScaler -= 0.05;
           //changes stage1 percentage of stage2 subtracts 5%
           LCD.println(Line.kUser6, 1, "Subtracting 5% to Stage One Percentile");
       }
       
       
public void shooterPrint() { // prints different variables. NYI
        if (target > currentRPMT2) {
           LCD.println(Line.kUser5, 1, "Accelerating");
       } else if (target < currentRPMT2) {
           LCD.println(Line.kUser5, 1, "Decelerating");
       }
       LCD.println(Line.kUser1, 1, "Motors ");
       LCD.println(Line.kUser3, 1, "Stage One Speed Percentile: " + (currentRPMT1/currentRPMT2)*100 + "%");
       LCD.println(Line.kUser4, 1, "Target Speed: " + (target) + "RPM");
       
       LCD.updateLCD();
        
    }
}
