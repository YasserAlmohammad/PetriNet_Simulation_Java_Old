/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

import java.io.Serializable;

/**
 * token information class
 * @author 
 */
public class Token implements Serializable{
    public int id;
    public int arrivalTime;
    public int burst;
    public int deadline;
    public int priority;
    public static int tokensGenerated=0; //will be used for new token generation
    public Token(int id, int arrivalTime, int burst, int deadline, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burst = burst;
        this.deadline = deadline;
        this.priority = priority;
        tokensGenerated++;
    }
    public void Print(){
        System.out.println("TokenID      ="+id);
        System.out.println("Arrival Time ="+arrivalTime);
        System.out.println("Burst        ="+burst);
        System.out.println("Deadline     ="+deadline);
        System.out.println("Priority     ="+priority);
    }
}
