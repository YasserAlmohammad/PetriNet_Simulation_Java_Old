/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

import java.io.Serializable;

/**
 *
 * @author 
 */
public class Topology implements Serializable{
    public int placeID;
    public int transitionID;
    public String direction; //input or output

    public Topology(int placeID, int transitionID, String direction) {
        this.placeID = placeID;
        this.transitionID = transitionID;
        this.direction = direction;
    }
    public void print(){
        System.out.println("placeID="+placeID+" ,transitionID="+transitionID+"  ,direction="+direction);
    }
}
