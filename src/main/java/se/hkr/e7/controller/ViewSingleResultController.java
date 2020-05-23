package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Result;

import java.util.Optional;

public class ViewSingleResultController extends Controller {

    public Text dateText;
    public Text statusText;
    public Text noteText;
    public Text patientNameText;
    public Text doctorNameText;
    public Text doctorEmailText;
    public Text doctorPhoneText;
    public Button addResultButton;
    private Result result;

    @FXML
    public void initialize() {
        Singleton singleton = Singleton.getInstance();
        singleton.addSceneHistory("view/ViewSingleResult.fxml");
        updateScene();

        if (singleton.getCurrentUser() instanceof Employee && result.getStatus() == Result.Status.PENDING) {
            addResultButton.setVisible(true);
            addResultButton.setOnAction(this::addResult);
        }
    }

    private void updateScene() {
        result = Singleton.getInstance().getResult();
        dateText.setText(result.getDateFormat());
        statusText.setText(result.getStatus().toString());
        noteText.setText(result.getNote());

        Employee examiner = result.getExaminer();
        doctorNameText.setText(examiner.getName());
        doctorEmailText.setText(examiner.getEmail());
        doctorPhoneText.setText(examiner.getPhone());
    }

    private void addResult(ActionEvent actionEvent) {
        ButtonType buttonPositive = new ButtonType("Positive");
        ButtonType buttonNegative = new ButtonType("Negative");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Choose the result status.",
                buttonPositive, buttonNegative, ButtonType.CANCEL);
        alert.setHeaderText("Add new result status");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.isEmpty()) {
            return;
        }

        if (buttonType.get() == buttonPositive) {
            result.updateStatus(Result.Status.POSITIVE);
        } else if (buttonType.get() == buttonNegative) {
            result.updateStatus(Result.Status.NEGATIVE);
        } else {
            return;
        }
        DatabaseHandler.save(result);
        showConfirmation("Saved", "The status was updated");
        updateScene();
    }
}
