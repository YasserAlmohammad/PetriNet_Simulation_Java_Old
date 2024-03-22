/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netexecutionresults;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import petri.*;
/**
 *
 * @author Yasser
 */
public class NetExecutionResults {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Socket net_socket=new Socket(Protocol.hostname, Protocol.EnginePort);
            ObjectOutputStream  out = new ObjectOutputStream (net_socket.getOutputStream());
            ObjectInputStream  in = new ObjectInputStream(net_socket.getInputStream());
            out.writeUTF(Protocol.getNet);
            out.flush();
            System.out.println("Net Request Sent...");
            Object ob=in.readObject();
            Petrinet net=(Petrinet)ob;
            System.out.println("Net Request Received..."); 
            simulate(net);
        } catch (Exception ex) {
            Logger.getLogger(NetExecutionResults.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       public static void simulate(Petrinet net){
        //now iterate transitions and fire tell no more possible firing exists
        while(true){
            boolean fired=false;
            for(int i=0;i< net.transitions.size();i++){
               if(net.transitions.get(i).execute()==true)
                    fired=true; //at least one is ok
            }
            if(!fired)//end simulation, no more firing.
                break;                         
        }
    }
}
