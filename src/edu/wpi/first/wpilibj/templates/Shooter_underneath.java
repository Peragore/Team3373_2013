/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.templates.Shooter;
import edu.wpi.first.wpilibj.templates.Team3373;
/**
 *
 * @author Philip2
 */
public class Shooter_underneath extends Shooter {
    public void RPMTarget(){ //defines target based on input
        if (shootA){
            target = (RPMModifier *ShooterSpeedScale) + currentRPMT2;
        } else if (shootB){
            target = (( -RPMModifier * ShooterSpeedScale) + currentRPMT2);
        }
        
    }
}
