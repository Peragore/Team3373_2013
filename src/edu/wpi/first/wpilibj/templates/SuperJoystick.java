/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Jamie
 */
public class SuperJoystick extends Joystick{
     
   private boolean flagA;
   boolean flagB;
   boolean flagX;
   boolean flagY;
   boolean flagLB;
   boolean flagRB;
   boolean flagStart;
   boolean flagBack;
   boolean flagLStick;
   boolean flagRStick;
    SuperJoystick(int port){
        super(port); //also need to clear joystick class
        clearButtons();
    }
    
    public boolean isButtonPushed(int button){
        if (getRawButton(button)){
            return true;
        } else { 
            return false;
        }
    }
    public boolean isAPushed(){
        if(isButtonPushed(1) && flagA){
            flagA = false;
            return true;
        }
        else{
            return false;
        }
    }    
    public boolean isBPushed(){
        if(isButtonPushed(2) && flagB){
            flagB = false;
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isXPushed(){
        if(isButtonPushed(3) && flagX){
            flagX = false;
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isYPushed(){
        if(isButtonPushed(4) && flagY){
            flagY = false;
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isLBPushed(){
        if(isButtonPushed(5) && flagLB){
            flagLB = false;
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isRBPushed(){
        if(isButtonPushed(6) && flagRB){
            flagRB = false;
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isBackPushed(){
        if(isButtonPushed(7) && flagBack){
            flagBack = false;
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isStartPushed(){
        if(isButtonPushed(8) && flagStart){
            flagStart = false;
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isLStickPushed(){
        if(isButtonPushed(9) && flagLStick){
            flagLStick = false;
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isRStickPushed(){
        if(isButtonPushed(10) && flagRStick){
            flagRStick = false;
            return true;
        }
        else{
            return false;
        }
    }
    public void clearButtons(){
        if (!flagA && !getRawButton(1)) { //toggles
            flagA = true;
        } else if (!flagB && !getRawButton(2)){
            flagB = true;
        } else if (!flagX && !getRawButton(3)){
            flagX = true;
        } else if (!flagY && !getRawButton(4)){
            flagY = true;
        } else if (!flagLB && !getRawButton(5)){
            flagLB = true;
        } else if (!flagRB && !getRawButton(6)){
            flagRB = true;
        } else if (!flagBack && !getRawButton(7)){
            flagBack = true;
        } else if (!flagStart && !getRawButton(8)){
            flagStart = true;
        } else if (!flagLStick && !getRawButton(9)){
            flagLStick = true;
        } else if (!flagRStick && !getRawButton(10)){
            flagRStick = true;
        }
    }


}
