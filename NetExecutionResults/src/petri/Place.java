/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class Place implements Serializable{
    public int id;
    public String name;
    public ArrayList<Token> tokens;
    Semaphore sem; //only one adding or one removal.
    Thread addingThread;
    Thread removingThread;
    public Place(int id,String name) {
        this.id = id;
        this.name=name;
        tokens=new ArrayList<>();
        sem=new Semaphore(1);
    }
    
    //use adding thread
    public void add(Token t){
     //   tokens.add(t);
        addingThread=new Thread(){
            public void run(){
                try {
                    sem.acquire();
                    System.out.println("Adding Semaphone Lock Acquired");
                    tokens.add(t);
                    sem.release();
                    System.out.println("Adding Semaphone Lock Released");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        addingThread.start();
        try {
            addingThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //use removing thread;
    public Token remove(int index){
      //  Token t= tokens.remove(index);
        
        final Token t=tokens.get(index);
        try {
            removingThread=new Thread(){
                public void run(){
                    try {
                        sem.acquire();
                        System.out.println("Removing Semaphone Lock Acquired");
                        tokens.remove(index);
                        sem.release();
                        System.out.println("Removing Semaphone Lock Released");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            removingThread.start();
            removingThread.join();
        } catch (Exception ex) {
            Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    int getNumberOfToekns(){
        return tokens.size();
    }
    boolean isEmpty(){
        return tokens.isEmpty();
    }
    public void print(){
        System.out.println("PlaceID="+id+"  ,PlaceName="+name);
    }
}
