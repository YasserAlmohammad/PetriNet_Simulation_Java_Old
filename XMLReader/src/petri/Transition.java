/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class Transition implements Serializable{
    public ArrayList<Place> input;
    public ArrayList<Place> output;
    public int ID;
    public String name;
    public String policy;
    /*if every input place contains at least one token
    a method that returns true if the transition is enabled. 
    A transition is said to be enabled if and only if for every input place 
    to this transition, that place has 1 or more tokens
    */
    public Transition(ArrayList<Place> input, ArrayList<Place> output,int ID,String name,String policy) {
        this.input = input;
        this.output = output;
        this.ID=ID;
        this.name=name;
        this.policy=policy;
    }
    public Transition(int ID,String name,String policy) {
        input = new ArrayList<Place>();
        output = new ArrayList<Place>();
        this.ID=ID;
        this.name=name;
        this.policy=policy;
    }
    
    /**
     * if all input places have at least one token
     * @return 
     */
    boolean isEnabled(){
        for(int i=0;i<input.size();i++){
            if(input.get(i).isEmpty())
                return false;
        }
        return true;
    }
    
    /**
     * a method that chooses which tokens in each place to be deleted based
     * on the policy defined in the xml for this particular transition
     * returns index
     */
    int schedule(Place p){
        if(policy.equalsIgnoreCase("FCFS"))
            return FCFS(p);
        else if(policy.equalsIgnoreCase("SJF"))
            return SJF(p);
        else if(policy.equalsIgnoreCase("SRTF"))
            return SRTF(p);
        else if(policy.equalsIgnoreCase("EDF"))
            return EDF(p);
        return -1; //error
    }
        
    
    /**
     * a method that deletes one token from each and every input place 
     * (The token that was chosen by the scheduler) and creates one token 
     * in each and every output place.
     */ 
    void fire(){
        int newArrival=0,newBurst=0,newDeadline=0,newPriority=0;
        System.out.println("Transition with ID:"+ID+" using scheduling["+policy+"]is enabled and was fired:");
        for(int i=0;i<input.size();i++){
            //choose token from input places based on scheduler.
            int index=schedule(input.get(i));
            //delete token from input place
            Token t=input.get(i).remove(index);
            System.out.println("Token wiht ID:"+t.id+" was removed from Place with ID:"+input.get(i).id);
            //now we must send the token! into every output place
            for(int j=0;j<output.size();j++){
                System.out.println("Token was added to Transition output place with ID:"+output.get(j).id);
              //  Token t=new Token(Token.tokensGenerated+1,newArrival,newBurst,newDeadline,newPriority);
                output.get(j).add(t);
               // t.Print();
            }
        }

    }
    
    /**
     * a method that checks if the transition is enabled and if it is enabled 
     * then it calls the Fire method
     */
    public boolean execute(){
        if(isEnabled()){
            fire();
            return true;
        }
        return false;
    }
    
    /**
     * called by schedule method
     * First Come First Serve
     */
    private int FCFS(Place p){
        //first come first servce always returns first element element to have arrived
        int arrival=Integer.MAX_VALUE; //large number
        int firstIndex=0;
        for(int i=0;i<p.tokens.size();i++){
            if(p.tokens.get(i).arrivalTime<arrival){
                firstIndex=i;
                arrival=p.tokens.get(i).arrivalTime;
            }
        }
        return firstIndex;
    }
    /**
     * Shortest Job First
     * @param p
     * @return 
     */
    private int SJF(Place p){
        //shortest task first
        int burst=Integer.MAX_VALUE; //large task
        int shortestIndex=0;
        for(int i=0;i<p.tokens.size();i++){
            if(p.tokens.get(i).burst<burst)
                shortestIndex=i;
        }
        return shortestIndex;
    }
    /**
     * shortest remaining time
     * @param p
     * @return 
     */
    private int SRTF(Place p){
        //how to define this without slice? and without remaining time property
        //ideally we would have remaining time and take the token with shortest remaining time.
        return SJF(p);
    }
    
    /**
     * earliest deadline
     * @param p
     * @return 
     */
    private int EDF(Place p){
        int deadline=Integer.MAX_VALUE; //large task
        int earliestIndex=0;
        for(int i=0;i<p.tokens.size();i++){
            if(p.tokens.get(i).deadline<deadline)
                earliestIndex=i;
        }
        return earliestIndex;
    }
    public void print(){
        System.out.println("TID="+ID+"   ,TName="+name+"  ,Policy="+policy);
    }
    //include input and output places
    public void printExtended(){
        System.out.println("\nTID="+ID+"   ,TName="+name+"  ,Policy="+policy);
        System.out.println("Input Places For Transition ID:"+ID);
        for(int i=0;i<input.size();i++)
            input.get(i).print();
        System.out.println("Output Places Transition ID:"+ID);
        for(int i=0;i<output.size();i++)
            output.get(i).print();

        
    }
}
