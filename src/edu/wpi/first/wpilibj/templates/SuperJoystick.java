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
     
   boolean flagA;
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
    }
    
    public boolean isButtonPushed(int button){
        if (this.getRawButton(button)){
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
}
