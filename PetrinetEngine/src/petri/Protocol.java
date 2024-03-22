/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

/**
 *
 * @author will contain some static text to be used accross the different classes
 */
public class Protocol {
    public static String getXML="GetXML"; //sent from engine to xml reader socker
    public static String getNet="GetNet"; //sent from execution to enginer socket
    public static int XMLPort=13131;
    public static int EnginePort=13132;
    
    public static String hostname="localhost";
}
