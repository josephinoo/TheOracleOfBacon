/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import theoracleofbacon.NumberBacon;
import util.Vertex;

/**
 * FXML Controller class
 *
 * @author soyjosephavila
 */
public class MainController implements Initializable {

    @FXML
    private TextField person1;
    @FXML
    private TextField person2;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
     
    }    
    public List<String> actores(){
    LinkedList<String> actores= new LinkedList<>();  
    for(Vertex<String> actor:NumberBacon.graphBacon().getVertexes()){
        actores.add(actor.getData());
    }
    return actores;
    }
}
