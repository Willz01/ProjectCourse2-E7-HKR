package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;

public class AdminDashboardController extends Controller {
    public Button Back;
    public Button Exit;
    public Button addAdmin;
    public Button addPatient;
    public Button addStaff;
    public Button viewResults;
    public Button search;
    public Button viewStaff;
    public Button viewPatient;
    public Button removeAdmin;
    public Button removeStaff;
    public TextField searchText;

    public void Back(ActionEvent actionEvent) throws IOException {
       loadScene("view/staffLogin.fxml",actionEvent);
    }

    public void Exit() {
        System.exit(0);
    }

    public void addAdmin(ActionEvent actionEvent) throws IOException {
       loadScene("view/AddAdmin.fxml",actionEvent);
    }

    public void addPatient(ActionEvent actionEvent) throws IOException {
       loadScene("view/AddPatient.fxml",actionEvent);
    }

    public void addStaff(ActionEvent actionEvent) throws IOException {
        loadScene("view/AddStaff.fxml",actionEvent);
    }

    public void viewResults(ActionEvent actionEvent) throws IOException {
        loadScene("view/ViewResults.fxml",actionEvent);
    }

    public void viewPatient(ActionEvent actionEvent) throws IOException {
      loadScene("view/ViewPatients.fxml",actionEvent);
    }

    public void viewStaff(ActionEvent actionEvent) throws IOException {
       loadScene("view/ViewStaff.fxml",actionEvent);
    }

    public void search(ActionEvent actionEvent)   {
        System.out.println("not implemented yet");
    }

    public void removeStaff(ActionEvent actionEvent) throws IOException {
        loadScene("view/RemoveStaff.fxml",actionEvent);
    }

    public void removeAdmin(ActionEvent actionEvent) throws IOException {
      loadScene("view/RemoveAdmin.fxml",actionEvent);
    }
}
