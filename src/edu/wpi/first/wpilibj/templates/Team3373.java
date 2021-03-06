/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.templates.*;
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
   Servo frontCameraServo = new Servo(6);
   Relay GrabSpike = new Relay(1);
   Relay ShootSpike = new Relay(2);
   Relay armSpike = new Relay(3);
   Solenoid grabSolenoid = new Solenoid(8);
   AnalogChannel pot1 = new AnalogChannel(7);
   AnalogChannel pot2 = new AnalogChannel(6);
   DigitalInput handUp = new DigitalInput(2);
   DigitalInput armLimit = new DigitalInput(3); //returns true if clicked
   Talon StageOneTalon = new Talon(1, 4); //Creates instance of StageOne PWM
   Talon StageTwoTalon = new Talon(1, 2); //Creates instance of StageTwo PWM 
   DriverStationLCD LCD = DriverStationLCD.getInstance();
   SmartDashboard smartDashboard;
   Joystick driveStick = new Joystick(1);
   Joystick shootStick = new Joystick(2);
   Shooter objShooter = new Shooter(this);
   //Deadband objDeadband = new Deadband();
   Timer robotTimer = new Timer();
   PickArm Arm = new PickArm(this);
   Solenoid_Pickup solPick = new Solenoid_Pickup(this);
   double rotateLimitMaximum = 4.8;
   double rotateLimitMinimum = 0.2;
   //drive Drive = new drive(this);
   /**********************
    * XBOX Drive Buttons *
    **********************/
   boolean driveA;
   boolean driveB;
   boolean driveX;
   boolean driveY;
   boolean driveRB;
   boolean driveLB;
   boolean driveStart;
   boolean driveBack;
   
   /********************
    *  XBOX Drive Axes *
    ********************/
   double driveLX;
   double driveLY;
   double driveTriggers;
   double driveRX;
   double driveRY;
   double driveDP;
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
   boolean solenidFlag=false;
   
   /************************
    * XBOX Shooter Axes *
    * *********************/
   
   double shootLX; 
   double shootLY;
   double shootTriggers;
   double shootRX;
   double shootRY;
   double shootDP; 
   
  /*********************************
   * Math/Shooter Action Variables *
   *********************************/
   
   TableLookUp objTableLookUp = new TableLookUp();
   
   double ShooterSpeedStage2 = 0;//was StageTwoTalon.get()
   double percentageScaler = 0.75;
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
   double Scaler = 5936;
   double change;
   
   double armHome = 2.5;
   double dropOff = 4.2;
   double pickUp = 1;
   
   double startTime = 9000000;
   double backTime = 90000000;
   double aTime = 900000000;
   double bTime = 900000000;
   double targetPosition = pot1.getVoltage();
   
   boolean flagA;
   boolean flagB;
   boolean flagX;
   boolean flagY;
   boolean flagStart;
   boolean flagBack;
   boolean flagBack2;
   boolean flagLeft;
   boolean flagRight;
   double shooterTarget;
   boolean armTestFlag;
   boolean manualToggle;
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
        }
   
   flagA = true;
   flagB = true;
   flagX = true;
   flagY = true;
   flagStart = true;
   flagBack = true;
   flagRight = true;
   flagLeft = true;
   armTestFlag = false;
   manualToggle = true;
   
   while (isOperatorControl() & isEnabled()){
   /**********************
    * XBOX Drive Buttons *
    **********************/
   objTableLookUp.test();
   
       
   driveA = driveStick.getRawButton(1);
   driveB = driveStick.getRawButton(2);
   driveX = driveStick.getRawButton(3);
   driveY = driveStick.getRawButton(4);
   driveLB = driveStick.getRawButton(5);
   driveRB = driveStick.getRawButton(6);
   driveBack = driveStick.getRawButton(7);
   driveStart = driveStick.getRawButton(8);
   
   /*******************
    * XBOX Drive Axes *
    *******************/
   
   driveLX = driveStick.getRawAxis(1); 
   driveLY = driveStick.getRawAxis(2);
   driveTriggers = driveStick.getRawAxis(3);
   driveRX = driveStick.getRawAxis(4);
   driveRY = driveStick.getRawAxis(5);
   driveDP = driveStick.getRawAxis(6);
   
   /************************
    * XBOX Shooter Buttons *
    * *********************/
   
   shootA = shootStick.getRawButton(1);
   shootB = shootStick.getRawButton(2);
   shootX = shootStick.getRawButton(3);
   shootY = shootStick.getRawButton(4);
   shootRB = shootStick.getRawButton(6);
   shootLB = shootStick.getRawButton(5);
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
   
   /****************
   **Shooter Code***
   ****************/
   /*
  ShooterSpeedStage1 = ShooterSpeedStage2 * percentageScaler;   
  StageOneTalon.set(ShooterSpeedStage1);
  StageTwoTalon.set(ShooterSpeedStage2);
        if (shootStart && flagStart) {
            ShooterSpeedStage2 = objShooter.start();
            flagStart = false;
        }   else if (shootA && flagA){//increases stage 2
            ShooterSpeedStage2 = objShooter.increaseSpeed(ShooterSpeedStage2);
            flagA = false;
        }   else if (shootB && flagB){//decrease stage 2
            ShooterSpeedStage2 = objShooter.decreaseSpeed(ShooterSpeedStage2);           
            flagB = false;
        } else if (shootX && flagX){//increases percentage between Stage1 and Stage2
            percentageScaler = objShooter.decreasePercentage(percentageScaler);
            flagX = false;
        } else if (shootY && flagY){//decreases percentage between Stage1 and Stage2
            percentageScaler = objShooter.decreasePercentage(percentageScaler);
            flagY = false;
        } else if (shootBack && flagBack){//turns off
          ShooterSpeedStage2 = objShooter.stop();
          percentageScaler = 0.75;
        }
        */
        //Arm.rotate();
        //objShooter.elevator();
        //Arm.grabFrisbee();
        //Arm.armUp();
        //Arm.armDown();
        if (shootA && !armTestFlag){ 
            StageOneTalon.set(.5);
        } else if (shootB && !armTestFlag){
            StageOneTalon.set(-.5);
        } else {
            StageOneTalon.set(0);
        }
        /********************
         * Testing Arm Code *
         ********************/
        if (shootA && shootX && shootY && !armTestFlag){ //allows the test mode for the arm assembly to start
            armTestFlag = true;
        } else if (shootA && shootX && shootY && armTestFlag){ //turns the test mode for arm off
            armTestFlag = false;
        }
        if (armTestFlag){
            if (shootX){
                targetPosition = 2.7;
            } else if(shootY){
                targetPosition = 2.3;
            }
            if (shootStick.getRawButton(9) && manualToggle){
                manualToggle = false;
            } else if (shootStick.getRawButton(9) && !manualToggle){
                manualToggle = true;
            }
            
            if (manualToggle){
                if (shootRB){
                    StageOneTalon.set(.5);
                } else if (shootLB){
                    StageOneTalon.set(-.5);
                }
            }
            
            Arm.armUp(shootA);
            Arm.armDown(shootB);
            Arm.grabFrisbee(shootStart);
            
            if (!manualToggle){
                Arm.moveArm(targetPosition);
            }
        }
        //Arm.moveArm(targetPosition);
        solPick.solenoid();
        /*
        //try {Thread.sleep(1000);} catch(Exception e){}
        //String percentage = Double.toString();
        double speedOne = StageOneTalon.get();
        String speed1 = Double.toString(speedOne);
        double speedTwo = StageTwoTalon.get();
        String speed2 = Double.toString(speedTwo);
        LCD.println(Line.kUser3, 1, ((StageOneTalon.get()/StageTwoTalon.get()) *100) + "                       %");
        LCD.println(Line.kUser4, 1,"S1:" + speed1);
        LCD.println(Line.kUser2, 1,"S2:" + speed2);
        //LCD.println(Line.kUser1, 1, "RPM1: " + (speedOne * Scaler));
        //LCD.println(Line.kUser2, 1, "RPM2: " + (speedTwo * Scaler));
        LCD.updateLCD();*/
        
        
        /*************
        **Flag Code***
        *************/
   
         //moved to SuperJoystick class
        
        /************
         * Solenoid *
         ***********/

        /*******************
         * Servo Test Code *
         ******************/
        if (shootTriggers<= 0.8){
            shootTriggers = 0.8;
        } else if (shootTriggers >= 0.25){
            shootTriggers = 0.25;
        }
            
        frontCameraServo.set(shootTriggers);

        
        /*frontCameraServo.set(0.5);
        double servoPosition = frontCameraServo.get();
        String tilt = Double.toString(servoPosition);
        LCD.println(Line.kUser6, 1, "Servo: " + tilt);
        LCD.updateLCD();
        try{
            //Thread.sleep(1000);
        }
        catch(Exception e){
            
        }
        frontCameraServo.set(0.75);
        servoPosition = frontCameraServo.get();
        tilt = Double.toString(servoPosition);
        LCD.println(Line.kUser6, 1, "Servo: " + tilt);
        LCD.updateLCD();
        */ 
        
        String currentTime = Double.toString(robotTimer.get());
        LCD.println(Line.kUser6, 1, currentTime);
        
        String potString = Double.toString(pot1.getVoltage());
        LCD.println(Line.kUser2, 1, potString);
        LCD.updateLCD();
    
        
        }
    }
    public void test() {
        double currentPosition = pot1.getVoltage();
        robotTimer.start();
        while (isDisabled() && isTest()){
            smartDashboard.putNumber("Elbow: ", currentPosition);
            LiveWindow.run();
        }
        while (isEnabled() && isTest()) {
               objTableLookUp.test();
   
       
   driveA = driveStick.getRawButton(1);
   driveB = driveStick.getRawButton(2);
   driveX = driveStick.getRawButton(3);
   driveY = driveStick.getRawButton(4);
   driveLB = driveStick.getRawButton(5);
   driveRB = driveStick.getRawButton(6);
   driveBack = driveStick.getRawButton(7);
   driveStart = driveStick.getRawButton(8);
   
   /*******************
    * XBOX Drive Axes *
    *******************/
   
   driveLX = driveStick.getRawAxis(1); 
   driveLY = driveStick.getRawAxis(2);
   driveTriggers = driveStick.getRawAxis(3);
   driveRX = driveStick.getRawAxis(4);
   driveRY = driveStick.getRawAxis(5);
   driveDP = driveStick.getRawAxis(6);
   
   /************************
    * XBOX Shooter Buttons *
    * *********************/
   
   shootA = shootStick.getRawButton(1);
   shootB = shootStick.getRawButton(2);
   shootX = shootStick.getRawButton(3);
   shootY = shootStick.getRawButton(4);
   shootRB = shootStick.getRawButton(6);
   shootLB = shootStick.getRawButton(5);
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
            
            //LiveWindow.run();
            /*if (shootA && flagA && currentPosition <= 3.334){                
                targetPosition = currentPosition + .25;
                flagA = false;
            } else if (shootB && flagB && currentPosition >= 1.5){
                targetPosition = currentPosition - .25;
                flagB = false;
            }
            if ((currentPosition >= 1.5 && currentPosition <= 3.334) && Math.abs(shootLX) >= .025) {
                StageOneTalon.set(shootLX * .25);
            }
            if ((currentPosition - targetPosition) >= .005){
                StageOneTalon.set(.25); //if (targetPosition <= currentPosition + Math.abs(.005);
            } else if ((currentPosition - targetPosition) <= -.005){
                StageOneTalon.set(-.25);
            } else {
                StageOneTalon.set(0);
            }*/
            /*if (shootA){
                GrabSpike.set(Value.kForward);
                flagA = false;
            } else if (shootB){
                GrabSpike.set(Value.kReverse);
                flagB = false;
            } else {
                GrabSpike.set(Value.kOff);
            }*/
            Arm.rotate();
            //StageOneTalon.set(shootLX * .25);

            solPick.solenoid();
            LiveWindow.setEnabled(false);
            LiveWindow.run();
            smartDashboard.putNumber("Current Position: ", currentPosition);
            smartDashboard.putNumber("Target Position: ", targetPosition);
            smartDashboard.putBoolean("Solenoid State: ", solenidFlag);
            smartDashboard.putBoolean("Pressure Position: ", armLimit.get());
            String armMagnet = ""+armLimit.get();
            
            
            LCD.println(Line.kUser2, 1, armMagnet + " ");
            
        
        if (!shootA && !flagA) { //toggles
            flagA = true;
        } else if (!shootB && !flagB){
            flagB = true;
        }else if (!shootX && !flagX){
            flagX = true;
        }else if (!shootY && !flagY){
            flagY = true;
        } else if (!shootStart && !flagStart){
            flagStart = true;
        }else if (!shootBack && !flagBack){
            flagBack = true;
        }
        }
    }
}


