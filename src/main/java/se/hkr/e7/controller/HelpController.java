package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import se.hkr.e7.Singleton;

public class HelpController extends Controller {

    public Text helpHeading;
    public Text helpContent;

    @FXML
    public void initialize() {
        switch (Singleton.getInstance().getCurrentScene()) {
            case "view/Login.fxml":
                helpHeading.setText("Login");
                helpContent.setText("- Enter your valid already registered login credentials.\n" +
                        "- Your SSN(YYMMDDXXXX) and password and then login.\n" +
                        "- If you have a problem logging try the forgot password link " +
                        "and enter your valid email address for a new password.");
                break;
            case "view/AdminDashboard.fxml":
                helpHeading.setText("Admin Dashboard");
                helpContent.setText("Control panel for all administrative privileges." +
                        "1) Add account : Add account adds a new employee account to the system's DB." +
                        "Depending on its selected role(ADMIN,ANALYSER,DOCTOR) it can perform certain " +
                        "functions and login into the system at anytime." +
                        "2) Add patient : Add patients add a new patient to the system, the user fills in " +
                        "the required info and saves it, if the inputted data is perfect it saves automatically." +
                        "3) View results : The admin can view all results in the system with no restricted access or view." +
                        "4) View staff : Displays information pertaining all employees in the system irrespective of their roles" +
                        "5) View patient : Displays all patient in the system and their respective saved info." +
                        "6) Remove account : Remove account deletes the account of the inputted SSN " +
                        "immediately be it and Employee or patient account." +
                        "7) Search : All information of the provided SSN is displayed immediately.");
                break;
            case "view/DoctorDashboard.fxml":
                helpHeading.setText("Doctor Dashboard");
                helpContent.setText("1) View patient : Displays all patients added by the currently logged in doctor " +
                        "in the system and their respective saved info." +
                        "2) Add patient : Add patients add a new patient to the system, the user fills in " +
                        "the required info and saves it, if the inputted data is perfect it saves automatically." +
                        "3) Add result : Adds new result to the patients SSN provided. By default the Date field is set" +
                        " to today's date but can be changed to and earlier date only." +
                        "4) View results : Displays all results added by the currently logged in doctor." +
                        "Depending on the status of the result all 'pending' results can the changed to 'Positive or negative'" +
                        "after further tests.");
                break;
            case "view/PatientDashboard.fxml":
                helpHeading.setText("Patient Dashboard");
                helpContent.setText("The logged in patient cam view all of his/her " +
                        "results as well as updating his basic information.");
                break;
            case "view/AddEmployee.fxml":
                helpHeading.setText("Add Employee");
                helpContent.setText("Fill all required fields with the right data to an an employee account." +
                        "On addition of wrong data type am error is showed depending on the error.");
                break;
            case "view/AddPatient.fxml":
                helpHeading.setText("Add Patient");
                helpContent.setText("Fill all required fields with the right data to an an patient to the system." +
                        "On addition of wrong data type am error is showed depending on the error.");
                break;
            case "view/AddResult.fxml":
                helpHeading.setText("Add Result");
                helpContent.setText("Adds new result to the specified SSN.By default the date field is set to today's" +
                        "date but can be changed to date earlier than that. Adding a note isn't mandatory  but other are required.");
                break;
            case "view/AnalyserDashboard.fxml":
                helpHeading.setText("Analyser Dashboard");
                break;
            case "view/AnalyserLineChart.fxml":
                helpHeading.setText("Analyser Line Chart");
                helpContent.setText("The Line chart illustrates a graphical representation of the number of 'POSITIVE','NEGATIVE' " +
                        "and 'PENDING' results added against their 'DATES'");
                break;
            case "view/ChangeInfo.fxml":
                helpHeading.setText("Update Info");
                helpContent.setText("You can edit your current basic information here,just changed the fields that require can and save.");
                break;
            case "view/RemoveAccount.fxml":
                helpHeading.setText("Removing account");
                helpContent.setText("Provide the SSN of the account to be deleted and click remove.");
                break;
            case "view/ViewPatients.fxml":
                helpHeading.setText("View patients");
                helpContent.setText("Displays all patients saved in the system");
                break;
            case "view/ViewResults.fxml":
                helpHeading.setText("View results");
                helpContent.setText("Displays all results saved in the system as well as their respective status and date added," +
                        "you can view more info about a particular result by clicking on it column");
                break;
            case "view/ViewStaff.fxml":
                helpHeading.setText("View staffs");
                helpContent.setText("Displays all staff saved in the system. A filter field exists in which the user can input a" +
                        "text about the particular staff he/she is currently in search of.");
                break;
            default:
                helpHeading.setText("Help Menu");
                helpContent.setText("Help menu could not be loaded.");
        }
    }
}
