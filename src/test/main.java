/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class main extends Application{

  @Override
  public void start(Stage primaryStage) throws Exception { 
    primaryStage.setTitle("HBox Experiment 1");

 

    VBox vbox = new VBox();
    addEdges(4,vbox);
    Scene scene = new Scene(vbox);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
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