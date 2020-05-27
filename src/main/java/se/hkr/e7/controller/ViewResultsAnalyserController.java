package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Result;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class ViewResultsAnalyserController extends Controller {

    public TableView<Result> resultsTableView;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/ViewResults.fxml");
        List<Result> results = DatabaseHandler.loadAll(Result.class);

        for (Map.Entry<String, String> entry : Map.ofEntries(
                new AbstractMap.SimpleEntry<>("dateFormat", "Date"),
                new AbstractMap.SimpleEntry<>("status", "Status")
        ).entrySet()) {
            TableColumn<Result, String> tableColumn = new TableColumn<>(entry.getValue());
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(entry.getKey()));
            resultsTableView.getColumns().add(tableColumn);
        }

        resultsTableView.getItems().addAll(results);
    }
}
