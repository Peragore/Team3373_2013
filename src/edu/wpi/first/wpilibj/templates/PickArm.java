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
    int goToStatus = 0;
    Team3373 team;
    String grabString;
    double lastTime;
    double previousTime;
    boolean pickUpFlag = true;
    
    double currentPosition;
    
    public PickArm(Team3373 t){
       team = t;
    }
    public void goToPosition (double targetPosition){
        currentPosition = team.pot1.getVoltage();
        switch(goToStatus){
            case 0:
                if(team.shootX && team.flagY){
                   goToStatus = 1;
                }
                break;
            case 1:
                if(targetPosition > currentPosition){
                    team.StageOneTalon.set(0.5);
                } else if (targetPosition < currentPosition){
                    team.StageOneTalon.set(-0.5);
                } else if (currentPosition > (targetPosition - 0.05) || currentPosition < (targetPosition + 0.05)){
                    goToStatus = 2;
                }
                break;
            case 2:
                team.StageOneTalon.set(0);
                goToStatus = 0;
                break;
        }
    }

    public void armUp(){
        if (team.shootA && pickUpFlag){
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
        if (team.shootB && pickUpFlag){
            lastTime = team.robotTimer.get();
            team.GrabSpike.set(Value.kReverse);
            pickUpFlag = false;
        } 
        if ((team.robotTimer.get() - lastTime) >= 2){
            team.GrabSpike.set(Value.kOff);
            pickUpFlag = true;
        }
    }
    public void rotate() {
    } 
    
    
    public void grabFrisbee() {//used to create suction after arm goes down to grab frisbee

        
        grabString = Integer.toString(grabStatus);
        team.LCD.println(DriverStationLCD.Line.kUser1, 1, grabString);       
        
        switch(grabStatus){
            case 0: //init stage, not running and no signal to run
            if (team.shootY && team.flagY){
                grabStatus = 1;
                team.flagY = false;
            }
                break;
            case 1:// parked and signal to run
                team.GrabSpike.set(Value.kForward);
                if (!team.armLimit.get()){
                grabStatus = 2;
                }
                break;
            case 2: //running
                team.GrabSpike.set(Value.kForward);
                if(team.armLimit.get()){
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
