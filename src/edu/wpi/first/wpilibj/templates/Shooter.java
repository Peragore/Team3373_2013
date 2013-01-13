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
public class Shooter {


   
     /**************
    * Shooter code
    * *************/
   Team3373 team;
   DriverStationLCD dsLCD;
   
    public Shooter(Team3373 t){
       team = t;
   }
   
   public double RPMtarget(double a){ //defines target based on input. Appeaers to be better than speed increase. can probbaly be used in place of a bunch of code.
      if (team.shootA){
           team.target = ((team.RPMModifier * team.ShooterSpeedScale) + team.currentRPMT2) * a;
       } else if (team.shootB){
           team.target = (( -team.RPMModifier * team.ShooterSpeedScale) + team.currentRPMT2) * a;
       }
   return team.target;
   }
    
    public void shootInit() { //to be called at beginning of teleoperated
       DriverStationLCD.getInstance();
       team.target = team.ShooterSpeedMax;
   }
       
       /******************
        * Initialization *
        * ****************/
 public void Start(){ //Initialization code, used to turn motors on and off
       if (team.shootStart){
           team.StageTwoTalon.set(team.idle);
           team.StageOneTalon.set(team.idle * team.stageOneScaler);
           //dsLCD.println(Line.kUser1, 7, "On");
           //dsLCD.updateLCD();
       } else if (team.shootBack){
           team.StageTwoTalon.set(team.off);
           team.StageOneTalon.set(team.off * team.stageOneScaler);
           //dsLCD.println(Line.kUser1, 7, "Off");
           //dsLCD.updateLCD();
       }
 }
       /*********************
        * Increase/Decrease *
        * *******************/
public void speedChange(){ //increases speed by amount/second designated. Needs the per second part
       
           RPMtarget(1);
           RPMtarget(team.stageOneScaler);
           team.StageTwoTalon.set(team.target);
           team.StageOneTalon.set(team.target * team.stageOneScaler);
           
           if (team.target > 0) {
               //dsLCD.println(Line.kUser2, 1, "Adding " + team.target + "RPM");
               //dsLCD.updateLCD();
               System.out.println("Adding RPM");
           } else if (team.target < 0) {
               //dsLCD.println(Line.kUser2, 1, "Subtracting " + team.target + "RPM");               
               //dsLCD.updateLCD();
               System.out.println("Subtracting RPM");
           }
           //dsLCD.updateLCD();
 }
       
 /* public void speedDecrease() { //decrease speed by set number. Works like speedIncrease() in reverse
           //This code is used to subtrack the current speed of Stage 2
           team.StageTwoTalon.set(team.target);
           team.StageOneTalon.set(team.target *.5);       
           //dsLCD.println(Line.kUser2, 1, "Removing " + team.RPMModifier + "RPM.");
           //dsLCD.updateLCD();
           if (team.StageTwoTalon.get() <= 0){
               team.StageTwoTalon.set(team.off);
               //if the speed is less than 0, turn off
           }
       }
*/
public void percentageAdd() { //adds 5% to the scaler of stage one       
           team.stageOneScaler += 0.05;
           //changes stage1 percentage of stage2 adds 5%
           //dsLCD.println(Line.kUser6, 1, "Adding 5% to Stage One Percentile");
           //dsLCD.updateLCD
           System.out.println("Adding percentage");
       } 

public void percentageSubtract() {//reduces percentage, subtracts 5%. i.e. 45% - 40%
           team.stageOneScaler -= 0.05;
           //changes stage1 percentage of stage2 subtracts 5%
           //dsLCD.println(Line.kUser6, 1, "Subtracting 5% to Stage One Percentile");
           //dsLCD.updateLCD();
           System.out.println("Subtracting percentage");
       }
       
       
public void shooterPrint() { // prints different variables. NYI
        if (RPMtarget(1) > team.currentRPMT2) {
           //dsLCD.println(Line.kUser5, 1, "Accelerating");
           //dsLCD.updateLCD();
       } else if (RPMtarget(1) < team.currentRPMT2) {
           //dsLCD.println(Line.kUser5, 1, "Decelerating");
           //dsLCD.updateLCD();
       }
       //dsLCD.println(Line.kUser1, 1, "Motors ");
       //dsLCD.updateLCD();
       //dsLCD.println(Line.kUser3, 1, "Stage One Speed Percentile: " + (team.currentRPMT1/team.currentRPMT2)*100 + "%");
       //dsLCD.updateLCD();
       //dsLCD.println(Line.kUser4, 1, "Target Speed: " + (RPMtarget(1)) + "RPM");
       //dsLCD.updateLCD();        
    }
}
