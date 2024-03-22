/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author 
 */
public class ReadXML implements Serializable{
    public ArrayList<Token> tokens;
    public ArrayList<Place> places;
    public ArrayList<Transition> transitions;
    public ArrayList<Marking> markings;
    public ArrayList<Topology> topology;

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public ArrayList<Marking> getMarkings() {
        return markings;
    }

    public ArrayList<Topology> getTopology() {
        return topology;
    }

    public ReadXML() {
        tokens=new ArrayList<>();
        places=new ArrayList<>();
        transitions=new ArrayList<>();
        markings=new ArrayList<>();
        topology=new ArrayList<>();        
    }
    /**
     * read xml file and add data to the class member variable lists
     * @param filename 
     */
    public  void readXML(String filename){
        try {
           File fXmlFile = new File(filename);
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.parse(fXmlFile);
           doc.getDocumentElement().normalize();

           NodeList tokenList = doc.getElementsByTagName("Token");
           NodeList placeList = doc.getElementsByTagName("Place");
           NodeList markingList = doc.getElementsByTagName("Marking");
           NodeList transitionList = doc.getElementsByTagName("Transition");
           NodeList topologyList = doc.getElementsByTagName("Topology");
           
           getTokens(tokenList);
           getPlaces(placeList);
           getTransitions(transitionList);
           getMarkings(markingList);
           getTopology(topologyList);
           
       } catch (Exception e) {
           e.printStackTrace();
       }      
    }

     /* <Token>
            <ID>1</ID>
            <Arrival> 5 </Arrival>
            <Burst> 100 </Burst>
            <Priority> 1 </Priority>
            <Deadline> 200 </Deadline>
         /Token>
    */
    private void getTokens(NodeList tokenList){
        System.out.println("------------ List of Tokens ----------");
        for (int temp = 0; temp < tokenList.getLength(); temp++) {
           Node nNode = tokenList.item(temp);
           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                   Element eElement = (Element) nNode;
                   int id=Integer.parseInt(eElement.getElementsByTagName("ID").item(0).getTextContent());
                   int arrival=Integer.parseInt(eElement.getElementsByTagName("Arrival").item(0).getTextContent().trim());
                   int burst=Integer.parseInt(eElement.getElementsByTagName("Burst").item(0).getTextContent().trim());
                   int priority=Integer.parseInt(eElement.getElementsByTagName("Priority").item(0).getTextContent().trim());
                   int deadline=Integer.parseInt(eElement.getElementsByTagName("Deadline").item(0).getTextContent().trim());
                   Token token=new Token(id,arrival,burst,priority,deadline);
                   token.Print();
                   System.out.println();
                   tokens.add(token);
           }
        }
    }
     /* <Place>
            <ID> 1 </ID>
            <Name> P1 </Name>
        </Place>
    */
    private void getPlaces(NodeList nodelist){
        System.out.println("------------ List of Places ----------");
        for (int temp = 0; temp < nodelist.getLength(); temp++) {
           Node nNode = nodelist.item(temp);
           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                   Element eElement = (Element) nNode;
                   int id=Integer.parseInt(eElement.getElementsByTagName("ID").item(0).getTextContent().trim());
                   String name=eElement.getElementsByTagName("Name").item(0).getTextContent().trim();
                   Place place=new Place(id,name);
                   place.print();
                    System.out.println();
                   places.add(place);
           }
        }
    }
     /* <Transition>
            <ID>1</ID>
            <Name>T1</Name>
            <Policy> FCFS </Policy>
        </Transition>
    */
    private void getTransitions(NodeList nodelist){
        System.out.println("------------ List of Transition ----------");
        for (int temp = 0; temp < nodelist.getLength(); temp++) {
           Node nNode = nodelist.item(temp);
           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                   Element eElement = (Element) nNode;
                   int id=Integer.parseInt(eElement.getElementsByTagName("ID").item(0).getTextContent().trim());
                   String name=eElement.getElementsByTagName("Name").item(0).getTextContent().trim();
                   String policy=eElement.getElementsByTagName("Policy").item(0).getTextContent().trim();
                   
                   Transition t=new Transition(id, name, policy);
                   t.print();
                    System.out.println();
                   transitions.add(t);
           }
        }
    }
    
     /*<Marking>
            <PID>2</PID>
            <TokenID>5</TokenID>
       </Marking>
    */
    private void getMarkings(NodeList nodelist){
        System.out.println("------------ List of Markings ----------");
        for (int temp = 0; temp < nodelist.getLength(); temp++) {
           Node nNode = nodelist.item(temp);
           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                   Element eElement = (Element) nNode;
                   int pid=Integer.parseInt(eElement.getElementsByTagName("PID").item(0).getTextContent().trim());
                   int tokenid=Integer.parseInt(eElement.getElementsByTagName("TokenID").item(0).getTextContent().trim());
                   
                   Marking m=new Marking(pid, tokenid);
                   m.print();
                    System.out.println();
                   markings.add(m);
           }
        }
    }
     /*<Topology>
            <PID>1</PID>
            <TID>1</TID>
            <Direction>Input</Direction>
       </Topology>
    */
    private void getTopology(NodeList nodelist){
        System.out.println("------------ List of Topolgoies ----------");
        for (int temp = 0; temp < nodelist.getLength(); temp++) {
           Node nNode = nodelist.item(temp);
           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                   Element eElement = (Element) nNode;
                   int pid=Integer.parseInt(eElement.getElementsByTagName("PID").item(0).getTextContent().trim());
                   int tid=Integer.parseInt(eElement.getElementsByTagName("TID").item(0).getTextContent().trim());
                   String direction=eElement.getElementsByTagName("Direction").item(0).getTextContent().trim();
             
                   Topology t=new Topology(pid, tid, direction);
                   t.print();
                    System.out.println();
                   topology.add(t);
           }
        }
    }    

}
