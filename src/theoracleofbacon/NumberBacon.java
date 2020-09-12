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
import util.GraphLA;

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
               for (int i=1;i<actores.length;i++){
                   graphBacon.addEdge(actores[0], actores[i], 1, pelicula);
               }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
         return graphBacon;
        
     
}
    
}