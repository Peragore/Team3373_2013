/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.DriverStationLCD.*;
import edu.wpi.first.wpilibj.Relay.*;
import edu.wpi.first.wpilibj.templates.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
/**
 *
 * @author Philip2
 */
public class PickArm {
    int grabStatus = 0;
    int rotateStatus = 1;
    Team3373 team;
    String grabString;
    double lastTime;
    double previousTime;
    double startTime;
    double min = 1.693;
    double max = 2.900;
    double pos1 = 2.444;
    double pos2 = 2.089;
    boolean toggleFlag = true;
    boolean pickUpFlag = true;
    boolean testFlag = false;
    
    double currentPosition;
    
    public PickArm(Team3373 t){
       team = t;
    }

    public void rotate (){
        currentPosition = team.pot1.getVoltage(); //manual control
        if (team.shootStart && currentPosition <= max){ //a control (moving positive)
            team.targetPosition = (currentPosition + .25);
            team.flagA = false;
        } else if (team.shootBack && currentPosition >= min){ //b control (moving negative)
            team.targetPosition = (currentPosition - .25);
            team.flagB = false;
        } 
        
        if (currentPosition <= max && currentPosition >= min ) { //manual control using Lstick X axis (positive and negative)                      
            team.StageOneTalon.set(team.shootLX);
        }
        team.smartDashboard.putNumber("Target Position: ", team.targetPosition);
        team.smartDashboard.putNumber("Rotate Status: ", rotateStatus);
        team.smartDashboard.putNumber("Current Position: ", currentPosition);
         if (toggleFlag && team.shootA){
             toggleFlag = false;
         } else if (!toggleFlag && team.shootA){
             toggleFlag = true;
         }
        if (toggleFlag) { 
            switch (rotateStatus){
                    case 0:

                          rotateStatus = 1;
                          team.flagX = false;

                        break;
                  case 1:
                      if(team.targetPosition > currentPosition && currentPosition <= max){
                           team.StageOneTalon.set(-0.75);
                      } else if (team.targetPosition < currentPosition && currentPosition >= min){
                         team.StageOneTalon.set(0.75);
                     } 
                     if (currentPosition > (team.targetPosition - 0.01) || currentPosition < (team.targetPosition + 0.01)){
                          rotateStatus = 2;
                        }
                      break;
                   case 2:
                      team.StageOneTalon.set(0);
                      if (Math.abs(team.targetPosition - currentPosition) >= .1){
                          rotateStatus = 1;
                      }
                      break;
            }
    } //else if (!toggleFlag){
        //team.StageOneTalon.set(team.shootLX * .5);
    //}
    }
    public void armUp(boolean armUpButton){
        if (armUpButton && pickUpFlag){
            lastTime = team.robotTimer.get();
            team.armSpike.set(Value.kForward);
            pickUpFlag = false;
        } 
        if ((team.robotTimer.get() - lastTime) >= 2){
            team.armSpike.set(Value.kOff);
            pickUpFlag = true;
        }
    }
    public void armDown(boolean armDownButton){
        if (armDownButton && pickUpFlag){
            lastTime = team.robotTimer.get();
            team.armSpike.set(Value.kReverse);
            pickUpFlag = false;
        } 
        if ((team.robotTimer.get() - lastTime) >= 2){
            team.armSpike.set(Value.kOff);
            pickUpFlag = true;
        }
    } 
    
    
    public void grabFrisbee(boolean grabButton) {//used to create suction after arm goes down to grab frisbee

        
        grabString = Integer.toString(grabStatus);
        team.LCD.println(DriverStationLCD.Line.kUser1, 1, grabString);       
        
        switch(grabStatus){
            case 0: //init stage, not running and no signal to run
                team.GrabSpike.set(Value.kOff);            
            if (grabButton){
                grabStatus = 1;
                team.flagY = false;
            }
                break;
            case 1:// parked and signal to run
                team.GrabSpike.set(Value.kReverse);
                team.solenidFlag = true;
                grabStatus = 2;
                startTime = team.robotTimer.get();                
                break;
            case 2: //running
                team.GrabSpike.set(Value.kReverse);
                if(!team.armLimit.get() && ((team.robotTimer.get() - startTime) >= .25)){
                    grabStatus = 0;  
                } else if ((team.robotTimer.get() - startTime) >= 2){
                    grabStatus = 0;
                }
                break;
        }

            
        }
        public void moveArm(double target){ //moves arm to target, or doesn't move if arm is close.
          if (Math.abs(target - currentPosition) <= .1){
              team.StageOneTalon.set(0);
          } else {
            if(target > currentPosition && currentPosition <= 2.8){
               team.StageOneTalon.set(0.25);
            } 
            if (target < currentPosition && currentPosition >= 2.0){
               team.StageOneTalon.set(-0.25);
            } 
          }

        }
 
        
}

