/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.DriverStationLCD.*;
import edu.wpi.first.wpilibj.Relay.*;
import edu.wpi.first.wpilibj.templates.*;
/**
 *
 * @author Philip2
 */
public class PickArm {
    int grabStatus = 0;
    int rotateStatus = 0;
    Team3373 team;
    String grabString;
    double lastTime;
    double previousTime;
    boolean pickUpFlag = true;
    
    double currentPosition;
    
    public PickArm(Team3373 t){
       team = t;
    }

    public void rotate (){
        currentPosition = team.pot1.getVoltage(); //manual control
        if (team.shootA && team.flagA && currentPosition <= 3.334){ //a control (moving positive)
            team.targetPosition = (currentPosition + .25);
            team.flagA = false;
        } else if (team.shootB && team.flagB && currentPosition >= 1.5){ //b control (moving negative)
            team.targetPosition = (currentPosition - .25);
            team.flagB = false;
        } else if (currentPosition <= 3.334 && currentPosition >= 1.5 ) { //manual control using Lstick X axis (positive and negative)                      
            team.StageOneTalon.set(team.shootLX);
        }
        team.smartDashboard.putNumber("Target Position: ", team.targetPosition);
        team.smartDashboard.putNumber("Rotate Status: ", rotateStatus);
        team.smartDashboard.putNumber("Current Position: ", currentPosition);
            switch(rotateStatus){
                case 0:
                   if(team.shootX && team.flagX){
                      rotateStatus = 1;
                      team.flagX = false;
                   }
                    break;
              case 1:
                  if(team.targetPosition > currentPosition && currentPosition <= 3.334){
                       team.StageOneTalon.set(0.5);
                  } else if (team.targetPosition < currentPosition && currentPosition >= 1.5){
                     team.StageOneTalon.set(-0.5);
                 } 
                 if (currentPosition > (team.targetPosition - 0.05) && currentPosition < (team.targetPosition + 0.05)){
                      rotateStatus = 2;
                    }
                  break;
               case 2:
                  team.StageOneTalon.set(0);
                  rotateStatus = 0;
                  break;
        }
    }

    public void armUp(){
        if (team.shootStart && pickUpFlag){
            lastTime = team.robotTimer.get();
            team.GrabSpike.set(Value.kForward);
            pickUpFlag = false;
        } 
        if ((team.robotTimer.get() - lastTime) >= 2){
            team.GrabSpike.set(Value.kOff);
            pickUpFlag = true;
        }
    }
    public void armDown(){
        if (team.shootBack && pickUpFlag){
            lastTime = team.robotTimer.get();
            team.armSpike.set(Value.kReverse);
            pickUpFlag = false;
        } 
        if ((team.robotTimer.get() - lastTime) >= 2){
            team.armSpike.set(Value.kOff);
            pickUpFlag = true;
        }
    } 
    
    
    public void grabFrisbee() {//used to create suction after arm goes down to grab frisbee

        
        grabString = Integer.toString(grabStatus);
        team.LCD.println(DriverStationLCD.Line.kUser1, 1, grabString);       
        
        switch(grabStatus){
            case 0: //init stage, not running and no signal to run
            if (team.shootY && team.flagY){
                team.GrabSpike.set(Value.kOff);
                grabStatus = 1;
                team.flagY = false;
            }
                break;
            case 1:// parked and signal to run
                team.GrabSpike.set(Value.kReverse);
                team.solenidFlag = true;
                grabStatus = 2;
                break;
            case 2: //running
                team.GrabSpike.set(Value.kReverse);
                double startTime = team.robotTimer.get();
                if(team.armLimit.get() && ((team.robotTimer.get() - startTime) >= .25)){
                    grabStatus = 3;  
                }
                break;
            case 3: //back home, off
                team.GrabSpike.set(Value.kOff);
                grabStatus = 0;
            }
        
        team.LCD.updateLCD();
    }
}
