package se.hkr.e7.controller;

import javafx.event.ActionEvent;

import java.io.IOException;


public class ViewStaffController extends Controller {

    public void Back(ActionEvent actionEvent) throws IOException {
       loadScene("view/AdminDashboard.fxml",actionEvent);
    }

    public void Exit() {
        System.exit(0);
    }
}
