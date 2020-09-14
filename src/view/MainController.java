/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import util.AutoCompleteTextField;
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
                    if (entries.size() > 0) {
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
        for (Vertex<String> actor : NumberBacon.graphBacon().getVertexes()) {
            actores.add(actor.getData());
        }
        return actores;
    }

    public void addEdges(List<Edge<String>> connection, VBox vBoxPane) {
        int i = 0;
        for (Edge<String> edge : connection) {
            StackPane stackRectangle2 = new StackPane();
            if (i == 0) {
                Rectangle rectangle2 = new Rectangle(200, 50);
                rectangle2.setFill(Color.rgb(189, 253, 178));
                Text textoOrigen = new Text(edge.getVOrigen().getData());
                stackRectangle2.getChildren().addAll(rectangle2, textoOrigen);
                vBoxPane.getChildren().add(stackRectangle2);
                i++;
            }

            /// Primera Linea
            StackPane stackLine = new StackPane();
            Line line = new Line(100, 0, 100, 30);
            line.setStroke(Color.GRAY);
            line.setStrokeWidth(3);

            Text textWasIn = new Text("was in");
            textWasIn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            stackLine.getChildren().addAll(line, textWasIn);
            vBoxPane.getChildren().add(stackLine);
            /// Rectangle 1
            StackPane stackRectangle = new StackPane();
            Rectangle rectangle = new Rectangle(200, 50);
            rectangle.setFill(Color.rgb(72, 172, 253));
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(0.5);
            Text textoMovie = new Text(edge.getMovie());
            stackRectangle.getChildren().addAll(rectangle, textoMovie);
            vBoxPane.getChildren().add(stackRectangle);

            //Linea 2
            StackPane stackLine2 = new StackPane();
            Text textWith = new Text("with");
            textWith.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            stackLine2.getChildren().addAll(line, textWith);
            vBoxPane.getChildren().add(stackLine2);
            // Rectangle2
            StackPane stackRectangle3 = new StackPane();
            Rectangle rectangle3 = new Rectangle(200, 50);
            rectangle3.setFill(Color.rgb(189, 253, 178));
            rectangle3.setStroke(Color.BLACK);
            rectangle3.setStrokeWidth(0.5);
            Text textoDestino = new Text(edge.getVDestino().getData());
            stackRectangle3.getChildren().addAll(rectangle, textoDestino);
            vBoxPane.getChildren().addAll(stackRectangle3);

        }
    }

}
