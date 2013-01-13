/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;



import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.templates.*;
import edu.wpi.first.wpilibj.templates.Shooter;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory. 
 */
public class Team3373 extends SimpleRobot{
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    
   int StageOneMotorPWM = 1; //Declares channel of StageOne PWM
   int StageTwoMotorPWM = 2; //Declares channel of StageTwo PWM
   Talon StageOneTalon = new Talon(StageOneMotorPWM); //Creates instance of StageOne PWM
   Talon StageTwoTalon = new Talon(StageTwoMotorPWM); //Creates instance of StageTwo PWM 
   DriverStationLCD LCD = DriverStationLCD.getInstance();
   SmartDashboard smartDashboard;
   Joystick shootStick = new Joystick(2);
   Shooter objShooter = new Shooter(this);
   
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
   
   double ShooterSpeedStage1 = StageOneTalon.get();
   double ShooterSpeedStage2 = StageTwoTalon.get();
   double ShooterSpeedMax = 5300.0;
   double ShooterSpeedAccel = 250;
   double stageOneScaler = .5; //What stage one is multiplied by in order to make it a pecentage of stage 2
   double PWMMax = 1; //maximum voltage sent to motor
   double MaxScaler = PWMMax/10000;
   double ShooterSpeedScale = MaxScaler * ShooterSpeedMax; //Scaler for voltage to RPM. Highly experimental!!
   double currentRPMT2 = StageTwoTalon.get()*ShooterSpeedScale;
   double currentRPMT1 = currentRPMT2*stageOneScaler;
   double target;
   double RPMModifier = 250;
   double idle = 1 * ShooterSpeedScale;
   double off = 0;
   double change;
   public Team3373(){
        
    }
    
    public void autonomous() {
        for (int i = 0; i < 4; i++)  {
            
            }
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while (isOperatorControl() ){
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
   
   boolean FlagA = false;
   boolean FlagB = false;
   boolean FlagX = false;
   boolean FlagY = false;
   
        //Shooter objShooter = new Shooter();
        //objShooter.shootInit();
        //objShooter.shooterPrint();
        //objShooter.Start();
        
       if (shootA & !FlagA) { //increases speed
            objShooter.speedChange();
            LCD.println(Line.kUser2, 1, "Pressing A");
            LCD.updateLCD();
            FlagA = true;
       }
       if (!shootA & FlagA) {
           FlagA = false;
       }
       
       if (shootB & !FlagB) { //decreases speed
            objShooter.speedChange();
            LCD.println(Line.kUser2, 1, "Pressing B");
            LCD.updateLCD();
            FlagB = true;
        } 
       
       if (!shootB & FlagB) {
           FlagB = true;
       }
        
        if (shootX & stageOneScaler <= 100 & !FlagX){
           stageOneScaler += 0.05;
           //changes stage1 percentage of stage2 adds 5%
           LCD.println(Line.kUser6, 1, "Adding 5% to Stage One Percentile");
           LCD.updateLCD();
           FlagX = true;
        }   
        
        if (!shootX & FlagX) {
            FlagX = true;
        }
        
        if (shootY & !Flag){
            objShooter.percentageSubtract();
            LCD.println(Line.kUser2, 1, "Pressing Y");
            LCD.updateLCD();
        } 
        LCD.println(Line.kUser2, 1, "Not pressing");
        LCD.updateLCD();
    }
    }
    
}
