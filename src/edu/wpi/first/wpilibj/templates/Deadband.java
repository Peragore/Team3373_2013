package edu.wpi.first.wpilibj.templates;

import java.lang.Math.*;

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
    
    public boolean zeroDeadBand (double topNumber, double bottomNumber, double value){
        boolean a = false;
        if (bottomNumber > value && value > topNumber){ /*Checks to see if the input value is between two numbers*/
         a = true;
         return a;
        } else {
            return a;
        }
    }
    
    public double fluxDeadBand (double value){
        /*Clips off the decimals to the hundreth place*/
        double a = value*100;
        int b = (int)a;
        double c = b/100;
        return c;
        
    }
    
}