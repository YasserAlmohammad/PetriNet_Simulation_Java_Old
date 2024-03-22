/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrinetengine;

import petri.ReadXML;
import petri.Protocol;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import petri.*;
/**
 *
 * @author 
 */
public class PetrinetEngine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //run server and listen for incomming connections
            Socket xml_socket=new Socket(Protocol.hostname, Protocol.XMLPort);
            ObjectOutputStream  out = new ObjectOutputStream (xml_socket.getOutputStream());
            ObjectInputStream  in = new ObjectInputStream(xml_socket.getInputStream());
            out.writeUTF(Protocol.getXML);
            out.flush();
            System.out.println("XML Request Sent...");
            Object ob=in.readObject();
            ReadXML xml=(ReadXML)ob;
            System.out.println("XML Request Received...");
            
            //build Petrinet
            Petrinet net=new Petrinet(xml);
            net.build();
            
            ServerSocket serverSocket = new ServerSocket(Protocol.EnginePort);
            System.out.println("Waiting for Executing Request...");
            Socket netSocket = serverSocket.accept();
            System.out.println("Execution Request Received...");
            ObjectOutputStream  net_out = new ObjectOutputStream (netSocket.getOutputStream());
            ObjectInputStream  net_in = new ObjectInputStream(netSocket.getInputStream());
            
            String message=net_in.readUTF();
            if(message.equalsIgnoreCase(Protocol.getNet)){
                //return ReadXML object
                net_out.writeObject(net); //send the entire net
                net_out.flush();
                Thread.sleep(5000);
                System.out.println("Net is sent via sockets.");
            }
            else{
                System.out.println("Expecting Message:"+Protocol.getNet);
            }
                    
            //if xml file is requested we send it.
        } catch (Exception ex) {
            Logger.getLogger(PetrinetEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
}
