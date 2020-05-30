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
                helpContent.setText("- Enter your valid already registered login credentials.\n\n" +
                        "- Your SSN(YYMMDDXXXX) and password and then login.\n\n" +
                        "- If you have a problem logging try the forgot password link " +
                        "and enter your valid email address for a new password.\n\n");
                break;
            case "view/AdminDashboard.fxml":
                helpHeading.setText("Admin Dashboard");
                helpContent.setText("\tControl panel for all administrative privileges.\n\n" +
                        "1) Add account : Add account adds a new employee account to the system's DB. " +
                        "Depending on its selected role(ADMIN,ANALYSER,DOCTOR) it can perform certain " +
                        "functions and login into the system at anytime.\n\n" +
                        "2) Add patient : Add patients add a new patient to the system, the user fills in " +
                        "the required info and saves it, if the inputted data is perfect it saves automatically with no error messages.\n\n" +
                        "3) View results : The admin can view all results in the system with no restricted access or view.\n\n" +
                        "4) View staff : Displays information pertaining all employees in the system irrespective of their roles.\n\n" +
                        "5) View patient : Displays all patients in the system and their respective saved info.\n\n" +
                        "6) Remove account : Remove account deletes the account of the inputted SSN " +
                        "immediately be it an employee or patient account.\n\n" +
                        "7) Search : All information of the provided SSN is displayed immediately.\n\n");
                break;
            case "view/DoctorDashboard.fxml":
                helpHeading.setText("Doctor Dashboard");
                helpContent.setText("\tControl panel for all administrative privileges.\n\n" +
                        "1) View patients : Displays all patients added by the currently logged in doctor " +
                        "in the system and their respective saved info.\n\n" +
                        "2) Add patient : Adds a new patient to the system, the user fills in " +
                        "the required info and saves it, if the inputted data is perfect it saves automatically.\n\n" +
                        "3) Add result : Adds new result to the patient's SSN provided. By default the Date field is set" +
                        " to today's date but can be changed to an earlier date only.\n\n" +
                        "4) View results : Displays all results added by the currently logged in doctor." +
                        "Depending on the status of the result all 'PENDING' results can the changed to 'POSITIVE or NEGATIVE'" +
                        " after further tests.\n\n");
                break;
            case "view/PatientDashboard.fxml":
                helpHeading.setText("Patient Dashboard");
                helpContent.setText("Patient can view all of his/her " +
                        "results as well as updating his/her basic information.\n\n");
                break;
            case "view/AddEmployee.fxml":
                helpHeading.setText("Add Employee");
                helpContent.setText("Fill all required fields with the right data for an employee account." +
                        " On addition of wrong data type an error message is shown.\n\n");
                break;
            case "view/AddPatient.fxml":
                helpHeading.setText("Add Patient");
                helpContent.setText("Fill all required fields with the right data to add a patient to the system." +
                        "On addition of wrong data type an error is shown.\n\n");
                break;
            case "view/AddResult.fxml":
                helpHeading.setText("Add Result");
                helpContent.setText("Adds new result to the specified SSN.By default the date field is set to today's" +
                        "date but can be changed to date earlier than that. Adding a note isn't mandatory but others are required.\n\n");
                break;
            case "view/AnalyserDashboard.fxml":
                helpHeading.setText("Analyser Dashboard");
                helpContent.setText("Line chart gives a line chart representation of all results added.\n\n" +
                        "View result displays all result in the system.\n\n" +
                        "Update info changes basic info user wants to change.\n\n");
                break;
            case "view/AnalyserLineChart.fxml":
                helpHeading.setText("Analyser Line Chart");
                helpContent.setText("The Line chart illustrates a graphical representation of the number of 'POSITIVE','NEGATIVE' " +
                        "and 'PENDING' results added against their 'DATES'.\n\n");
                break;
            case "view/AnalyserBarChart.fxml":
                helpHeading.setText("Analyser Bar-chart");
                helpContent.setText("Display Positive,negative and pending results in a bar chart format against dates added, the user can " +
                        "decide adjust date range.\n\n");
                break;
            case "view/AnalyserPieChart.fxml":
                helpHeading.setText("Analyser PieChart");
                helpContent.setText("Shows the ratio of the different results status.\n\n");
                break;
            case "view/ViewSingleResult.fxml" :
                helpHeading.setText("Single result");
                helpContent.setText("The user can see specific info about a the particular results.\n\n");
                break;
            case "view/EditAccount.fxml":
                helpHeading.setText("Edit Info");
                helpContent.setText("You can edit your current basic information here, just change the fields that require change and save.\n\n");
                break;
            case "view/ViewResultsAnalyser.fxml":
                helpHeading.setText("View results");
                helpContent.setText("Displays all results in the system.\n\n");
                break;
            case "view/ChangeInfo.fxml":
                helpHeading.setText("Update Info");
                helpContent.setText("You can edit your current basic information here, just change the fields that require change and save.\n\n");
                break;
            case "view/RemoveAccount.fxml":
                helpHeading.setText("Removing account");
                helpContent.setText("Provide the SSN of the account to be deleted and click remove and the account is bye gone.\n\n");
                break;
            case "view/ViewPatients.fxml":
                helpHeading.setText("View patients");
                helpContent.setText("Displays all patients saved in the system.\n\n");
                break;
            case "view/ViewResults.fxml":
                helpHeading.setText("View results");
                helpContent.setText("Displays all results saved in the system as well as their respective status and date issued," +
                        "you can view more info about a particular result by clicking on the result.\n\n");
                break;
            case "view/ViewStaff.fxml":
                helpHeading.setText("View staffs");
                helpContent.setText("Displays all staff saved in the system. A filter field exists in which the user can input a" +
                        " text about the particular staff he/she is currently in search of.\n\n");
                break;
            default:
                helpHeading.setText("Help Menu");
                helpContent.setText("Help menu could not be loaded.");
        }
    }
}
