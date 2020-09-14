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
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import theoracleofbacon.NumberBacon;
import util.Edge;
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
    @FXML
    private VBox vbDijkstra;
    @FXML
    private VBox vbBFS;
    @FXML
    private VBox vbDFS;
    @FXML
    private Label tiempoDijkstra;
    @FXML
    private Label tiempoDFS;
    @FXML
    private Label tiempoBFS;

    private ContextMenu entriesPopup;
    private final SortedSet<String> entries;

    public MainController() {
        entries = new TreeSet<>();
        entriesPopup = new ContextMenu();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        entries.addAll(actores());
        autocompleteTextField(person1);
        autocompleteTextField(person2);

    }

    private void autocompleteTextField(TextField person) {
        person.textProperty().addListener(
                new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {

                if (person.getText().length() == 0) {
                    entriesPopup.hide();
                } else {
                    LinkedList<String> searchResult = new LinkedList<>();
                    searchResult.addAll(entries.subSet(person.getText(), person.getText() + Character.MAX_VALUE));
                    if (!entries.isEmpty()) {
                        populatePopup(searchResult, person);
                        if (!entriesPopup.isShowing()) {
                            entriesPopup.show(person, Side.BOTTOM, 0, 0);
                        }
                    } else {
                        entriesPopup.hide();
                    }
                }
            }
        }
        );
    }

    private void populatePopup(List<String> searchResult, TextField person) {
        List<CustomMenuItem> menuItems = new LinkedList<>();
        // If you'd like more entries, modify this line.
        int maxEntries = 10;
        int count = Math.min(searchResult.size(), maxEntries);
        for (int i = 0; i < count; i++) {
            final String result = searchResult.get(i);
            Label entryLabel = new Label(result);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    person.setText(result);
                    entriesPopup.hide();
                }
            });
            menuItems.add(item);
        }
        entriesPopup.getItems().clear();
        entriesPopup.getItems().addAll(menuItems);

    }

    public List<String> actores() {
        LinkedList<String> actores = new LinkedList<>();
        for (Vertex<String> actor : NumberBacon.getGraph().getVertexes()) {
            actores.add(actor.getData());
        }
        return actores;
    }

    public void addEdges(List<Edge<String>> connection, VBox vBoxPane) {
        int i = 0;
        for (Edge<String> edge : connection) {
            if (i == 0) {
                crearRectangulo(edge.getVOrigen().getData(), vBoxPane, Color.rgb(189, 253, 178));
                i++;
            }
            /// Primera Linea
            anadirLinea("was in", vBoxPane);
            /// Rectangle 1
            crearRectangulo(edge.getMovie(), vBoxPane, Color.rgb(72, 172, 253));
            //Linea 2
            anadirLinea("with", vBoxPane);
            // Rectangle2
            crearRectangulo(edge.getVDestino().getData(), vBoxPane, Color.rgb(189, 253, 178));
        }
    }

    private void anadirLinea(String text, VBox vBoxPane) {
        StackPane stackLine = new StackPane();
        crearLinea(stackLine, text);
        vBoxPane.getChildren().add(stackLine);
    }

    private void crearRectangulo(String text, VBox vb, Color color) {
        StackPane stackRectangle = new StackPane();
        Rectangle rectangle = new Rectangle(200, 50);
        rectangle.setFill(color);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(0.5);
        Text textoMovie = new Text(text);
        stackRectangle.getChildren().addAll(rectangle, textoMovie);
        vb.getChildren().add(stackRectangle);
    }

    private void crearLinea(StackPane stackLine, String text) {
        Line line = new Line(100, 0, 100, 30);

        line.setStroke(Color.GRAY);
        line.setStrokeWidth(3);

        Text textWasIn = new Text(text);
        textWasIn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        stackLine.getChildren().addAll(line, textWasIn);
    }

    @FXML
    public void showGraphs() {
        clearVBoxes();
        String origen = person1.getText();
        String destino = person2.getText();

        long startDI = System.currentTimeMillis();
        List<Edge<String>> dijkstra = NumberBacon.getGraph().camino(origen, destino, "dijkstra");
        long endDI = System.currentTimeMillis();
        tiempoDijkstra.setText(time(startDI,endDI));
        long startBFS = System.currentTimeMillis();
        List<Edge<String>> bfs = NumberBacon.getGraph().camino(origen, destino, "bfs");
        long endBFS = System.currentTimeMillis();
        tiempoBFS.setText(time(startBFS,endBFS));

        long startDFS = System.currentTimeMillis();
        List<Edge<String>> dfs = NumberBacon.getGraph().camino(origen, destino, "dfs");
        long endDFS = System.currentTimeMillis();
        tiempoDFS.setText(time(startDFS,endDFS));
        addEdges(dijkstra, vbDijkstra);
        addEdges(dfs, vbDFS);
        addEdges(bfs, vbBFS);
        clearTextFields();
    }
    
    private String time(long start, long end){
    return "Tiempo: " + String.valueOf(end-start) + "millis";
    }
   

    private void clearTextFields() {


        person1.clear();
        person2.clear();
    }

    private void clearVBoxes() {
        vbDijkstra.getChildren().clear();
        vbBFS.getChildren().clear();
        vbDFS.getChildren().clear();
    }

}
