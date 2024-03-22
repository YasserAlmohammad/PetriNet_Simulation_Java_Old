/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import petri.*;
/**
 *
 * @author Yasser
 */
public class XMLReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ReadXML reader=new ReadXML();
        if(args.length==1){
            reader.readXML(args[0]); //data.xml
            try {
                //run server and listen for incomming connections
                ServerSocket serverSocket = new ServerSocket(Protocol.XMLPort);
                System.out.println("Waiting For Engine XML Request...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Engine XML Request Received...");
                ObjectOutputStream  out = new ObjectOutputStream (clientSocket.getOutputStream());
                ObjectInputStream  in = new ObjectInputStream(clientSocket.getInputStream());
                String message=in.readUTF();
                if(message.equalsIgnoreCase(Protocol.getXML)){
                    //return ReadXML object
                    out.writeObject(reader); //send the entire object
                 //   out.writeObject(reader.tokens);
                    out.flush();
                    Thread.sleep(5000);
                    System.out.println("XML Content Read and Sent via Sockets.");
                }
                else{
                    System.out.println("Expecting Message:"+Protocol.getXML);
                }
                //if xml file is requested we send it.
            } catch (Exception ex) {
                Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else
            System.out.println("You need to provide xml file as argument");
        
    }
    
}
