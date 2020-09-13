/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theoracleofbacon;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import util.Edge;
import util.GraphLA;
import util.Vertex;

/**
 *
 * @author soyjosephavila
 */
public class NumberBacon {
    
    
    
    public static GraphLA<String> graphBacon(){
        GraphLA<String> graphBacon= new GraphLA<>(false);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("src/files/movies.csv"));
            Scanner sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] cadena=line.split(",");
                String pelicula=cadena[0];
                String[] actores=cadena[1].split("-");
                for(String actor:actores){
   
                    if(actor.substring(0, 1).equals(" ")){
                        graphBacon.addVertex(actor.substring(1));  
                    }
                    else{
                        graphBacon.addVertex(actor);
                    }
                }
                  for (Vertex<String> v : graphBacon.getVertexes()) {
                    for (int i = 0; i < actores.length; i++) {
                        if (actores[i].substring(0, 1).equals(" ")) {
                            actores[i] = actores[i].substring(1);
                        }

                        if (!v.getData().equals(actores[i])) {
                            Vertex<String> v1 = new Vertex<>(actores[i]);
                            if (!v.equals(v1)) {
                                Edge<String> e = new Edge<>(v, v1, pelicula);
                                if(!v.getEdges().contains(e)){
                                v.addEdge(e);

                            }}
                        }

                    }

                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
         return graphBacon;
        
     
}
   
               
               
    }
    

