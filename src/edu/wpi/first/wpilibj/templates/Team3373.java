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
//import edu.wpi.first.wpilibj.templates.Shooter;
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
   Talon StageOneTalon = new Talon(1, 1); //Creates instance of StageOne PWM
   Talon StageTwoTalon = new Talon(1, 2); //Creates instance of StageTwo PWM 
   DriverStationLCD LCD = DriverStationLCD.getInstance();
   SmartDashboard smartDashboard;
   Joystick shootStick = new Joystick(2);
   Shooter objShooter = new Shooter(this);
   Deadband objDeadband = new Deadband();
   Timer robotTimer = new Timer();
   
   /************************
    * XBOX Shooter Buttons *
    * *********************/
   
   boolean shootA; 
   boolean shootB;
   boolean shootX;
   boolean shootY;
   boolean shootRB;
   boolean shootLB;
   boolean shootBack; 
   boolean shootStart;
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
   
   
   double ShooterSpeedStage2 = 0.1;//was StageTwoTalon.get()
   double percentageScaler = 0.5;
   double ShooterSpeedStage1 = ShooterSpeedStage2 * percentageScaler;//was StageOneTalon.get()
   
   double ShooterSpeedMax = 5300.0;
   double ShooterSpeedAccel = 250;
   double stageOneScaler = .5; //What stage one is multiplied by in order to make it a pecentage of stage 2
   double PWMMax = 1; //maximum voltage sent to motor
   double MaxScaler = PWMMax/5300;
   double ShooterSpeedScale = MaxScaler * ShooterSpeedMax; //Scaler for voltage to RPM. Highly experimental!!
   double currentRPMT2 = StageTwoTalon.get()*ShooterSpeedScale;
   double currentRPMT1 = currentRPMT2*stageOneScaler;
   double target;
   double RPMModifier = 250;
   double idle = 1 * ShooterSpeedScale;
   double off = 0;
   double change;
   
   boolean flagA;
   boolean flagB;
   boolean flagX;
   boolean flagY;
   boolean flagStart;
   boolean flagBack;
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
        robotTimer.start();
        while (isOperatorControl() & isDisabled()){
           objShooter.shootInit(); 
        }

        while (isOperatorControl() & isEnabled()){
           /************************
    * XBOX Shooter Buttons *
    * *********************/
   
    shootA = shootStick.getRawButton(1);
   shootB = shootStick.getRawButton(2);
   shootX = shootStick.getRawButton(3);
   shootY = shootStick.getRawButton(4);
   shootRB = shootStick.getRawButton(5);
   shootLB = shootStick.getRawButton(6);
   shootBack = shootStick.getRawButton(7); 
   shootStart = shootStick.getRawButton(8);
   
   
   /************************
    * XBOX Shooter Axes *
    * *********************/
   
   shootLX = shootStick.getRawAxis(1); 
   shootLY = shootStick.getRawAxis(2);
   shootTriggers = shootStick.getRawAxis(3);
   shootRX = shootStick.getRawAxis(4);
   shootRY = shootStick.getRawAxis(5);
   shootDP = shootStick.getRawAxis(6);
   
   flagA = true;
   flagB = true;
   flagX = true;
   flagY = true;
   flagStart = true;
   flagBack = true;
   
        //Shooter objShooter = new Shooter();
        
        //objShooter.shooterPrint();
        //objShooter.Start();
        if (shootStart ) {
            StageOneTalon.set(ShooterSpeedStage1);
            StageTwoTalon.set(ShooterSpeedStage2);
        }   else if (shootA && flagA){//increases stage 2
            ShooterSpeedStage2 += 0.1;
            ShooterSpeedStage1 = ShooterSpeedStage2 * percentageScaler;
            StageOneTalon.set(ShooterSpeedStage1);
            StageTwoTalon.set(ShooterSpeedStage2);
            flagA = false;

        }   else if (shootB && flagB){//decrease stage 2
            ShooterSpeedStage2 -= 0.1;
            ShooterSpeedStage1 = ShooterSpeedStage2 * percentageScaler;
            StageOneTalon.set(ShooterSpeedStage1);
            StageTwoTalon.set(ShooterSpeedStage2);
        } else if (shootX && flagX){//increases percentage between Stage1 and Stage2
            percentageScaler += 0.05;
            ShooterSpeedStage1 = ShooterSpeedStage2 * percentageScaler;
            StageOneTalon.set(ShooterSpeedStage1);
            StageTwoTalon.set(ShooterSpeedStage2);
        } else if (shootY && flagY){//decreases percentage between Stage1 and Stage2
            percentageScaler -= 0.05;
            ShooterSpeedStage1 = ShooterSpeedStage2 * percentageScaler;
            StageOneTalon.set(ShooterSpeedStage1);
            StageTwoTalon.set(ShooterSpeedStage2);
        } else if (shootBack){//turns off
            StageOneTalon.set(0);
            StageTwoTalon.set(0);
        }
        
        if (!shootA && !flagA) { //toggles
            flagA = true;
        } else if (!shootB && !flagB){
            flagB = true;
        }else if (!shootX && !flagX){
            flagB = true;
        }else if (!shootY && !flagY){
            flagB = true;
        } else if (!shootStart && !flagStart){
            flagStart = true;
        }else if (!shootBack && !flagBack){
            flagBack = true;
        }
        
        //try {Thread.sleep(1000);} catch(Exception e){}
        String percentage = Double.toString(percentageScaler);
        double speedOne = StageOneTalon.get();
        String speed1 = Double.toString(speedOne);
        double speedTwo = StageTwoTalon.get();
        String speed2 = Double.toString(speedTwo);
        LCD.println(Line.kUser3, 1,"%:" + percentage);
        LCD.println(Line.kUser4, 1,"S1:" + speed1);
        LCD.println(Line.kUser5, 1,"S2:" + speed2);
        LCD.updateLCD();
       /*if (shootA & !flagA) { //increases speed
            objShooter.speedChange();
            LCD.println(Line.kUser2, 1, "Pressing A");
            LCD.updateLCD();
            flagA = true;
       }
       if (!shootA & flagA) { //if a is not pressed and it has been pressed set it to false
           flagA = false;
       }
       
       if (shootB & !flagB) { //decreases speed
            objShooter.speedChange();
            LCD.println(Line.kUser2, 1, "Pressing B");
            LCD.updateLCD();
            flagB = true;
        } 
       
       if (!shootB & flagB) { //if b is not pressed and it has been pressed set it to false
           flagB = false;
       }
        
        if (shootX & stageOneScaler <= 100 & !flagX){
           stageOneScaler += 0.05;
           //changes stage1 percentage of stage2 adds 5%
           LCD.println(Line.kUser6, 1, "Adding 5% to Stage One Percentile");
           LCD.updateLCD();
           flagX = true;
        }   
        
        if (!shootX & flagX) { //if x is not pressed and it has been pressed set it to false
            flagX = false;
        }
        
        if (shootY & !flagY){
            objShooter.percentageSubtract();
            LCD.println(Line.kUser2, 1, "Pressing Y");
            LCD.updateLCD();
        }*/ 
        
        String currentTime = Double.toString(robotTimer.get());
        LCD.println(Line.kUser6, 1, currentTime);

    }
    }
    
}
