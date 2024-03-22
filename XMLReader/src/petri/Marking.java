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
public class Marking implements Serializable{
    public int placeID;
    public int tokenID;

    public Marking(int placeID, int tokenID) {
        this.placeID = placeID;
        this.tokenID = tokenID;
    }
    public void print(){
        System.out.println("placeID="+placeID+" ,tokenID="+tokenID);
    }
}
