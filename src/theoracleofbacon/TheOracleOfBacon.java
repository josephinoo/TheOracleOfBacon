/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theoracleofbacon;

import java.util.LinkedList;

import util.GraphLA;
import util.Vertex;

/**
 *
 * @author soyjosephavila
 */
public class TheOracleOfBacon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       GraphLA<String> bacon= NumberBacon.graphBacon();
       bacon.caminoMinimo("Vin Diesel","Matt Damon");
      
        //bacon.getVertexes().stream().forEach(e->System.out.println(e.getData()));
       
       

       
    }
    
    
}
