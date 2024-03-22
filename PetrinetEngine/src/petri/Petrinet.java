/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

import java.io.Serializable;
import petri.Topology;
import petri.Marking;
import petri.Token;
import petri.ReadXML;
import petri.Place;
import petri.Transition;
import java.util.ArrayList;
/**
 *
 * @author 
 */
public class Petrinet implements Serializable{
    public ArrayList<Token> tokens;
    public ArrayList<Place> places;
    public ArrayList<Transition> transitions;
    public ArrayList<Marking> markings;
    public ArrayList<Topology> topology;
    public ReadXML xmlreader;
    public Petrinet(ReadXML xmlreader){
        this.xmlreader=xmlreader;
    }
    public void build(){
        //now based on the lists that were created inside the xml reader we build transition
        places=xmlreader.getPlaces();
        tokens=xmlreader.getTokens();
        transitions=xmlreader.getTransitions();
        markings=xmlreader.getMarkings();
        topology=xmlreader.getTopology();
        System.out.println("\nLinking Transition and Places...");
        //update transitions with input and output places
        for(int i=0;i<transitions.size();i++){
            for(int j=0;j<topology.size();j++){
                if(topology.get(j).transitionID==transitions.get(i).ID){
                    //find place object
                    for(int p=0;p<places.size();p++){
                        if(places.get(p).id==topology.get(j).placeID){
                            if(topology.get(j).direction.equalsIgnoreCase("input"))
                                transitions.get(i).input.add(places.get(p));
                            else
                                 if(topology.get(j).direction.equalsIgnoreCase("output"))
                                     transitions.get(i).output.add(places.get(p));
                        }
                    }
                }
            }
            transitions.get(i).printExtended();
        }
        System.out.println("Transition Input and Outputs were linked");
        //link tokens to places using markings
        System.out.println("\nLinking Places to Tokens using Markings");
        for(int m=0;m<markings.size();m++){
            for(int p=0;p<places.size();p++){
                if(places.get(p).id==markings.get(m).placeID){
                    for(int t=0;t<tokens.size();t++){
                        if(markings.get(m).tokenID==tokens.get(t).id){
                            places.get(p).tokens.add(tokens.get(t));
                            System.out.println("Place with ID:"+places.get(p).id+
                                    " was linked with Token ID:"+tokens.get(t).id+"  using Marking");
                        }
                    }
                }
            }
        }
        System.out.println("Place-Token Linkage Complete.\n");
    }
}
