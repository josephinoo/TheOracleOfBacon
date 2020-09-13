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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
    public void addEdges(int shape ,VBox vBoxPane ){
      for(int i =0 ;i<shape;i++){
        StackPane stackRectangle= new StackPane();
        Rectangle rectangle = new Rectangle(200, 50);
         StackPane stackLine= new StackPane();
        Line line= new Line(100,0,100,30);
        if(i%2==0){
        rectangle.setFill(Color.rgb(189,253,178));
        Text texto= new Text("was");
        stackLine.getChildren().add(texto);
        }
        else {
            rectangle.setFill(Color.rgb(172,172,253));
            Text texto= new Text("with in ");
             stackLine.getChildren().add(texto);
        }
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(0.5);
        
        stackRectangle.getChildren().addAll(rectangle);
        vBoxPane.getChildren().addAll(stackRectangle);
       
        stackLine.getChildren().addAll(line);
        if(i<shape-1){
        vBoxPane.getChildren().addAll(stackLine);}

      
      
      }
}
}
