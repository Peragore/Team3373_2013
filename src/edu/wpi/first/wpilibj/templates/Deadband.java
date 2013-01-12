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
    
    /*
    static int flag = 0;
    
    public static void Range (double targetValue, double bottomNumber, double value, double increase){
        while (targetValue > value && flag != 1){
            value += increase;
            if (targetValue <= value){
                flag += 1;
            }
        }
    }*/
    
    public boolean Range (double topNumber, double bottomNumber, double value){
        boolean a = false;
        if (bottomNumber > value && value > topNumber){
         a = true;
         return a;
        } else return a;
    }
}