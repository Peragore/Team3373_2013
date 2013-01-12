/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Philip2
 */
public class Deadband{
    
    
    static int flag = 0;
    
    public static void Range (double target, double bottomNumber, double value, double increase){
        while (target > value || flag != 1){
            value += increase;
            if (target <= value){
                flag += 1;
            }
        }
        
    }
}
