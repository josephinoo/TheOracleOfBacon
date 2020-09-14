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

    public static GraphLA<String> graph;

    public static GraphLA<String> graphBacon() {
        graph = new GraphLA<>(false);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("src/files/movies.csv"));
            Scanner sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] cadena = line.split(",");
                String pelicula = cadena[0];
                String[] actores = cadena[1].split("-");
                for (String actor : actores) {
                    graph.addVertex(actor.strip());
                }
                for (int i = 0; i < actores.length; i++) {
                    for (int j = i + 1; j < actores.length; j++) {
                        graph.addEdge(actores[i].strip(), actores[j].strip(), 1, pelicula);

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return graph;

    }

}
