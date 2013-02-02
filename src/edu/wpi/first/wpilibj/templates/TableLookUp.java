/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import java.util.Hashtable;

/**
 *
 * @author RoboHawks
 */
public class TableLookUp {
    
    public void test(){
    Hashtable hash = new Hashtable();
    hash.put(new Double(1.0), new Double(1.0));
    hash.put(new Double(2.0), new Double(2.0));
    hash.put(new Double(3.0), new Double(3.0));
    hash.put(new Double(4.0), new Double(4.0));
    hash.put(new Double(5.0), new Double(5.0));
    //double hashDouble = hash.getValue(1.0);
    System.out.println(hash.toString());
    }
}